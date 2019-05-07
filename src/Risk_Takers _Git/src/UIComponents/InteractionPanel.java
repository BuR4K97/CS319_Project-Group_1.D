package UIComponents;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import AnimationComponents.AnimationHandler;
import Controller.GameController;
import Controller.GameInteractions;
import Controller.MainApplication;
import ModelClasses.Card;
import ModelClasses.Territory;

public class InteractionPanel {
	
	private JButton nextPhaseButton;
	private JButton actionRequestButton;
	private JTextField actionAmountField;
	private JButton attackTillCapture; 
	private JButton attackPerRoll;
	private JButton terminateAttack;
	private JButton cardButton;
	private JButton activateCards;
	private JButton cardModeBackButton;
	
	public void initialize(VisualTerritoryVisualization visualTerritoryPanel) {
		nextPhaseButton = new JButton("Next Phase");
		actionRequestButton = new JButton("Action Request");
		actionAmountField = new JTextField();
		
		nextPhaseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GameController.interactions.requestNextPhase();
				AnimationHandler.terminateMouseOnTerritoryAnimation(visualTerritoryPanel.getFocusTerritories()[0]);
				AnimationHandler.terminateMouseOnTerritoryAnimation(visualTerritoryPanel.getFocusTerritories()[1]);
				visualTerritoryPanel.getFocusTerritories()[0] = null; visualTerritoryPanel.getFocusTerritories()[1] = null;
			}
		});
		
		actionRequestButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int actionAmount = Integer.parseInt(actionAmountField.getText());
					GameController.interactions.requestAction(actionAmount);
					AnimationHandler.terminateMouseOnTerritoryAnimation(visualTerritoryPanel.getFocusTerritories()[0]);
					AnimationHandler.terminateMouseOnTerritoryAnimation(visualTerritoryPanel.getFocusTerritories()[1]);
					visualTerritoryPanel.getFocusTerritories()[0] = null; visualTerritoryPanel.getFocusTerritories()[1] = null;
				}
				catch(NumberFormatException exception) {
					System.out.println("Enter an action amount!!!");
				}
			}
		});
		
		actionAmountField.setPreferredSize(new Dimension(90, 30));
		
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
		
		cardButton = new JButton("Show Cards");
		cardButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				((GamePanel)MainApplication.frame.focusPanel).initializeCardMode();
			}
			
		});
		
		activateCards = new JButton("Activate Cards");
		activateCards.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
		cardModeBackButton = new JButton("Back");
		cardModeBackButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				((GamePanel)MainApplication.frame.focusPanel).terminateCardMode();
			}
			
		});
		activateCards.setVisible(false);
		activateCards.setEnabled(false);
		cardModeBackButton.setVisible(false);
		cardModeBackButton.setEnabled(false);
		
	}
	
	public void insertButtons(JPanel target) {
		target.add(nextPhaseButton);
		target.add(actionRequestButton);
		target.add(actionAmountField);
		target.add(attackTillCapture);
		target.add(attackPerRoll);
		target.add(terminateAttack);
		target.add(cardButton);
		target.add(activateCards);
		target.add(cardModeBackButton);
	}
	
	public void activateCombatMode() {
		nextPhaseButton.setEnabled(false);
		nextPhaseButton.setVisible(false);
		actionRequestButton.setEnabled(false);
		actionRequestButton.setVisible(false);
		actionAmountField.setEnabled(false);
		actionAmountField.setVisible(false);
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
		nextPhaseButton.setEnabled(true);
		nextPhaseButton.setVisible(true);
		actionRequestButton.setEnabled(true);
		actionRequestButton.setVisible(true);
		actionAmountField.setEnabled(true);
		actionAmountField.setVisible(true);
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
		nextPhaseButton.setEnabled(false);
		nextPhaseButton.setVisible(false);
		actionRequestButton.setEnabled(false);
		actionRequestButton.setVisible(false);
		actionAmountField.setEnabled(false);
		actionAmountField.setVisible(false);
		cardButton.setEnabled(false);
		cardButton.setVisible(false);
		activateCards.setVisible(true);
		activateCards.setEnabled(true);
		cardModeBackButton.setVisible(true);
		cardModeBackButton.setEnabled(true);
	}
	
	public void deactivateCardMode() {
		nextPhaseButton.setEnabled(true);
		nextPhaseButton.setVisible(true);
		actionRequestButton.setEnabled(true);
		actionRequestButton.setVisible(true);
		actionAmountField.setEnabled(true);
		actionAmountField.setVisible(true);
		cardButton.setEnabled(true);
		cardButton.setVisible(true);
		activateCards.setVisible(false);
		activateCards.setEnabled(false);
		cardModeBackButton.setVisible(false);
		cardModeBackButton.setEnabled(false);
	}
}
