package firststage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by NEGU on 7/10/2015.
 */
class CheckBoxChoosePlot extends JPanel{
    private Singleton_S1 s = Singleton_S1.getInstance();
    private ButtonGroup groupOfRadioButtons = new ButtonGroup();
    private JRadioButton atenuacion = new JRadioButton("Attenuation");
    private JRadioButton fase = new JRadioButton("Phase");
    private JRadioButton normalizedTemplate = new JRadioButton("Normalized Template");
    private JRadioButton polosCeros = new JRadioButton("Poles and Zeros");
    private JRadioButton step = new JRadioButton("Step");
    private JRadioButton impulse = new JRadioButton("Impulse");


    public CheckBoxChoosePlot() {
        atenuacion.setSelected(true);

        polosCeros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s.getPlotPlot().updatePoleZeroPlot();
            }
        });
        atenuacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s.getPlotPlot().recreateTemplate();
                s.getPlotPlot().updateAttenuationPlot();
            }
        });
        step.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s.getPlotPlot().updateStep();
            }
        });
        impulse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s.getPlotPlot().updateImpulse();
            }
        });
        fase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s.getPlotPlot().updatePhase();
            }
        });
        normalizedTemplate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s.getPlotPlot().updateNormalizedTemplate();
            }
        });

        groupOfRadioButtons.add(atenuacion);
        groupOfRadioButtons.add(fase);
        groupOfRadioButtons.add(normalizedTemplate);
        groupOfRadioButtons.add(polosCeros);
        groupOfRadioButtons.add(step);
        groupOfRadioButtons.add(impulse);

        this.setMinimumSize(new Dimension(500, 100));
        this.setMaximumSize(new Dimension(1980, 100));
        this.setPreferredSize(new Dimension(1980, 100));

        this.add(atenuacion);
        this.add(fase);
        this.add(normalizedTemplate);
        this.add(polosCeros);
        this.add(impulse);
        this.add(step);
    }

    public JRadioButton getFase() { return fase; }
    public JRadioButton getNormalizedTemplate() { return normalizedTemplate; }
    public JRadioButton getPolosCeros() { return polosCeros; }
    public JRadioButton getAtenuacion() { return atenuacion; }
    public JRadioButton getStep() { return step; }
    public JRadioButton getImpulse() { return impulse; }
}
