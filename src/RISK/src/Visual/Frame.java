package Visual;

import javax.swing.JFrame;

public class Frame extends JFrame{
	
	private static Panel panel = new Panel();
	
	public Frame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.add(panel);
		this.setBounds(200, 200, 1300, 700);
	}
	public Panel getPanel() {
		return panel;
	}
}
