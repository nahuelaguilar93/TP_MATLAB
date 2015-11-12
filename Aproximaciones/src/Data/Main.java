package Data;

import tclib.MathUtils;

import javax.swing.*;
import java.awt.*;

import static javax.swing.SwingUtilities.invokeLater;

public class Main extends JFrame {

    public Main() {
        MathUtils.getAllPermutations(5);
        PrincipalPanel principalPanel = new PrincipalPanel();

        this.add(principalPanel);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH); //Full-Screen at Start

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TP de Aproximaciones en Java");
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(1280, 720)); //Minimum Screen Size
    }

    public static void main(String[] args) {

        // Ejemplo de funciones pedidas.
 //       System.out.println(Jacobi.cn(4, 3));
 //       de.jtem.mfc.field.Complex c = new de.jtem.mfc.field.Complex(0.2,0);
 //       System.out.println(Jacobi.K_from_k(c));

        for(int x:new int[]{})
            System.out.println(x);


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