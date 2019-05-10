package AnimationComponents;

import UIComponents.VisualTerritory;

public class MouseOnTerritoryAnimation extends Animation {

	private static final int LOWER_BOUND = VisualTerritory.DEFAULT_DRAW_SIZE;
	private static final int UPPER_BOUND = VisualTerritory.PIXEL_JUMP;
	private static final double ANIMATION_MODIFIER = (UPPER_BOUND - LOWER_BOUND) / 13.0;
	
	private VisualTerritory animateTerritory;
	private double drawSize;
	
	public MouseOnTerritoryAnimation(VisualTerritory animateTerritory) {
		this.animateTerritory = animateTerritory;
		drawSize = LOWER_BOUND;
	}
	
	@Override
	public boolean execute() {
		if(terminating) 
			drawSize -= ANIMATION_MODIFIER;
		else if(animateTerritory.drawSize < UPPER_BOUND)
			drawSize += ANIMATION_MODIFIER;
		
		animateTerritory.drawSize = (int) drawSize;
		if(animateTerritory.drawSize < LOWER_BOUND) {
			terminate();
			return false;
		}
		return true;
	}

	public boolean equals(VisualTerritory check) {
		return animateTerritory == check;
	}

	@Override
	protected void terminate() {
		animateTerritory.drawSize = LOWER_BOUND;
	}
	
}
