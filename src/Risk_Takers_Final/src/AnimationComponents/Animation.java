package AnimationComponents;

public abstract class Animation {
	
	public boolean animationActive;
	public boolean terminating;
	
	public abstract boolean execute();
	protected abstract void terminate();

}
