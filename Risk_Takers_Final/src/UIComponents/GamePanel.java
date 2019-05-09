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
	private InGameSettingsPanel inGameSettingsPanel;
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
		
		fillDraftPhaseStr();
		
//		for(int i = 0; i < draftPhaseStr.size(); i++)
//			currentStateStrToDraw.add(draftPhaseStr.get(i));
		currentStateStrToDraw.add(new VisualString(1210, 875, 5, "Fortify"));
		currentStateStrToDraw.add(new VisualString(1400, 875, 5, "Player 1", Color.GREEN));
		currentStateStrToDraw.add(new VisualString(1210, 925, 4, "Attack Till Capture"));
		currentStateStrToDraw.add(new VisualString(1210, 965, 4, "Attack"));
		currentStateStrToDraw.add(new VisualString(1210, 1005, 4, "Attack"));
		
		inGameSettingsPanel = new InGameSettingsPanel();
		
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
		if(!suspendVisualCardPanelPaintEvent())
			visualCardPanel.paint(painter);
		if(!suspendTextualInGamePanelPaintEvent()) {
			textualInGamePanel.paint(painter);
			drawStringBorders(painter);
			for(int i = 0; i < currentStateStrToDraw.size(); i++)
				currentStateStrToDraw.get(i).paint(painter);
		}
//		int width, height;
//		width = 800;
//		height = 400;
//		painter.setColor(new Color(0, 0, 0, 170));
//		painter.fillRect(1920/2 - width/2, 1080/2 - height/2, width, height);
//		painter.setColor(Color.CYAN);
//		painter.drawRect(1920/2 - width/2, 1080/2 - height/2, width, height);
	}
	
	public void drawStringBorders(Graphics g) {
		int xCor = 1200, yCor = 860;
		for(int i = 0; i < 46; i++) {
			g.drawRect(xCor, yCor, 4, 4);
			xCor += 8;
		}
		xCor = 1204; yCor = 910;
		for(int i = 0; i < 45; i++) {
			g.drawRect(xCor, yCor, 4, 4);
			xCor += 8;
		}
		xCor = 1200; yCor = 1030;
		for(int i = 0; i < 46; i++) {
			g.drawRect(xCor, yCor, 4, 4);
			xCor += 8;
		}
		xCor = 1200; yCor = 866;
		for(int i = 0; i < 33; i++) {
			g.drawRect(xCor, yCor, 2, 2);
			yCor += 5;
		}
		xCor = 1380; yCor = 866;
		for(int i = 0; i < 9; i++) {
			g.drawRect(xCor, yCor, 2, 2);
			yCor += 5;
		}
		xCor = 1562; yCor = 866;
		for(int i = 0; i < 33; i++) {
			g.drawRect(xCor, yCor, 2, 2);
			yCor += 5;
		}
		
	}

	public void update() {
		if(!suspendVisualTerritoryPanelUpdate()) visualTerritoryPanel.update();
		if(!suspendTextualInGamePanelUpdate()) textualInGamePanel.update();
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
	
	private boolean suspendTextualInGamePanelUpdate() {
		if(currState != PANEL_STATES.NORMAL)
			return true;
		return AnimationHandler.suspendTextualInGamePanelUpdate();
	}
	
	private boolean suspendTextualInGamePanelPaintEvent() {
		if(currState != PANEL_STATES.NORMAL)
			return true;
		return AnimationHandler.suspendTextualInGamePanelPaintEvent();
	}
	
	private boolean suspendVisualCardPanelPaintEvent() {
		if(currState != PANEL_STATES.CARD)
			return true;
		return AnimationHandler.suspendVisualCardPanelPaintEvent();
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
	
	public void fillCurrentStateStrToDraw() {
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
	
	public void fillDraftPhaseStr(){
		//System.out.println(textualInGamePanel.getActivePlayerText());
		//System.out.println(textualInGamePanel.getActivePhaseText());
		
		//draftPhaseStr.add(new VisualString(1210, 875, 5, textualInGamePanel.getPhaseLabel().getText(), Color.CYAN));
		//draftPhaseStr.add(new VisualString(1400, 875, 5, textualInGamePanel.getPlayerLabel().getText(), textualInGamePanel.getPlayerLabel().getForeground()));
		//System.out.println(textualInGamePanel.getPhaseLabel().getText());
	}
}
