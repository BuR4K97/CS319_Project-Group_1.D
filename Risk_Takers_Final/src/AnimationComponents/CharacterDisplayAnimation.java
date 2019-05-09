package AnimationComponents;

import java.util.ArrayList;

import AnimationComponents.AnimationVisualBuffer.VisualCoordinate;
import AnimationComponents.AnimationVisualBuffer.VisualCoordinate.DRAW_MODE;
import UIComponents.Coordinate;
import UIComponents.VisualTerritory;

public class CharacterDisplayAnimation extends Animation {

	private static final int SCALE_MODIFIER = 3;
	private static final int LOWER_BOUND = VisualTerritory.DEFAULT_DRAW_SIZE;
	private static final int UPPER_BOUND = VisualTerritory.PIXEL_JUMP;
	private static final double ANIMATION_MODIFIER = (UPPER_BOUND - LOWER_BOUND) / 13.0;

	private ArrayList<VisualCoordinate> animateVisualCoordinates;
	private Coordinate displayCoordinate;
	private String animatedCharacter;
	private boolean terminateImmediate;

	public CharacterDisplayAnimation(String animatedCharacter, Coordinate displayCoordinate) {
		this.animatedCharacter = animatedCharacter;
		this.displayCoordinate = displayCoordinate;
		if(AnimationHandler.visualBuffer.visualBuffer != null && VisualTerritory.checkPixelJumpHierarchy(displayCoordinate)) {
			animateVisualCoordinates = new ArrayList<VisualCoordinate>();
			initializeVisualCoordinates(AnimationHandler.visualBuffer.visualBuffer); 
		}
		else 
			terminateImmediate = true;
	}

	@Override
	public boolean execute() {
		if(terminateImmediate) return false;
		
		boolean terminate = false;
		for(VisualCoordinate coordinate : animateVisualCoordinates) {
			if(terminating)
				coordinate.drawSize -= ANIMATION_MODIFIER;
			else if(coordinate.drawSize < UPPER_BOUND)
				coordinate.drawSize += ANIMATION_MODIFIER;
				
			coordinate.drawMode = DRAW_MODE.WHITE;
			if(coordinate.drawSize < LOWER_BOUND)
				terminate = true;
		}
		if(terminate) {
			terminate();
			return false;
		}

		return true;
	}

	@Override
	protected void terminate() {
		for(VisualCoordinate coordinate : animateVisualCoordinates) {
			coordinate.drawSize = VisualTerritory.DEFAULT_DRAW_SIZE;
			coordinate.drawMode = DRAW_MODE.NORMAL; 
		}
		
	}

	public boolean equals(Coordinate checkCoordinate) {
		return displayCoordinate.equals(checkCoordinate);
	}
	
	public boolean equals(String checkCharacter, Coordinate checkCoordinate) {
		if(!animatedCharacter.equals(checkCharacter)) return false;
		if(!displayCoordinate.equals(checkCoordinate)) return false;
		return true;
	}


	private void initializeVisualCoordinates(ArrayList<VisualCoordinate> visualBuffer) {
		Coordinate[] cubeCoordinates = new Coordinate[15]; 
		int xCoord, yCoord, currIndex = 0;
		for(int i = 0; i < 3; i++) {
			for(int n = 0; n < 5; n++) {
				xCoord = displayCoordinate.xCoord + (SCALE_MODIFIER * VisualTerritory.PIXEL_JUMP * i);
				yCoord = displayCoordinate.yCoord + (SCALE_MODIFIER * VisualTerritory.PIXEL_JUMP * n);
				cubeCoordinates[currIndex++] = new Coordinate(xCoord, yCoord);
			}
		}

		if(animatedCharacter.equals("0")) {
			selectCubeCoordinates(visualBuffer, cubeCoordinates[0]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[1]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[2]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[3]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[4]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[5]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[9]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[10]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[11]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[12]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[13]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[14]);
		}
		else if(animatedCharacter.equals("1")) {
			selectCubeCoordinates(visualBuffer, cubeCoordinates[0]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[4]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[5]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[6]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[7]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[8]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[9]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[14]);
		}
		else if(animatedCharacter.equals("2")) {
			selectCubeCoordinates(visualBuffer, cubeCoordinates[0]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[2]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[3]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[4]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[5]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[7]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[9]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[10]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[11]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[12]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[14]);
		}
		else if(animatedCharacter.equals("3")) {
			selectCubeCoordinates(visualBuffer, cubeCoordinates[0]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[2]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[4]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[5]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[7]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[9]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[10]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[11]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[12]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[13]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[14]);
		}
		else if(animatedCharacter.equals("4")) {
			selectCubeCoordinates(visualBuffer, cubeCoordinates[0]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[1]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[2]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[7]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[10]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[11]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[12]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[13]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[14]);
		}
		else if(animatedCharacter.equals("5")) {
			selectCubeCoordinates(visualBuffer, cubeCoordinates[0]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[1]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[2]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[4]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[5]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[7]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[9]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[10]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[12]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[13]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[14]);
		}
		else if(animatedCharacter.equals("6")) {
			selectCubeCoordinates(visualBuffer, cubeCoordinates[0]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[1]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[2]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[3]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[4]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[5]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[7]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[9]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[10]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[12]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[13]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[14]);

		}
		else if(animatedCharacter.equals("7")) {
			selectCubeCoordinates(visualBuffer, cubeCoordinates[0]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[5]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[10]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[11]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[12]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[13]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[14]);
		}
		else if(animatedCharacter.equals("8")) {
			selectCubeCoordinates(visualBuffer, cubeCoordinates[0]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[1]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[2]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[3]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[4]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[5]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[7]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[9]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[10]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[11]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[12]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[13]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[14]);
		}
		else if(animatedCharacter.equals("9")) {
			selectCubeCoordinates(visualBuffer, cubeCoordinates[0]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[1]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[2]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[4]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[5]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[7]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[9]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[10]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[11]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[12]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[13]);
			selectCubeCoordinates(visualBuffer, cubeCoordinates[14]);
		}
	}

	public void selectCubeCoordinates(ArrayList<VisualCoordinate> visualBuffer, Coordinate topLeftCoordinate) {
		int pixelJump = VisualTerritory.PIXEL_JUMP;
		int xCoordBoundry = topLeftCoordinate.xCoord + (SCALE_MODIFIER * pixelJump);
		int yCoordBoundry = topLeftCoordinate.yCoord + (SCALE_MODIFIER * pixelJump);
		for(int i = topLeftCoordinate.xCoord; i < xCoordBoundry; i += pixelJump)
			for(int j = topLeftCoordinate.yCoord; j < yCoordBoundry; j += pixelJump)
				for(int k = 0; k < visualBuffer.size(); k++)
					if(visualBuffer.get(k).pixelPosition.equals(new Coordinate(i, j))) {
						animateVisualCoordinates.add(visualBuffer.get(k)); 
						break;
					}
	}

}
