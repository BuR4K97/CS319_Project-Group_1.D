import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public class GeometricObject extends Transformable {
	
	private Polygon[] polygons;
	private int polygonCount;
	
	public GeometricObject(int polygonCount, Polygon[] polygons) {
		this.polygonCount = polygonCount;
		this.polygons = new Polygon[this.polygonCount];
		for(int i = 0; i < polygonCount; i++)
			this.polygons[i] = polygons[i];
	}

	public Polygon[] getPolygons() { return this.polygons; }
	public int getPolygonCount() { return this.polygonCount; }
	
	public void setPolygon(int polygonIndex, Polygon polygon) {
		if(polygonIndex < 0) return;
		if(polygonIndex >= polygonCount) return;
		
		polygons[polygonIndex] = polygon;
	}
	
	public Polygon getPolygon(int polygonIndex) {
		if(polygonIndex < 0) return null;
		if(polygonIndex >= polygonCount) return null;
		
		return polygons[polygonIndex];
	}
	
	public int getEdgeCount() {
		int edgeCount = 0;
		for(int i = 0; i < polygonCount; i++) edgeCount += polygons[i].getEdgeCount();
		return edgeCount;
	}
	
	public void moveObject(Vector3f moveVector) {
		super.positionModifier[0] += moveVector.x;
		super.positionModifier[1] += moveVector.y;
		super.positionModifier[2] += moveVector.z;
	}
	
	public void rotateObject(float rotationAngle, Vector3f rotationAxis) {
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
	
	public void scaleObject(float scalingFactor) { super.scalingFactor *= scalingFactor; }
	
	public void printGeometricObject() {
		for(int i = 0; i < polygonCount; i++)
			polygons[i].printPolygon();
	}
	
}//endClass
