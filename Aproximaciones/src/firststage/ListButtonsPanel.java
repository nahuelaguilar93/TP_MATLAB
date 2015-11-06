package firststage;

import Data.Singleton;
import Data.UserData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by NEGU on 5/11/2015.
 */
class ListButtonsPanel extends JPanel{
    private JButton deleteItem;
    private JButton deleteAllItems;
    private JButton selectFilter;


    ListButtonsPanel () {
        deleteItem = new JButton("Delete Item");
        deleteAllItems = new JButton("Delete All Items");
        selectFilter = new JButton("Select Filter");

        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApproxList approxList = Singleton_S1.getInstance().getApproxList();

                if (approxList.isAnItemSelected()){
                    int index = approxList.getIndex();
                    UserData uData = Singleton.getInstance().getUserData();
                    uData.getApproximationList().remove(index);

                    if (index < uData.getSelection())
                        uData.setSelection(uData.getSelection()-1);
                    else if (index == uData.getSelection())
                        uData.setSelection(-1);

                    approxList.updateList();
                }
            }
        });
        deleteAllItems.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Singleton.getInstance().getUserData().getApproximationList().clear();
                Singleton.getInstance().getUserData().setSelection(-1);
                Singleton_S1.getInstance().getApproxList().updateList();
            }
        });
        selectFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Singleton_S1 s = Singleton_S1.getInstance();
                ApproxList approxList = s.getApproxList();

                if (approxList.isAnItemSelected()) {
                    int index = approxList.getIndex();
                    Singleton.getInstance().getUserData().setSelection(index);
                    approxList.updateList();
                    //TODO: Guardar el TF selccionado para pasar al StageTwo
                }
                else {
                    JInternalFrame frame = new JInternalFrame();
                    JOptionPane.showMessageDialog(frame, "There is no filter selected.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(deleteItem)
                                .addComponent(deleteAllItems))
                        .addComponent(selectFilter)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(deleteItem)
                                .addComponent(deleteAllItems))
                        .addComponent(selectFilter)
        );
    }
}
