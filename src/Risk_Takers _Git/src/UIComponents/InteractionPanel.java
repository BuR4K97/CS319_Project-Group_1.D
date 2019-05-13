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

	public void initialize() {

		nextPhaseLabel = new JLabel("Next Phase");
		nextPhaseLabel.setBounds(815, 1013, 185, 29);
		nextPhaseLabel.setForeground(Color.GRAY);
		nextPhaseLabel.setBackground(new Color(0, 0, 0, 0));
		nextPhaseLabel.setFont(new Font("Calibri", Font.BOLD, 32));

		attackRequestLabel = new JLabel("Attack");
		attackRequestLabel.setBounds(985, 1013, 235, 29);
		attackRequestLabel.setForeground(Color.GRAY);
		attackRequestLabel.setBackground(new Color(0, 0, 0, 0));
		attackRequestLabel.setFont(new Font("Calibri", Font.BOLD, 32));

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
		attackTillCapture.setBounds(815, 1013, 285, 29);
		attackTillCapture.setForeground(Color.GRAY);
		attackTillCapture.setBackground(new Color(0, 0, 0, 0));
		attackTillCapture.setFont(new Font("Calibri", Font.BOLD, 32));

		attackPerRoll = new JLabel("Attack Per Roll");
		attackPerRoll.setBounds(1090, 1013, 230, 29);
		attackPerRoll.setForeground(Color.GRAY);
		attackPerRoll.setBackground(new Color(0, 0, 0, 0));
		attackPerRoll.setFont(new Font("Calibri", Font.BOLD, 32));

		terminateAttack = new JLabel("Terminate Attack");
		terminateAttack.setBounds(1305, 1013, 265, 29);
		terminateAttack.setForeground(Color.GRAY);
		terminateAttack.setBackground(new Color(0, 0, 0, 0));
		terminateAttack.setFont(new Font("Calibri", Font.BOLD, 32));

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
		cardButton.setBounds(1320, 1013, 285, 29);
		cardButton.setForeground(Color.GRAY);
		cardButton.setBackground(new Color(0, 0, 0, 0));
		cardButton.setFont(new Font("Calibri", Font.BOLD, 32));
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
		activateCards.setBounds(1090, 1013, 230, 29);
		activateCards.setForeground(Color.GRAY);
		activateCards.setBackground(new Color(0, 0, 0, 0));
		activateCards.setFont(new Font("Calibri", Font.BOLD, 32));

		cardModeBackButton = new JLabel("Back");
		cardModeBackButton.setBounds(1305, 1013, 265, 29);
		cardModeBackButton.setForeground(Color.GRAY);
		cardModeBackButton.setBackground(new Color(0, 0, 0, 0));
		cardModeBackButton.setFont(new Font("Calibri", Font.BOLD, 32));

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

}