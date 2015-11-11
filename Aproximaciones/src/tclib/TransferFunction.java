package tclib;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.solvers.LaguerreSolver;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.util.CombinatoricsUtils;
import tclib.templates.*;

import java.util.*;

public class TransferFunction {

    PolynomialFunction numerador;
    PolynomialFunction denominador;

    //estas son las listas armadas de las raices
    private List<Complex> numeradorRootList;
    private List<Complex> denominadorRootList;

    //estas hashtables tienen como key el polo y como value el orden del mismo
    private Hashtable<Complex, Integer> numeradorPoleOrderTable;
    private Hashtable<Complex, Integer> denominadorPoleOrderTable;

    public TransferFunction(PolynomialFunction numerador, PolynomialFunction denominador) {
        this.numerador = numerador;
        this.denominador = denominador;
    }
    public TransferFunction(TransferFunction that) {
        this.numerador = new PolynomialFunction(that.numerador.getCoefficients());
        this.denominador = new PolynomialFunction(that.denominador.getCoefficients());
    }

    /**
     * @param numPoly: Indice del elemento indica la potencia de ese termino del polinomio.
     * @param denPoly: Indice del elemento indica la potencia de ese termino del polinomio.
     */
    public TransferFunction(double[] numPoly, double[] denPoly) {
        numerador = new PolynomialFunction(numPoly);
        denominador = new PolynomialFunction(denPoly);
    }

    /**
     * @param zeros: Array of complex numbers for each zero.
     * @param poles: Array of complex numbers for each pole.
     */
    public TransferFunction(Complex[] zeros, Complex[] poles) {
        double[] polyInit = {1.0};
        numerador = new PolynomialFunction(polyInit);
        denominador = new PolynomialFunction(polyInit);

        for (Complex zero : zeros) {
            if (zero.getImaginary() == 0) {
                double[] roots = {-zero.getReal(), 1};
                PolynomialFunction aux = new PolynomialFunction(roots);
                numerador = numerador.multiply(aux);
            } else if (zero.getImaginary() > 0) {
                double a = zero.getReal();
                double b = zero.getImaginary();

                double[] roots = {a * a + b * b, -2 * a, 1};
                PolynomialFunction aux = new PolynomialFunction(roots);
                numerador = numerador.multiply(aux);
            }

        }

        for (Complex pole : poles) {
            if (pole.getImaginary() == 0) {
                double[] roots = {-pole.getReal(), 1};
                PolynomialFunction aux = new PolynomialFunction(roots);
                denominador = denominador.multiply(aux);
            } else if (pole.getImaginary() > 0) {
                double a = pole.getReal();
                double b = pole.getImaginary();

                double[] roots = {a * a + b * b, -2 * a, 1};
                PolynomialFunction aux = new PolynomialFunction(roots);
                denominador = denominador.multiply(aux);
            }
        }
    }

    public double getModuleAtOrigin() {
        return numerador.getCoefficients()[0] / denominador.getCoefficients()[0];
    }

    public boolean equals(TransferFunction that) {
        double thisNum[] = this.numerador.getCoefficients();
        double thatNum[] = that.numerador.getCoefficients();
        double thisDen[] = this.denominador.getCoefficients();
        double thatDen[] = that.denominador.getCoefficients();
        if(thisNum.length == thatNum.length && thisDen.length == thatDen.length) {
            for (int i = 0; i < thisNum.length; i++)
                if (thisNum[i] != thatNum[i]) return false;
            for (int i = 0; i < thisDen.length; i++)
                if (thisDen[i] != thatDen[i]) return false;
            return true;
        } else return false;
    }


    public Complex evaluateApproximationAtOmega(double omega) {
        Complex evaluationPoint = new Complex(0, omega);
        Complex numValue = new Complex(0);
        double[] numCoefficient = numerador.getCoefficients();
        for (int i = 0; i < numCoefficient.length; i++) {
            numValue = numValue.add(evaluationPoint.pow(i).multiply(numCoefficient[i]));
        }

        Complex denValue = new Complex(0);
        double[] denCoefficient = denominador.getCoefficients();
        for (int i = 0; i < denCoefficient.length; i++) {
            denValue = denValue.add(evaluationPoint.pow(i).multiply(denCoefficient[i]));
        }

        return numValue.divide(denValue);
    }

    public Complex[] evaluateApproximationAtRange(double[] omegaRange) {
        Complex[] result = new Complex[omegaRange.length];

        for (int i = 0; i < omegaRange.length; i++) {
            result[i] = evaluateApproximationAtOmega(omegaRange[i]);
        }

        return result;
    }

    public void multiply(double gain) {
        double p[] = {gain};
        PolynomialFunction aux = new PolynomialFunction(p);
        numerador = numerador.multiply(aux);
    }

    public void multiply(TransferFunction that) {
        this.numerador = this.numerador.multiply(that.numerador);
        this.denominador = this.denominador.multiply(that.denominador);
    }

    public Complex[] getZeros() {
        if(numerador.getCoefficients().length > 1) {
            LaguerreSolver laguerreSolver = new LaguerreSolver();
            return laguerreSolver.solveAllComplex(numerador.getCoefficients(), 0, 100000);
        } else return new Complex[0];
    }

    public Complex[] getPoles() {
        if(denominador.getCoefficients().length > 1) {
            LaguerreSolver laguerreSolver = new LaguerreSolver();
            return laguerreSolver.solveAllComplex(denominador.getCoefficients(), 0, 100000);
        } else return new Complex[0];
    }

    public double[] getModule(double[] omegaRange) {
        Complex[] approximationResult = evaluateApproximationAtRange(omegaRange);
        double[] moduleResult = new double[omegaRange.length];
        for (int i = 0; i < moduleResult.length; i++) {
            moduleResult[i] = approximationResult[i].abs();
        }
        return moduleResult;
    }

    public double[] getModuleDB(double[] omegaRange) {
        double[] moduleResult = getModule(omegaRange);
        for (int i = 0; i < moduleResult.length; i++) {
            moduleResult[i] = 20 * Math.log10(moduleResult[i]);
        }
        return moduleResult;
    }

    public double[] getPhase(double[] omegaRange) {
        Complex[] approximationResult = evaluateApproximationAtRange(omegaRange);
        double[] phaseResult = new double[omegaRange.length];
        for (int i = 0; i < phaseResult.length; i++) {
            phaseResult[i] = approximationResult[i].getArgument();
        }
        return phaseResult;
    }

    public double[] getImpulseResponse(double[] time) {
        double[] impulse = new double[time.length];
        for (int i = 0; i < time.length; i++) {
            impulse[i] = getImpulseResponseAtTime(time[i]);
        }
        return impulse;
    }

    public double[] getStepResponse(double[] time) {
        double[] s = {0,1};
        PolynomialFunction num = numerador;
        PolynomialFunction den = denominador.multiply(new PolynomialFunction(s));

//        double[] impulse = getImpulseResponse(time);
//        double[] step = new double[time.length];
//        double cumSum = 0;
//        for (int i = 0; i < impulse.length; i++) {
//            step[i] = cumSum;
//            cumSum += impulse[i];
//        }
//        return step;

        return new TransferFunction(num, den).getImpulseResponse(time);
    }

    public double getImpulseResponseAtTime(double time) {
        // aca calculo la funcion de transferencia para un delta t en particular
        // delta t para derivar en el caso de grado num igual a grado den
        // TODO: Hacer el backup de las variables internas que se necesitan reutilizar.
        preloadRoots();
        calculatePoleOrders();

        Complex result;
        if (numeradorRootList.size() < denominadorRootList.size()) {
            //primero verifico si el grado del polinomio del numerador es menor al grado del polinomio del denominador.
            result = calculateResidueResult(time); //llamo a la funcion que haya el residuo
        } else {
            //si los grados de los polinomios son iguales, divido por S y derivo la antitransformada
            int derivativeOrder = 0;
            while (numeradorRootList.size() >= denominadorRootList.size()) {
                addPoleToSolve();
                derivativeOrder++;
            }
            result = calculateDerivative(time, derivativeOrder);
        }
        return result.getReal();
    }

    public boolean isPureLowpass() {
        return numerador.degree() == 0;
    }

    public TransferFunction denormalize(SuperTemplate Template, double denorm) {
        if (Template instanceof LowpassTemplate)
            return denormalize((LowpassTemplate) Template, denorm);
        else if (Template instanceof HighpassTemplate)
            return denormalize((HighpassTemplate) Template, denorm);
        else if (Template instanceof BandpassTemplate)
            return denormalize((BandpassTemplate) Template, denorm);
        else if (Template instanceof BandrejectTemplate)
            return denormalize((BandrejectTemplate) Template, denorm);
        else if (Template instanceof DelayTemplate)
            return denormalize((DelayTemplate) Template, denorm);
        else
            return new TransferFunction(new double[]{1}, new double[]{1});
    }

    public TransferFunction denormalize(LowpassTemplate lowpassTemplate, double denorm) {
        double wd = lowpassTemplate.getWp() * denorm;

        double[] currentDen = denominador.getCoefficients();
        double[] currentNum = numerador.getCoefficients();

        double[] denormalizedDen = new double[currentDen.length];
        double[] denormalizedNum = new double[currentNum.length];

        for (int i = 0; i < currentDen.length; i++)
            denormalizedDen[i] = currentDen[i] * Math.pow(1.0 / wd, i);

        for (int i = 0; i < currentNum.length; i++)
            denormalizedNum[i] = currentNum[i] * Math.pow(1.0 / wd, i);

        return new TransferFunction(denormalizedNum, denormalizedDen);
    }

    public TransferFunction denormalize(DelayTemplate delayTemplate, double denorm) {
        double wd = delayTemplate.getWp() * denorm;

        double[] currentDen = denominador.getCoefficients();
        double[] currentNum = numerador.getCoefficients();

        double[] denormalizedDen = new double[currentDen.length];
        double[] denormalizedNum = new double[currentNum.length];

        for (int i = 0; i < currentDen.length; i++)
            denormalizedDen[i] = currentDen[i] * Math.pow(1.0 / wd, i);

        for (int i = 0; i < currentNum.length; i++)
            denormalizedNum[i] = currentNum[i] * Math.pow(1.0 / wd, i);

        return new TransferFunction(denormalizedNum, denormalizedDen);
    }
    
    public TransferFunction denormalize(HighpassTemplate highpassTemplate, double denorm) {
        double wd = highpassTemplate.getWp() / denorm;

        double[] currentDen = denominador.getCoefficients();
        double[] currentNum = numerador.getCoefficients();

        double[] denormalizedDen = new double[currentDen.length];
        double[] denormalizedNum = new double[currentDen.length];

        for (int i = 0; i < currentDen.length; i++)
            denormalizedDen[i] = currentDen[i] * Math.pow(wd, i);

        for (int i = 0; i < currentNum.length; i++)
            denormalizedNum[i] = currentNum[i] * Math.pow(wd, i);

        ArrayUtils.reverse(denormalizedDen);
        ArrayUtils.reverse(denormalizedNum);

        return new TransferFunction(denormalizedNum, denormalizedDen);
    }

    public TransferFunction denormalize(BandpassTemplate bandpassTemplate, double denorm) {
        double Bd = bandpassTemplate.getB() * denorm;
        double wo = bandpassTemplate.getWo();
        double[] currentDen = denominador.getCoefficients();
        double[] currentNum = numerador.getCoefficients();
        int denominatorDegree = currentDen.length - currentNum.length; // Defino por que potencia de S tengo que multiplicar y dividir
        PolynomialFunction denpol = finalPolinomeBandPass(currentDen, 0, Bd, wo); // creo el nuevo cociente
        PolynomialFunction numpol = finalPolinomeBandPass(currentNum, denominatorDegree, Bd, wo);
        return new TransferFunction(numpol, denpol);
    }

    public TransferFunction denormalize(BandrejectTemplate bandrejectTemplate, double denorm) {
        double Bd = (bandrejectTemplate.getB() / denorm);
        double wo = bandrejectTemplate.getWo();
        double[] currentDen = denominador.getCoefficients();
        double[] currentNum = numerador.getCoefficients();
        int denominatorDegree = currentDen.length - currentNum.length;// Defino por que potencia de S tengo que multiplicar y dividir
        PolynomialFunction denpol = finalPolinomeBandReject(currentDen, 0, Bd, wo);// Creo el nuevo cociente
        PolynomialFunction numpol = finalPolinomeBandReject(currentNum, denominatorDegree, Bd, wo);
        return new TransferFunction(numpol, denpol);
    }

    //<editor-fold desc="Internal Functions of Passband and Bandreject Approximations">
    private PolynomialFunction finalPolinomeBandPass(double[] originalpol, int multiplicatororder, double B, double wo) {
        int coefiterator; // Con este manejo el término del polinomio que estoy expandiendo
        double[] fix = {0}; // Para crear un polinomio vacio
        double[] addattheend = new double[originalpol.length];//aca meto el termino independiente del pol original multiplicado por s
        PolynomialFunction polinome; // COn este creo los polinomios intermedios
        PolynomialFunction finalpolinome = new PolynomialFunction(fix);

        for (coefiterator = 1; coefiterator < originalpol.length; coefiterator++) {
            polinome = turnDouble2PolynomePass(originalpol, coefiterator, B, wo);
            finalpolinome = finalpolinome.add(polinome); // Voy sumando los polinomios expandidos
        }
        addattheend[addattheend.length - 1] = originalpol[0]; // Al final le sumo el término independiente
        finalpolinome = finalpolinome.add(new PolynomialFunction(addattheend));

        if (multiplicatororder > 0) { // Si multiplique por S de un orden mayor que el orden del polinomio, corrijo con este algoritmo:
            double[] corrector = new double[multiplicatororder + 1];
            corrector[corrector.length - 1] = 1;// Simplemente multiplico lo ya hecho por un S de orden tal que cubra la diferencia
            finalpolinome = finalpolinome.multiply(new PolynomialFunction(corrector));
        }

        return finalpolinome;
    }

    private PolynomialFunction turnDouble2PolynomePass(double[] originalpolinome, int iteration, double bandwidth, double centerFrequency) {
        PolynomialFunction returnthis;
        double[] currentpolinome;
        double[] multiplicator = new double[originalpolinome.length - iteration]; // Con esto luego multiplico por S de orden tal que no queden cocientes

        currentpolinome = newtonExpands(iteration, originalpolinome[iteration], bandwidth, centerFrequency);// Expando cada binomio en newton
        returnthis = new PolynomialFunction(currentpolinome);
        multiplicator[originalpolinome.length - iteration - 1] = 1;
        returnthis = returnthis.multiply(new PolynomialFunction(multiplicator));// Devuelvo el polinomio listo para ser sumado

        return returnthis;
    }

    private PolynomialFunction finalPolinomeBandReject(double[] originalpol, int multiplicatororder, double B, double wo) {
        int coefiterator;
        double[] addattheend = new double[originalpol.length];// Con esto voy a agregar el termino independiente (en este caso el coeficiente de mayor grado) al final
        double[] fix = {0};
        PolynomialFunction polinome;
        PolynomialFunction finalpolinome = new PolynomialFunction(fix);

        for (coefiterator = 1; coefiterator < originalpol.length; coefiterator++) {
            polinome = turnDouble2PolynomeReject(originalpol, coefiterator, B, wo);
            finalpolinome = finalpolinome.add(polinome);// Voy sumando los polinomios ya expandidos y corregidos
        }

        addattheend[addattheend.length - 1] = originalpol[originalpol.length - 1];
        finalpolinome = finalpolinome.add(new PolynomialFunction(addattheend));// Aca agrego el que habia quedado como término independiente

        if (multiplicatororder > 0) {// Si el polinomio fue multiplicado por un término de grado mayor al necesario, agrego esa parte con este algoritmo:
            double[] corrector = newtonExpands(multiplicatororder, 1, B, wo);
            finalpolinome = finalpolinome.multiply(new PolynomialFunction(corrector));
        }

        return finalpolinome;
    }

    private PolynomialFunction turnDouble2PolynomeReject(double[] originalpolinome, int iteration, double bandwidth, double centerFrequency) {
        PolynomialFunction returnthis;
        double[] currentpolinome;
        double[] multiplicator = new double[originalpolinome.length - iteration]; // Con esto multiplico por S de orden tal que no queden S dividiendo

        currentpolinome = newtonExpands(iteration, originalpolinome[originalpolinome.length - 1 - iteration], bandwidth, centerFrequency);// Expando el termino con binomio de newton para poder operar con el
        returnthis = new PolynomialFunction(currentpolinome);// acá transformo el polinomio de double[] a polynome
        multiplicator[originalpolinome.length - iteration - 1] = 1;// multiplico por S^(orden total - orden del termino)
        returnthis = returnthis.multiply(new PolynomialFunction(multiplicator)); //Lo devuelvo listo para ser sumado

        return returnthis;
    }

    private double[] newtonExpands(int n, double coef, double bandwidth, double centerFrequency) { // Esta funcion expande un binomio en un polinomio
        int iterate; //esta es la potencia de S
        int kadvancer = 0; //este es K de la sumatoria
        double[] thispolinome = new double[2 * n + 1];// El tamaño va a ser de 2n (por la potencia maxima) +  1 (por el termino independiente)
        for (iterate = 0; iterate < 2 * n + 1; iterate = iterate + 2) { //
            thispolinome[iterate] = coef * Math.pow(1 / (bandwidth * centerFrequency), n) * newtonTerm(n, kadvancer, centerFrequency);
            kadvancer++;
        }
        return thispolinome;
    }

    private double newtonTerm(int n, int k, double centerFrequency) {// Esta funcion devuelve el coeficiente del termino k del polinomio de newton
        double tobereturned = CombinatoricsUtils.binomialCoefficientDouble(n, k) * Math.pow(centerFrequency, 2 * (n - k));
        return tobereturned;
    }
    //</editor-fold>

    //<editor-fold desc="Internal Functions of Impulse Response">
    private void preloadRoots() {
        Complex[] numeradorRoots = factorPolynomial(numerador);
        Complex[] denominadorRoots = factorPolynomial(denominador);
        //factorizo ambos polinomios:
        for (int i = 0; i < numeradorRoots.length; i++) {
            //redondeo las raices porque sino tira fruta
            numeradorRoots[i] = new Complex((double) Math.round(numeradorRoots[i].getReal() * 100000) / 100000,
                    (double) Math.round(numeradorRoots[i].getImaginary() * 100000) / 100000);
        }
        for (int i = 0; i < denominadorRoots.length; i++) {
            //redondeo las raices porque sino tira fruta
            denominadorRoots[i] = new Complex((double) Math.round(denominadorRoots[i].getReal() * 100000) / 100000,
                    (double) Math.round(denominadorRoots[i].getImaginary() * 100000) / 100000);
        }

        //entro a la funcion que simplifica raices comunes
        simplifyRoots(numeradorRoots, denominadorRoots);
    }

    private Complex[] factorPolynomial(PolynomialFunction polynomial) {

        if (polynomial.degree() > 0) {
            //implemento esta funcion para corregir el caso en el que no haya raices
            LaguerreSolver laguerreSolver = new LaguerreSolver();
            Complex[] polynomialRoots = laguerreSolver.solveAllComplex(polynomial.getCoefficients(), 0);
            return polynomialRoots;
        } else {
            Complex[] returnValue = {new Complex(polynomial.getCoefficients()[0])};
            return returnValue;
        }
    }

    private void simplifyRoots(Complex[] numeradorRoots, Complex[] denominadorRoots) {
        //transformo los arreglos en listas para mejor manejo
        List<Complex> numeradorRootList = new ArrayList<>(Arrays.asList(numeradorRoots));
        List<Complex> denominadorRootList = new ArrayList<>(Arrays.asList(denominadorRoots));
        List<Complex> common = new ArrayList<>(numeradorRootList);
        common.retainAll(denominadorRootList); //aca busco los factores comunes
        //ahora hago efectivamente la acción de simplificar en ambos polinomios

        if (!common.isEmpty() && numerador.degree() > 0 && denominador.degree() > 0) {
            //simplifico SOLO si ninguno de los dos es una cte
            for (Complex commoniter : common) {
                while (numeradorRootList.contains(commoniter) && denominadorRootList.contains(commoniter)) {
                    //lo hago con while
                    numeradorRootList.remove(commoniter);//para contemplar raices multiples
                    denominadorRootList.remove(commoniter);
                }
            }
        }
        this.numeradorRootList = numeradorRootList;
        this.denominadorRootList = denominadorRootList; //guardo los polinomios factorizados en las variables de la clase
    }

    private boolean calculatePoleOrders() {
        // ahora armo las tablas que tenian como key los polos y como value el orden de los mismos
        Hashtable<Complex, Integer> numeradorPoleOrderTable = new Hashtable<>();
        Hashtable<Complex, Integer> denominadorPoleOrderTable = new Hashtable<>();
        // primero lo hago con el numerador:
        if (numerador.degree() > 0) {
            for (Complex numeradorRoot : numeradorRootList) {
                //ese if verifica de no contar varias veces la misma raiz
                if (!numeradorPoleOrderTable.containsKey(numeradorRoot))
                    numeradorPoleOrderTable.put(numeradorRoot, Collections.frequency(numeradorRootList, numeradorRoot));
            }
        } else
            numeradorPoleOrderTable.put(new Complex((numerador.getCoefficients())[0]), 0);

        //ahora con el denominador
        if (denominador.degree() > 0) {
            for (Complex denominadorRoot : denominadorRootList) {
                if (!denominadorPoleOrderTable.containsKey(denominadorRoot))
                    denominadorPoleOrderTable.put(denominadorRoot, Collections.frequency(denominadorRootList, denominadorRoot));
            }
        } else
            denominadorPoleOrderTable.put(new Complex(denominador.getCoefficients()[0]), 0);

        this.numeradorPoleOrderTable = numeradorPoleOrderTable; //guardo las tablas conla informacion en las variables de la clase
        this.denominadorPoleOrderTable = denominadorPoleOrderTable;

        //dejo un return por posibles errores para el futuro
        return true;
    }

    private Complex calculateDerivative(double time, int order) {//PROBAAAAAAAAAAAAAAAAAAR
        double deltaT = 0.00005;  //delta t para derivar en el caso de grado num igual a grado den
        Complex posteriorValue; //esta la uso para derivar
        Complex priorValue;
        Complex derivative;
        if (order == 1) {
            priorValue = calculateResidueResult(time - deltaT);
            posteriorValue = calculateResidueResult(time + deltaT);
            derivative = (posteriorValue.subtract(priorValue)).divide(2 * deltaT);
        } else {
            priorValue = calculateDerivative(time - deltaT, order - 1);
            posteriorValue = calculateDerivative(time + deltaT, order - 1);
            derivative = posteriorValue.subtract(priorValue).divide(2 * deltaT);
        }
        return derivative;
    }

    private void addPoleToSolve() {
        denominadorRootList.add(new Complex(0)); //aca estoy dividiendo por s
        if (denominadorPoleOrderTable.contains(new Complex(0))) {
            //me fijo si ya esta la raiz 0,
            int originalOrder = denominadorPoleOrderTable.get(new Complex(0));
            denominadorPoleOrderTable.remove(new Complex(0));
            //si es asi, le cambio el orden
            denominadorPoleOrderTable.put(new Complex(0), ++originalOrder);
        } else {
            //si no simplemente lo agrego
            denominadorPoleOrderTable.put(new Complex(0), 1);
        }
    }

    private Complex calculateResidueResult(double time) {
        Complex result = new Complex(0);
        Set<Complex> denominatorKeys = denominadorPoleOrderTable.keySet();//hago iteradores para iterar sobre la hash tabla
        for (Complex element : denominatorKeys) {
            //primero hago los residuos de orden 1
            if (denominadorPoleOrderTable.get(element) == 1) {
                //llamo a los residuos y los voy sumando
                result = result.add(getResidualForSimpleRoot(time, element));
            } else if (denominadorPoleOrderTable.get(element) > 1) {
                //aca hago las de orden 2.
                result = result.add(getResidualForMultipleRoots(time, element, denominadorPoleOrderTable.get(element) - 1));
            }
        }
        return result;
    }

    private Complex getResidualForSimpleRoot(double time, Complex root) {

        Complex neperian = new Complex(2.71828182846); //creo el numero E porque no andaba exp
        Complex value = new Complex(1); //creo el valor de retorno

        if (numerador.degree() > 0) {
            for (Complex element : numeradorRootList) {
                value = value.multiply(root.subtract(element)); //primero resuelvo la parte del numerador de los residuos
            }
        } else {
            value = value.multiply(numerador.getCoefficients()[0]);
        }
        for (Complex element : denominadorRootList) {//RESOLVERESTO
            if (element.subtract(root).abs() > 0.001) { //ahora resuelvo la parte del denominador, obviamente simplificando
                value = value.divide(root.subtract(element));//al punto donde evaluo el residuo
            }
        }
        value = value.multiply(neperian.pow(root.multiply(time)));//ahora multiplico por la exponencial
        return value;
    }

    private Complex getResidualForMultipleRoots(double time, Complex root, int order) {
        Complex priorValue;
        Complex posteriorValue;
        Complex derivativeValue;
        Complex deltaZ = new Complex(0.00005);
        if (order == 1) {
            priorValue = getResidualForSimpleRoot(time, root.subtract(deltaZ));//aca hago el residuo en un deta z anterior
            posteriorValue = getResidualForSimpleRoot(time, root.add(deltaZ));//aca en un delta z posterior
            derivativeValue = (posteriorValue.subtract(priorValue)).divide(deltaZ.multiply(2)); //aca resuelvo la derivada

        } else {
            priorValue = getResidualForMultipleRoots(time, root.subtract(deltaZ), order - 1);
            posteriorValue = getResidualForMultipleRoots(time, root.add(deltaZ), order - 1);
            derivativeValue = (posteriorValue.subtract(priorValue)).divide(deltaZ.multiply(2));
        }
        return derivativeValue;
    }
    //</editor-fold>

    public static void executeTest() {

        Complex zero1 = new Complex(2, 3);

        Complex[] zeros = {zero1};


        TransferFunction tf = new TransferFunction(zeros, zeros);

        System.out.println(Arrays.toString(tf.numerador.getCoefficients()));
        System.out.println(Arrays.toString(tf.denominador.getCoefficients()));

    }
}
