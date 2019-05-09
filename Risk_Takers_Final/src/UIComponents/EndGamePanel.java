package UIComponents;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class EndGamePanel extends JPanel {
	
	private JLabel quit;
	private JLabel menu;
	
	public EndGamePanel() {
		this.setPreferredSize(new Dimension(1920, 1080));
		setLayout(null);
		setBackground(Color.BLACK);
	}
}
