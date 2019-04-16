package UIComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import AnimationComponents.AnimationHandler;
import Controller.GameController;
import GameAssets.GameConstants;
import HelperTools.ImageHandler;
import ModelClasses.Territory;

public class GamePanel extends DynamicPanel {
	
	private VisualTerritoryVisualization visualTerritoryPanel;
	private InteractionPanel interactionPanel;
	private TextualInGamePanel textualInGamePanel;
	private BufferedImage backgroundTexture;
	
	public GamePanel() {
		if(GameController.activeMode == null) return;
		
		setBackground(Color.BLACK);
		backgroundTexture = GameConstants.backgroundTexture;
		
		visualTerritoryPanel = new VisualTerritoryVisualization();
		visualTerritoryPanel.initialize();
		interactionPanel = new InteractionPanel();
		interactionPanel.initialize(visualTerritoryPanel.getFocusTerritories());
		textualInGamePanel = new TextualInGamePanel();
		textualInGamePanel.initialize();
		
		attackTillCapture = new JButton("Attack TillCapture");
		attackPerRoll = new JButton("Attack PerRoll");
		terminateAttack = new JButton("Terminate Attack");
		attackTillCapture.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GameController.interactions.requestAttackTillCapture();
			}
			
		});
		attackPerRoll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GameController.interactions.requestAttackPerRoll();
			}
			
		});
		terminateAttack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GameController.interactions.terminateCombat();
			}
		
		});
		attackTillCapture.setVisible(false);
		attackTillCapture.setEnabled(false);
		attackPerRoll.setVisible(false);
		attackPerRoll.setEnabled(false);
		terminateAttack.setVisible(false);
		terminateAttack.setEnabled(false);
		
		visualTerritoryPanel.insertMouseListeners(this);
		textualInGamePanel.insertLabels(this);
		interactionPanel.insertButtons(this);
		add(attackTillCapture) ;
		add(attackPerRoll);
		add(terminateAttack);
	}
	
	private JButton attackTillCapture; 
	private JButton attackPerRoll;
	private JButton terminateAttack;
	public void initializeAttackScenario(Territory[] combatTerritories) {
		interactionPanel.deactivate();
		attackTillCapture.setVisible(true);
		attackTillCapture.setEnabled(true);
		attackPerRoll.setVisible(true);
		attackPerRoll.setEnabled(true);
		terminateAttack.setVisible(true);
		terminateAttack.setEnabled(true);
		
		visualTerritoryPanel.inCombatMode(combatTerritories);
	}
	
	public void terminateAttackScenario() {
		interactionPanel.activate();
		attackTillCapture.setVisible(false);
		attackTillCapture.setEnabled(false);
		attackPerRoll.setVisible(false);
		attackPerRoll.setEnabled(false);
		terminateAttack.setVisible(false);
		terminateAttack.setEnabled(false);
			
		visualTerritoryPanel.outCombatMode();
	}
	
	public void paintComponent(Graphics painter) {
		super.paintComponent(painter);
		//painter.drawImage(backgroundTexture, 0, 0, this);
		visualTerritoryPanel.paint(painter);
	}
	
	public void update() {
		visualTerritoryPanel.update();
		if(interactionPanel.getTextualPanelUpdateRequest())
			textualInGamePanel.update(); 
	}
	
	public void destroy() {
		visualTerritoryPanel.destroy();
	}
	
}
