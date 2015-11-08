package firststage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by NEGU on 7/10/2015.
 */
class CheckBoxChoosePlot extends JPanel{
    private Singleton_S1 s = Singleton_S1.getInstance();
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

        polosCeros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s.getPlotPlot().createPoleZeroPlot();
            }
        });
        atenuacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s.getPlotPlot().recreateTemplate();
                s.getPlotPlot().addPlots();
            }
        });

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
