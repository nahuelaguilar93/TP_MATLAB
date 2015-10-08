package gui.firststage.drawingpanel;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by NEGU on 7/10/2015.
 */
public class CheckBoxChoosePlot extends JPanel{
    JCheckBox atenuacion = new JCheckBox("Atenuacion");
    JCheckBox fase = new JCheckBox("Fase");
    JCheckBox transferencia = new JCheckBox("Transferencia");
    JCheckBox polosCeros = new JCheckBox("Polos y Ceros");

    public CheckBoxChoosePlot() {
        atenuacion.setSelected(true);
        fase.setSelected(false);
        transferencia.setSelected(false);
        polosCeros.setSelected(false);

        //AddListener
        atenuacion.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) { //CheckBox Selected
                    fase.setSelected(false);
                    transferencia.setSelected(false);
                    polosCeros.setSelected(false);
                }
            }
        });
        fase.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) { //CheckBox Selected
                    atenuacion.setSelected(false);
                    transferencia.setSelected(false);
                    polosCeros.setSelected(false);
                }
            }
        });
        transferencia.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) { //CheckBox Selected
                    fase.setSelected(false);
                    atenuacion.setSelected(false);
                    polosCeros.setSelected(false);
                }
            }
        });
        polosCeros.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) { //CheckBox Selected
                    fase.setSelected(false);
                    transferencia.setSelected(false);
                    atenuacion.setSelected(false);
                }
            }
        });

        this.add(atenuacion);
        this.add(fase);
        this.add(transferencia);
        this.add(polosCeros);
    }
}
