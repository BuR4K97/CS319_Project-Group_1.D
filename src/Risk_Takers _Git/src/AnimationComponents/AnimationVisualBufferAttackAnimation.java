package AnimationComponents;

import java.awt.Color;
import java.math.BigInteger;
import java.util.ArrayList;

import AnimationComponents.AnimationVisualBuffer.VisualCoordinate;
import Controller.GameController;
import ModelClasses.Combat;
import ModelClasses.Player;
import ModelClasses.Territory;
import UIComponents.ApplicationFrame;
import UIComponents.Coordinate;
import UIComponents.VisualTerritory;

public class AnimationVisualBufferAttackAnimation extends Animation {
	
	private Territory sourceTerritory;
	private Territory targetTerritory;
	private Player sourcePlayer, targetPlayer;
	private ArrayList<VisualCoordinate> visualBuffer;
	private boolean terminateImmediate;
	
	public AnimationVisualBufferAttackAnimation(Territory sourceTerritory, Territory targetTerritory) {
		this.sourceTerritory = sourceTerritory;
		this.targetTerritory = targetTerritory;
		sourcePlayer = sourceTerritory.getPlayer();
		targetPlayer = targetTerritory.getPlayer();
		if(Combat.combatable(sourceTerritory, targetTerritory)) {
			AnimationHandler.visualBuffer.createAttackAnimationBuffer();
			visualBuffer = AnimationHandler.visualBuffer.visualBuffer; 
		}
		else terminateImmediate = true;
	}

	private boolean firstPhase = false;
	private int leftCurrIndex = 0, rightCurrIndex = ApplicationFrame.width - VisualTerritory.PIXEL_JUMP;
	private Coordinate targetCoordinate;
	private Coordinate leftTerminatingTargetCoordinate = new Coordinate(-VisualTerritory.PIXEL_JUMP, 0);
	private Coordinate rightTerminatingTargetCoordinate = new Coordinate(ApplicationFrame.width, 0);
	ArrayList<Integer> updateIndex = new ArrayList<Integer>();
	private int recentLeft, recentRight;
	private boolean leftDirection = true, rightDirection = true;
	@Override
	public boolean execute() {
		if(terminateImmediate) return false; 
		
		boolean executing = true;
		if(!firstPhase) update();
		
		if(firstPhase) {
			if(updateIndex.size() == 0) {
				recentLeft = (int)leftCurrIndex;
				updateIndex.add(recentLeft);
				recentRight = (int)rightCurrIndex;
				updateIndex.add(recentRight);
			}
			else {
				updateIndex.clear();
				if(leftDirection) {
					while(recentLeft < leftCurrIndex) {
						recentLeft += VisualTerritory.PIXEL_JUMP;
						updateIndex.add(recentLeft);
					}
				}
				if(rightDirection) {
					while(recentRight > rightCurrIndex) {
						recentRight -= VisualTerritory.PIXEL_JUMP;
						updateIndex.add(recentRight);
					} 
				}
			}
			for(VisualCoordinate visualCoordinate : visualBuffer) {
				if(updateIndex.contains(visualCoordinate.pixelPosition.xCoord)) {
					if(visualCoordinate.pixelPosition.xCoord < targetCoordinate.xCoord)
						visualCoordinate.pixelColor = Color.RED;
					else
						visualCoordinate.pixelColor = Color.BLUE;
				}
			}
			
			int leftRemaining = targetCoordinate.xCoord - leftCurrIndex;
			int rightRemaining = rightCurrIndex - targetCoordinate.xCoord;
			if(leftRemaining != 0 && rightRemaining != 0) {
				int normalizationModifier = normalizationModifier(leftRemaining, rightRemaining);
				normalizationModifier = (int)Math.abs(normalizationModifier);
				leftCurrIndex += VisualTerritory.PIXEL_JUMP * (leftRemaining / normalizationModifier);
				rightCurrIndex -= VisualTerritory.PIXEL_JUMP * (rightRemaining / normalizationModifier);
				leftDirection = leftRemaining > 0;
				rightDirection = rightRemaining > 0;
			}
			else {
				recentLeft = leftCurrIndex - VisualTerritory.PIXEL_JUMP;
				recentRight = rightCurrIndex + VisualTerritory.PIXEL_JUMP;
				firstPhase = false;
			}
		}
		else if(terminating) {
			AnimationHandler.terminateCharacterDisplayAnimation(DISPLAY_CHARACTER_COORDINATES[0]);
			AnimationHandler.terminateCharacterDisplayAnimation(DISPLAY_CHARACTER_COORDINATES[1]);
			AnimationHandler.terminateCharacterDisplayAnimation(DISPLAY_CHARACTER_COORDINATES[2]);
			AnimationHandler.terminateCharacterDisplayAnimation(DISPLAY_CHARACTER_COORDINATES[3]);
			recentLeft += VisualTerritory.PIXEL_JUMP;
			recentRight -= VisualTerritory.PIXEL_JUMP;
			updateIndex.clear();
			while(recentLeft > leftCurrIndex) {
				updateIndex.add(recentLeft);
				recentLeft -= VisualTerritory.PIXEL_JUMP;
			}
			while(recentRight < rightCurrIndex) {
				updateIndex.add(recentRight);
				recentRight += VisualTerritory.PIXEL_JUMP;
			} 
			for(VisualCoordinate visualCoordinate : visualBuffer) {
				if(updateIndex.contains(visualCoordinate.pixelPosition.xCoord))
					visualCoordinate.pixelColor = null;
			}
			int leftRemaining = leftCurrIndex - leftTerminatingTargetCoordinate.xCoord;
			int rightRemaining = rightTerminatingTargetCoordinate.xCoord - rightCurrIndex;
			if(leftCurrIndex > -VisualTerritory.PIXEL_JUMP || rightCurrIndex < ApplicationFrame.width) {
				int normalizationModifier = normalizationModifier(1 , leftRemaining);
				normalizationModifier = (int)Math.abs(normalizationModifier);
				leftCurrIndex -= VisualTerritory.PIXEL_JUMP * (leftRemaining / normalizationModifier);
				normalizationModifier = normalizationModifier(1 , rightRemaining);
				normalizationModifier = (int)Math.abs(normalizationModifier);
				rightCurrIndex += VisualTerritory.PIXEL_JUMP * (rightRemaining / normalizationModifier);
			}
			else executing = false; 
		}
		
		if(!executing) {
			terminate();
			return false;
		}
		return true;
	}
	
	
	private static final Coordinate[] DISPLAY_CHARACTER_COORDINATES = new Coordinate[] {
			new Coordinate(VisualTerritory.PIXEL_JUMP * 23, VisualTerritory.PIXEL_JUMP * 5)
			, new Coordinate(VisualTerritory.PIXEL_JUMP * 43, VisualTerritory.PIXEL_JUMP * 5)
			, new Coordinate(VisualTerritory.PIXEL_JUMP * 73, VisualTerritory.PIXEL_JUMP * 5)
			, new Coordinate(VisualTerritory.PIXEL_JUMP * 93, VisualTerritory.PIXEL_JUMP * 5)
	};
	private void update() {
		Coordinate temp = targetCoordinate;
		int source = sourceTerritory.getUnitNumber();
		int target = targetTerritory.getUnitNumber();
		boolean victory = false;
		if(targetTerritory.getPlayer() != targetPlayer) {
			targetCoordinate = new Coordinate(ApplicationFrame.width, 0);
			victory = true;
		}
		else if(sourceTerritory.getUnitNumber() < Combat.MIN_ATTACK_UNIT)
			targetCoordinate = new Coordinate(-VisualTerritory.PIXEL_JUMP, 0); 
		else 
			targetCoordinate = VisualTerritory.getIndexedCoordinate( ((double)source) / (target + source), 0);
		if(temp == null) {
			AnimationHandler.requestCharacterDisplayAnimation(Integer.toString(source / 10), DISPLAY_CHARACTER_COORDINATES[0]);
			AnimationHandler.requestCharacterDisplayAnimation(Integer.toString(source % 10), DISPLAY_CHARACTER_COORDINATES[1]);
			AnimationHandler.requestCharacterDisplayAnimation(Integer.toString(target / 10), DISPLAY_CHARACTER_COORDINATES[2]);
			AnimationHandler.requestCharacterDisplayAnimation(Integer.toString(target % 10), DISPLAY_CHARACTER_COORDINATES[3]);
			firstPhase = true;
		}
		else if(temp.findDistance(targetCoordinate) != 0) {
			AnimationHandler.terminateCharacterDisplayAnimation(DISPLAY_CHARACTER_COORDINATES[0]);
			AnimationHandler.terminateCharacterDisplayAnimation(DISPLAY_CHARACTER_COORDINATES[1]);
			AnimationHandler.terminateCharacterDisplayAnimation(DISPLAY_CHARACTER_COORDINATES[2]);
			AnimationHandler.terminateCharacterDisplayAnimation(DISPLAY_CHARACTER_COORDINATES[3]);
			if(!victory) {
				AnimationHandler.requestCharacterDisplayAnimation(Integer.toString(source / 10)
						, DISPLAY_CHARACTER_COORDINATES[0]);
				AnimationHandler.requestCharacterDisplayAnimation(Integer.toString(source % 10)
						, DISPLAY_CHARACTER_COORDINATES[1]);
				AnimationHandler.requestCharacterDisplayAnimation(Integer.toString(target / 10)
						, DISPLAY_CHARACTER_COORDINATES[2]);
				AnimationHandler.requestCharacterDisplayAnimation(Integer.toString(target % 10)
						, DISPLAY_CHARACTER_COORDINATES[3]);
			}
			else {
				AnimationHandler.requestCharacterDisplayAnimation(Integer.toString((source + target) / 10)
						, DISPLAY_CHARACTER_COORDINATES[0]);
				AnimationHandler.requestCharacterDisplayAnimation(Integer.toString((source + target) % 10)
						, DISPLAY_CHARACTER_COORDINATES[1]);
				AnimationHandler.requestCharacterDisplayAnimation(Integer.toString(0), DISPLAY_CHARACTER_COORDINATES[2]);
				AnimationHandler.requestCharacterDisplayAnimation(Integer.toString(0), DISPLAY_CHARACTER_COORDINATES[3]);
			}
			firstPhase = true; 
		}
	}
	
	private static int normalizationModifier(int left, int right) {
		final int UPPER_BOUND = 4;
		BigInteger temp1 = BigInteger.valueOf(left);
		BigInteger temp2 = BigInteger.valueOf(right);
		int gcd = temp1.gcd(temp2).intValue();
		int normalizationModifier = gcd;
		while((left + right) / normalizationModifier > UPPER_BOUND)
			normalizationModifier += gcd;
		return normalizationModifier;
	}

	@Override
	protected void terminate() {
		AnimationHandler.visualBuffer.destroyBuffer();
	}
	
	

}
