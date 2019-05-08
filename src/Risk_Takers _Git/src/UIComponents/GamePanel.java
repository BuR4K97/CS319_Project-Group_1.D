package UIComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import AnimationComponents.AnimationHandler;
import Controller.GameController;
import GameAssets.GameConstants;
import HelperTools.ImageHandler;
import ModelClasses.Card;
import ModelClasses.Territory;

public class GamePanel extends DynamicPanel {

	private VisualTerritoryVisualization visualTerritoryPanel;
	private InteractionPanel interactionPanel;
	private TextualInGamePanel textualInGamePanel;
	private VisualCardPanel visualCardPanel;
	private MouseInGameListener mouseTracer;
	
	public GamePanel() {
		if(GameController.activeMode == null) return;
		setLayout(null);
		setBackground(Color.BLACK);
		
		mouseTracer = new MouseInGameListener();
		mouseTracer.initialize();
		visualTerritoryPanel = new VisualTerritoryVisualization();
		visualTerritoryPanel.initialize(mouseTracer);
		interactionPanel = new InteractionPanel();
		interactionPanel.initialize(visualTerritoryPanel);
		textualInGamePanel = new TextualInGamePanel();
		textualInGamePanel.initialize(mouseTracer);
		visualCardPanel = new VisualCardPanel();
		visualCardPanel.initialize(mouseTracer);

		visualTerritoryPanel.insertMouseListeners(this);
		textualInGamePanel.insertLabels(this);
		interactionPanel.insertButtons(this);
	}

	public void initializeAttackScenario(Territory[] combatTerritories) {
		interactionPanel.activateCombatMode();
		visualTerritoryPanel.inCombatMode(combatTerritories);
	}

	public void terminateAttackScenario() {
		interactionPanel.deactivateCombatMode();
		visualTerritoryPanel.outCombatMode();
	}

	public void initializeCardMode() {
		interactionPanel.activateCardMode();
		visualCardPanel.inCardMode();
	}

	public void terminateCardMode() {
		interactionPanel.deactivateCardMode();
		visualCardPanel.outCardMode();
	}

	public void paintComponent(Graphics painter) {
		super.paintComponent(painter);
		painter.setFont(new Font("pixel", Font.BOLD, 20));
		visualTerritoryPanel.paint(painter);
		visualCardPanel.paint(painter);
		
		//call textual ingamepanel paint method
		textualInGamePanel.paint(painter);
	}

	public void update() {
		visualTerritoryPanel.update();
		textualInGamePanel.update();
		visualCardPanel.update();
		
		mouseTracer.mouseReleased = false;
		mouseTracer.mousePressed = false;
		mouseTracer.mouseClicked = false;
		mouseTracer.leftButtonClicked = false;
		mouseTracer.rightButtonClicked = false;
	}

	public void destroy() {
		visualTerritoryPanel.destroy();
	}
	
	public ArrayList<VisualCard> getFocusVisualCards() {
		return visualCardPanel.getFocusVisualCards();
	}

	public void setStringList() {
		//this.interactionStringList = new ArrayList<VisualString>();
	}
	
}
