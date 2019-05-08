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
			visualCards = GameInteractions.extractActivePlayerVisualCards();
			resetDrawCoordinates();
		}
		if(visualCards.size() > 0)
			mouseEventUpdate();
	}

	public void flushPrevState() {
		for(int i = 0; i < focusVisualCards.length; i++)
			focusVisualCards[i] = null;
	}

	public void paint(Graphics painter) {
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

	public ArrayList<VisualCard> getFocusVisualCards() {
		ArrayList<VisualCard> focus = new ArrayList<VisualCard>();
		for(VisualCard vc : focusVisualCards)
			if(vc != null)
				focus.add(vc);
		return focus;
	}

	private void resetDrawCoordinates() {
		drawCoordinates.clear();
		int xDistance = (VisualCard.width * visualCards.size()) + (xCoordCardGap * (visualCards.size() - 1));
		int initialXCoord = xDrawCoord + (((ApplicationFrame.width - 2 * xDrawCoord) - xDistance) / 2);
		for(VisualCard card : visualCards) {
			drawCoordinates.add(new Coordinate(initialXCoord, yDrawCoord));
			initialXCoord += xCoordCardGap + VisualCard.width;
		}
	}

	private void mouseEventUpdate() {
		int yCoordDifferential = mouseTracer.mousePosition.yCoord - yDrawCoord;
		if(yCoordDifferential > 0) {
			if(yCoordDifferential < VisualCard.height) {
				int xCoordDifferential = mouseTracer.mousePosition.xCoord - drawCoordinates.get(0).xCoord;
				int focusIndex = xCoordDifferential / (VisualCard.width + xCoordCardGap);
				if(visualCards.size() > focusIndex && focusIndex > -1) {
					xCoordDifferential %= (VisualCard.width + xCoordCardGap);
					if(xCoordDifferential < VisualCard.width)
						if(mouseTracer.mouseClicked) pushIntoFocusVisualCards(visualCards.get(focusIndex));
				}
			}
			else if(yCoordDifferential < 2*VisualCard.height + 2*xCoordCardGap
					&& yCoordDifferential > VisualCard.height + 2*xCoordCardGap) {
				int xCoordDifferential = mouseTracer.mousePosition.xCoord - slotCoordinates[0].xCoord;
				int focusIndex = xCoordDifferential / (VisualCard.width + xCoordCardGap);
				if(focusVisualCards.length > focusIndex && focusIndex > -1) {
					xCoordDifferential %= (VisualCard.width + xCoordCardGap);
					if(xCoordDifferential < VisualCard.width)
						if(mouseTracer.mouseClicked) popOutFocusVisualCard(focusVisualCards[focusIndex]);
				}
			}
		}
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
				focusVisualCards[n] = null;
	}
	
}
