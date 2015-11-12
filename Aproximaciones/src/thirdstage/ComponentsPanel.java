package thirdstage;

import Data.Singleton;
import mathematics.Stage;

import javax.swing.*;
import java.util.List;

public class ComponentsPanel extends JPanel{
    private DefaultListModel<String> componentListModel = new DefaultListModel<>();
    private JList<String> componentList = new JList<>(componentListModel);

	ComponentsPanel(){
        setBorder(BorderFactory.createTitledBorder("Components"));

        componentList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        JScrollPane componentListScroller = new JScrollPane(componentList);

        updateComponentList();

        //Setup layout
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup().addComponent(componentListScroller));
        layout.setVerticalGroup(layout.createSequentialGroup().addComponent(componentListScroller));
	}

    public void updateComponentList() {
        Singleton s = Singleton.getInstance();
        Singleton_S3 s3 = Singleton_S3.getInstance();

        List<Stage> currentStage = s.getUserData().getStageList();
        int index = s3.getStagePanel().getSelectedIndex();

        if ( index != -1 ) {
            double Q = currentStage.get(index).getQ();
            double wo = currentStage.get(index).getPoles()[0].getImaginary();
            double G = currentStage.get(index).getGdB();

            switch ( s3.getTopologyConfigPanel().getSelectedString() ) {
                case "HPSallen":
                    getHPSallen(Q, wo, G);
                    break;
                case "LPSallen":
                    getLPSallen(Q, wo, G);
                    break;
                case "Ackerberg Mossberg":
                    getAckerbergMossberg(Q, wo, G);
                    break;
                case "BPRauch":
                    getBPRauch(Q, wo, G);
                    break;
                case "BPSallen":
                    getBPSallen(Q, wo, G);
                    break;
                case "BRSallen":
                    getBRSallen(Q, wo, G);
                    break;
                case "Fleischer Tow":
                    getFleischerTow(Q, wo, G);
                    break;
                case "Kerwin Huelsman Newcomb":
                    getKHN(Q, wo, G);
                    break;
                case "Tow Thomas":
                    getTT(Q, wo, G);
                    break;
                default:
                    System.out.println("Se te escapó un switch case! Ojo!");
                    break;
            }
            //TODO: un switch con los distintos nombres de TopologyConfigPanel
            getBPRauch(currentStage.get(index).getQ(), currentStage.get(index).getPoles()[0].getImaginary(), currentStage.get(index).getGdB());
        }
    }

    private void getBPSallen(double Q, double wo, double G) {
        System.out.println("BandPass Sallen");
    }
    private void getBRSallen(double Q, double wo, double G) {
        System.out.println("BandReject Sallen");
    }
    private void getFleischerTow(double Q, double wo, double G) {
        System.out.println("Fleischer Tow");
    }
    private void getKHN(double Q, double wo, double G) {
        System.out.println("KHN");
    }
    private void getTT(double Q, double wo, double G) {
        System.out.println("Tow Thomas");
    }
    private void getHPSallen(double Q, double wo, double G) {
        System.out.println("High Pass Sallen");
    }
    private void getAckerbergMossberg(double Q, double wo, double G) {
        System.out.println("Ackerberg Mossberg");
    }
    private void getLPSallen(double Q, double wo, double G) {
        System.out.println("Low Pass Sallen");
    }
    private void getBPRauch(double Q, double w0, double midBandGaindb){
        componentListModel.removeAllElements();

        //Componentes del circuito
        double R1 = 50000;    //Seteado en 50k para tener buenos valores de Zin
        double C2;   //C2==C3
        double C3;
        double R4;
        double R5;
        double Rx1;
        double Rx2;

        //Formas de calcularlo
        double Qo = 1.5;
        double alpha = (2.*(1-Qo/Q))/9.;
        double K = alpha/(1+alpha);
        double H = (midBandGaindb*Qo*(1-K))/Q;
        double a = H/(2*Math.pow(Qo,2));
        C2 = (2*Qo)/(w0*9*a*R1);
        C3 = C2;
        double R = R1*a;
        R4 = R/(1-a);
        R5 = 9*R;
        double Rx = 10000;    //seteo resistencia media de salida en 10 k porque si, esto es una monarquía
        Rx1 = Rx*(1-K);
        Rx2 = Rx*K;

        componentListModel.addElement( "| R1 = " + R1 + " |" );
        componentListModel.addElement( "| R2 = " + Rx2 + " |" );
        componentListModel.addElement( "| Rx1 = " + Rx1 + " |" );
        componentListModel.addElement( "| R4 = " + R4 + " |" );
        componentListModel.addElement( "| R5 = " + R5 + " |" );
        componentListModel.addElement( "| C2 = " + C2 + " |" );
        componentListModel.addElement( "| C3 = " + C3 + " |" );
    }
}
