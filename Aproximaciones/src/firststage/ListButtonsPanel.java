package firststage;

import org.apache.commons.math3.analysis.function.Sin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by NEGU on 5/11/2015.
 */
public class ListButtonsPanel extends JPanel{
    private JButton deleteSelectedItem;
    private JButton deleteAllItems;
    private JButton selectFilter;


    ListButtonsPanel () {
        deleteSelectedItem = new JButton("Delete Selected Item");
        deleteAllItems = new JButton("Delete All Items");
        selectFilter = new JButton("Select Filter");

        deleteSelectedItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Singleton_S1 s = Singleton_S1.getInstance();
                ApproxList approxList = s.getApproxList();

                if (approxList.IsAnItemSelected())
                    approxList.DeleteElementFromList();
            }
        });
        deleteAllItems.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Singleton_S1 s = Singleton_S1.getInstance();
                ApproxList approxList = s.getApproxList();

                approxList.DeleteAll();
            }
        });
        selectFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Singleton_S1 s = Singleton_S1.getInstance();
                ApproxList approxList = s.getApproxList();

                if (approxList.IsAnItemSelected()) {
                    //TODO: Acá habría que poner "enable" en el boton next y guardar el TF selccionado para pasar al StageTwo
                }
                else {
                    JInternalFrame frame = new JInternalFrame();
                    JOptionPane.showMessageDialog(frame, "There is no filter selected", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.add(deleteSelectedItem);
        this.add(deleteAllItems);
        this.add(selectFilter);
    }
}
