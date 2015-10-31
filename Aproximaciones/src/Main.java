import gui.GuiFactory;
import gui.PrincipalPanel;
import gui.firststage.StageOne;
import gui.secondstage.StageTwo;
import gui.thirdstage.StageThree;
import tclib.TransferFunction;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.SwingUtilities.invokeLater;

public class Main extends JFrame {

    public Main() {
        /*The configuration goes like this:
        *           There is a principalPanel with two Panels. One is the bigPanel where it has everything of the stage
        *           The other is a button panel which only has two buttons to go back and forth between panels
         */
        PrincipalPanel principalPanel = new PrincipalPanel();

        this.add(principalPanel);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TP de Aproximaciones en Java");
        setLocationRelativeTo(null);
        //setPreferredSize(new Dimension(1024, 768));
        setMinimumSize(new Dimension(800, 600));
    }

    public static void main(String[] args) {
        // <editor-fold defaultstate="collapsed" desc="Look and Feel">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        invokeLater(() -> new Main().setVisible(true));
    }
}