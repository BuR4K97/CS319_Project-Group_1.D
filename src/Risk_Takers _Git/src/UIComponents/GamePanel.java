package UIComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.KeyStroke;

import AnimationComponents.AnimationHandler;
import Controller.GameController;
import GameAssets.SoundConstants;
import ModelClasses.Territory;
import javax.swing.JLabel;

public class GamePanel extends DynamicPanel {

	private static enum PANEL_STATES {NORMAL, ATTACK, CARD}
	
	private PANEL_STATES currState;
	private VisualTerritoryPanel visualTerritoryPanel;
	private InteractionPanel interactionPanel;
	private TextualInGamePanel textualInGamePanel;
	private VisualCardPanel visualCardPanel;
	private MouseInGameListener mouseTracer;
	public boolean escPressed;
	
	private JLabel inGameMenuLabel;
	private JLabel mainMenuLabel;
	private JLabel backToGameLabel;
	private JLabel quitLabel;
	
	//private JLabel quitInGame;
	
	public GamePanel() {
		
		if(GameController.activeMode == null) return;
		this.setPreferredSize(new Dimension(1920, 1080));
		setLayout(null);
		setBackground(Color.BLACK);
		setFocusable(true);
		requestFocusInWindow();
		
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
		

		this.addMouseListener(mouseTracer);
		this.addMouseMotionListener(mouseTracer);
		textualInGamePanel.insertLabels(this);
		interactionPanel.insertButtons(this);
		
		
		inGameMenuLabel = new JLabel("");
		inGameMenuLabel.setFont(new Font("Baskerville Old Face", Font.BOLD, 50));
		inGameMenuLabel.setBounds(0, 0, 30, 30);
		inGameMenuLabel.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {
				SoundConstants.menuMouseOnButtonSound();
			}
			public void mouseClicked(MouseEvent e) {
				if (escPressed)
					escPressed = false;
				else
					escPressed = true;
				SoundConstants.snapSound();
			}
		});	
		add(inGameMenuLabel);
		
//		mainMenuLabel = new JLabel("MAIN MENU");
//		mainMenuLabel.setFont(new Font("pixel", Font.BOLD, 50));
//		mainMenuLabel.setForeground(Color.WHITE);
//		mainMenuLabel.setBounds(800, 340, 300, 51);
//		add(mainMenuLabel);
//		
//		backToGameLabel = new JLabel("BACK TO RISK");
//		backToGameLabel.setFont(new Font("pixel", Font.BOLD, 50));
//		backToGameLabel.setForeground(Color.WHITE);
//		backToGameLabel.setBounds(780, 520, 400, 51);
//		add(backToGameLabel);
//		
//		quitLabel = new JLabel("QUIT");
//		quitLabel.setFont(new Font("pixel", Font.BOLD, 50));
//		quitLabel.setForeground(Color.WHITE);
//		quitLabel.setBounds(890, 700, 200, 51);
//		add(quitLabel);
		
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
	
	int inGameSettingsPanelWidth = 800;
	int inGameSettingsPanelHeight = 500;
	public void paintComponent(Graphics painter) {
		super.paintComponent(painter);
		
		visualTerritoryPanel.paint(painter);
		if(!suspendVisualCardPanelPaintEvent())
			visualCardPanel.paint(painter);
		if(!suspendTextualInGamePanelPaintEvent())
			textualInGamePanel.paint(painter);
		painter.setColor(Color.white);
		painter.fillRect(0, 0, 30, 5);
		painter.fillRect(0, 12, 30, 5);
		painter.fillRect(0, 25, 30, 5);
		
		if(escPressed) {
			painter.setColor(new Color(0, 0, 0, 220));
			painter.fillRect(1920/2 - inGameSettingsPanelWidth / 2, 1080 / 2 - inGameSettingsPanelHeight / 2, inGameSettingsPanelWidth, inGameSettingsPanelHeight);
			painter.setColor(Color.CYAN);
			painter.drawRect(1920/2 - inGameSettingsPanelWidth / 2, 1080 / 2 - inGameSettingsPanelHeight / 2, inGameSettingsPanelWidth, inGameSettingsPanelHeight);
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
	
	public void requestNotification(String message) {
		textualInGamePanel.requestNotification(message);
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
	
	public void requestVisualDeviationEffect(VisualTerritory focusTerritory, int effectAmount) {
		visualTerritoryPanel.requestVisualDeviationEffect(focusTerritory, effectAmount);
	}
	
	public void requestPushIntoVisualTerritoryPanelSelectableTerritory(VisualTerritory push) {
		visualTerritoryPanel.requestPushIntoSelectableTerritory(push);
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
}
