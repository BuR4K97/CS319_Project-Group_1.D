package AnimationComponents;

import java.util.ArrayList;

import ModelClasses.Territory;
import UIComponents.Coordinate;
import UIComponents.VisualTerritory;

public class AnimationHandler {

	private static ArrayList<Animation> animations = new ArrayList<Animation>();
	public static AnimationVisualBuffer visualBuffer = new AnimationVisualBuffer();

	
	public static void requestMouseOnTerritoryAnimation(VisualTerritory animateTerritory) {
		if(animateTerritory == null) return;
		
		for(Animation animation : animations)
			if(animation instanceof MouseOnTerritoryAnimation)
				if(((MouseOnTerritoryAnimation) animation).equals(animateTerritory)) {
					animation.terminating = false;
					return; 
				}

		animations.add(new MouseOnTerritoryAnimation(animateTerritory));
	}
	
	public static void requestAttackAnimation(ArrayList<VisualTerritory> vs, VisualTerritory source, VisualTerritory target
			, Territory sourceTerritory, Territory targetTerritory) {
		if(source == null && target == null) return;
		if(suspendArtificialIntelligenceUpdate()) return;
		
		for(Animation animation : animations)
			if(animation instanceof AttackAnimation) 
				return;

			animations.add(new AttackAnimation(vs, source, target, sourceTerritory, targetTerritory));
	}

	public static void requestMapBuildingAnimation(ArrayList<VisualTerritory> vs) {
		animations.add(new MapBuildingAnimation(vs));
	}
	
	public static void requestAnimationVisualBufferAttackAnimation(Territory sourceTerritory, Territory targetTerritory) {
		for(Animation animation : animations)
			if(animation instanceof AnimationVisualBufferAttackAnimation)
				return;

		animations.add(new AnimationVisualBufferAttackAnimation(sourceTerritory, targetTerritory));
	}
	
	public static void requestCharacterDisplayAnimation(String animatedCharacter, Coordinate displayCoordinate) {
		for(int i = 0; i < animations.size(); i++)
			if(animations.get(i) instanceof CharacterDisplayAnimation)
				if(((CharacterDisplayAnimation)animations.get(i)).equals(animatedCharacter, displayCoordinate))
					animations.remove(i);
		
		animations.add(new CharacterDisplayAnimation(animatedCharacter, displayCoordinate));
	}
	
	public static void requestArtificialIntelligenceAnimation() {
		animations.add(new ArtificialIntelligenceAnimation());
	}

	public static void terminateMouseOnTerritoryAnimation(VisualTerritory animateTerritory) {
		if(animateTerritory == null) return;

		for(Animation animation : animations)
			if(animation instanceof MouseOnTerritoryAnimation)
				if(((MouseOnTerritoryAnimation) animation).equals(animateTerritory)) {
					animation.terminating = true;
					return;
				}
	}
	
	public static void terminateAttackAnimation() {
		for(Animation animation : animations)
			if(animation instanceof AttackAnimation) {
				animation.terminating = true;
				return;
			}
	}
	
	public static void terminateAnimationVisualBufferAttackAnimation() {
		for(Animation animation : animations)
			if(animation instanceof AnimationVisualBufferAttackAnimation)
				animation.terminating = true;
	}

	public static void terminateCharacterDisplayAnimation(Coordinate displayCoordinate) {
		for(Animation animation : animations)
			if(animation instanceof CharacterDisplayAnimation)
				if(((CharacterDisplayAnimation)animation).equals(displayCoordinate))
					animation.terminating = true;
	}
	
	public static boolean suspendVisualTerritoryPanel() {
		for(Animation animation : animations) {
			if(animation instanceof AttackAnimation)
				 return true;
			if(animation instanceof MapBuildingAnimation)
				return true;
		}
		return false;
	}
	
	public static boolean suspendAttackAnimationTermination() {
		for(Animation animation : animations) {
			if(animation instanceof AnimationVisualBufferAttackAnimation)
				return true;
			if(animation instanceof CharacterDisplayAnimation)
				return true;
		}
		return false;
	}
	
	public static void executeAnimations() {
		for(int i = 0; i < animations.size(); i++) {
			if(!animations.get(i).execute())
				animations.remove(i--);
		}
	}

	public static boolean suspendTextualInGamePanelPaintEvent() {
		for(Animation animation : animations) {
			if(animation instanceof AnimationVisualBufferAttackAnimation)
				return true;
		}
		return false;
	}

	public static boolean suspendVisualCardPanelPaintEvent() {
		for(Animation animation : animations) {
			if(animation instanceof AnimationVisualBufferAttackAnimation)
				return true;
			if(animation instanceof MapBuildingAnimation)
				return true;
		}
		return false;
	}

	public static boolean suspendTextualInGamePanelUpdate() {
		for(Animation animation : animations) {
			if(animation instanceof AttackAnimation)
				return true;
			if(animation instanceof MapBuildingAnimation)
				return true;
		}
		return false;
	}
	
	public static boolean suspendArtificialIntelligenceUpdate() {
		for(Animation animation : animations)
			if(animation instanceof ArtificialIntelligenceAnimation)
				return true;
		return false;
	}
	
}
