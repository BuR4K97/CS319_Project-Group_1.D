package UIComponents;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.GameController;

public class TextualInGamePanel {
	
	private JLabel phaseLabel;
	private JLabel playerLabel;
	
	public void initialize() {
		phaseLabel = new JLabel();
		phaseLabel.setFont(new Font("Calibri", Font.BOLD, 32));
		playerLabel = new JLabel();
		playerLabel.setFont(new Font("Calibri", Font.BOLD, 32));
	}
	
	public void insertLabels(JPanel target) {
		target.add(phaseLabel);
		target.add(playerLabel);
	}
	
	public void update() {
		phaseLabel.setText(GameController.interactions.getActivePhase().toString());
		playerLabel.setText(GameController.interactions.getActivePlayer().toString());
		Color playerColor = GameController.interactions.getActivePlayer().getColor();
		playerLabel.setForeground(new Color(playerColor.getRed(), playerColor.getGreen(), playerColor.getBlue()
				, 255));
	}

}
