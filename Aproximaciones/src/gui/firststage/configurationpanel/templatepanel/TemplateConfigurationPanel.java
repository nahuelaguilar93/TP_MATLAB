package gui.firststage.configurationpanel.templatepanel;

import gui.firststage.configurationpanel.approximationpanel.ComboBoxAprox;
import tclib.templates.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by NEGU on 8/10/2015.
 */
public class TemplateConfigurationPanel extends JPanel implements TemplatesInterface {
    private JPanel comboBoxAprox;
    private static JComboBox filterList = new JComboBox(templateStrings);
    private final AmplitudePanel amplitudePanel = new AmplitudePanel();
    private ConfiguratorPanel[] configuratorPanels = new ConfiguratorPanel[4];
    private ButtonFilterType buttonFilterType;

    int index = 0;


    public TemplateConfigurationPanel(JPanel carlos) {
        comboBoxAprox = carlos;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createTitledBorder("Template Configurator"));

        //Configuration panels according to the filter type selected.
        configuratorPanels[0] = new LowPassConfiguratorPanel();
        configuratorPanels[1] = new HighPassConfiguratorPanel();
        configuratorPanels[2] = new BandPassConfiguratorPanel();
        configuratorPanels[3] = new RejectBandConfiguratorPanel();
        //

        buttonFilterType = new ButtonFilterType(configuratorPanels);
        //This is used to select the default panel. By default it will start in LowPass TODO:get the index "0" from user data
        configuratorPanels[0].setVisible(true);
        for (int i = 1; i < 4; i++) {
            configuratorPanels[i].setVisible(false);
        }

        filterList.setSelectedIndex(0);     //Low Pass as Default TODO:get it from user data
        filterList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                index = filterList.getSelectedIndex();
                buttonFilterType.selectConfigurator(index);  //TODO: This function is to be killed and changed with cardlayout
            }
        });

        filterList.setMaximumSize(new Dimension(150, 30));
        filterList.setPreferredSize(new Dimension(150, 30));
        filterList.setMinimumSize(new Dimension(150, 30));

        //Adds everything to the TemplateConfigurationPanel
        this.add(filterList);
        this.add(amplitudePanel);
        this.add(configuratorPanels[0]);
        this.add(configuratorPanels[1]);
        this.add(configuratorPanels[2]);
        this.add(configuratorPanels[3]);
        this.add(buttonFilterType);
    }

    public class ButtonFilterType extends JPanel {
        JButton buttonFilterType = new JButton("Create Template");

        public ButtonFilterType(ConfiguratorPanel[] configuratorPanels) {
            buttonFilterType.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    //ToDo: IsDataValid antes de crear el template
                    int index = filterList.getSelectedIndex();

                    switch ( templateType.values()[index] ) {
                        case LOWPASS:
                            //UserData.CurrentTemplate = new LowpassTemplate();
                            break;
                        case HIGHPASS:
                            //UserData.CurrentTemplate = new HighpassTemplate();
                            break;
                        case BANDPASS:
                            //UserData.CurrentTemplate = new BandpassTemplate();
                            break;
                        case BANDREJECT:
                            //UserData.CurrentTemplate = new BandrejectTemplate();
                            break;
                        case DELAY:
                            //UserData.CurrentTemplate = new DelayTemplate();
                            break;

                    }
                    ComboBoxAprox asd = (ComboBoxAprox) comboBoxAprox;
                    asd.UpdateComboBoxAproximation();
                }
            });
            this.add(buttonFilterType);
        }
        /*
        This Function select which sub-panel to show according to the FilterType selected with the filterList
        */
        private void selectConfigurator(int index) {
            for (int i = 0; i < 4; i++) {
                if (index == i) {
                    configuratorPanels[i].setVisible(true);
                } else {
                    configuratorPanels[i].setVisible(false);
                }
            }
        }
    }
}

