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
	private ArrayList<VisualString> draftPhaseStr;
	private ArrayList<VisualString> attackPhaseStr;
	private ArrayList<VisualString> fortifyPhaseStr;
	private ArrayList<VisualString> showCardsModeStr;
	private ArrayList<VisualString> attackModeStr;
	private ArrayList<VisualString> currentStateStrToDraw;
	private ArrayList<VisualString> borderLinesForLabels;
	
	//stringList.add(new VisualString(770, 126, 14, "Options"));
	
	public GamePanel() {
		if(GameController.activeMode == null) return;
		setLayout(null);
		setBackground(Color.BLACK);
		
		setDraftPhaseStr();
		setAttackPhaseStr();
		setFortifyPhaseStr();
		setShowCardsModeStr();
		setAttackModeStr();
		setCurrentStateStrToDraw();
		setBorderLinesForLabels();
		
		fillBorderLinesForLabels();
		fillDraftPhaseStr();
		
//		for(int i = 0; i < draftPhaseStr.size(); i++)
//			currentStateStrToDraw.add(draftPhaseStr.get(i));
		currentStateStrToDraw.add(new VisualString(1210, 875, 5, "Draft"));
		currentStateStrToDraw.add(new VisualString(1400, 875, 5, "Player 1", Color.GREEN));
		currentStateStrToDraw.add(new VisualString(1210, 925, 4, "Attack Till Capture"));
		currentStateStrToDraw.add(new VisualString(1210, 965, 4, "Attack"));
		currentStateStrToDraw.add(new VisualString(1210, 1005, 4, "Attack"));
		
		
		
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
		for(int i = 0; i < borderLinesForLabels.size(); i++)
			borderLinesForLabels.get(i).paint(painter);
		for(int i = 0; i < currentStateStrToDraw.size(); i++)
			currentStateStrToDraw.get(i).paint(painter);
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
	
	public void requestAttackButtonState(boolean activate) {
		if(activate) interactionPanel.activateAttackButton();
		else interactionPanel.deactivateAttackButton();
	}
	
	public void requestFortifyInteractionEffect(VisualTerritory focusTerritory, int effectAmount) {
		visualTerritoryPanel.requestFortifyInteractionEffect(focusTerritory, effectAmount);
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

	public void setShowCardsModeStr() {
		showCardsModeStr = new ArrayList<VisualString>();
	}

	public void setDraftPhaseStr() {
		draftPhaseStr = new ArrayList<VisualString>();
	}

	public void setAttackPhaseStr() {
		attackPhaseStr = new ArrayList<VisualString>();
	}

	public void setFortifyPhaseStr() {
		fortifyPhaseStr = new ArrayList<VisualString>();
	}

	public void setAttackModeStr() {
		attackModeStr = new ArrayList<VisualString>();
	}

	public void setCurrentStateStrToDraw() {
		currentStateStrToDraw = new ArrayList<VisualString>();
	}
	
	public void setBorderLinesForLabels() {
		borderLinesForLabels = new ArrayList<VisualString>();
	}
	
	public void fillCurrentStateStrToDraw() {
		for(int i = 0; i < borderLinesForLabels.size(); i++)
			currentStateStrToDraw.add(borderLinesForLabels.get(i));
		for(int i = 0; i < showCardsModeStr.size(); i++)
			currentStateStrToDraw.add(showCardsModeStr.get(i));
		for(int i = 0; i < draftPhaseStr.size(); i++)
			currentStateStrToDraw.add(draftPhaseStr.get(i));
		for(int i = 0; i < attackPhaseStr.size(); i++)
			currentStateStrToDraw.add(attackPhaseStr.get(i));
		for(int i = 0; i < fortifyPhaseStr.size(); i++)
			currentStateStrToDraw.add(fortifyPhaseStr.get(i));
		for(int i = 0; i < attackModeStr.size(); i++)
			currentStateStrToDraw.add(attackModeStr.get(i));
	}
	
	public void fillBorderLinesForLabels() {
		borderLinesForLabels.add(new VisualString(1200, 860, 1, "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
		borderLinesForLabels.add(new VisualString(1200, 910, 1, "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
		borderLinesForLabels.add(new VisualString(1200, 1030, 1, "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
		borderLinesForLabels.add(new VisualString(1200, 860, 1, "0"));
		borderLinesForLabels.add(new VisualString(1200, 870, 1, "0"));
		borderLinesForLabels.add(new VisualString(1200, 880, 1, "0"));
		borderLinesForLabels.add(new VisualString(1200, 890, 1, "0"));
		borderLinesForLabels.add(new VisualString(1200, 900, 1, "0"));
		borderLinesForLabels.add(new VisualString(1200, 910, 1, "0"));
		borderLinesForLabels.add(new VisualString(1200, 920, 1, "0"));
		borderLinesForLabels.add(new VisualString(1200, 930, 1, "0"));
		borderLinesForLabels.add(new VisualString(1200, 940, 1, "0"));
		borderLinesForLabels.add(new VisualString(1200, 950, 1, "0"));
		borderLinesForLabels.add(new VisualString(1200, 960, 1, "0"));
		borderLinesForLabels.add(new VisualString(1200, 970, 1, "0"));
		borderLinesForLabels.add(new VisualString(1200, 980, 1, "0"));
		borderLinesForLabels.add(new VisualString(1200, 990, 1, "0"));
		borderLinesForLabels.add(new VisualString(1200, 1000, 1, "0"));
		borderLinesForLabels.add(new VisualString(1200, 1010, 1, "0"));
		borderLinesForLabels.add(new VisualString(1200, 1020, 1, "0"));
		borderLinesForLabels.add(new VisualString(1200, 1030, 1, "0"));
		///////////////////////////////////////////////////////////////
		borderLinesForLabels.add(new VisualString(1380, 860, 1, "0"));
		borderLinesForLabels.add(new VisualString(1380, 870, 1, "0"));
		borderLinesForLabels.add(new VisualString(1380, 880, 1, "0"));
		borderLinesForLabels.add(new VisualString(1380, 890, 1, "0"));
		borderLinesForLabels.add(new VisualString(1380, 900, 1, "0"));
		borderLinesForLabels.add(new VisualString(1380, 910, 1, "0"));
		///////////////////////////////////////////////////////////////
		borderLinesForLabels.add(new VisualString(1560, 860, 1, "0"));
		borderLinesForLabels.add(new VisualString(1560, 870, 1, "0"));
		borderLinesForLabels.add(new VisualString(1560, 880, 1, "0"));
		borderLinesForLabels.add(new VisualString(1560, 890, 1, "0"));
		borderLinesForLabels.add(new VisualString(1560, 900, 1, "0"));
		borderLinesForLabels.add(new VisualString(1560, 910, 1, "0"));
		borderLinesForLabels.add(new VisualString(1560, 920, 1, "0"));
		borderLinesForLabels.add(new VisualString(1560, 930, 1, "0"));
		borderLinesForLabels.add(new VisualString(1560, 940, 1, "0"));
		borderLinesForLabels.add(new VisualString(1560, 950, 1, "0"));
		borderLinesForLabels.add(new VisualString(1560, 960, 1, "0"));
		borderLinesForLabels.add(new VisualString(1560, 970, 1, "0"));
		borderLinesForLabels.add(new VisualString(1560, 980, 1, "0"));
		borderLinesForLabels.add(new VisualString(1560, 990, 1, "0"));
		borderLinesForLabels.add(new VisualString(1560, 1000, 1, "0"));
		borderLinesForLabels.add(new VisualString(1560, 1010, 1, "0"));
		borderLinesForLabels.add(new VisualString(1560, 1020, 1, "0"));
		borderLinesForLabels.add(new VisualString(1560, 1030, 1, "0"));
	}
	
	public void fillDraftPhaseStr(){
		//System.out.println(textualInGamePanel.getActivePlayerText());
		//System.out.println(textualInGamePanel.getActivePhaseText());
		
		//draftPhaseStr.add(new VisualString(1210, 875, 5, textualInGamePanel.getPhaseLabel().getText(), Color.CYAN));
		//draftPhaseStr.add(new VisualString(1400, 875, 5, textualInGamePanel.getPlayerLabel().getText(), textualInGamePanel.getPlayerLabel().getForeground()));
		//System.out.println(textualInGamePanel.getPhaseLabel().getText());
	}
}
