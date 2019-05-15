package UIComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import AnimationComponents.AnimationHandler;
import Controller.GameController;
import Controller.GameInteractions;
import Controller.MainApplication;
import GameAssets.SoundConstants;
import ModelClasses.Card;
import ModelClasses.Territory;

public class InteractionPanel {

	private JLabel nextPhaseLabel;
	private JLabel attackRequestLabel;
	private JLabel attackTillCapture;
	private JLabel attackPerRoll;
	private JLabel terminateAttack;
	private JLabel cardButton;
	private JLabel activateCards;
	private JLabel cardModeBackButton;
	private JLabel inGameMenuLabel;
	private JLabel mainMenuLabel;
	private JLabel backToGameLabel;
	private JLabel quitLabel;
	private int yAxis = 1013;
	
	private int nextPhaseLabelX = 925;
	private int cardButtonX = 1175;
	private int attackRequestLabelX = 1430;
	
	private int attackTillCaptureX = 900;
	private int attackPerRollX = 1270;
	private int terminateAttackX = 1565;
	
	private int activateCardsX = 925;
	private int cardModeBackButtonX = 1200;

	public void initialize() {

		nextPhaseLabel = new JLabel("Next Phase");
		nextPhaseLabel.setBounds(nextPhaseLabelX, yAxis, 185, 29);
		nextPhaseLabel.setForeground(Color.LIGHT_GRAY);
		nextPhaseLabel.setBackground(new Color(0, 0, 0, 0));
		nextPhaseLabel.setFont(new Font("pixel", Font.PLAIN, 32));

		attackRequestLabel = new JLabel("Attack");
		attackRequestLabel.setBounds(attackRequestLabelX, yAxis, 235, 29);
		attackRequestLabel.setForeground(Color.LIGHT_GRAY);
		attackRequestLabel.setBackground(new Color(0, 0, 0, 0));
		attackRequestLabel.setFont(new Font("pixel", Font.PLAIN, 32));

		nextPhaseLabel.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {
				SoundConstants.menuMouseOnButtonSound();
			}
			public void mouseClicked(MouseEvent e) {
				GameController.interactions.synchronizeFocusTerritories(null, null);
				GameController.interactions.requestNextPhase();
				((GamePanel)MainApplication.frame.focusPanel).requestFlushVisualTerritoryPanel();
				((GamePanel)MainApplication.frame.focusPanel).requestFlushTextualInGamePanel();
				SoundConstants.gameMouseClickSound();
			}
		});

		attackRequestLabel.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {
				SoundConstants.menuMouseOnButtonSound();
			}
			public void mouseClicked(MouseEvent e) {
				GameController.interactions.requestAction(0);
				((GamePanel)MainApplication.frame.focusPanel).requestFlushVisualTerritoryPanel();
				SoundConstants.gameMouseClickSound();
			}
		});

		attackTillCapture = new JLabel("Attack Till Capture");
		attackTillCapture.setBounds(attackTillCaptureX, yAxis, 315, 29);
		attackTillCapture.setForeground(Color.LIGHT_GRAY);
		attackTillCapture.setBackground(new Color(0, 0, 0, 0));
		attackTillCapture.setFont(new Font("pixel", Font.PLAIN, 32));

		attackPerRoll = new JLabel("Attack Per Roll");
		attackPerRoll.setBounds(attackPerRollX, yAxis, 255, 29);
		attackPerRoll.setForeground(Color.LIGHT_GRAY);
		attackPerRoll.setBackground(new Color(0, 0, 0, 0));
		attackPerRoll.setFont(new Font("pixel", Font.PLAIN, 32));

		terminateAttack = new JLabel("Terminate Attack");
		terminateAttack.setBounds(terminateAttackX, yAxis, 280, 29);
		terminateAttack.setForeground(Color.LIGHT_GRAY);
		terminateAttack.setBackground(new Color(0, 0, 0, 0));
		terminateAttack.setFont(new Font("pixel", Font.PLAIN, 32));

		attackTillCapture.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {
				SoundConstants.menuMouseOnButtonSound();
			}
			public void mouseClicked(MouseEvent e) {
				GameController.interactions.requestAttackTillCapture();
				SoundConstants.multiDiceSound();
			}
		});

		attackPerRoll.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {
				SoundConstants.menuMouseOnButtonSound();
			}
			public void mouseClicked(MouseEvent e) {
				GameController.interactions.requestAttackPerRoll();
				SoundConstants.singleDiceSound();
			}
		});

		terminateAttack.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {
				SoundConstants.menuMouseOnButtonSound();
			}
			public void mouseClicked(MouseEvent e) {
				GameController.interactions.terminateCombat();
				SoundConstants.gameMouseClickSound();
			}
		});

		attackTillCapture.setVisible(false);
		attackTillCapture.setEnabled(false);
		attackPerRoll.setVisible(false);
		attackPerRoll.setEnabled(false);
		terminateAttack.setVisible(false);
		terminateAttack.setEnabled(false);

		cardButton = new JLabel("Show Cards");
		cardButton.setBounds(cardButtonX, yAxis, 285, 29);
		cardButton.setForeground(Color.LIGHT_GRAY);
		cardButton.setBackground(new Color(0, 0, 0, 0));
		cardButton.setFont(new Font("pixel", Font.PLAIN, 32));
		cardButton.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {
				SoundConstants.menuMouseOnButtonSound();
			}
			public void mouseClicked(MouseEvent e) {
				((GamePanel)MainApplication.frame.focusPanel).initializeCardMode();
				((GamePanel)MainApplication.frame.focusPanel).requestFlushVisualTerritoryPanel();
				((GamePanel)MainApplication.frame.focusPanel).requestFlushTextualInGamePanel();
				SoundConstants.gameMouseClickSound();
			}
		});

		activateCards = new JLabel("Activate Cards");
		activateCards.setBounds(activateCardsX, yAxis, 235, 29);
		activateCards.setForeground(Color.LIGHT_GRAY);
		activateCards.setBackground(new Color(0, 0, 0, 0));
		activateCards.setFont(new Font("pixel", Font.PLAIN, 32));

		cardModeBackButton = new JLabel("Back");
		cardModeBackButton.setBounds(cardModeBackButtonX, yAxis, 265, 29);
		cardModeBackButton.setForeground(Color.LIGHT_GRAY);
		cardModeBackButton.setBackground(new Color(0, 0, 0, 0));
		cardModeBackButton.setFont(new Font("pixel", Font.PLAIN, 32));

		activateCards.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {
				SoundConstants.menuMouseOnButtonSound();
			}
			public void mouseClicked(MouseEvent e) {
				if(GameInteractions.requestCardActivation(((GamePanel)MainApplication.frame.focusPanel).getFocusVisualCards()))
					((GamePanel)MainApplication.frame.focusPanel).requestFlushVisualCardPanel();
				SoundConstants.gameMouseClickSound();
			}
		});

		cardModeBackButton.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {
				SoundConstants.menuMouseOnButtonSound();
			}
			public void mouseClicked(MouseEvent e) {
				((GamePanel)MainApplication.frame.focusPanel).terminateCardMode();
				((GamePanel)MainApplication.frame.focusPanel).requestFlushVisualCardPanel();
				SoundConstants.gameMouseClickSound();
			}
		});
		activateCards.setVisible(false);
		activateCards.setEnabled(false);
		cardModeBackButton.setVisible(false);
		cardModeBackButton.setEnabled(false);
		
		
		// In Game Menu Labels
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
				GamePanel.stringList = new ArrayList<VisualString>();
				GamePanel.stringList.add(new VisualString(790, 340, 10, "Main Menu"));
				GamePanel.stringList.add(new VisualString(730, 520, 10, "Back to Risk"));
				GamePanel.stringList.add(new VisualString(890, 700, 10, "Quit"));
				if (GamePanel.inGameMenuLabelPressed)
					GamePanel.inGameMenuLabelPressed = false;
				else
					GamePanel.inGameMenuLabelPressed = true;
				SoundConstants.snapSound();
			}
		});
		
		mainMenuLabel = new JLabel("");
		mainMenuLabel.setFont(new Font("pixel", Font.BOLD, 50));
		mainMenuLabel.setForeground(Color.WHITE);
		mainMenuLabel.setBounds(790, 340, 348, 51);
		mainMenuLabel.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {
				SoundConstants.menuMouseOnButtonSound();
			}
			public void mouseClicked(MouseEvent e) {
				MainApplication.destroyGame();
				MainApplication.initializeMenu();
				GamePanel.inGameMenuLabelPressed = false;
				SoundConstants.snapSound();
			}
		});
		
		backToGameLabel = new JLabel("");
		backToGameLabel.setFont(new Font("pixel", Font.BOLD, 50));
		backToGameLabel.setForeground(Color.WHITE);
		backToGameLabel.setBounds(730, 520, 470, 51);
		backToGameLabel.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {
				SoundConstants.menuMouseOnButtonSound();
			}
			public void mouseClicked(MouseEvent e) {
				GamePanel.inGameMenuLabelPressed = false;
				SoundConstants.snapSound();
			}
		});
		
		quitLabel = new JLabel("");
		quitLabel.setFont(new Font("pixel", Font.BOLD, 50));
		quitLabel.setForeground(Color.WHITE);
		quitLabel.setBounds(890, 700, 150, 51);
		quitLabel.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {
				SoundConstants.menuMouseOnButtonSound();
			}
			public void mouseClicked(MouseEvent e) {
				GamePanel.inGameMenuLabelPressed = false;
				SoundConstants.snapSound();
				System.exit(1);
			}
		});	
	}

	public void insertButtons(JPanel target) {
		target.add(nextPhaseLabel);
		target.add(attackRequestLabel);
		target.add(attackTillCapture);
		target.add(attackPerRoll);
		target.add(terminateAttack);
		target.add(cardButton);
		target.add(activateCards);
		target.add(cardModeBackButton);
		target.add(inGameMenuLabel);
		target.add(mainMenuLabel);
		target.add(backToGameLabel);
		target.add(quitLabel);
	}

	public void activateCombatMode() {
		nextPhaseLabel.setEnabled(false);
		nextPhaseLabel.setVisible(false);
		attackRequestLabel.setEnabled(false);
		attackRequestLabel.setVisible(false);
		cardButton.setEnabled(false);
		cardButton.setVisible(false);
		attackTillCapture.setVisible(true);
		attackTillCapture.setEnabled(true);
		attackPerRoll.setVisible(true);
		attackPerRoll.setEnabled(true);
		terminateAttack.setVisible(true);
		terminateAttack.setEnabled(true);
	}

	public void deactivateCombatMode() {
		nextPhaseLabel.setEnabled(true);
		nextPhaseLabel.setVisible(true);
		attackRequestLabel.setEnabled(true);
		attackRequestLabel.setVisible(true);
		cardButton.setEnabled(true);
		cardButton.setVisible(true);
		attackTillCapture.setVisible(false);
		attackTillCapture.setEnabled(false);
		attackPerRoll.setVisible(false);
		attackPerRoll.setEnabled(false);
		terminateAttack.setVisible(false);
		terminateAttack.setEnabled(false);
	}

	public void activateCardMode() {
		nextPhaseLabel.setEnabled(false);
		nextPhaseLabel.setVisible(false);
		attackRequestLabel.setEnabled(false);
		attackRequestLabel.setVisible(false);
		cardButton.setEnabled(false);
		cardButton.setVisible(false);
		activateCards.setVisible(true);
		activateCards.setEnabled(true);
		cardModeBackButton.setVisible(true);
		cardModeBackButton.setEnabled(true);
	}

	public void deactivateCardMode() {
		nextPhaseLabel.setEnabled(true);
		nextPhaseLabel.setVisible(true);
		attackRequestLabel.setEnabled(true);
		attackRequestLabel.setVisible(true);
		cardButton.setEnabled(true);
		cardButton.setVisible(true);
		activateCards.setVisible(false);
		activateCards.setEnabled(false);
		cardModeBackButton.setVisible(false);
		cardModeBackButton.setEnabled(false);
	}
	
	public void activateAttackButton() {
		attackRequestLabel.setEnabled(true);
		attackRequestLabel.setVisible(true);
	}
	
	public void deactivateAttackButton() {
		attackRequestLabel.setEnabled(false);
		attackRequestLabel.setVisible(false);
	}
	
	public void updateInGameLabels() {
		if(GamePanel.inGameMenuLabelPressed) {
			mainMenuLabel.setEnabled(true);
			backToGameLabel.setEnabled(true);
			quitLabel.setEnabled(true);
		}
		else {
			mainMenuLabel.setEnabled(false);
			backToGameLabel.setEnabled(false);
			quitLabel.setEnabled(false);
		}
	}

}