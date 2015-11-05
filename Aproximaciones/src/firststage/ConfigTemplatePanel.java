package firststage;

import Data.Singleton;
import Data.UserData;
import tclib.templates.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

class ConfigTemplatePanel extends JPanel implements TemplatesInterface {
    private ApproxComboBox approxComboBox;
    private JComboBox filterList = new JComboBox(templateStrings);
    private ConfigAmplitudePanel configAmplitudePanel;
    private ButtonFilterType buttonFilterType;
    private CardLayout cardLayout = new CardLayout();
    private JPanel freqPanel = new JPanel();
    private int index = 0;
    private final List<String> cardList = Arrays.asList("LP", "HP", "BP", "BR", "DL");

    public ConfigTemplatePanel() {
        Singleton_S1 s = Singleton_S1.getInstance();
        approxComboBox = s.getApproxComboBox();
        configAmplitudePanel = s.getConfigAmplitudePanel();
        freqPanel.setLayout(cardLayout);
        freqPanel.add(s.getConfigLowPassPanel(), cardList.get(0));
        freqPanel.add(s.getConfigHighPassPanel(), cardList.get(1));
        freqPanel.add(s.getConfigBandPassPanel(), cardList.get(2));
        freqPanel.add(s.getConfigBandRejectPanel(), cardList.get(3));
        freqPanel.add(s.getConfigDelayPanel(), cardList.get(4));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createTitledBorder("Template Configurator"));

        buttonFilterType = new ButtonFilterType();
        //This is used to select the default panel. By default it will start in LowPass TODO:get the index "0" from user data

        filterList.setSelectedIndex(0);     //Low Pass as Default TODO:get it from user data
        filterList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                index = filterList.getSelectedIndex();
                cardLayout.show(freqPanel, cardList.get(index));
            }
        });

        filterList.setPreferredSize(new Dimension(150, 30));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(filterList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(configAmplitudePanel)
                                .addComponent(freqPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addComponent(buttonFilterType)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(filterList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(configAmplitudePanel)
                                .addComponent(freqPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addComponent(buttonFilterType)
        );
    }

    public class ButtonFilterType extends JPanel {
        JButton buttonFilterType = new JButton("Create Template");

        public ButtonFilterType() {
            buttonFilterType.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //ToDo: IsParsable antes de crear el template
                    Singleton_S1 s = Singleton_S1.getInstance();
                    UserData uData = Singleton.getInstance().getUserData();

                    double ap;
                    double aa;
                    if (s.getConfigAmplitudePanel().isParsable()) {
                        ap = s.getConfigAmplitudePanel().getAp();
                        aa = s.getConfigAmplitudePanel().getAa();
                    } else return;

                    int index = filterList.getSelectedIndex();
                    switch ( templateType.values()[index] ) {
                        case LOWPASS:
                            if (s.getConfigLowPassPanel().isParsable()) {
                                double wp = s.getConfigLowPassPanel().getWp();
                                double wa = s.getConfigLowPassPanel().getWa();
                                uData.CurrentTemplate = new LowpassTemplate(wp, wa, ap, aa);
                            } break;
                        case HIGHPASS:
                            if (s.getConfigHighPassPanel().isParsable()) {
                                double wp = s.getConfigHighPassPanel().getWp();
                                double wa = s.getConfigHighPassPanel().getWa();
                                uData.CurrentTemplate = new HighpassTemplate(wp, wa, ap, aa);
                            } break;
                        case BANDPASS:
                            if (s.getConfigBandPassPanel().isParsable()) {
                                double wpm = s.getConfigBandPassPanel().getWpm();
                                double wam = s.getConfigBandPassPanel().getWam();
                                double wpp = s.getConfigBandPassPanel().getWpm();
                                double wap = s.getConfigBandPassPanel().getWap();
                                uData.CurrentTemplate = new BandpassTemplate(wpm, wam, wpp, wap, ap, aa);
                            } break;
                        case BANDREJECT:
                            if (s.getConfigBandRejectPanel().isParsable()) {
                                double wpm = s.getConfigBandRejectPanel().getWpm();
                                double wam = s.getConfigBandRejectPanel().getWam();
                                double wpp = s.getConfigBandRejectPanel().getWpm();
                                double wap = s.getConfigBandRejectPanel().getWap();
                                uData.CurrentTemplate = new BandrejectTemplate(wpm, wam, wpp, wap, ap, aa);
                            } break;
                        case DELAY:
                            //UserData.CurrentTemplate = new DelayTemplate();
                            break;
                    }
                    approxComboBox.updateList();
                }
            });
            this.add(buttonFilterType);
        }
    }
}

