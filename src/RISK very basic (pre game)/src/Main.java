import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Main {

	
	public static void main(String []args) {
		
		JFrame frame = new JFrame("RISK");
		Panel pnl = new Panel();
		frame.add(pnl);
		frame.setVisible(true);
		frame.setBounds(100, 100, 1500, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.pack();
	}
}
