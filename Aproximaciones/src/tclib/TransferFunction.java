package tclib;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.solvers.LaguerreSolver;
import org.apache.commons.math3.complex.Complex;
import tclib.templates.HighpassTemplate;
import tclib.templates.LowpassTemplate;

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

    /**
     * @param numPoly: Indice del elemento indica la potencia de ese termino del polinomio.
     * @param denPoly: Indice del elemento indica la potencia de ese termino del polinomio.
     */
    public TransferFunction(double[] numPoly, double[] denPoly) {
        numerador = new PolynomialFunction(numPoly);
        denominador = new PolynomialFunction(denPoly);


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

    public TransferFunction denormalize(LowpassTemplate lowpassTemplate) {
        double[] currentDen = denominador.getCoefficients();
        double[] currentNum = numerador.getCoefficients();

        double[] denormalizedDen = new double[currentDen.length];
        double[] denormalizedNum = new double[currentNum.length];

        for (int i = 0; i < currentDen.length; i++) {
            denormalizedDen[i] = currentDen[i] * Math.pow(1.0 / lowpassTemplate.omegaP, i);
        }

        for (int i = 0; i < currentNum.length; i++) {
            denormalizedNum[i] = currentNum[i] * Math.pow(1.0 / lowpassTemplate.omegaP, i);
        }

        return new TransferFunction(denormalizedNum, denormalizedDen);
    }

    public TransferFunction denormalize(HighpassTemplate highpassTemplate) {
        double[] currentDen = denominador.getCoefficients();
        double[] currentNum = numerador.getCoefficients();

        double[] denormalizedDen = new double[currentDen.length];
        double[] denormalizedNum = new double[currentDen.length];

        for (int i = 0; i < currentDen.length; i++) {
            denormalizedDen[i] = currentDen[i] * Math.pow(highpassTemplate.omegaA, i);
        }

        for (int i = 0; i < currentNum.length; i++) {
            denormalizedNum[i] = currentNum[i] * Math.pow(highpassTemplate.omegaA, i);
        }

        ArrayUtils.reverse(denormalizedDen);
        ArrayUtils.reverse(denormalizedNum);

        return new TransferFunction(denormalizedNum, denormalizedDen);

    }


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

        double[] num = {1};
        double[] den = {1, 1, 1};

        TransferFunction tf = new TransferFunction(num, den);

        LowpassTemplate lowpass = new LowpassTemplate(5, 0, 0, 0);
        HighpassTemplate highpass = new HighpassTemplate(0, 5, 0, 0);

        TransferFunction tflow = tf.denormalize(lowpass);
        TransferFunction tfhigh = tf.denormalize(highpass);

        System.out.println(Arrays.toString(tflow.numerador.getCoefficients()));
        System.out.println(Arrays.toString(tflow.denominador.getCoefficients()));

        System.out.println(Arrays.toString(tfhigh.numerador.getCoefficients()));
        System.out.println(Arrays.toString(tfhigh.denominador.getCoefficients()));

        tflow.getImpulseResponseAtTime(5);


    }

}
