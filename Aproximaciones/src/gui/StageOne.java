package gui;

import javafx.scene.layout.Pane;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.solvers.LaguerreSolver;
import org.apache.commons.math3.complex.Complex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class StageOne extends JPanel {

    JPanel panelFiltro;


    public StageOne() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBackground(Color.ORANGE);

        panelFiltro = new JPanel(); //Panel where you select and configure the Type of Filter (BP, LP, etc)

        this.add(panelFiltro);
        panelFiltro.add(FilterSelect.getButtons());
    }
}
