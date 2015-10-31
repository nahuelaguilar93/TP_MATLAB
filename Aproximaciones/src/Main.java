import gui.GuiFactory;
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
        JPanel principalPanel = new JPanel();
        JPanel bigPanel = new JPanel();
        StageOne stageOne = new StageOne();
        StageTwo stageTwo = new StageTwo();
        StageThree stageThree = new StageThree();
        CardLayout cardLayout = new CardLayout();
        JButton nextStageButton = new JButton();

        bigPanel.setLayout(cardLayout);
        bigPanel.add(stageOne, "One");
        bigPanel.add(stageTwo, "Two");
        bigPanel.add(stageThree, "Three");

        //Falta hacer que esto ande bien
        //cardLayout.show(bigPanel, "Three");

        principalPanel.add(bigPanel);
        this.add(bigPanel);

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


  /* Esto dentro del main, no lo quiero perder por eso lo dejo acá
        JTabbedPane jTabbedPane = new JTabbedPane();
        jTabbedPane.addTab("Stage One", GuiFactory.getStageOne());
        jTabbedPane.addTab("Stage Two", GuiFactory.getStageTwo());
        jTabbedPane.addTab("Stage Three", GuiFactory.getStageThree());
        this.add(jTabbedPane);

        principalPanel.setLayout(cardLayout);

        principalPanel.add(stageOne, "One");
        principalPanel.add(stageTwo, "Two");

        JButton nextStage = new JButton("Next Stage");
        nextStage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(principalPanel, "Two");
            }
        });

        buttonPanel.add(nextStage);

        this.add(principalPanel);
        this.add(buttonPanel);
*/


