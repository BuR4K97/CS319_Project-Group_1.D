package UIComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import Controller.GameInteractions;
import ModelClasses.Card;
import ModelClasses.Card.CARD_TYPES;
import ModelClasses.Card.CARD_ACTIVATION;

public class VisualCardPanel {
	
	private ArrayList<VisualCard> visualCards;
	private boolean cardMode = false;
	
	public void update() {
		visualCards = GameInteractions.extractActivePlayerVisualCards();
	}
	
	
	private static final int yDrawCoord = 240;
	private static final int xDrawCoord = 40;
	private static final int xCoordCardGap = 20;
	private static Coordinate[] slotCoordinates = new Coordinate[CARD_ACTIVATION.COMBINATIONAL.activation]; 
	static {
		int xDistance = (VisualCard.width * slotCoordinates.length) + (xCoordCardGap * (slotCoordinates.length - 1));
		int initialXCoord = xDrawCoord + (((ApplicationFrame.width - 2 * xDrawCoord) - xDistance) / 2);
		for(int i = 0; i < slotCoordinates.length; i++) {
			slotCoordinates[i] = new Coordinate(initialXCoord, yDrawCoord + xCoordCardGap + VisualCard.height);
			initialXCoord += xCoordCardGap + VisualCard.width;
		}
	} 
	
	private ArrayList<Coordinate> drawCoordinates = new ArrayList<Coordinate>();
	//private ArrayList<Coordinate> 
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
			for(Coordinate coord : slotCoordinates)
				painter.drawRect(coord.xCoord, coord.yCoord, VisualCard.width, VisualCard.height);
			
			for(int i = 0; i < visualCards.size(); i++)
				visualCards.get(i).paint(painter, drawCoordinates.get(i));
		}
	}

	

}
