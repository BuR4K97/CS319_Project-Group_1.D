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
import Controller.GameInteractions;
import ModelClasses.Turn;
import ModelClasses.Turn.TURN_PHASE;
import UIComponents.EnvanterBoxUI.EnvanterBox;

public class TextualInGamePanel {

	private JLabel phaseLabel;
	private JLabel playerLabel;
	public EnvanterBox envanterBox;
	private FortifyInteraction foritfyInteraction;
	private MouseInGameListener mouseTracer;

	public void initialize(MouseInGameListener mouseTracer) {
		phaseLabel = new JLabel();
		phaseLabel.setFont(new Font("pixel", Font.PLAIN, 32));
		phaseLabel.setForeground(Color.LIGHT_GRAY);
		phaseLabel.setBounds(575, 1013, 116, 29);
		
		playerLabel = new JLabel();
		playerLabel.setFont(new Font("pixel", Font.PLAIN, 32));
		playerLabel.setBounds(725, 1013, 150, 29);
				
		envanterBox = new EnvanterBox();
		foritfyInteraction = new FortifyInteraction();
		
		this.mouseTracer = mouseTracer;
	}

	public void insertLabels(JPanel target) {
		target.add(phaseLabel);
		target.add(playerLabel);
	}

	public void update() {
		if(GameController.interactions.getTextualPanelUpdateRequest()) {
			phaseLabel.setText(GameInteractions.getActivePhase().toString());
			playerLabel.setText(GameInteractions.getActivePlayer().toString());
			Color playerColor = GameInteractions.getActivePlayer().getColor();
			playerLabel.setForeground(new Color(playerColor.getRed(), playerColor.getGreen(), playerColor.getBlue(), 255));
			envanterBox.updatePlayer(GameInteractions.getActivePlayer());
		}
		if(!suspendEnvanterBox())
			envanterBox.updateMouseEvent(mouseTracer);
		if(!suspendFortifyInteraction())
			foritfyInteraction.update(mouseTracer);
	}
	
	public void flushPrevState() {
		envanterBox.flushState();
		foritfyInteraction.flushState();
	}
	
	public void paint(Graphics painter) {
		envanterBox.paint(painter);
		foritfyInteraction.paint(painter);
	}
	
	private boolean suspendEnvanterBox() {
		return GameInteractions.getActivePhase() != TURN_PHASE.DRAFT;
	}
	
	private boolean suspendFortifyInteraction() {
		return GameInteractions.getActivePhase() != TURN_PHASE.FORTIFY;
	}

	public JLabel getPhaseLabel() {
		return phaseLabel;
	}

	public JLabel getPlayerLabel() {
		return playerLabel;
	}
}
