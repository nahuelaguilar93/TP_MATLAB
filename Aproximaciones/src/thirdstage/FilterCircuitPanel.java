package thirdstage;

import javax.swing.*;
import java.awt.*;

public class FilterCircuitPanel extends JPanel{

	FilterCircuitPanel(){
		setBorder(BorderFactory.createTitledBorder("Filter Circuit"));
		setBounds(0, 0, 724, 512);
		//setLayout(new GridLayout(2,1));
		
		JPanel panelparaelplot = new JPanel();
		panelparaelplot.setBackground(Color.black);
		
		JPanel botones =new JPanel();
		//botones.setLayout(new GridLayout(1,2));
		botones.add(new JButton("Stage 1"));
		botones.add(new JButton("Stage 2"));
		
		
		add(panelparaelplot);
		add(botones);

	}

}
