package UIComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InGameSettingsPanel extends JPanel {
	
	//private Sound sound
	private JLabel quit;
	private JLabel close;
	
	public InGameSettingsPanel() {
		this.setPreferredSize(new Dimension(450, 300));
		setLayout(null);
		setBackground(Color.CYAN);
		
		quit = new JLabel("QUIT");
		quit.setBounds(10, 100, 90, 30);
		quit.setForeground(Color.GRAY);
		quit.setBackground(new Color(0, 0, 0, 0));
		quit.setFont(new Font("Calibri", Font.BOLD, 32));
		
		quit.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				System.exit(1);
			}
		});
		
		add(quit);
	}
	
	public void insertPanel(JPanel target) {
		target.add(this);
	}	
}
