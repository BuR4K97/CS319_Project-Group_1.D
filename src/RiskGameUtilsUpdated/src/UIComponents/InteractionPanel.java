package UIComponents;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.GameController;

public class InteractionPanel extends JPanel {

	private VisualTerritory[] focusTerritories;
	private JButton nextPhaseButton;
	private JButton actionRequestButton;
	private JTextField actionAmountField;
	
	public void initialize(VisualTerritory[] focusTerritories) {
		this.focusTerritories = focusTerritories;
		nextPhaseButton = new JButton("Next Phase");
		actionRequestButton = new JButton("Action Request");
		actionAmountField = new JTextField();
		
		nextPhaseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GameController.interactions.requestNextPhase();
				focusTerritories[0] = null; focusTerritories[1] = null;
			}
		});
		
		actionRequestButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int actionAmount = Integer.parseInt(actionAmountField.getText());
					GameController.interactions.requestAction(actionAmount);
					focusTerritories[0] = null; focusTerritories[1] = null;
				}
				catch(NumberFormatException exception) {
					System.out.println("Enter an action amount!!!");
				}
			}
		});
		
		actionAmountField.setPreferredSize(new Dimension(90, 30));
		
		add(nextPhaseButton);
		add(actionRequestButton);
		add(actionAmountField);
	}
	
	public void render() { repaint(); }
	
	public void destroy() { 
		focusTerritories = null;
	}
	
}
