package gui;


import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.solvers.LaguerreSolver;
import org.apache.commons.math3.complex.Complex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * Created by kdewald on 23/09/15.
 */
public class StageOne extends JPanel {

    JLabel raicesText;

    public StageOne() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBackground(Color.ORANGE);

        raicesText = new JLabel("Resultado de un ejemplo:");
        JButton boton = new JButton("Ejemplo de Raices");
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double[] polyCoefs = {2,1};
                PolynomialFunction polynomialFunction = new PolynomialFunction(polyCoefs);

                LaguerreSolver laguerreSolver = new LaguerreSolver();
                Complex[] raices = laguerreSolver.solveAllComplex(polynomialFunction.getCoefficients(), 0);

                raicesText.setText(Arrays.toString(raices));
            }
        });

        this.add(boton);
        this.add(raicesText);
    }
}
