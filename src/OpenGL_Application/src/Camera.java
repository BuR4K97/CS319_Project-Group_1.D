import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public class Camera extends Transformable {
	
	//In Vertex Shader, view transform applied first and then projection
	private float fieldOfView = 70.0f;
	private float aspectRatio = (float)OpenGLWindow.resolutionWidth / (float)OpenGLWindow.resolutionWidth;
    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE = 300.0f;

	public Matrix4f getViewMatrix() { 
		Matrix4f viewMatrix = new Matrix4f();
		viewMatrix.translate(new Vector3f(-super.positionModifier[0], -super.positionModifier[1]
				, -super.positionModifier[2]));
		viewMatrix.rotate(-super.rotationAngle, new Vector3f(super.rotationAxis[0], 
				super.rotationAxis[1], super.rotationAxis[2]));
		return viewMatrix;
	}
	
	public Matrix4f getProjectionMatrix() {
		Matrix4f projectionMatrix = new Matrix4f();
		float yScale = 1.0f / (float) Math.tan((Math.PI * (fieldOfView / 2.0f) / 180));
		float xScale = yScale / aspectRatio;
        float frustumLength = FAR_PLANE - NEAR_PLANE;
        
        projectionMatrix.m00 = xScale;
        projectionMatrix.m11 = yScale;
        projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustumLength);
        projectionMatrix.m23 = 1;
        projectionMatrix.m32 = ((2 * NEAR_PLANE * FAR_PLANE) / frustumLength);
        projectionMatrix.m33 = 0;
        return projectionMatrix;
	}
	
	public void setFieldOfView(float angle) {
		if(angle > 180) return;
		fieldOfView = angle;
	}
	
	public void moveCamera(Vector3f moveVector) {
		super.positionModifier[0] += moveVector.x;
		super.positionModifier[1] += moveVector.y;
		super.positionModifier[2] += moveVector.z;
	}
	
	public void rotateCamera(float rotationAngle, Vector3f rotationAxis) {
		if(rotationAngle == 0) return;
		
		Matrix4f rotateMatrix = new Matrix4f();
		super.rotationAngle = (float)(Math.PI * super.rotationAngle / 180.0f);
		rotateMatrix.rotate(super.rotationAngle, new Vector3f(super.rotationAxis[0],
				super.rotationAxis[1], super.rotationAxis[2]));
		rotateMatrix.rotate((float)(Math.PI * rotationAngle / 180.0f), rotationAxis);
		
		//Setting rotation axis
		rotationAxis = new Vector3f(rotateMatrix.m21 - rotateMatrix.m12, rotateMatrix.m02 - rotateMatrix.m20
				, rotateMatrix.m10 - rotateMatrix.m01);
		rotationAxis.normalise();
		if(Float.isNaN(rotationAxis.x) || Float.isNaN(rotationAxis.y) || Float.isNaN(rotationAxis.z)) { 
			super.rotationAngle = 0.0f; 
			return; 
		}
		
		//Calculating rotation angle
		Vector3f temp = new Vector3f(1, 0, 0); Vector3f perpendicular = new Vector3f();
		Vector3f.cross(rotationAxis, temp, perpendicular);
		if(perpendicular.length() == 0) {
			temp.set(0, 1, 0);
			Vector3f.cross(rotationAxis, temp, perpendicular);
		}
		Vector4f rotated = new Vector4f();
		Matrix4f.transform(rotateMatrix, new Vector4f(perpendicular.x, perpendicular.y, perpendicular.z
				, 1), rotated);
		temp.set(rotated.x, rotated.y, rotated.z);
		
		Vector3f cross = new Vector3f(), relativeAxis  = new Vector3f();
		Vector3f.cross(perpendicular, temp, cross); Vector3f.cross(cross, temp, relativeAxis);
		float sinusOfAngle = cross.length() / (perpendicular.length() * temp.length());
		if(Vector3f.dot(perpendicular, relativeAxis) > 0) sinusOfAngle *= -1;
		double angle = 180 * Math.asin(sinusOfAngle) / Math.PI;
		if(Vector3f.dot(perpendicular, temp) < 0) angle = 180 - angle;
		
		super.rotationAngle = (float)(-angle);
		super.rotationAxis[0] = rotationAxis.x;
		super.rotationAxis[1] = rotationAxis.y;
		super.rotationAxis[2] = rotationAxis.z;
	}
}
