package UIComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import Controller.GameController;
import Controller.GameInteractions;
import ModelClasses.Card;
import ModelClasses.Card.CARD_TYPES;
import ModelClasses.Card.CARD_ACTIVATION;

public class VisualCardPanel {
	
	private ArrayList<VisualCard> visualCards;
	private VisualCard[] focusVisualCards = new VisualCard[CARD_ACTIVATION.COMBINATIONAL.activation];
	private MouseInGameListener mouseTracer;
	
	public void initialize(MouseInGameListener mouseTracer) {
		this.mouseTracer = mouseTracer;
	}
	
	private static final int yDrawCoord = 240;
	private static final int xDrawCoord = 40;
	private static final int xCoordCardGap = 20;
	private static Coordinate[] slotCoordinates = new Coordinate[CARD_ACTIVATION.COMBINATIONAL.activation]; 
	static {
		int xDistance = (VisualCard.width * slotCoordinates.length) + (xCoordCardGap * (slotCoordinates.length - 1));
		int initialXCoord = xDrawCoord + (((ApplicationFrame.width - 2 * xDrawCoord) - xDistance) / 2);
		for(int i = 0; i < slotCoordinates.length; i++) {
			slotCoordinates[i] = new Coordinate(initialXCoord, yDrawCoord + 2*xCoordCardGap + VisualCard.height);
			initialXCoord += xCoordCardGap + VisualCard.width;
		}
	}
	
	private ArrayList<Coordinate> drawCoordinates = new ArrayList<Coordinate>();
	
	public void update() {
		if(GameController.interactions.getVisualCardPanelUpdateRequest()) {
			flushPrevState();
			visualCards = GameInteractions.extractActivePlayerVisualCards();
			int xDistance = (VisualCard.width * visualCards.size()) + (xCoordCardGap * (visualCards.size() - 1));
			int initialXCoord = xDrawCoord + (((ApplicationFrame.width - 2 * xDrawCoord) - xDistance) / 2);
			for(VisualCard card : visualCards) {
				drawCoordinates.add(new Coordinate(initialXCoord, yDrawCoord));
				initialXCoord += xCoordCardGap + VisualCard.width;
			}
		}
		if(cardMode) {
			
		}
	}
	
	private boolean cardMode = false;
	 
	public void inCardMode() {
		cardMode = true;
	}
	
	public void outCardMode() {
		cardMode = false;
		flushPrevState();
	}
	
	public void paint(Graphics painter) {
		if(cardMode) {
			for(Coordinate coord : slotCoordinates)
				painter.drawRect(coord.xCoord, coord.yCoord, VisualCard.width, VisualCard.height);
			
			cardLoop:for(int i = 0; i < visualCards.size(); i++) {
				for(int n = 0; n < focusVisualCards.length; n++)
					if(focusVisualCards[n] == visualCards.get(i)) {
						visualCards.get(i).paint(painter, slotCoordinates[n]); 
						continue cardLoop;
					}
				visualCards.get(i).paint(painter, drawCoordinates.get(i)); 
			}
		}
	}

	public ArrayList<VisualCard> getFocusVisualCards() {
		ArrayList<VisualCard> focus = new ArrayList<VisualCard>();
		for(VisualCard vc : focusVisualCards)
			focus.add(vc);
		return focus;
	}
	
	private void pushIntoFocusVisualCards(VisualCard push) {
		int availableIndex = -1;
		for(int n = 0; n < focusVisualCards.length; n++) {
			if(focusVisualCards[n] == push) return;
			if(focusVisualCards[n] == null && availableIndex == -1)
				availableIndex = n;
		}
		if(availableIndex != -1)
			focusVisualCards[availableIndex] = push;
	}
	
	private void popOutFocusVisualCard(VisualCard pop) {
		for(int n = 0; n < focusVisualCards.length; n++)
			if(focusVisualCards[n] == pop)
				focusVisualCards = null;
	}
	
	private void flushPrevState() {
		drawCoordinates.clear();
		for(VisualCard vc : focusVisualCards)
			vc = null;
	}

}
