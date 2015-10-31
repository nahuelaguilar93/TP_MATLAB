package gui.firststage.configurationpanel.filterselectpanel;

import data.UserData;
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
    private ConfiguratorPanel[] configuratorPanels = new ConfiguratorPanel[4];
    private ButtonFilterType buttonFilterType;
    private static int TEXT_HEIGH = 50;
    private static int TEXT_WIDTH = 65;
    private static JComboBox filterList = new JComboBox(templateStrings);

    private final GenericConfiguratorPanel genericConfiguratorPanel = new GenericConfiguratorPanel();
    int index = 0;
    private JPanel comboBoxAprox;

    public TemplateConfigurationPanel(JPanel carlos) {
        comboBoxAprox = carlos;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createTitledBorder("Filter Configurator"));

        //Configuration panels according to the filter type selected.
        configuratorPanels[0] = new LowPassConfiguratorPanel();
        configuratorPanels[1] = new HighPassConfiguratorPanel();
        configuratorPanels[2] = new BandPassConfiguratorPanel();
        configuratorPanels[3] = new RejectBandConfiguratorPanel();
        //
        buttonFilterType = new ButtonFilterType(configuratorPanels);
        //This is used to select the default panel. By default it will start in LowPass
        configuratorPanels[0].setVisible(true);
        for (int i = 1; i < 4; i++) {
            configuratorPanels[i].setVisible(false);
        }



        filterList.setSelectedIndex(0); //Low Pass as Default
        filterList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                index = filterList.getSelectedIndex();
                buttonFilterType.selectConfigurator(index);  //This function
            }
        });

        filterList.setMaximumSize(new Dimension(150, 30));
        filterList.setPreferredSize(new Dimension(150, 30));
        filterList.setMinimumSize(new Dimension(150, 30));

        //Adds everything to the TemplateConfigurationPanel
        this.add(filterList);
        this.add(genericConfiguratorPanel);
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

    public abstract class ConfiguratorPanel extends JPanel {}
    /*
    Here there are all the sub-panels, One generic and the others according to the Filter Selected
    */
    public class GenericConfiguratorPanel extends JPanel {
        JTextField textFilterAp = new JTextField("[dB]");
        JTextField textFilterAa = new JTextField("[dB]");
        JLabel labelAa = new JLabel("Aa");
        JLabel labelAp = new JLabel("Ap");

        public GenericConfiguratorPanel() {
                this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
                textFilterAa.setMinimumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGH));
                textFilterAp.setMaximumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGH));
                textFilterAa.setMaximumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGH));
                textFilterAp.setMinimumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGH));

                textFilterAa.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            //filterData.Aa = Double.parseDouble(textFilterAa.getText());
                        } catch (NumberFormatException nfe) {
                            //error Message for order
                            JInternalFrame frame = new JInternalFrame();
                            JOptionPane.showMessageDialog(frame, "The Input must be a number", "Input Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
                textFilterAp.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            //filterData.Ap = Double.parseDouble(textFilterAp.getText());
                        } catch (NumberFormatException nfe) {
                            //error Message for order
                            JInternalFrame frame = new JInternalFrame();
                            JOptionPane.showMessageDialog(frame, "The Input must be a number", "Input Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                this.add(labelAa);
                this.add(textFilterAa);
                this.add(labelAp);
                this.add(textFilterAp);
            }

        public double getFilterApValue() { return Double.parseDouble(textFilterAp.getText());}
        public double getFilterAaValue() { return Double.parseDouble(textFilterAa.getText());}
        }
    public class LowPassConfiguratorPanel extends ConfiguratorPanel {
            private JTextField textFilterWp = new JTextField("[rad/seg]");
            private JTextField textFilterWa = new JTextField("[rad/seg]");
            private JLabel labelWp = new JLabel("Wp");
            private JLabel labelWa = new JLabel("Wa");

            public LowPassConfiguratorPanel() {
                //Set action after input
                textFilterWa.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            //filterData.Wa = Double.parseDouble(textFilterWa.getText());
                        } catch (NumberFormatException nfe) {
                            //error Message for input
                            JInternalFrame frame = new JInternalFrame();
                            JOptionPane.showMessageDialog(frame, "The Input must be a number", "Input Error", JOptionPane.ERROR_MESSAGE);
                        }

                    }
                });
                textFilterWp.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            //filterData.Wp = Double.parseDouble(textFilterWp.getText());
                        } catch (NumberFormatException nfe) {
                            //error Message for order
                            JInternalFrame frame = new JInternalFrame();
                            JOptionPane.showMessageDialog(frame, "The Input must be a number", "Input Error", JOptionPane.ERROR_MESSAGE);
                        }

                    }
                });

                //Set Size
                textFilterWp.setMinimumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGH));
                textFilterWp.setMaximumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGH));
                textFilterWa.setMaximumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGH));
                textFilterWa.setMaximumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGH));

                this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

                this.add(labelWa);
                this.add(textFilterWa);
                this.add(labelWp);
                this.add(textFilterWp);
            }

            public double getFilterWpValue() { return Double.parseDouble(textFilterWp.getText());}
            public double getFilterWaValue() { return Double.parseDouble(textFilterWa.getText());}
        public void asd() {}
        }
    public class HighPassConfiguratorPanel extends ConfiguratorPanel {
            JTextField textFilterWp = new JTextField("[rad/seg]");
            JTextField textFilterWa = new JTextField("[rad/seg]");
            JLabel labelWp = new JLabel("Wp");
            JLabel labelWa = new JLabel("Wa");

            public HighPassConfiguratorPanel() {
                textFilterWa.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            //filterData.Wa = Double.parseDouble(textFilterWa.getText());
                        } catch (NumberFormatException nfe) {
                            //error Message for order
                            JInternalFrame frame = new JInternalFrame();
                            JOptionPane.showMessageDialog(frame, "The Input must be a number", "Input Error", JOptionPane.ERROR_MESSAGE);
                        }

                    }
                });
                textFilterWp.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            //filterData.Wp = Double.parseDouble(textFilterWp.getText());
                        } catch (NumberFormatException nfe) {
                            //error Message for order
                            JInternalFrame frame = new JInternalFrame();
                            JOptionPane.showMessageDialog(frame, "The Input must be a number", "Input Error", JOptionPane.ERROR_MESSAGE);
                        }

                    }
                });
                this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

                textFilterWp.setMinimumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGH));
                textFilterWp.setMaximumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGH));
                textFilterWa.setMaximumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGH));
                textFilterWa.setMaximumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGH));

                this.add(labelWa);
                this.add(textFilterWa);
                this.add(labelWp);
                this.add(textFilterWp);
            }

            public double getFilterWpValue() { return Double.parseDouble(textFilterWp.getText());}
            public double getFilterWaValue() { return Double.parseDouble(textFilterWa.getText());}
        }
    public class BandPassConfiguratorPanel extends ConfiguratorPanel {
            JTextField textFilterWo = new JTextField("[rad/seg]");
            JTextField textFilterB = new JTextField("[rad/seg");
            JLabel labelWo = new JLabel("Wo");
            JLabel labelB = new JLabel("B");

            public BandPassConfiguratorPanel() {
                this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

                textFilterB.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            //filterData.B = Double.parseDouble(textFilterB.getText());
                        } catch (NumberFormatException nfe) {
                            //error Message for order
                            JInternalFrame frame = new JInternalFrame();
                            JOptionPane.showMessageDialog(frame, "The Input must be a number", "Input Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
                textFilterWo.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            //filterData.Wo = Double.parseDouble(textFilterWo.getText());
                        } catch (NumberFormatException nfe) {
                            //error Message for order
                            JInternalFrame frame = new JInternalFrame();
                            JOptionPane.showMessageDialog(frame, "The Input must be a number", "Input Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                textFilterWo.setMinimumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGH));
                textFilterWo.setMaximumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGH));
                textFilterB.setMaximumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGH));
                textFilterB.setMaximumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGH));

                this.add(labelWo);
                this.add(textFilterWo);
                this.add(labelB);
                this.add(textFilterB);
            }
        }
    public class RejectBandConfiguratorPanel extends ConfiguratorPanel {
            JTextField textFilterWo = new JTextField("[rad/seg]");
            JTextField textFilterB = new JTextField("[rad/seg");
            JLabel labelWo = new JLabel("Wo");
            JLabel labelB = new JLabel("B");

            public RejectBandConfiguratorPanel() {
                this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

                textFilterB.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            //filterData.B = Double.parseDouble(textFilterB.getText());
                        } catch (NumberFormatException nfe) {
                            //error Message for order
                            JInternalFrame frame = new JInternalFrame();
                            JOptionPane.showMessageDialog(frame, "The Input must be a number", "Input Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
                textFilterWo.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            //filterData.Wo = Double.parseDouble(textFilterWo.getText());
                        } catch (NumberFormatException nfe) {
                            //error Message for order
                            JInternalFrame frame = new JInternalFrame();
                            JOptionPane.showMessageDialog(frame, "The Input must be a number", "Input Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                textFilterWo.setMinimumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGH));
                textFilterWo.setMaximumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGH));
                textFilterB.setMaximumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGH));
                textFilterB.setMaximumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGH));

                this.add(labelWo);
                this.add(textFilterWo);
                this.add(labelB);
                this.add(textFilterB);
            }
        }

}
