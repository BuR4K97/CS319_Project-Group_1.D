import org.lwjgl.util.vector.Vector3f;

public class Polygon {
	
	public static int POSITION_DIMENSION = 3;
	public static int COLOR_DIMENSION = 4;
	
	private float[] edgePoints;
	private float[] edgeColorMultipliers;
	private float[] normalVector;
	private int edgeCount;
	
	public Polygon(int edgeCount) {
		this.edgeCount = edgeCount;
		this.edgePoints = new float[POSITION_DIMENSION * this.edgeCount];
		this.edgeColorMultipliers = new float[COLOR_DIMENSION * this.edgeCount];
		this.normalVector = new float[POSITION_DIMENSION];
	}
	
	public void setEdgePosition(int edgeIndex, float x, float y, float z) {
		if(edgeIndex < 0) return;
		if(edgeIndex >= this.edgeCount) return;
		
		edgePoints[edgeIndex * POSITION_DIMENSION] = x;
		edgePoints[edgeIndex * POSITION_DIMENSION + 1] = y;
		edgePoints[edgeIndex * POSITION_DIMENSION + 2] = z;
		if(edgeIndex == edgeCount - 1) calculateNormalVector();
	}
	
	public void setEdgeColor(int edgeIndex, float r, float g, float b, float a) {
		if(edgeIndex < 0) return;
		if(edgeIndex >= this.edgeCount) return;
		
		edgeColorMultipliers[edgeIndex * COLOR_DIMENSION] = r;
		edgeColorMultipliers[edgeIndex * COLOR_DIMENSION + 1] = g;
		edgeColorMultipliers[edgeIndex * COLOR_DIMENSION + 2] = b;
		edgeColorMultipliers[edgeIndex * COLOR_DIMENSION + 3] = a;
	}
	
	public int getEdgeCount() { return this.edgeCount; }
	public float[] getEdgePoints() { return this.edgePoints; }
	public float[] getEdgeColors() { return this.edgeColorMultipliers; }
	public float[] getNormalVector() { return this.normalVector; }
	public void calculateNormalVector() {
		Vector3f temp1 = new Vector3f(edgePoints[POSITION_DIMENSION] - edgePoints[0]
				,  edgePoints[POSITION_DIMENSION + 1] - edgePoints[1]
				, edgePoints[POSITION_DIMENSION + 2] - edgePoints[2]);
		Vector3f temp2 = new Vector3f(edgePoints[2 * POSITION_DIMENSION] - edgePoints[0]
				,  edgePoints[2 * POSITION_DIMENSION + 1] - edgePoints[1]
				, edgePoints[2 * POSITION_DIMENSION + 2] - edgePoints[2]);
		Vector3f cross = new Vector3f(); Vector3f.cross(temp1, temp2, cross);
		normalVector[0] = cross.x; normalVector[1] = cross.y; normalVector[2] = cross.z;
	}
	
	public void printPolygon() {
		System.out.println("Polygon with edges : ");
		for(int i = 0; i < edgeCount; i++)
			System.out.println("Edge (" + edgePoints[i * POSITION_DIMENSION] + ", " 
					+ edgePoints[i * POSITION_DIMENSION + 1] + ", "
					+ edgePoints[i * POSITION_DIMENSION + 2] + ")");
	}
	
}//endClass
