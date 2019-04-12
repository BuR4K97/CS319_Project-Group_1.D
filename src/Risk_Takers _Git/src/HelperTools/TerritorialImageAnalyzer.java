package HelperTools;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Scanner;

import UIComponents.Coordinate;
import UIComponents.VisualTerritory;

public class TerritorialImageAnalyzer {
	
	public static BufferedImage image;
	
	public static boolean constructTerritorialData(ArrayList<VisualTerritory> visualTerritories) {
		if(image == null) return false;
		
		int pixelJump = VisualTerritory.PIXEL_JUMP;

		Color pixelColor;
		for (int i = 0; i < 1920; i += pixelJump) {
			pixelCheck: for(int j = 0; j < 1080; j += pixelJump) {
				pixelColor = new Color(image.getRGB(i, j));

				for(int k = 0; k < visualTerritories.size() - 1; k++) {
					if(visualTerritories.get(k).color.equals(pixelColor)) {
						visualTerritories.get(k).coordinates.add(new Coordinate(i, j));
						continue pixelCheck;
					}
				}
				visualTerritories.get(visualTerritories.size() - 1).coordinates.add(new Coordinate(i, j));
			}
		}
		return true;

		
		/**
		final int COMBINATIONAL_RANGE_FOR_DIRECTION = 1;
		Color pixelColor, tempColor; int width = 0, height = 0; 
		boolean border;
		while(width < image.getWidth()) {
			while (height < image.getHeight()) {
				pixelColor = new Color(image.getRGB(width, height));
				border = false;
				
				for(int i = 0; i < visualTerritories.size(); i++) {
					if(visualTerritories.get(i).color.equals(pixelColor)) {
						lookBorder:for(int k = -1 * COMBINATIONAL_RANGE_FOR_DIRECTION; k < COMBINATIONAL_RANGE_FOR_DIRECTION; k++) {
							for(int n = -1 * COMBINATIONAL_RANGE_FOR_DIRECTION; n < COMBINATIONAL_RANGE_FOR_DIRECTION; n++) {
								tempColor = new Color(image.getRGB(width + k, height + n));
								if(!tempColor.equals(pixelColor)) { border = true; break lookBorder; }
							}
						}
						if(border) visualTerritories.get(i).border.add(new Coordinate(width, height));
						else visualTerritories.get(i).coordinates.add(new Coordinate(width, height));
						
						break;
					}
				}
				height++;
			}
			width++;
			height = 0;
		}
		
		//Scanner scan = new Scanner(System.in);
		final int RASTERIZATION_SCALE = 4; int[] offsets = new int[2];
		for(VisualTerritory currElement : visualTerritories) {
			currElement.print();
			currElement.coordinates = rasterizeCoordinates(currElement.coordinates, RASTERIZATION_SCALE);
			if(constructVisualBuffer(currElement, RASTERIZATION_SCALE, offsets))
				processVisualBuffer(currElement, RASTERIZATION_SCALE, offsets);
			//scan.nextLine();
			currElement.print();
			//scan.nextLine();
			System.out.println("Going next");
		}
		return true;
		**/
	}
	
	public static boolean constructVisualBuffer(VisualTerritory visualTerritory, int rasterization_scale, int[] offsets) {
		if(visualTerritory.coordinates.size() == 0) return false;
		
		int minXCoord = visualTerritory.coordinates.get(0).xCoord, minYCoord = visualTerritory.coordinates.get(0).yCoord;
		int maxXCoord = visualTerritory.coordinates.get(0).xCoord, maxYCoord = visualTerritory.coordinates.get(0).yCoord;
		for(int i = 1; i < visualTerritory.coordinates.size(); i++) {
			if(visualTerritory.coordinates.get(i).xCoord > maxXCoord)
				maxXCoord = visualTerritory.coordinates.get(i).xCoord;
			else if(visualTerritory.coordinates.get(i).xCoord < minXCoord)
				minXCoord = visualTerritory.coordinates.get(i).xCoord;
			
			if(visualTerritory.coordinates.get(i).yCoord > maxYCoord)
				maxYCoord = visualTerritory.coordinates.get(i).yCoord;
			else if(visualTerritory.coordinates.get(i).yCoord < minYCoord)
				minYCoord = visualTerritory.coordinates.get(i).yCoord;
		}
		int xCoordBufferSize = ((maxXCoord - minXCoord) / rasterization_scale) + 1;
		int yCoordBufferSize = ((maxYCoord - minYCoord) / rasterization_scale) + 1;
		visualTerritory.visualBuffer = new Coordinate[xCoordBufferSize][yCoordBufferSize];
		offsets[0] = minXCoord;
		offsets[1] = minYCoord;
		return true;
	}
	
	public static void processVisualBuffer(VisualTerritory visualTerritory, int rasterization_scale, int[] offsets) {
		int xIndex, yIndex;
		for(int i = 0; i < visualTerritory.coordinates.size(); i++) {
			xIndex = (visualTerritory.coordinates.get(i).xCoord - offsets[0]) / rasterization_scale;
			yIndex = (visualTerritory.coordinates.get(i).yCoord - offsets[1]) / rasterization_scale;
			visualTerritory.visualBuffer[xIndex][yIndex] = visualTerritory.coordinates.get(i);
			visualTerritory.coordinates.get(i).visualBufferXCoordIndex = xIndex;
			visualTerritory.coordinates.get(i).visualBufferYCoordIndex = yIndex;
		}
	}
	
	private static ArrayList<Coordinate> rasterizeCoordinates(ArrayList<Coordinate> coordinates, int rasterization_scale) {
		ArrayList<Coordinate> rasterizedCoordinates = new ArrayList<Coordinate>();
		Coordinate optimumPoint = findOptimumPathStart(coordinates);
		Adjacent currAdjacent = new Adjacent(optimumPoint);
		
		HelperQueue<Adjacent> queue = new HelperQueue<Adjacent>(); 
		queue.queue(currAdjacent); int currIndex = 0;
		Adjacent[] tempAdjacent = new Adjacent[1];
		while(currAdjacent != null) {
			currAdjacent.findAdjacentCoordinates(coordinates);
			currAdjacent.scaleAdjacentCoordinates(coordinates, rasterization_scale); 
			while(currAdjacent.executeAdjacent(tempAdjacent)) {
				if(tempAdjacent[0] == null) continue;
				
				queue.queue(tempAdjacent[0]);
				tempAdjacent[0] = null;
			}
			rasterizedCoordinates.add(currAdjacent.sourcePoint);
			currAdjacent = queue.retrieveElement(++currIndex);
		}
		return rasterizedCoordinates;
	}
	
	private static Coordinate findOptimumPathStart(ArrayList<Coordinate> coordinates) {
		int coordinateNumber = coordinates.size();
		int xCoordArithmeticSum = 0, yCoordArithmeticSum = 0;
		for(int n = 0; n < coordinateNumber; n++) {
			xCoordArithmeticSum += coordinates.get(n).xCoord;
			yCoordArithmeticSum += coordinates.get(n).yCoord;
		}
		Coordinate idealPoint = new Coordinate(xCoordArithmeticSum / coordinateNumber
				,  yCoordArithmeticSum / coordinateNumber);
		
		Coordinate optimumStart = coordinates.get(0);
		int optimumDistance = optimumStart.findDistance(idealPoint);
		
		Coordinate currPoint; int currDistance;
		for(int n = 1; n < coordinateNumber; n++) {
			currPoint = coordinates.get(n);
			currDistance = currPoint.findDistance(idealPoint);
			if(currDistance < optimumDistance) {
				optimumDistance = currDistance;
				optimumStart = currPoint;
			}
		}	
		return optimumStart;
	}
	
	private static class Adjacent {
		private static enum RASTERIZATION_PRIORITY { UP, RIGHT, DOWN, LEFT, TERMINATED };
		private static final int ADJACENCY_RANGE = 1;
		private static final int MAX_ADJACENT_NUMBER = 4;
		
		private Coordinate sourcePoint;
		private Coordinate[] adjacentCoordinates = new Coordinate[MAX_ADJACENT_NUMBER];
		private boolean[] directions = {false, false, false, false};
		private RASTERIZATION_PRIORITY currPriority = RASTERIZATION_PRIORITY.UP;
		
		private Adjacent(Coordinate sourcePoint) { 
			this.sourcePoint = sourcePoint;
		}
		
		public boolean equals(Object obj) {
			Adjacent check = (Adjacent) obj;
			if(sourcePoint == check.sourcePoint) {
				directions[0] = directions[0] || check.directions[0];
				directions[1] = directions[1] || check.directions[1];
				directions[2] = directions[2] || check.directions[2];
				directions[3] = directions[3] || check.directions[3];
				return true;
			}
			return false;
		}
		
		private void findAdjacentCoordinates(ArrayList<Coordinate> coordinates) {
			final int MAX_DISTANCE = (int)Math.pow(ADJACENCY_RANGE, 2);
			
			Coordinate currCoordinate;
			int coordinateNumber = coordinates.size(); int currIndex = 0;
			for(int i = 0; i < coordinateNumber; i++) {
				currCoordinate = coordinates.get(i);
				if(currCoordinate.findDistance(sourcePoint) <= MAX_DISTANCE) {
					if(currCoordinate != sourcePoint)
						adjacentCoordinates[currIndex++] = currCoordinate; 
				}
				if(currIndex == MAX_ADJACENT_NUMBER) break;
			}
		}
		
		private void scaleAdjacentCoordinates(ArrayList<Coordinate> coordinates, int rasterization_scale) {
			int coordinateNumber = coordinates.size();
			Coordinate linearCoordinate, currCoordinate; boolean scaled;
			for(int i = 0; i < MAX_ADJACENT_NUMBER; i++) {
				if(adjacentCoordinates[i] != null) {
					scaled = false;
					linearCoordinate = adjacentCoordinates[i];
					for(int n = 0; n < coordinateNumber; n++) {
						currCoordinate = coordinates.get(n);
						if(checkLinear(linearCoordinate, currCoordinate)) {
							if(currCoordinate.findDistance(sourcePoint) == rasterization_scale) {
								linearCoordinate = currCoordinate;
								scaled = true;
								break;
							}
						}
					}
					if(scaled) adjacentCoordinates[i] = linearCoordinate;
					else adjacentCoordinates[i] = null;
				}
			}
		}
		
		private boolean checkLinear(Coordinate adjacent, Coordinate check) {
			int xCoordDifferential = adjacent.xCoord - sourcePoint.xCoord;
			boolean direction = xCoordDifferential > 0;
			int yCoordDifferential = adjacent.yCoord - sourcePoint.yCoord;
			double slope = ((double)yCoordDifferential) / xCoordDifferential;
			xCoordDifferential = check.xCoord - sourcePoint.xCoord;
			yCoordDifferential = check.yCoord - sourcePoint.yCoord;
			if(slope == ((double)yCoordDifferential) / xCoordDifferential)
				return direction == xCoordDifferential > 0;
			return false;
		}
		
		private boolean executeAdjacent(Adjacent[] rasterizedAdjacents) {
			Coordinate adjacentCoordinate; int adjacentIndex;
			if(currPriority == RASTERIZATION_PRIORITY.UP) {
				if(!directions[0]) {
					adjacentIndex = findAdjacentIndex(RASTERIZATION_PRIORITY.UP);
					if(adjacentIndex != -1) {
						adjacentCoordinate = adjacentCoordinates[adjacentIndex];
						rasterizedAdjacents[0] = new Adjacent(adjacentCoordinate);
						rasterizedAdjacents[0].directions[2] = true;
					}
					directions[0] = true;
				}
				currPriority = RASTERIZATION_PRIORITY.RIGHT; 
				return true;
			}
			if(currPriority == RASTERIZATION_PRIORITY.RIGHT) {
				if(!directions[1]) {
					adjacentIndex = findAdjacentIndex(RASTERIZATION_PRIORITY.RIGHT);
					if(adjacentIndex != -1) {
						adjacentCoordinate = adjacentCoordinates[adjacentIndex];
						rasterizedAdjacents[0] = new Adjacent(adjacentCoordinate);
						rasterizedAdjacents[0].directions[3] = true;
					}
					directions[1] = true;
				}
				currPriority = RASTERIZATION_PRIORITY.DOWN; 
				return true;
			}
			if(currPriority == RASTERIZATION_PRIORITY.DOWN) {
				if(!directions[2]) {
					adjacentIndex = findAdjacentIndex(RASTERIZATION_PRIORITY.DOWN);
					if(adjacentIndex != -1) {
						adjacentCoordinate = adjacentCoordinates[adjacentIndex];
						rasterizedAdjacents[0] = new Adjacent(adjacentCoordinate);
						rasterizedAdjacents[0].directions[0] = true;
					}
					directions[2] = true;
				}
				currPriority = RASTERIZATION_PRIORITY.LEFT; 
				return true;
			}
			if(currPriority == RASTERIZATION_PRIORITY.LEFT) {
				if(!directions[3]) {
					adjacentIndex = findAdjacentIndex(RASTERIZATION_PRIORITY.LEFT);
					if(adjacentIndex != -1) {
						adjacentCoordinate = adjacentCoordinates[adjacentIndex];
						rasterizedAdjacents[0] = new Adjacent(adjacentCoordinate);
						rasterizedAdjacents[0].directions[1] = true;
					}
					directions[3] = true;
				}
				
				currPriority = RASTERIZATION_PRIORITY.TERMINATED; 
				return true;
			}
			else return false;
		}
		
		private int findAdjacentIndex(RASTERIZATION_PRIORITY direction) {
			Coordinate currAdjacent;
			if(direction == RASTERIZATION_PRIORITY.UP) {
				for(int i = 0; i < MAX_ADJACENT_NUMBER; i++) {
					currAdjacent = adjacentCoordinates[i];
					if(currAdjacent == null) continue;
					if(currAdjacent.xCoord == sourcePoint.xCoord && currAdjacent.yCoord < sourcePoint.yCoord)
						return i;
				}
			}
			else if(direction == RASTERIZATION_PRIORITY.RIGHT) {
				for(int i = 0; i < MAX_ADJACENT_NUMBER; i++) {
					currAdjacent = adjacentCoordinates[i];
					if(currAdjacent == null) continue;
					if(currAdjacent.xCoord > sourcePoint.xCoord && currAdjacent.yCoord == sourcePoint.yCoord)
						return i;
				}
			}
			else if(direction == RASTERIZATION_PRIORITY.DOWN) {
				for(int i = 0; i < MAX_ADJACENT_NUMBER; i++) {
					currAdjacent = adjacentCoordinates[i];
					if(currAdjacent == null) continue;
					if(currAdjacent.xCoord == sourcePoint.xCoord && currAdjacent.yCoord > sourcePoint.yCoord)
						return i;
				}
			}
			else {
				for(int i = 0; i < MAX_ADJACENT_NUMBER; i++) {
					currAdjacent = adjacentCoordinates[i];
					if(currAdjacent == null) continue;
					if(currAdjacent.xCoord < sourcePoint.xCoord && currAdjacent.yCoord == sourcePoint.yCoord)
						return i;
				}
			}
			return -1;
		}
		
	}//endInnerClass
	
	/**
	//Not working in correct manner
	private static ArrayList<Coordinate> extractBorderPath(ArrayList<Coordinate> borderCoordinates) {
		ArrayList<Coordinate> borderPath = new ArrayList<Coordinate>();
		borderPath.add(findOptimumPathStart(borderCoordinates));
		
		final int MAX_DISTANCE = 100000;
		Coordinate currCoordinate, closestCoordinate, possibleCoordinate; 
		int borderLength = borderCoordinates.size(), currDistance, closestDistance; 
		for(int i = 0; i < borderLength - 1; i++) {
			currCoordinate = borderPath.get(i);
			closestCoordinate = null;
			closestDistance = MAX_DISTANCE;
			findClosest:for(int n = 0; n < borderCoordinates.size(); n++) {
				possibleCoordinate = borderCoordinates.get(n);
				currDistance = currCoordinate.findDistance(possibleCoordinate);
				if(currDistance < closestDistance) {
				for(int k = 0; k < borderPath.size(); k++)
					if(possibleCoordinate == borderPath.get(k)) continue findClosest;
						closestDistance = currDistance;
				}
			}
			if(closestCoordinate != null)
				borderPath.add(closestCoordinate);
			else {
				i--;
				borderLength--;
			}
		}
		return borderPath;
	}
	**/
	
	/**
	private static ArrayList<Coordinate> rasterizeCoordinates(ArrayList<Coordinate> coordinates, final int MAX_DISTANCE) {
		ArrayList<Coordinate> rasterizedCoordinates = new ArrayList<Coordinate>();
		Coordinate startPoint = findOptimumPathStart(coordinates);
		Adjacent currAdjacent = new Adjacent(startPoint);
		
		HelperQueue<Adjacent> queue = new HelperQueue<Adjacent>();
		queue.queue(currAdjacent);
		Adjacent[] rasterizedAdjacents; int currIndex = 0;
		while(currAdjacent != null) {
			rasterizedAdjacents = new Adjacent[2];
			currAdjacent.findAdjacentCoordinates(coordinates);
			currAdjacent.scaleAdjacentCoordinates(coordinates, MAX_DISTANCE);
			while(currAdjacent.executeAdjacent(rasterizedAdjacents)) {
				if(rasterizedAdjacents[1] == null) continue;
				
				rasterizedCoordinates.add(currAdjacent.sourcePoint);
				rasterizedCoordinates.add(rasterizedAdjacents[0].sourcePoint);
				rasterizedCoordinates.add(rasterizedAdjacents[1].sourcePoint);
				queue.queue(rasterizedAdjacents[0]);
				queue.queue(rasterizedAdjacents[1]);
				rasterizedAdjacents = new Adjacent[2];
			} 
			currAdjacent = queue.retrieveElement(++currIndex);
			//currAdjacent = queue.dequeue();
		}
		return rasterizedCoordinates;
	}
	
	
	
	private static class Adjacent {
		private static enum RASTERIZATION_PRIORITY { UP, RIGHT, DOWN, LEFT, TERMINATED };
		private static final int ADJACENCY_RANGE = 1;
		private static final int MAX_ADJACENT_NUMBER = 4;
		
		private Coordinate sourcePoint;
		private Coordinate[] adjacentCoordinates = new Coordinate[MAX_ADJACENT_NUMBER];
		private boolean[] hierarchyDisrupt = {false, false, false, false};
		private boolean[] directions = {false, false, false, false};
		private RASTERIZATION_PRIORITY currPriority = RASTERIZATION_PRIORITY.UP;
		private  boolean process = true;
		
		
		private Adjacent(Coordinate sourcePoint) { 
			this.sourcePoint = sourcePoint;
		}
		
		public boolean equals(Object obj) {
			Adjacent check = (Adjacent) obj;
			if(sourcePoint == check.sourcePoint) {
				directions[0] = directions[0] || check.directions[0];
				directions[1] = directions[1] || check.directions[1];
				directions[2] = directions[2] || check.directions[2];
				directions[3] = directions[3] || check.directions[3];
				return true;
			}
			return false;
		}
		
		private void findAdjacentCoordinates(ArrayList<Coordinate> coordinates) {
			final int MAX_DISTANCE = (int)Math.pow(ADJACENCY_RANGE, 2);
			
			Coordinate currCoordinate;
			int coordinateNumber = coordinates.size(); int currIndex = 0;
			for(int i = 0; i < coordinateNumber; i++) {
				currCoordinate = coordinates.get(i);
				if(currCoordinate.findDistance(sourcePoint) <= MAX_DISTANCE) {
					if(currCoordinate != sourcePoint)
						adjacentCoordinates[currIndex++] = currCoordinate; 
				}
				if(currIndex == MAX_ADJACENT_NUMBER) break;
			}
			
			//for(int i = 0; i < MAX_ADJACENT_NUMBER; i++)
				//System.out.println(adjacentCoordinates[i]);
		}
		
		private void scaleAdjacentCoordinates(ArrayList<Coordinate> coordinates, final int MAX_DISTANCE) {
			int coordinateNumber = coordinates.size();
			Coordinate linearCoordinate, currCoordinate; int linearDistance, currDistance;
			for(int i = 0; i < MAX_ADJACENT_NUMBER; i++) {
				if(adjacentCoordinates[i] != null) {
					linearCoordinate = adjacentCoordinates[i];
					linearDistance = linearCoordinate.findDistance(sourcePoint);
					for(int n = 0; n < coordinateNumber; n++) {
						currCoordinate = coordinates.get(n);
						if(checkLinear(linearCoordinate, currCoordinate)) {
							if(currCoordinate == sourcePoint) continue;
							
							currDistance = currCoordinate.findDistance(sourcePoint);
							if(currDistance <= MAX_DISTANCE && currDistance > linearDistance) {
								linearCoordinate = currCoordinate;
								linearDistance = currDistance;
							}
						}
					}
					if(linearDistance != MAX_DISTANCE) hierarchyDisrupt[i] = true;
					adjacentCoordinates[i] = linearCoordinate;
				}
			}
		}
		
		private boolean checkLinear(Coordinate adjacent, Coordinate check) {
			int xCoordDifferential = adjacent.xCoord - sourcePoint.xCoord;
			int yCoordDifferential = adjacent.yCoord - sourcePoint.yCoord;
			double slope = ((double)yCoordDifferential) / xCoordDifferential;
			xCoordDifferential = check.xCoord - sourcePoint.xCoord;
			yCoordDifferential = check.yCoord - sourcePoint.yCoord;
			return slope == ((double)yCoordDifferential) / xCoordDifferential;
		}
		
		private boolean executeAdjacent(Adjacent[] rasterizedAdjacents) {
			if(!process) return false;
			
			Coordinate adjacentCoordinate; int adjacentIndex;
			if(currPriority == RASTERIZATION_PRIORITY.UP) {
				if(!directions[0]) {
					adjacentIndex = findAdjacentIndex(RASTERIZATION_PRIORITY.UP);
					if(adjacentIndex != -1) {
						adjacentCoordinate = adjacentCoordinates[adjacentIndex];
						rasterizedAdjacents[0] = new Adjacent(adjacentCoordinate);
						rasterizedAdjacents[0].process = !hierarchyDisrupt[adjacentIndex];
						//rasterizedAdjacents[0].directions[1] = true;
						rasterizedAdjacents[0].directions[2] = true;
						adjacentIndex = findAdjacentIndex(RASTERIZATION_PRIORITY.RIGHT);
						if(adjacentIndex != -1) {
							rasterizedAdjacents[1] = new Adjacent(adjacentCoordinates[adjacentIndex]);
							rasterizedAdjacents[1].process = !hierarchyDisrupt[adjacentIndex];
							//rasterizedAdjacents[1].directions[2] = true;
							rasterizedAdjacents[1].directions[3] = true;
						}
					}
				}
				directions[0] = true;
				currPriority = RASTERIZATION_PRIORITY.RIGHT; 
				return true;
			}
			if(currPriority == RASTERIZATION_PRIORITY.RIGHT) {
				if(!directions[1]) {
					adjacentIndex = findAdjacentIndex(RASTERIZATION_PRIORITY.RIGHT);
					if(adjacentIndex != -1) {
						adjacentCoordinate = adjacentCoordinates[adjacentIndex];
						rasterizedAdjacents[0] = new Adjacent(adjacentCoordinate);
						rasterizedAdjacents[0].process = !hierarchyDisrupt[adjacentIndex];
						//rasterizedAdjacents[0].directions[2] = true;
						rasterizedAdjacents[0].directions[3] = true;
						adjacentIndex = findAdjacentIndex(RASTERIZATION_PRIORITY.DOWN);
						if(adjacentIndex != -1) {
							rasterizedAdjacents[1] = new Adjacent(adjacentCoordinates[adjacentIndex]);
							rasterizedAdjacents[1].process = !hierarchyDisrupt[adjacentIndex];
							rasterizedAdjacents[1].directions[0] = true;
							//rasterizedAdjacents[1].directions[3] = true;
						}
					}
				}
				directions[1] = true;
				currPriority = RASTERIZATION_PRIORITY.DOWN; 
				return true;
			}
			if(currPriority == RASTERIZATION_PRIORITY.DOWN) {
				if(!directions[2]) {
					adjacentIndex = findAdjacentIndex(RASTERIZATION_PRIORITY.DOWN);
					if(adjacentIndex != -1) {
						adjacentCoordinate = adjacentCoordinates[adjacentIndex];
						rasterizedAdjacents[0] = new Adjacent(adjacentCoordinate);
						rasterizedAdjacents[0].process = !hierarchyDisrupt[adjacentIndex];
						rasterizedAdjacents[0].directions[0] = true;
						//rasterizedAdjacents[0].directions[3] = true;
						adjacentIndex = findAdjacentIndex(RASTERIZATION_PRIORITY.LEFT);
						if(adjacentIndex != -1) {
							rasterizedAdjacents[1] = new Adjacent(adjacentCoordinates[adjacentIndex]);
							rasterizedAdjacents[1].process = !hierarchyDisrupt[adjacentIndex];
							//rasterizedAdjacents[1].directions[0] = true;
							rasterizedAdjacents[1].directions[1] = true;
						}
					}
				}
				directions[2] = true;
				currPriority = RASTERIZATION_PRIORITY.LEFT; 
				return true;
			}
			if(currPriority == RASTERIZATION_PRIORITY.LEFT) {
				if(!directions[3]) {
					adjacentIndex = findAdjacentIndex(RASTERIZATION_PRIORITY.LEFT);
					if(adjacentIndex != -1) {
						adjacentCoordinate = adjacentCoordinates[adjacentIndex];
						rasterizedAdjacents[0] = new Adjacent(adjacentCoordinate);
						rasterizedAdjacents[0].process = !hierarchyDisrupt[adjacentIndex];
						//rasterizedAdjacents[0].directions[0] = true;
						rasterizedAdjacents[0].directions[1] = true;
						adjacentIndex = findAdjacentIndex(RASTERIZATION_PRIORITY.UP);
						if(adjacentIndex != -1) {
							rasterizedAdjacents[1] = new Adjacent(adjacentCoordinates[adjacentIndex]);
							rasterizedAdjacents[1].process = !hierarchyDisrupt[adjacentIndex];
							//rasterizedAdjacents[1].directions[1] = true;
							rasterizedAdjacents[1].directions[2] = true;
						}
					}
				}
				directions[3] = true;
				currPriority = RASTERIZATION_PRIORITY.TERMINATED; 
				return true;
			}
			else return false;
		}
		
		private int findAdjacentIndex(RASTERIZATION_PRIORITY direction) {
			Coordinate currAdjacent;
			if(direction == RASTERIZATION_PRIORITY.UP) {
				for(int i = 0; i < MAX_ADJACENT_NUMBER; i++) {
					currAdjacent = adjacentCoordinates[i];
					if(currAdjacent == null) continue;
					if(currAdjacent.xCoord == sourcePoint.xCoord && currAdjacent.yCoord < sourcePoint.yCoord)
						return i;
				}
			}
			else if(direction == RASTERIZATION_PRIORITY.RIGHT) {
				for(int i = 0; i < MAX_ADJACENT_NUMBER; i++) {
					currAdjacent = adjacentCoordinates[i];
					if(currAdjacent == null) continue;
					if(currAdjacent.xCoord > sourcePoint.xCoord && currAdjacent.yCoord == sourcePoint.yCoord)
						return i;
				}
			}
			else if(direction == RASTERIZATION_PRIORITY.DOWN) {
				for(int i = 0; i < MAX_ADJACENT_NUMBER; i++) {
					currAdjacent = adjacentCoordinates[i];
					if(currAdjacent == null) continue;
					if(currAdjacent.xCoord == sourcePoint.xCoord && currAdjacent.yCoord > sourcePoint.yCoord)
						return i;
				}
			}
			else {
				for(int i = 0; i < MAX_ADJACENT_NUMBER; i++) {
					currAdjacent = adjacentCoordinates[i];
					if(currAdjacent == null) continue;
					if(currAdjacent.xCoord < sourcePoint.xCoord && currAdjacent.yCoord == sourcePoint.yCoord)
						return i;
				}
			}
			return -1;
		}
		
	}
	**/
}
