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
    private JComboBox filterTypeList = new JComboBox(templateStrings);
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

        SuperTemplate t = Singleton.getInstance().getUserData().getCurrentTemplate();
        if (t instanceof LowpassTemplate) {
            index = 0;
            LowpassTemplate temp = (LowpassTemplate) t;
            s.getConfigLowPassPanel().setTextBoxes(temp.getWp(), temp.getWa());
        } else if (t instanceof HighpassTemplate) {
            index = 1;
            HighpassTemplate temp = (HighpassTemplate) t;
            s.getConfigHighPassPanel().setTextBoxes(temp.getWp(),temp.getWa());
        } else if (t instanceof BandpassTemplate) {
            index = 2;
            BandpassTemplate temp = (BandpassTemplate) t;
            s.getConfigBandPassPanel().setTextBoxes(temp.getWpm(), temp.getWam(), temp.getWpp(), temp.getWap());
        } else if (t instanceof BandrejectTemplate) {
            index = 3;
            BandrejectTemplate temp = (BandrejectTemplate) t;
            s.getConfigBandRejectPanel().setTextBoxes(temp.getWpm(), temp.getWam(), temp.getWpp(), temp.getWap());
        }  else { //(t instanceof DelayTemplate)
            index = 4;
            DelayTemplate temp = (DelayTemplate) t;
            s.getConfigDelayPanel().setTextBoxes(temp.getWp(), temp.getWa(), temp.getDelay(), temp.getPsi());
        }
        s.getConfigAmplitudePanel().setTextBoxes(t.getAp(), t.getAa(), t.getG());
        cardLayout.show(freqPanel,cardList.get(index));
        filterTypeList.setSelectedIndex(index);

        filterTypeList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                index = filterTypeList.getSelectedIndex();
                cardLayout.show(freqPanel, cardList.get(index));
            }
        });

        filterTypeList.setPreferredSize(new Dimension(150, 30));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(filterTypeList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(configAmplitudePanel)
                                .addComponent(freqPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addComponent(buttonFilterType)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(filterTypeList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(configAmplitudePanel)
                                .addComponent(freqPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addComponent(buttonFilterType)
        );
    }

    public class ButtonFilterType extends JPanel {
        JButton buttonFilterType = new JButton("Create Template");

        public ButtonFilterType() {
            this.add(buttonFilterType);
            buttonFilterType.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //ToDo: IsParsable antes de crear el template
                    Singleton_S1 s = Singleton_S1.getInstance();
                    UserData uData = Singleton.getInstance().getUserData();

                    double ap, aa, g;
                    if (s.getConfigAmplitudePanel().isParsable()) {
                        ap = s.getConfigAmplitudePanel().getAp();
                        aa = s.getConfigAmplitudePanel().getAa();
                        g = s.getConfigAmplitudePanel().getG();
                    } else return;

                    int index = filterTypeList.getSelectedIndex();
                    SuperTemplate newTemplate;
                    switch ( templateType.values()[index] ) {
                        case LOWPASS:
                            if (s.getConfigLowPassPanel().isParsable()) {
                                double wp = s.getConfigLowPassPanel().getWp();
                                double wa = s.getConfigLowPassPanel().getWa();
                                newTemplate = new LowpassTemplate(ap, aa, g, wp, wa);
                            } else return; break;
                        case HIGHPASS:
                            if (s.getConfigHighPassPanel().isParsable()) {
                                double wp = s.getConfigHighPassPanel().getWp();
                                double wa = s.getConfigHighPassPanel().getWa();
                                newTemplate = new HighpassTemplate(ap, aa, g, wp, wa);
                            } else return; break;
                        case BANDPASS:
                            if (s.getConfigBandPassPanel().isParsable()) {
                                double wpm = s.getConfigBandPassPanel().getWpm();
                                double wam = s.getConfigBandPassPanel().getWam();
                                double wpp = s.getConfigBandPassPanel().getWpp();
                                double wap = s.getConfigBandPassPanel().getWap();
                                newTemplate = new BandpassTemplate(ap, aa, g, wpm, wam, wpp, wap);
                            } else return; break;
                        case BANDREJECT:
                            if (s.getConfigBandRejectPanel().isParsable()) {
                                double wpm = s.getConfigBandRejectPanel().getWpm();
                                double wam = s.getConfigBandRejectPanel().getWam();
                                double wpp = s.getConfigBandRejectPanel().getWpp();
                                double wap = s.getConfigBandRejectPanel().getWap();
                                newTemplate = new BandrejectTemplate(ap, aa, g, wpm, wam, wpp, wap);
                            } else return; break;
                        default: //case DELAY:
                            if (s.getConfigDelayPanel().isParsable()) {
                                double wp = s.getConfigDelayPanel().getWp();
                                double wa = s.getConfigDelayPanel().getWa();
                                double delay = s.getConfigDelayPanel().getDelay();
                                double psi = s.getConfigDelayPanel().getPsi();
                                newTemplate = new DelayTemplate(ap, aa, g, wp, wa, delay, psi);
                            } else return; break;
                    }
                    if (newTemplate.equals(uData.getCurrentTemplate()))
                        return;
                    else {
                        uData.setCurrentTemplate(newTemplate);
                        uData.getApproximationList().clear();
                        uData.setSelection(-1);
                        s.getFilterList().updateList();
                        approxComboBox.updateList();
                    }
                }
            });
        }
    }
}