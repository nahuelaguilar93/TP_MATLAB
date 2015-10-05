package tclib;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.complex.Complex;

import java.util.Arrays;

/**
 * Created by kdewald on 24/09/15.
 */
public class Approximation {

    PolynomialFunction numerador;
    PolynomialFunction denominador;

    /**
     * @param numPoly: Indice del elemento indica la potencia de ese termino del polinomio.
     * @param denPoly: Indice del elemento indica la potencia de ese termino del polinomio.
     */
    public Approximation(double[] numPoly, double[] denPoly) {
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

    public double[] getImpulseResponse(double endTime, double stepTime) {
        int stepCount = (int) Math.ceil(endTime / stepTime);

        double[] impulseResponse = new double[stepCount];
        double[] numCoefficient = numerador.getCoefficients();
        double[] denCoefficient = denominador.getCoefficients();
        double[] inputValues = new double[numCoefficient.length];
        double[] inputValuesPre = new double[inputValues.length];
        double[] outputValues = new double[denCoefficient.length];
        double[] outputValuesPre = new double[outputValues.length];
        inputValues[0] = 1; // Impulse function!

        for (int step = 0; step < stepCount; step++) {

            // First step, calculate all higher order derivatives of the output.
            for (int i = 1; i < outputValues.length; i++) {
                outputValues[i] = (outputValues[i - 1] - outputValuesPre[i - 1]) / stepTime;
            }

            // Second step, calculate all higher order derivatives of the input.
            for (int i = 1; i < inputValues.length; i++) {
                inputValues[i] = (inputValues[i - 1] - inputValuesPre[i - 1]) / stepTime;
            }

            // Third step, calculate the effect of the inputs.
            double xSum = 0;
            for (int i = 0; i < inputValues.length; i++) {
                xSum += inputValues[i] * numCoefficient[i] * Math.pow(stepTime,i);
            }

            double ySum = 0;
            for (int i = 1; i < outputValues.length; i++) {
                ySum += outputValues[i] * denCoefficient[i] * Math.pow(stepTime,i);
            }

            inputValuesPre = Arrays.copyOf(inputValues, inputValues.length);
            outputValuesPre = Arrays.copyOf(outputValues, outputValues.length);

            inputValues[0] = 0; // Impulse function for all other values;
            outputValues[0] = (xSum - ySum)/denCoefficient[0];
            impulseResponse[step] = outputValues[0];

        }
        return impulseResponse;
    }

}
