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

	private ArrayList<VisualCard> visualCards = new ArrayList<VisualCard>();
	private Integer[] focusIndex = new Integer[CARD_ACTIVATION.COMBINATIONAL.activation];
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
			visualCards.clear();
			visualCards.addAll(GameInteractions.extractActivePlayerVisualCards());
			resetDrawCoordinates();
		}
		if(visualCards.size() > 0)
			mouseEventUpdate();
	}

	public void flushPrevState() {
		for(int i = 0; i < focusIndex.length; i++)
			focusIndex[i] = null;
	}

	public void paint(Graphics painter) {
		for(Coordinate coord : slotCoordinates)
			painter.drawRect(coord.xCoord, coord.yCoord, VisualCard.width, VisualCard.height);

		cardLoop:for(int i = 0; i < visualCards.size(); i++) {
			for(int n = 0; n < focusIndex.length; n++)
				if(new Integer(i).equals(focusIndex[n])) {
					visualCards.get(i).paint(painter, slotCoordinates[n]); 
					continue cardLoop;
				}
			visualCards.get(i).paint(painter, drawCoordinates.get(i)); 
		}
	}

	public ArrayList<VisualCard> getFocusVisualCards() {
		ArrayList<VisualCard> focus = new ArrayList<VisualCard>();
		for(Integer index : focusIndex)
			if(focus != null && visualCards != null)
				focus.add(visualCards.get(index));
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
				int focusedIndex = xCoordDifferential / (VisualCard.width + xCoordCardGap);
				if(visualCards.size() > focusedIndex && focusedIndex > -1) {
					xCoordDifferential %= (VisualCard.width + xCoordCardGap);
					if(xCoordDifferential < VisualCard.width)
						if(mouseTracer.mouseClicked) pushIntoFocusIndex(new Integer(focusedIndex));
				}
			}
			else if(yCoordDifferential < 2*VisualCard.height + 2*xCoordCardGap
					&& yCoordDifferential > VisualCard.height + 2*xCoordCardGap) {
				int xCoordDifferential = mouseTracer.mousePosition.xCoord - slotCoordinates[0].xCoord;
				int focusedIndex = xCoordDifferential / (VisualCard.width + xCoordCardGap);
				if(focusIndex.length > focusedIndex && focusedIndex > -1) {
					xCoordDifferential %= (VisualCard.width + xCoordCardGap);
					if(xCoordDifferential < VisualCard.width)
						if(mouseTracer.mouseClicked) popOutFocusIndex(focusIndex[focusedIndex]);
				}
			}
		}
	}

	private void pushIntoFocusIndex(Integer index) {
		int availableIndex = -1;
		for(int n = 0; n < focusIndex.length; n++) {
			if(index.equals(focusIndex[n])) return;
			if(focusIndex[n] == null && availableIndex == -1)
				availableIndex = n;
		}
		if(availableIndex != -1)
			focusIndex[availableIndex] = 	index;
	}

	private void popOutFocusIndex(Integer pop) {
		for(int n = 0; n < focusIndex.length; n++)
			if(pop == focusIndex[n])
				focusIndex[n] = null;
	}
	
}
