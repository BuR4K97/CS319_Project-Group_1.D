package AnimationComponents;

import java.util.ArrayList;

import ModelClasses.Territory;
import UIComponents.ApplicationFrame;
import UIComponents.Coordinate;
import UIComponents.VisualTerritory;
import UIComponents.VisualTerritory.SCALE_MODE;

public class AttackAnimation extends Animation {

	private static enum CORNER_POINTS {
		
		TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT, DYNAMIC_TOP_LEFT, DYNAMIC_TOP_RIGHT
				, DYNAMIC_BOTTOM_LEFT, DYNAMIC_BOTTOM_RIGHT, CENTER, NULL;
		
		private Coordinate position;
		
		private static CORNER_POINTS getCorresponding(Coordinate coordinate) {
			if(coordinate.xCoord >= DYNAMIC_TOP_RIGHT.position.xCoord || coordinate.xCoord <= DYNAMIC_BOTTOM_LEFT.position.xCoord)
				return NULL;
			if(coordinate.yCoord <= DYNAMIC_TOP_RIGHT.position.yCoord || coordinate.yCoord >= DYNAMIC_BOTTOM_LEFT.position.yCoord)
				return NULL;
			if(coordinate.xCoord < CENTER.position.xCoord) {
				if(coordinate.yCoord < CENTER.position.yCoord)
					return DYNAMIC_TOP_LEFT;
				else
					return DYNAMIC_BOTTOM_LEFT;
			}
			else {
				if(coordinate.yCoord < CENTER.position.yCoord)
					return DYNAMIC_TOP_RIGHT;
				else
					return DYNAMIC_BOTTOM_RIGHT;
			}
		}
		
		private static void reset() {
			CENTER.position = new Coordinate(1920 / 2, 1080 / 2);
			TOP_LEFT.position = new Coordinate(-VisualTerritory.PIXEL_JUMP, -VisualTerritory.PIXEL_JUMP);
			TOP_RIGHT.position = new Coordinate(1920 + VisualTerritory.PIXEL_JUMP, -VisualTerritory.PIXEL_JUMP);
			BOTTOM_LEFT.position = new Coordinate(-VisualTerritory.PIXEL_JUMP, 1080 + VisualTerritory.PIXEL_JUMP);
			BOTTOM_RIGHT.position = new Coordinate(1920 + VisualTerritory.PIXEL_JUMP, 1080 + VisualTerritory.PIXEL_JUMP);
			DYNAMIC_TOP_LEFT.position = new Coordinate(1920 / 2, 1080 / 2);
			DYNAMIC_TOP_RIGHT.position = new Coordinate(1920 / 2, 1080 / 2);
			DYNAMIC_BOTTOM_LEFT.position = new Coordinate(1920 / 2, 1080 / 2);
			DYNAMIC_BOTTOM_RIGHT.position = new Coordinate(1920 / 2, 1080 / 2);
		}
		
		private static boolean execute(int amount) {
			boolean finished = true;
			if(DYNAMIC_TOP_LEFT.position.approachCoordinate(TOP_LEFT.position, amount))
				finished = false;
			if(DYNAMIC_TOP_RIGHT.position.approachCoordinate(TOP_RIGHT.position, amount))
				finished = false;
			if(DYNAMIC_BOTTOM_LEFT.position.approachCoordinate(BOTTOM_LEFT.position, amount))
				finished = false;
			if(DYNAMIC_BOTTOM_RIGHT.position.approachCoordinate(BOTTOM_RIGHT.position, amount))
				finished = false;
			return !finished;
		}
	}
	
	private ArrayList<VisualTerritory> visualTerritories = null;
	private ArrayList<VisualTerritory> visualTerritoriesCopy = new ArrayList<VisualTerritory>();
	private VisualTerritory source, target;
	private Territory sourceTerritory, targetTerritory;
	private boolean firstPhase = true;
	private int coordChanger = 12;

	public AttackAnimation(ArrayList<VisualTerritory> visualTerritories, VisualTerritory visualSourceTerritory,
			VisualTerritory visualTargetTerritory, Territory sourceTerritory, Territory targetTerritory) {
		this.visualTerritories = visualTerritories;
		source = visualSourceTerritory;
		target = visualTargetTerritory;
		this.sourceTerritory = sourceTerritory;
		this.targetTerritory = targetTerritory;
		for (VisualTerritory vt : visualTerritories)
			visualTerritoriesCopy.add(vt.copy());
		CORNER_POINTS.reset();
	}

	private boolean visualBufferAnimationTerminateRequested = false;
	private static Coordinate attackCoordinate = new Coordinate(VisualTerritory.PIXEL_JUMP * 25
			, VisualTerritory.PIXEL_JUMP * 42), defendCoordinate = new Coordinate(ApplicationFrame.width / 2 
					+ VisualTerritory.PIXEL_JUMP * 25, VisualTerritory.PIXEL_JUMP * 42);
	@Override
	public boolean execute() {
		boolean executing = true;
		int xCoord, yCoord;

		if (firstPhase) {
			for (int i = 0; i < this.visualTerritories.size(); i++) {
				if (visualTerritories.get(i) != source && visualTerritories.get(i) != target) {
					for (int j = 0; j < this.visualTerritories.get(i).coordinates.size(); j++) {
						visualTerritories.get(i).coordinates.get(j).approachCoordinate(CORNER_POINTS
								.getCorresponding(visualTerritories.get(i).coordinates.get(j)).position, coordChanger);
					}
				}
			}
			firstPhase = CORNER_POINTS.execute(coordChanger);
			if(!firstPhase && !terminating) {
				source.drawCoordinate = attackCoordinate; 
				target.drawCoordinate = defendCoordinate;
				source.scale = SCALE_MODE.UPSCALED; target.scale = SCALE_MODE.UPSCALED;
				AnimationHandler.requestAnimationVisualBufferAttackAnimation(sourceTerritory, targetTerritory); 
			}
		}
		else if(terminating) {
			if(!AnimationHandler.suspendAttackAnimationTermination()) {
				source.drawCoordinate = source.mainCoordinate; 
				target.drawCoordinate = target.mainCoordinate;
				source.scale = SCALE_MODE.NORMAL; target.scale = SCALE_MODE.NORMAL;
				boolean finished = true;
				for (int i = 0; i < this.visualTerritories.size(); i++) {
					if (visualTerritories.get(i) != source && visualTerritories.get(i) != target) {
						for (int j = 0; j < this.visualTerritories.get(i).coordinates.size(); j++) {
							if (visualTerritories.get(i).coordinates.get(j)
									.approachCoordinate(visualTerritoriesCopy.get(i).coordinates.get(j), coordChanger))
								finished = false;
						}
					}
				}
				executing = !finished;
			}
			else if(!visualBufferAnimationTerminateRequested) {
				AnimationHandler.terminateAnimationVisualBufferAttackAnimation();
				visualBufferAnimationTerminateRequested = true;
			}
		}
		
		if (!executing) {
			terminate();
			return false;
		}
		return true;

	}

	protected void terminate() {}
}
