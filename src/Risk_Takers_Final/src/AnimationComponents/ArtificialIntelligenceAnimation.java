package AnimationComponents;

public class ArtificialIntelligenceAnimation extends Animation {

	private static final int DELAY = 40;
	private int executeNumber = 0; 
	
	@Override
	public boolean execute() {
		executeNumber++;
		if(executeNumber < DELAY) return true;
		return false;
	}

	@Override
	protected void terminate() {}

}
