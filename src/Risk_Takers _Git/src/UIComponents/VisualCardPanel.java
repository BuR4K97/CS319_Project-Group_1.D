package UIComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import Controller.GameInteractions;
import ModelClasses.Card.CARD_TYPES;

public class VisualCardPanel {
	
	private ArrayList<VisualCard> visualCards;
	private boolean cardMode = false;
	
	public void update() {
		visualCards = GameInteractions.extractActivePlayerVisualCards();
	}
	
	
	private static final int yDrawCoord = 240;
	private static final int xDrawCoord = 40;
	private static final int xCoordCardGap = 20;
	private ArrayList<Coordinate> drawCoordinates = new ArrayList<Coordinate>();
	public void inCardMode() {
		cardMode = true;
		int xDistance = (VisualCard.width * visualCards.size()) + (xCoordCardGap * (visualCards.size() - 1));
		int initialXCoord = xDrawCoord + (((ApplicationFrame.width - 2 * xDrawCoord) - xDistance) / 2);
		for(VisualCard card : visualCards) {
			drawCoordinates.add(new Coordinate(initialXCoord, yDrawCoord));
			initialXCoord += xCoordCardGap + VisualCard.width;
		}
	}
	
	public void outCardMode() {
		cardMode = false;
		drawCoordinates.clear();
	}
	
	public void paint(Graphics painter) {
		if(cardMode) {
			for(int i = 0; i < visualCards.size(); i++)
				visualCards.get(i).paint(painter, drawCoordinates.get(i));
		}
	}

	

}
