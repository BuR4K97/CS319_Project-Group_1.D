import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public abstract class Transformable {
	
	public static int MATRIX4_ELEMENT_COUNT = 16;
	
	protected float[] positionModifier;
	protected float[] rotationAxis;
	protected float rotationAngle;
	protected float scalingFactor;

	public Transformable() {
		this.positionModifier = new float[] {0.0f, 0.0f, 0.0f};
		this.rotationAxis = new float[] {0.0f, 0.0f, 1.0f}; //Around z-axis
		this.rotationAngle = 0.0f;
		this.scalingFactor = 1.0f;
	}

	public float[] getPositionModifier() { return positionModifier; }

	public void setPositionModifier(float x, float y, float z) { 
		positionModifier[0] = x;
		positionModifier[1] = y;
		positionModifier[2] = z;
	}

	public float[] getRotationAxis() { return rotationAxis; }

	public void setRotationAxis(float vectorX, float vectorY, float vectorZ) {
		rotationAxis[0] = vectorX;
		rotationAxis[1] = vectorY;
		rotationAxis[2] = vectorZ;
	}

	public float getRotationAngle() {
		return rotationAngle;
	}

	public void setRotationAngle(float rotationAngle) {
		this.rotationAngle = rotationAngle;
	}

	public float getScalingFactor() {
		return scalingFactor;
	}

	public void setScalingFactor(float scalingFactor) {
		this.scalingFactor = scalingFactor;
	}
	
	public Matrix4f getModelTransform() {
		Matrix4f modelTransform = new Matrix4f();
		modelTransform.translate(new Vector3f(positionModifier[0], positionModifier[1], positionModifier[2]));
		modelTransform.rotate((float)(Math.PI * rotationAngle / 180.0f),
				new Vector3f(rotationAxis[0], rotationAxis[1], rotationAxis[2]));
		modelTransform.scale(new Vector3f(scalingFactor, scalingFactor, scalingFactor));
		return modelTransform;
	}
}
