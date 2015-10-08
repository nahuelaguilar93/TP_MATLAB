import gui.GuiFactory;

import javax.swing.*;

import static javax.swing.SwingUtilities.invokeLater;

public class Main extends JFrame {

    public Main() {
        JTabbedPane jTabbedPane = new JTabbedPane();
        jTabbedPane.addTab("Stage One", GuiFactory.getStageOne());
        jTabbedPane.addTab("Stage Two", GuiFactory.getStageTwo());
        jTabbedPane.addTab("Stage Three", GuiFactory.getStageThree());
        this.add(jTabbedPane);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TP de Aproximaciones en Java");
        setSize(GuiFactory.APP_WIDTH, GuiFactory.APP_HEIGHT);
        setLocationRelativeTo(null);
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
