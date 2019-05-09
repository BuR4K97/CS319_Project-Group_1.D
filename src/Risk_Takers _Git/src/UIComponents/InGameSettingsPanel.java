package UIComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InGameSettingsPanel extends JPanel {
	
	//private Sound sound
	private JLabel quit;
	private JLabel close;
	
	public InGameSettingsPanel() {
		this.setPreferredSize(new Dimension(100, 300));
		setLayout(null);
		setBackground(Color.BLACK);
		quit.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				System.exit(1);
			}
		});
	}
	
	public void insertPanel(JPanel target) {
		target.add(quit);
		target.add(close);
		target.add(this);
	}	
}
