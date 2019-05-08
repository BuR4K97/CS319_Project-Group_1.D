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
import ModelClasses.Turn;
import UIComponents.EnvanterBoxUI.EnvanterBox;

public class TextualInGamePanel {

	private JLabel phaseLabel;
	private JLabel playerLabel;
	private ArrayList<VisualString> interactionStringList;
	public EnvanterBox envanterBox;
	private MouseInGameListener mouseTracer;
	
	public void initialize(MouseInGameListener mouseTracer) {
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
		
		this.mouseTracer = mouseTracer;
	}

	public void insertLabels(JPanel target) {
		target.add(phaseLabel);
		target.add(playerLabel);
	}

	public void update() {
		if(GameController.interactions.getTextualPanelUpdateRequest()) {
			phaseLabel.setText(GameController.interactions.getActivePhase().toString());
			playerLabel.setText(GameController.interactions.getActivePlayer().toString());
			Color playerColor = GameController.interactions.getActivePlayer().getColor();
			playerLabel.setForeground(new Color(playerColor.getRed(), playerColor.getGreen(), playerColor.getBlue(), 255));
			envanterBox.updatePlayer(GameController.interactions.getActivePlayer());
		}
		envanterBox.updateMouseEvent(mouseTracer);
	}
	
	public void flushPrevState() {
		envanterBox.flushState();
	}
	
	//paint method
	public void paint(Graphics g) {
		envanterBox.paint(g);
	}
}
