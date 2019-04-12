package UIComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import AnimationComponents.AnimationHandler;
import Controller.GameController;

public class InteractionPanel {
	
	private JButton nextPhaseButton;
	private JButton actionRequestButton;
	private JTextField actionAmountField;
	private boolean textualPanelUpdateRequest;
	
	public void initialize(VisualTerritory[] focusTerritories) {
		nextPhaseButton = new JButton("Next Phase");
		actionRequestButton = new JButton("Action Request");
		actionAmountField = new JTextField();
		
		nextPhaseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GameController.interactions.requestNextPhase();
				AnimationHandler.terminateMouseOnTerritoryAnimation(focusTerritories[0]);
				AnimationHandler.terminateMouseOnTerritoryAnimation(focusTerritories[1]);
				focusTerritories[0] = null; focusTerritories[1] = null;
				textualPanelUpdateRequest = true;
			}
		});
		
		actionRequestButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int actionAmount = Integer.parseInt(actionAmountField.getText());
					GameController.interactions.requestAction(actionAmount);
					AnimationHandler.terminateMouseOnTerritoryAnimation(focusTerritories[0]);
					AnimationHandler.terminateMouseOnTerritoryAnimation(focusTerritories[1]);
					focusTerritories[0] = null; focusTerritories[1] = null;
				}
				catch(NumberFormatException exception) {
					System.out.println("Enter an action amount!!!");
				}
			}
		});
		
		actionAmountField.setPreferredSize(new Dimension(90, 30));
		
		textualPanelUpdateRequest = true;
	}
	
	public void insertButtons(JPanel target) {
		target.add(nextPhaseButton);
		target.add(actionRequestButton);
		target.add(actionAmountField);
	}
	
	public boolean getTextualPanelUpdateRequest() {
		if(textualPanelUpdateRequest) {
			textualPanelUpdateRequest = false;
			return true; 
		}
		return false;
	}
	
	public void deactivate() {
		nextPhaseButton.setEnabled(false);
		nextPhaseButton.setVisible(false);
		actionRequestButton.setEnabled(false);
		actionRequestButton.setVisible(false);
		actionAmountField.setEnabled(false);
		actionAmountField.setVisible(false);
	}
	
	public void activate() {
		nextPhaseButton.setEnabled(true);
		nextPhaseButton.setVisible(true);
		actionRequestButton.setEnabled(true);
		actionRequestButton.setVisible(true);
		actionAmountField.setEnabled(true);
		actionAmountField.setVisible(true);
	}
}
