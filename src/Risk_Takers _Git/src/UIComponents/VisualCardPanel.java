package UIComponents;

import java.awt.Graphics;
import java.util.ArrayList;
import Controller.GameInteractions;

public class VisualCardPanel {
	
	private ArrayList<VisualCard> visualCards;
	
	public void update() {
		visualCards = GameInteractions.extractActivePlayerVisualCards();
	}
	
	public void paint(Graphics painter) {
		
	}

	

}
