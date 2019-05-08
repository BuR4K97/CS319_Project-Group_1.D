package UIComponents;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import Controller.GameController;
import Controller.GameInteractions;
import Controller.MainApplication;
import ModelClasses.Combat;
import UIComponents.EnvanterBoxUI.SmallBox;

public class FortifyInteraction {
	
	private ArrayList<SmallBox> unitsInHand = new ArrayList<SmallBox>();
	private ArrayList<SmallBox> returningUnits = new ArrayList<SmallBox>();
	
	private VisualTerritory sourceTerritory;
	private VisualTerritory returningTerritory;
	public void update(MouseInGameListener mouseTracer) {
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
		
		if(returningUnits.size() < 10) {
			for(int i = 0; i < returningUnits.size(); i++) {
				returningUnits.get(i).goTarget(returningTerritory.mainCoordinate.xCoord, returningTerritory.mainCoordinate.yCoord);
				if(returningUnits.get(i).isInRectangle(new Rectangle(returningTerritory.mainCoordinate.xCoord
						, returningTerritory.mainCoordinate.yCoord, 20, 20))) {
					returningUnits.remove(i);
					break;
				}	
			}
		} else {
			returningUnits.get(0).goTarget(returningTerritory.mainCoordinate.xCoord, returningTerritory.mainCoordinate.yCoord);
			if(returningUnits.get(0).isInRectangle(new Rectangle(returningTerritory.mainCoordinate.xCoord
					, returningTerritory.mainCoordinate.yCoord, 20, 20))) {
				returningUnits.clear();
			}	
		}
		if(returningUnits.size() == 0) returningTerritory = null;
		
		
		if(mouseTracer.leftButtonClicked) {
			if(sourceTerritory == mouseTracer.getFocusTerritory() || sourceTerritory == null) {
					sourceTerritory = mouseTracer.getFocusTerritory();
				if(GameInteractions.findCorrespondingTerritory(sourceTerritory).getUnitNumber() 
						- unitsInHand.size() > Combat.MIN_DEFENSE_UNIT) {
					if(unitsInHand.size() < 3)
						unitsInHand.add(new SmallBox(mouseTracer.mousePosition.xCoord + unitsInHand.size() * 20, mouseTracer.mousePosition.yCoord));
					else if(unitsInHand.size() < 6) 
						unitsInHand.add(new SmallBox(mouseTracer.mousePosition.xCoord + (unitsInHand.size() -3) * 20, mouseTracer.mousePosition.yCoord + 1 * 20));
					else if(unitsInHand.size() < 9) 
						unitsInHand.add(new SmallBox(mouseTracer.mousePosition.xCoord + (unitsInHand.size() -6) * 20, mouseTracer.mousePosition.yCoord + 2 * 20));
					else 
						unitsInHand.add(new SmallBox(mouseTracer.mousePosition.xCoord + unitsInHand.size() * 20, mouseTracer.mousePosition.yCoord));
				}
			}
			else if(isSelectable(mouseTracer.getFocusTerritory())) {
				GameController.interactions.synchronizeFocusTerritories(sourceTerritory, mouseTracer.getFocusTerritory());
				GameController.interactions.requestAction(unitsInHand.size());
				sourceTerritory = null;
				unitsInHand.clear();
			}
		} 
		else if(mouseTracer.rightButtonClicked){
			if(mouseTracer.getFocusTerritory() == sourceTerritory && sourceTerritory != null) {
				returningTerritory = sourceTerritory;
				sourceTerritory = null;
				returningUnits.addAll(unitsInHand);
				unitsInHand.clear();
			}
		}
		((GamePanel)MainApplication.frame.focusPanel).requestFortifyInteractionEffect(sourceTerritory, unitsInHand.size());
		((GamePanel)MainApplication.frame.focusPanel).requestFortifyInteractionEffect(returningTerritory, returningUnits.size());
	}
	
	public void flushState() {
		sourceTerritory = null;
		returningTerritory = null;
		unitsInHand.clear();
		returningUnits.clear();
	}
	
	public void paint(Graphics painter) {
		if(unitsInHand.size() < 10) {
			for(int i = 0; i < unitsInHand.size();i++) {
				painter.fillRect(unitsInHand.get(i).x, unitsInHand.get(i).y, 15, 15);
			}
		} else {
			painter.setFont(new Font("pixel", Font.BOLD, 60));
			painter.drawString("" + unitsInHand.size(), unitsInHand.get(0).x, unitsInHand.get(0).y + 50);
		}
		
		if(returningUnits.size() < 10) {
			for(int i = 0; i < returningUnits.size();i++) {
				painter.fillRect(returningUnits.get(i).x, returningUnits.get(i).y, 15, 15);
			}
		} else {
			painter.setFont(new Font("pixel", Font.BOLD, 60));
			painter.drawString("" + returningUnits.size(), returningUnits.get(0).x, returningUnits.get(0).y + 50);
		}
	}
	
	private boolean isSelectable(VisualTerritory target) {
		if(sourceTerritory == null || target == null) return false;
		return GameInteractions.isSelectable(sourceTerritory, target);
	}

}
