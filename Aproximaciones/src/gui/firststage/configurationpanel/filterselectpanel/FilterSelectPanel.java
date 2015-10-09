package gui.firststage.configurationpanel.filterselectpanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by NEGU on 8/10/2015.
 */
public class FilterSelectPanel extends JPanel{
    private ConfiguratorPanel[] configuratorPanels = new ConfiguratorPanel[4];
    private ButtonFilterType buttonFilterType = new ButtonFilterType();
    private static int TEXT_HEIGH = 50;
    private static int TEXT_WIDTH = 65;
/*    private final LowPassConfiguratorPanel lowpassConfiguratorPanel = new LowPassConfiguratorPanel();
    private final HighPassConfiguratorPanel highpassConfiguratorPanel = new HighPassConfiguratorPanel();
    private final BandPassConfiguratorPanel bandpassConfiguratorPanel = new BandPassConfiguratorPanel();
    private final RejectBandConfiguratorPanel rejectBandConfiguratorPanel = new RejectBandConfiguratorPanel();*/
    private final GenericConfiguratorPanel genericConfiguratorPanel = new GenericConfiguratorPanel();

    public FilterSelectPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createTitledBorder("Filter Configurator"));

        configuratorPanels[0] = new LowPassConfiguratorPanel();
        configuratorPanels[1] = new HighPassConfiguratorPanel();
        configuratorPanels[2] = new BandPassConfiguratorPanel();
        configuratorPanels[3] = new RejectBandConfiguratorPanel();

        configuratorPanels[0].setVisible(true);
        for (int i = 1; i < 4; i++) {
            configuratorPanels[i].setVisible(false);
        }

        String[] filterStrings = { "Low Pass", "High Pass", "Band Pass", "Reject Band" };
        JComboBox filterList = new JComboBox(filterStrings);

        filterList.setSelectedIndex(0); //Low Pass as Default
        filterList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = filterList.getSelectedIndex();
                selectConfigurator(index);

            }
        });

        this.add(filterList);
        this.add(genericConfiguratorPanel);
        this.add(configuratorPanels[0]);
        this.add(configuratorPanels[1]);
        this.add(configuratorPanels[2]);
        this.add(configuratorPanels[3]);
        this.add(buttonFilterType);
    }

    private void selectConfigurator(int index) {
        for (int i = 0; i < 4; i++) {
            if (index == i) {
                configuratorPanels[i].setVisible(true);
            }
            else {configuratorPanels[i].setVisible(false);}
        }
    }

    public abstract class ConfiguratorPanel extends JPanel{}

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

            this.add(labelAa);
            this.add(textFilterAa);
            this.add(labelAp);
            this.add(textFilterAp);
        }

    }
    public class LowPassConfiguratorPanel extends ConfiguratorPanel {
        JTextField textFilterWp = new JTextField("[rad/seg]");
        JTextField textFilterWa = new JTextField("[rad/seg]");
        JLabel labelWp = new JLabel("Wp");
        JLabel labelWa = new JLabel("Wa");

        public LowPassConfiguratorPanel() {
            textFilterWa.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            textFilterWp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });

            textFilterWp.setMinimumSize(new Dimension(TEXT_WIDTH,TEXT_HEIGH));
            textFilterWp.setMaximumSize(new Dimension(TEXT_WIDTH,TEXT_HEIGH));
            textFilterWa.setMaximumSize(new Dimension(TEXT_WIDTH,TEXT_HEIGH));
            textFilterWa.setMaximumSize(new Dimension(TEXT_WIDTH,TEXT_HEIGH));

            this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

            this.add(labelWa);
            this.add(textFilterWa);
            this.add(labelWp);
            this.add(textFilterWp);
        }
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

                }
            });
            textFilterWp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

            textFilterWp.setMinimumSize(new Dimension(TEXT_WIDTH,TEXT_HEIGH));
            textFilterWp.setMaximumSize(new Dimension(TEXT_WIDTH,TEXT_HEIGH));
            textFilterWa.setMaximumSize(new Dimension(TEXT_WIDTH,TEXT_HEIGH));
            textFilterWa.setMaximumSize(new Dimension(TEXT_WIDTH,TEXT_HEIGH));

            this.add(labelWa);
            this.add(textFilterWa);
            this.add(labelWp);
            this.add(textFilterWp);
        }
    }
    public class BandPassConfiguratorPanel extends ConfiguratorPanel {
        JTextField textFilterWo = new JTextField("[rad/seg]");
        JTextField textFilterB = new JTextField("[rad/seg");
        JLabel labelWo = new JLabel("Wo");
        JLabel labelB = new JLabel("B");
        public BandPassConfiguratorPanel() {
            this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));


            textFilterWo.setMinimumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGH));
            textFilterWo.setMaximumSize(new Dimension(TEXT_WIDTH,TEXT_HEIGH));
            textFilterB.setMaximumSize(new Dimension(TEXT_WIDTH,TEXT_HEIGH));
            textFilterB.setMaximumSize(new Dimension(TEXT_WIDTH,TEXT_HEIGH));

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


            textFilterWo.setMinimumSize(new Dimension(TEXT_WIDTH,TEXT_HEIGH));
            textFilterWo.setMaximumSize(new Dimension(TEXT_WIDTH,TEXT_HEIGH));
            textFilterB.setMaximumSize(new Dimension(TEXT_WIDTH,TEXT_HEIGH));
            textFilterB.setMaximumSize(new Dimension(TEXT_WIDTH,TEXT_HEIGH));

            this.add(labelWo);
            this.add(textFilterWo);
            this.add(labelB);
            this.add(textFilterB);
        }
    }
}
