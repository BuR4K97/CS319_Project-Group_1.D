package UIComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.GameController;
import UIComponents.EnvanterBoxUI.EnvanterBox;

public class TextualInGamePanel {

	private JLabel phaseLabel;
	private JLabel playerLabel;
	private ArrayList<VisualString> interactionStringList;
	EnvanterBox envanterBox;
	
	public void initialize() {
		phaseLabel = new JLabel();
		phaseLabel.setFont(new Font("Calibri", Font.BOLD, 32));
		phaseLabel.setForeground(Color.WHITE);
		phaseLabel.setBounds(595, 1013, 116, 29);
		
		playerLabel = new JLabel();
		playerLabel.setFont(new Font("Calibri", Font.BOLD, 32));
		playerLabel.setBounds(715, 1013, 106, 29);
		
		interactionStringList = new ArrayList<VisualString>();
		interactionStringList.add(new VisualString(860, 0, 14, phaseLabel.getText()));
		
		envanterBox = new EnvanterBox();
		
	}

	public void insertLabels(JPanel target) {
		target.add(phaseLabel);
		target.add(playerLabel);
		target.add(envanterBox);

	}

	public void update() {
		phaseLabel.setText(GameController.interactions.getActivePhase().toString());
		playerLabel.setText(GameController.interactions.getActivePlayer().toString());
		Color playerColor = GameController.interactions.getActivePlayer().getColor();
		playerLabel.setForeground(new Color(playerColor.getRed(), playerColor.getGreen(), playerColor.getBlue(), 255));
	}
	
	//paint method
	public void paint(Graphics g) {
		envanterBox.paint(g);
	}
}
