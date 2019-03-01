import org.lwjgl.util.vector.Vector3f;

public class RenderableAragornObject implements SceneElements {

	public static GeometricObject[] aragornGeometricObjects;
	
	{
		if(aragornGeometricObjects == null) 
			System.out.println("LoadedMeshObject Class is not initialized! Please refer it first");
	}
	private RenderableType[] modifiableObject;
	
	public RenderableAragornObject() {
		modifiableObject = new RenderableType[aragornGeometricObjects.length];
		for(int i = 0; i < aragornGeometricObjects.length; i++)
			modifiableObject[i] = new RenderableType(aragornGeometricObjects[i].getPolygonCount()
					, aragornGeometricObjects[i].getPolygons());
	}
	
	public int getGeometricObjectCount() { return modifiableObject.length; }
	
	@Override
	public void render() {
		for(int i = 0; i < modifiableObject.length; i++) modifiableObject[i].render();
	}

	@Override
	public void update() {
		rotateObject(1.0f, new Vector3f(0, 1, 0));
		for(int i = 0; i < modifiableObject.length; i++) modifiableObject[i].update();
	}

	@Override
	public void destroy() {
		for(int i = 0; i < modifiableObject.length; i++) modifiableObject[i].destroy();
	}
	
	public void moveObject(Vector3f moveVector) {
		for(int i = 0; i < modifiableObject.length; i++) modifiableObject[i].moveObject(moveVector);
	}
	
	public void rotateObject(float rotationAngle, Vector3f rotationAxis) {
		for(int i = 0; i < modifiableObject.length; i++) modifiableObject[i].rotateObject(rotationAngle, rotationAxis);
	}
	
	public void scaleObject(float scalingFactor) {
		for(int i = 0; i < modifiableObject.length; i++) modifiableObject[i].scaleObject(scalingFactor);
	}
	
	public void print() {
		for(int i = 0; i < modifiableObject.length; i++) modifiableObject[i].printGeometricObject();
	}
	
}//endClass
