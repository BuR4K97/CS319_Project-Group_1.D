package UIComponents.EnvanterBoxUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import AnimationComponents.AnimationHandler;
import Controller.GameController;
import Controller.GameInteractions;
import Controller.MainApplication;
import ModelClasses.Player;
import UIComponents.Coordinate;
import UIComponents.GamePanel;
import UIComponents.MouseInGameListener;
import UIComponents.VisualString;
import UIComponents.VisualTerritory;

public class EnvanterBox{

	int x = 20;
	int borderLength = 120;// 120
	int y = 1080 - borderLength -30;
	int openingAmountLeft = 0; // 0   -50
	int openingAmountRigth = borderLength / 2; // 50  100
	final int OPENING_AMOUNT_LEFT_OPEN = -borderLength/2;
	final int OPENING_AMOUNT_LEFT_CLOSE = 0;
	final int OPENING_AMOUNT_RIGHT_OPEN = borderLength;
	final int OPENING_AMOUNT_RIGHT_CLOSE = borderLength/2;
	int movingAmount = 7;
	boolean inOpening = false;
	private double openAngle = 0;
	private double openRate = 7 * Math.PI / 180;
	Timer opening, closing;
	Timer movingBoxes;
	ArrayList<SmallBox> list = new ArrayList<SmallBox>();
	ArrayList<SmallBox> unitsInHand = new ArrayList<SmallBox>();
	ArrayList<SmallBox> returnToBoxAnimation = new ArrayList<SmallBox>();


	public EnvanterBox() {
		movingBoxes = new Timer(MainApplication.ONE_SEC / MainApplication.ANIMATION_UPDATE_FREQUENCY
				,  new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < list.size(); i++) {
					list.get(i).move(new Rectangle(x, y, borderLength, borderLength));
				}
			}

		});
		movingBoxes.start();

		opening = new Timer(16,  new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openAngle += openRate;
				if(openAngle > Math.PI / 2)
					openAngle = Math.PI / 2;
			}
		});
		closing = new Timer(16,  new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openAngle -= openRate;
				if(openAngle < 0)
					openAngle = 0;
			}
		});
	}

	private Player player;
	public void updatePlayer(Player activePlayer) {
		//if(player == activePlayer) return;
		this.player = activePlayer;
		resetPlayerNumber();
	}

	private void resetPlayerNumber() {
		list.clear();
		for(int i = 0; i < player.getAvailableUnitAmount(); i++)
			list.add(new SmallBox(x + borderLength/2, y + borderLength/2));
	}

	private VisualTerritory focusTerritory;
	private int selectedAmount;
	public void updateMouseEvent(MouseInGameListener mouseTracer) {
		if(mouseOnBox(mouseTracer.mousePosition.xCoord, mouseTracer.mousePosition.yCoord))
			open();
		else
			close();
		// units in hand
		for(int i = 0; i < unitsInHand.size();i++) {
			if(i < 3) {
				unitsInHand.get(i).goTarget(mouseTracer.mousePosition.xCoord + i * 20, mouseTracer.mousePosition.yCoord);
			} else if(i < 6) {
				unitsInHand.get(i).goTarget(mouseTracer.mousePosition.xCoord + (i-3) * 20, mouseTracer.mousePosition.yCoord + 1 * 20);
			}else if(i < 9) {
				unitsInHand.get(i).goTarget(mouseTracer.mousePosition.xCoord + (i -6)  * 20, mouseTracer.mousePosition.yCoord + 2 * 20);
			} else {
				unitsInHand.get(i).goTarget(mouseTracer.mousePosition.xCoord + unitsInHand.size() * 20, mouseTracer.mousePosition.yCoord);
			}
		}

		// return to box
		if(returnToBoxAnimation.size() < 10) {
			for(int i = 0; i < returnToBoxAnimation.size(); i++) {
				returnToBoxAnimation.get(i).goTarget(x + borderLength / 2, y + returnToBoxAnimation.get(i).length/2);
				if(returnToBoxAnimation.get(i).isInRectangle(new Rectangle(x, y, borderLength, borderLength))) {
					addUnit(1);
					returnToBoxAnimation.remove(i);
					break;
				}	
			}
		} else {
			returnToBoxAnimation.get(0).goTarget(x + borderLength / 2, y + returnToBoxAnimation.get(0).length/2);
			if(returnToBoxAnimation.get(0).isInRectangle(new Rectangle(x, y, borderLength, borderLength))) {
				addUnit(returnToBoxAnimation.size());
				returnToBoxAnimation = new ArrayList<SmallBox>();
			}	
		}

		if(GameInteractions.isSelectable(mouseTracer.getFocusTerritory(), null, -unitsInHand.size()))
			((GamePanel)MainApplication.frame.focusPanel).requestPushIntoVisualTerritoryPanelSelectableTerritory(
					mouseTracer.getFocusTerritory());
		else if(GameInteractions.isSelectable(mouseTracer.getFocusTerritory(), null, selectedAmount + 1))
			((GamePanel)MainApplication.frame.focusPanel).requestPushIntoVisualTerritoryPanelSelectableTerritory(
					mouseTracer.getFocusTerritory());
		if(mouseTracer.leftButtonClicked) {
			if(mouseOnBox(mouseTracer.mousePosition.xCoord, mouseTracer.mousePosition.yCoord)) {
				if(list.size() > 0) {
					removeUnit(1);
					if(unitsInHand.size() < 3)
						unitsInHand.add(new SmallBox(mouseTracer.mousePosition.xCoord + unitsInHand.size() * 20, mouseTracer.mousePosition.yCoord));
					else if(unitsInHand.size() < 6) 
						unitsInHand.add(new SmallBox(mouseTracer.mousePosition.xCoord + (unitsInHand.size() -3) * 20, mouseTracer.mousePosition.yCoord + 1 * 20));
					else if(unitsInHand.size() < 9) 
						unitsInHand.add(new SmallBox(mouseTracer.mousePosition.xCoord + (unitsInHand.size() -6) * 20, mouseTracer.mousePosition.yCoord + 2 * 20)); 
					else 
						unitsInHand.add(new SmallBox(mouseTracer.mousePosition.xCoord + unitsInHand.size() * 20, mouseTracer.mousePosition.yCoord));
				}
				//GameController.interactions.synchronizeFocusTerritories(focusTerritory, null);
				//GameController.interactions.requestAction(-selectedAmount);
				//GameInteractions.requestManualGameUpdate();
				//selectedAmount = 0;
			}
			else if(unitsInHand.size() > 0) {
				if(GameInteractions.isSelectable(mouseTracer.getFocusTerritory(), null, -unitsInHand.size())) {
					GameController.interactions.synchronizeFocusTerritories(focusTerritory, null);
					GameController.interactions.requestAction(-selectedAmount);
					GameInteractions.requestManualGameUpdate();
					selectedAmount = 0;
					GameController.interactions.synchronizeFocusTerritories(mouseTracer.getFocusTerritory(), null);
					GameController.interactions.requestAction(unitsInHand.size());
					unitsInHand.clear();
				}
			}
		}
		else if(mouseTracer.rightButtonClicked) {
			if(mouseOnBox(mouseTracer.mousePosition.xCoord, mouseTracer.mousePosition.yCoord)) {
				if(unitsInHand.size() > 0) {
					if(focusTerritory != null) {
						GameController.interactions.synchronizeFocusTerritories(focusTerritory, null);
						GameController.interactions.requestAction(-1);
						GameInteractions.requestManualGameUpdate();
					}
					addUnit(1);
					unitsInHand.remove(unitsInHand.size()-1);
					selectedAmount -= 1;
				}
			}
			else if(GameInteractions.isSelectable(mouseTracer.getFocusTerritory(), null, selectedAmount + 1))  {
				if(focusTerritory != mouseTracer.getFocusTerritory() && focusTerritory != null) {
					GameController.interactions.synchronizeFocusTerritories(focusTerritory, null);
					GameController.interactions.requestAction(-selectedAmount);
					GameInteractions.requestManualGameUpdate();
					for(int i = 0; i < unitsInHand.size(); i++) {
						returnToBoxAnimation.add(unitsInHand.get(i));
						returnToBoxAnimation.get(i).goTarget(x + borderLength / 2, y + returnToBoxAnimation.get(i).length/2);
					}
					unitsInHand.clear();
					selectedAmount = 0;
				}
				else {
					if(unitsInHand.size() < 3)
						unitsInHand.add(new SmallBox(mouseTracer.mousePosition.xCoord + unitsInHand.size() * 20, mouseTracer.mousePosition.yCoord));
					else if(unitsInHand.size() < 6) 
						unitsInHand.add(new SmallBox(mouseTracer.mousePosition.xCoord + (unitsInHand.size() -3) * 20, mouseTracer.mousePosition.yCoord + 1 * 20));
					else if(unitsInHand.size() < 9) 
						unitsInHand.add(new SmallBox(mouseTracer.mousePosition.xCoord + (unitsInHand.size() -6) * 20, mouseTracer.mousePosition.yCoord + 2 * 20)); 
					else 
						unitsInHand.add(new SmallBox(mouseTracer.mousePosition.xCoord + unitsInHand.size() * 20, mouseTracer.mousePosition.yCoord));
					focusTerritory = mouseTracer.getFocusTerritory();
					selectedAmount += 1;
				}
			}
			else if(mouseTracer.getFocusTerritory() != focusTerritory) {
				GameController.interactions.synchronizeFocusTerritories(focusTerritory, null);
				GameController.interactions.requestAction(-selectedAmount);
				GameInteractions.requestManualGameUpdate();
				for(int i = 0; i < unitsInHand.size(); i++) {
					returnToBoxAnimation.add(unitsInHand.get(i));
					returnToBoxAnimation.get(i).goTarget(x + borderLength / 2, y + returnToBoxAnimation.get(i).length/2);
				}
				unitsInHand.clear();
				selectedAmount = 0;
			}
		}
		if(focusTerritory != null)
			((GamePanel)MainApplication.frame.focusPanel).requestVisualDeviationEffect(focusTerritory, selectedAmount);
		if(selectedAmount == 0) focusTerritory = null;
	}

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		if(player != null)
			g2d.setColor(player.getColor());
		// boxes
		for(int i = 0; i < list.size(); i++) {
			g2d.fillRect(list.get(i).x, list.get(i).y, list.get(i).length, list.get(i).length);
		}
		g2d.setStroke(new BasicStroke(3));
		g2d.setColor(Color.LIGHT_GRAY);
		// left border
		g2d.drawLine(x, y, x, y + borderLength);
		// bottom border
		g2d.drawLine(x, y + borderLength, x + borderLength, y + borderLength);
		// right border
		g2d.drawLine(x + borderLength, y + borderLength, x + borderLength, y);
		//angle based moving border
		g2d.drawLine(x, y, x + (int)(borderLength * Math.cos(openAngle)), y - (int)(borderLength * Math.sin(openAngle)));

		// unit number
		g2d.setFont(new Font("pixel", Font.PLAIN, borderLength));
		g2d.drawString("" + list.size(), x + borderLength + 15, y + borderLength);
		// box in hand
		g2d.setColor(Color.LIGHT_GRAY);
		if(unitsInHand.size() < 10) {
			for(int i = 0; i < unitsInHand.size();i++) {
				g.fillRect(unitsInHand.get(i).x, unitsInHand.get(i).y, 15, 15);
			}
		} else {
			g2d.setFont(new Font("pixel", Font.BOLD, borderLength/2));
			g2d.drawString("" + unitsInHand.size(), unitsInHand.get(0).x, unitsInHand.get(0).y + borderLength/2 - 10);
		}
		// return to box
		if(returnToBoxAnimation.size() < 10) {
			for(int i = 0; i < returnToBoxAnimation.size();i++) {
				g.fillRect(returnToBoxAnimation.get(i).x, returnToBoxAnimation.get(i).y, 15, 15);
			}
		} else {
			g2d.setFont(new Font("pixel", Font.BOLD, borderLength/2));
			g2d.drawString("" + returnToBoxAnimation.size(), returnToBoxAnimation.get(0).x, returnToBoxAnimation.get(0).y);
		}
	}

	public void open() {
		closing.stop();
		opening.start();
	}

	public void close() {
		opening.stop();
		closing.start();
	}

	public void addUnit(int number) {
		for(int i = 0; i < number; i++)
			list.add(new SmallBox(x + borderLength/2, y + borderLength/2));
	}

	public void removeUnit(int number) {
		if((list.size() - number) > 0) {
			for(int i = 0; i < number; i++)
				list.remove(list.size()-1);
		} else {
			list = new ArrayList<SmallBox>();
		}
	}

	public void removeAll() {
		list = new ArrayList<SmallBox>();
	}

	public boolean mouseOnBox(int x, int y) {
		if(x >= this.x && (x <= this.x + borderLength) && y >= this.y && (y <= this.y + borderLength))
			return true;
		return false;
	}

	public void flushState() {
		if(focusTerritory == null)
			addUnit(unitsInHand.size());
		else {
			((GamePanel)MainApplication.frame.focusPanel).requestVisualDeviationEffect(focusTerritory, 0);
			addUnit(unitsInHand.size() - selectedAmount);
		}
		focusTerritory = null;
		unitsInHand.clear();
		selectedAmount = 0;
		returnToBoxAnimation.clear();
	}

}
