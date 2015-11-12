package thirdstage;

import Data.Singleton;
import mathematics.Stage;
import org.apache.commons.math3.analysis.solvers.LaguerreSolver;
import org.apache.commons.math3.complex.Complex;

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

            if ( s3.getTopologyConfigPanel().getIndex() != -1 ) {
                switch (s3.getTopologyConfigPanel().getSelectedString()) {
                    case "HPSallen":
                        getHPSallen(Q, wo, G);
                        break;
                    case "LPSallen":
                        getLPSallen(Q, wo, G);
                        break;
                    case "LPAckerberg Mossberg":
                        getAckerbergMossberg(Q, wo, G);
                        break;
                    case "HPAckerberg Mossberg":
                        getAckerbergMossberg(Q, wo, G);
                        break;
                    case "BSAckerberg Mossberg":
                        getAckerbergMossberg(Q, wo, G);
                        break;
                    case "BPAckerberg Mossberg":
                        getAckerbergMossberg(Q, wo, G);
                        break;
                    case "BPRauch":
                        getBPRauch(Q, wo, G);
                        break;
                    case "BPSallen":
                        getBPSallen(Q, wo, G);
                        break;
                    case "LPFleischer Tow":
                        getFleischerTow(Q, wo, G);
                        break;
                    case "HPFleischer Tow":
                        getFleischerTow(Q, wo, G);
                        break;
                    case "BSFleischer Tow":
                        getFleischerTow(Q, wo, G);
                        break;
                    case "BPFleischer Tow":
                        getFleischerTow(Q, wo, G);
                        break;
                    case "LPKerwin Huelsman Newcomb":
                        getKHN(Q, wo, G);
                        break;
                    case "HPKerwin Huelsman Newcomb":
                        getKHN(Q, wo, G);
                        break;
                    case "BSKerwin Huelsman Newcomb":
                        getKHN(Q, wo, G);
                        break;
                    case "BPKerwin Huelsman Newcomb":
                        getKHN(Q, wo, G);
                        break;
                    case "LPTow Thomas":
                        getTT(Q, wo, G);
                        break;
                    case "HPTow Thomas":
                        getTT(Q, wo, G);
                        break;
                    case "BSTow Thomas":
                        getTT(Q, wo, G);
                        break;
                    case "BPTow Thomas":
                        getTT(Q, wo, G);
                        break;
                    case "LPRauch":
                        getLPRauch(Q, wo, G);
                        break;
                    case "HPRauch":
                        getHPRauch(Q, wo, G);
                        break;
                    default:
                        System.out.println("Se te escapó un switch case! Ojo!");
                        break;
                }
            }
        }
    }

    //Sallen Key
    private void getLPSallen(double Q, double wo, double G) {
        componentListModel.removeAllElements();

        double Ra = 1000;
        double Rb;
        double R1;
        double R2;
        double C1 = 1000;
        double C2;

        Rb = (Math.pow(10, G/20)*Ra);
        LaguerreSolver laguerreSolver = new LaguerreSolver();
        double[] coefficients = {2+G, -1/Q, 1};
        Complex[] r2 = laguerreSolver.solveAllComplex( coefficients, 10 );
        if ( r2[0].getReal() < 0 ) {
            R2 = Math.abs(r2[1].getReal());
        }
        else {
            R2 = r2[0].getReal();
        }

        R1 = 1/(R2*Math.sqrt(wo*C1));
        C2 = C1;

        componentListModel.addElement( "| R1 = " + R1 + " |" );
        componentListModel.addElement( "| R2 = " + R2 + " |" );
        componentListModel.addElement( "| RA = " + Ra + " |" );
        componentListModel.addElement( "| RB = " + Rb + " |" );
        componentListModel.addElement( "| C1 = " + C1 + " |" );
        componentListModel.addElement( "| C2 = " + C2 + " |" );
    }
    private void getHPSallen(double Q, double wo, double G) {
        componentListModel.removeAllElements();

        double Ra = 1000;
        double Rb;
        double R1;
        double R2;
        double C1 = 1000;
        double C2;

        Rb = (Math.pow(10, G/20)*Ra);
        LaguerreSolver laguerreSolver = new LaguerreSolver();
        double[] coefficients = {1-G, -2/Q, 1};
        Complex[] r2 = laguerreSolver.solveAllComplex( coefficients, 10 );
        if ( r2[0].getReal() < 0 ) {
            R2 = Math.abs(r2[1].getReal());
        }
        else {
            R2 = r2[0].getReal();
        }

        R1 = 1/(R2*Math.sqrt(wo*C1));
        C2 = C1;

        componentListModel.addElement( "| R1 = " + R1 + " |" );
        componentListModel.addElement( "| R2 = " + R2 + " |" );
        componentListModel.addElement( "| RA = " + Ra + " |" );
        componentListModel.addElement( "| RB = " + Rb + " |" );
        componentListModel.addElement( "| C1 = " + C1 + " |" );
        componentListModel.addElement( "| C2 = " + C2 + " |" );
    }
    private void getBPSallen(double Q, double wo, double G) {
        //TODO: esta aun es chamuyo
        componentListModel.removeAllElements();

        double Ra = 1000;
        double Rb;
        double R1;
        double R2;
        double R3;
        double C1 = 1000;
        double C2;

        Rb = (Math.pow(10, G/20)*Ra);
        LaguerreSolver laguerreSolver = new LaguerreSolver();
        double[] coefficients = {1-G, -2/Q, 1};
        Complex[] r2 = laguerreSolver.solveAllComplex( coefficients, 10 );
        if ( r2[0].getReal() < 0 ) {
            R2 = Math.abs(r2[1].getReal());
        }
        else {
            R2 = r2[0].getReal();
        }

        R1 = 1/(R2*Math.sqrt(wo*C1));
        C2 = C1;
        R3 = R1;

        componentListModel.addElement( "| R1 = " + R1 + " |" );
        componentListModel.addElement( "| R2 = " + R2 + " |" );
        componentListModel.addElement( "| R3 = " + R3 + " |");
        componentListModel.addElement( "| RA = " + Ra + " |" );
        componentListModel.addElement( "| RB = " + Rb + " |" );
        componentListModel.addElement( "| C1 = " + C1 + " |" );
        componentListModel.addElement( "| C2 = " + C2 + " |" );
    }
    //Rauch
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
        double gain= Math.pow(10,midBandGaindb/20.);
        double H = (gain*Qo*(1-K))/Q;
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
    private void getLPRauch(double Qp, double wp, double gainDb) {
        componentListModel.removeAllElements();

        /*Componentes a definir*/
        double R1;
        double R2;
        double R3;
        double C4=Math.pow(10,-9);  //Seteo capacitor en 1 nF porque me pinto el ojete
        double C5;

        double gain=Math.pow(10,gainDb/20.);
        double m=0.5*(Math.pow(1./(4*Math.pow(Qp,2)*(1.+gain)),0.5));
        C5=Math.pow(m,2)*C4;
        R2=(1.+Math.pow(1.-4.*(1+gain)*(Math.pow(m*Qp,2)),0.5))/(2.*wp*Qp*Math.pow(m,2)*C4);
        R1=R2/gain;
        R3=1./(R2*Math.pow(m*wp*C4,2));

        componentListModel.addElement( "| R1 = " + R1 + " |" );
        componentListModel.addElement( "| R2 = " + R2 + " |" );
        componentListModel.addElement( "| R3 = " + R3 + " |" );
        componentListModel.addElement( "| C4 = " + C4 + " |" );
        componentListModel.addElement( "| C5 = " + C5 + " |" );
    }
    private void getHPRauch(double Qp, double wp, double gainDb) {
        componentListModel.removeAllElements();

        double C1=Math.pow(1.5*10,-9);  //1.5 nano porque pinto gira
        double C2;
        double C3;
        double R4;
        double R5;

        double gain=Math.pow(10,gainDb/20.);
        double m=0.5;
        C3=Math.pow(m,2)*C1;
        C2=C1/gain;
        R4=gain/(wp*Qp*C1*(1+(Math.pow(m,2)+1)*gain));
        R5=(Qp*(1+(Math.pow(m,2)+1)*gain))/(wp*C1*Math.pow(m,2));

        componentListModel.addElement( "| C1 = " + C1 + " |" );
        componentListModel.addElement( "| C2 = " + C2 + " |" );
        componentListModel.addElement( "| R3 = " + C3 + " |" );
        componentListModel.addElement( "| R4 = " + R4 + " |" );
        componentListModel.addElement( "| R5 = " + R5 + " |" );
    }
    //Universales
    private void getKHN(double Q, double wo, double G) {
        componentListModel.removeAllElements();

        //Componentes del Circuito
        double R4 = 47000;
        double R6;
        double R1 = 1000;
        double R3 = 47000;  //Zin
        double R5;
        double R2;
        double R7 = 1000;
        double R8;
        double R9;
        double Rg;
        double C1;
        double C2;

        //Calculo
        Rg = 2*Q/R4;
        C1 = 1/(wo*R1);
        R6 = Math.pow(10, G/20)*R3;


        C2 = C1;
        R2 = R1;
        R5 = R6;
        R8 = R7;
        R9 = R7;

        componentListModel.addElement( "| R4 = " + R4 + " |" );
        componentListModel.addElement( "| R6 = " + R6 + " |" );
        componentListModel.addElement( "| R1 = " + R1 + " |" );
        componentListModel.addElement( "| R3 = " + R3 + " |" );
        componentListModel.addElement( "| R5 = " + R5 + " |" );
        componentListModel.addElement( "| R2 = " + R2 + " |" );
        componentListModel.addElement( "| R7 = " + R7 + " (Only for Band Reject) |" );
        componentListModel.addElement( "| R8 = " + R8 + " (Only for Band Reject) |" );
        componentListModel.addElement( "| R9 = " + R9 + " (Only for Band Reject) |" );
        componentListModel.addElement( "| Rg = " + Rg + " |" );
        componentListModel.addElement( "| C1 = " + C1 + " |" );
        componentListModel.addElement( "| C2 = " + C2 + " |" );
    }
    private void getTT(double Q, double wo, double G) {
        componentListModel.removeAllElements();

        double R1 = 1000;
        double R3;
        double R4;
        double R6 = 1000;
        double R2;
        double R5;
        double R7 = 1000;
        double R8;
        double R9;
        double C1;
        double C2;

        R2 = Math.pow(10, G/20)*R1;
        C1 = 1/(R1*Math.pow(10, G/20));
        C1 = 1/(wo*R6);
        R3 = Q*R6;

        R4 = R6;
        R2 = R6;
        R5 = R6;
        C2 = C1;
        R8 = R7;
        R9 = R7;

        componentListModel.addElement( "| R6 = " + R6 + " |" );
        componentListModel.addElement( "| R5 = " + R5 + " |" );
        componentListModel.addElement( "| R1 = " + R1 + " |" );
        componentListModel.addElement( "| R4 = " + R4 + " |" );
        componentListModel.addElement( "| R2 = " + R2 + " |" );
        componentListModel.addElement( "| R3 = " + R3 + " |" );
        componentListModel.addElement( "| R7 = " + R7 + " (Only for Band Reject) |" );
        componentListModel.addElement( "| R8 = " + R8 + " (Only for Band Reject) |" );
        componentListModel.addElement( "| R9 = " + R9 + " (Only for Band Reject) |" );
        componentListModel.addElement( "| C1 = " + C1 + " |" );
        componentListModel.addElement( "| C2 = " + C2 + " |" );
    }
    private void getAckerbergMossberg(double Q, double wo, double G) {
        componentListModel.removeAllElements();

        double R1 = 1000;
        double R2 = 1000;
        double R3 = 1000;
        double R4;
        double R5;
        double R6;
        double R7 = 1000;
        double R8;
        double R9;
        double C1;
        double C2;

        R5 = Math.pow(10, G/20)*R1/Math.pow(wo, 2);
        C1 = 1/(wo*R3);
        R2 = Q/(wo*C1);

        C2 = C1;
        R4 = R3;
        R5 = R3;
        R6 = R3;
        R8 = R7;
        R9 = R7;

        componentListModel.addElement( "| R6 = " + R6 + " |" );
        componentListModel.addElement( "| R5 = " + R5 + " |" );
        componentListModel.addElement( "| R1 = " + R1 + " |" );
        componentListModel.addElement( "| R4 = " + R4 + " |" );
        componentListModel.addElement( "| R2 = " + R2 + " |" );
        componentListModel.addElement( "| R3 = " + R3 + " |" );
        componentListModel.addElement( "| R7 = " + R7 + " (Only for Band Reject) |" );
        componentListModel.addElement( "| R8 = " + R8 + " (Only for Band Reject) |" );
        componentListModel.addElement( "| R9 = " + R9 + " (Only for Band Reject) |" );
        componentListModel.addElement( "| C1 = " + C1 + " |" );
        componentListModel.addElement( "| C2 = " + C2 + " |" );
    }
    private void getFleischerTow(double Q, double wo, double G) {
        componentListModel.removeAllElements();

        double R1 = 1000;
        double R2;
        double R3 = 1000;
        double R4;
        double R5;
        double R6;
        double R7 = 1000;
        double R8;
        double R9;
        double C1;
        double C2;

        R5 = Math.pow(10, G/20)*R1/Math.pow(wo, 2);
        C1 = 1/(wo*R3);
        R2 = Q/(wo*C1);

        C2 = C1;
        R2 = R3;
        R4 = R3;
        R5 = R3;
        R6 = R3;
        R8 = R7;
        R9 = R7;

        componentListModel.addElement( "| R6 = " + R6 + " |" );
        componentListModel.addElement( "| R5 = " + R5 + " |" );
        componentListModel.addElement( "| R1 = " + R1 + " |" );
        componentListModel.addElement( "| R4 = " + R4 + " |" );
        componentListModel.addElement( "| R2 = " + R2 + " |" );
        componentListModel.addElement( "| R3 = " + R3 + " |" );
        componentListModel.addElement( "| R7 = " + R7 + " (Only for Band Reject) |" );
        componentListModel.addElement( "| R8 = " + R8 + " (Only for Band Reject) |" );
        componentListModel.addElement( "| R9 = " + R9 + " (Only for Band Reject) |" );
        componentListModel.addElement( "| C1 = " + C1 + " |" );
        componentListModel.addElement( "| C2 = " + C2 + " |" );
    }
}
