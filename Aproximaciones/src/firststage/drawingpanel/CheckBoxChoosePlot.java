package firststage.drawingpanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by NEGU on 7/10/2015.
 */
public class CheckBoxChoosePlot extends JPanel{
    private ButtonGroup groupOfRadioButtons = new ButtonGroup();
    private JRadioButton atenuacion = new JRadioButton("Atenuacion");
    private JRadioButton fase = new JRadioButton("Fase");
    private JRadioButton transferencia = new JRadioButton("Transferencia");
    private JRadioButton polosCeros = new JRadioButton("Polos y Ceros");

    public CheckBoxChoosePlot() {
        atenuacion.setSelected(true);
        fase.setSelected(false);
        transferencia.setSelected(false);
        polosCeros.setSelected(false);

        groupOfRadioButtons.add(atenuacion);
        groupOfRadioButtons.add(fase);
        groupOfRadioButtons.add(transferencia);
        groupOfRadioButtons.add(polosCeros);

        this.setMinimumSize(new Dimension(500, 100));
        this.setMaximumSize(new Dimension(1980, 100));
        this.setPreferredSize(new Dimension(1980, 100));

        this.add(atenuacion);
        this.add(fase);
        this.add(transferencia);
        this.add(polosCeros);
    }
}
