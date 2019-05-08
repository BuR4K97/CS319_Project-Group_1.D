package UIComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import AnimationComponents.AnimationHandler;
import Controller.GameController;
import ModelClasses.Territory;

public class GamePanel extends DynamicPanel {

	private static enum PANEL_STATES {NORMAL, ATTACK, CARD}
	
	private PANEL_STATES currState;
	private VisualTerritoryPanel visualTerritoryPanel;
	private InteractionPanel interactionPanel;
	private TextualInGamePanel textualInGamePanel;
	private VisualCardPanel visualCardPanel;
	private MouseInGameListener mouseTracer;
	
	public GamePanel() {
		if(GameController.activeMode == null) return;
		setLayout(null);
		setBackground(Color.BLACK);
		
		currState = PANEL_STATES.NORMAL;
		mouseTracer = new MouseInGameListener();
		mouseTracer.initialize();
		visualTerritoryPanel = new VisualTerritoryPanel();
		visualTerritoryPanel.initialize(mouseTracer);
		interactionPanel = new InteractionPanel();
		interactionPanel.initialize();
		textualInGamePanel = new TextualInGamePanel();
		textualInGamePanel.initialize(mouseTracer);
		visualCardPanel = new VisualCardPanel();
		visualCardPanel.initialize(mouseTracer);

		visualTerritoryPanel.insertMouseListeners(this);
		textualInGamePanel.insertLabels(this);
		interactionPanel.insertButtons(this);
	}

	public void initializeAttackScenario(Territory[] combatTerritories) {
		currState = PANEL_STATES.ATTACK;
		interactionPanel.activateCombatMode();
		visualTerritoryPanel.inCombatMode(combatTerritories);
	}

	public void terminateAttackScenario() {
		currState = PANEL_STATES.NORMAL;
		interactionPanel.deactivateCombatMode();
		visualTerritoryPanel.outCombatMode();
	}

	public void initializeCardMode() {
		currState = PANEL_STATES.CARD;
		interactionPanel.activateCardMode();
		visualCardPanel.update();
	}

	public void terminateCardMode() {
		currState = PANEL_STATES.NORMAL;
		interactionPanel.deactivateCardMode();
	}

	public void paintComponent(Graphics painter) {
		super.paintComponent(painter);
		visualTerritoryPanel.paint(painter);
		if(currState == PANEL_STATES.CARD)
			visualCardPanel.paint(painter);
		if(currState == PANEL_STATES.NORMAL)
			textualInGamePanel.paint(painter);
	}

	public void update() {
		if(!suspendVisualTerritoryPanelUpdate()) visualTerritoryPanel.update();
		if(!suspendTextualInGamePanel()) textualInGamePanel.update();
		if(!suspendVisualCardPanelUpdate()) visualCardPanel.update();
		
		mouseTracer.mouseReleased = false;
		mouseTracer.mousePressed = false;
		mouseTracer.mouseClicked = false;
		mouseTracer.leftButtonClicked = false;
		mouseTracer.rightButtonClicked = false;
	}
	
	public void requestFlushVisualTerritoryPanel() {
		visualTerritoryPanel.flushPrevState();
	}
	
	public void requestFlushVisualCardPanel() {
		visualCardPanel.flushPrevState();
	}
	
	public void requestFlushTextualInGamePanel() {
		textualInGamePanel.flushPrevState();
	}

	public void destroy() {
		visualTerritoryPanel.destroy();
	}
	
	public VisualTerritory[] getFocusVisualTerritories() {
		return visualTerritoryPanel.getFocusTerritories();
	}
	
	public ArrayList<VisualCard> getFocusVisualCards() {
		return visualCardPanel.getFocusVisualCards();
	}

	public void setStringList() {
		//this.interactionStringList = new ArrayList<VisualString>();
	}
	
	private boolean suspendVisualTerritoryPanelUpdate() {
		if(currState != PANEL_STATES.NORMAL)
			return true;
		return AnimationHandler.suspendVisualTerritoryPanel();
	}
	
	private boolean suspendVisualCardPanelUpdate() {
		if(currState != PANEL_STATES.CARD)
			return true;
		return false;
	}
	
	private boolean suspendTextualInGamePanel() {
		if(currState != PANEL_STATES.NORMAL)
			return true;
		return false;
	}
	
}
