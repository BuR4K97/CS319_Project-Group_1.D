package AnimationComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import AnimationComponents.AnimationVisualBuffer.VisualCoordinate.DRAW_MODE;
import Controller.GameController;
import Controller.MainApplication;
import GameAssets.DefaultRiskMode.DefaultRiskMode;
import UIComponents.ApplicationFrame;
import UIComponents.Coordinate;
import UIComponents.VisualTerritory;

public class AnimationVisualBuffer {
	
	public ArrayList<VisualCoordinate> visualBuffer;
	private static enum PAINT_MODE { SEA_MODE, ATTACK_ANIMATION_MODE, NONE };
	private PAINT_MODE paintMode = PAINT_MODE.NONE;
	
	public void createSeaAnimationBuffer() {
		paintMode = PAINT_MODE.SEA_MODE;
		visualBuffer = new ArrayList<VisualCoordinate>();
		ArrayList<Coordinate> coordinates;
		if(GameController.activeMode instanceof DefaultRiskMode) {
			coordinates = ((DefaultRiskMode)GameController.activeMode).getVisualTerritory(DefaultRiskMode.TERRITORIES.SEA)
					.coordinates;
			for(int i = 0; i < coordinates.size(); i++)
				visualBuffer.add(new VisualCoordinate(coordinates.get(i)));
		}
	}
	
	public void createAttackAnimationBuffer() {
		paintMode = PAINT_MODE.ATTACK_ANIMATION_MODE;
		visualBuffer = new ArrayList<VisualCoordinate>();
		for(int i = 0; i < ApplicationFrame.width; i += VisualTerritory.PIXEL_JUMP) {
			for(int n = 0; n < ApplicationFrame.height; n += VisualTerritory.PIXEL_JUMP) {
				visualBuffer.add(new VisualCoordinate(new Coordinate(i, n)));
			}
		}
	}
	
	public void destroyBuffer() {
		paintMode = PAINT_MODE.NONE;
		visualBuffer = null;
	}
	
	public void paint(Graphics painter) {
		if(visualBuffer == null) return;
		
		if(paintMode == PAINT_MODE.SEA_MODE)
			paintVisualBufferInSeaMode(painter);
		else if(paintMode == PAINT_MODE.ATTACK_ANIMATION_MODE)
			paintVisualBufferInAttackAnimationMode(painter);
	}
	
	private void paintVisualBufferInSeaMode(Graphics painter) {
		
	}
	
	private void paintVisualBufferInAttackAnimationMode(Graphics painter) {
		for(VisualCoordinate coordinate : visualBuffer) {
			if(coordinate.pixelColor == null) continue;
			
			if(coordinate.drawMode == DRAW_MODE.NORMAL) {
				painter.setColor(coordinate.pixelColor);
				painter.fillRect(coordinate.pixelPosition.xCoord, coordinate.pixelPosition.yCoord
						, coordinate.drawSize, coordinate.drawSize); 
			}
			else if(coordinate.drawMode == DRAW_MODE.BLACK) {
				painter.setColor(Color.BLACK);
				painter.fillRect(coordinate.pixelPosition.xCoord, coordinate.pixelPosition.yCoord
						, coordinate.drawSize, coordinate.drawSize); 
			}
		}
	}
	
	public static class VisualCoordinate {
		public static enum DRAW_MODE { NORMAL, BLACK};
		
		public Coordinate pixelPosition;
		public Color pixelColor;
		public DRAW_MODE drawMode = DRAW_MODE.NORMAL;
		public int drawSize = VisualTerritory.DEFAULT_DRAW_SIZE;
		
		private VisualCoordinate(Coordinate pixelPosition) {
			this.pixelPosition = pixelPosition;
		}
		
	}
	
}
