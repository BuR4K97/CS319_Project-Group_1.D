import org.lwjgl.util.vector.Vector3f;

public class RenderableCube extends RenderableType {

	public static final int CUBE_POLYGON_COUNT = 6;	
	public static Polygon[] QUAD_POLYGONS = new Polygon[CUBE_POLYGON_COUNT];
	static {
		QUAD_POLYGONS[0] = RenderableQuad.QUAD_POLYGON;
		QUAD_POLYGONS[1] = new Polygon(RenderableQuad.QUAD_EDGE_COUNT);
		QUAD_POLYGONS[2] = new Polygon(RenderableQuad.QUAD_EDGE_COUNT);
		QUAD_POLYGONS[3] = new Polygon(RenderableQuad.QUAD_EDGE_COUNT);
		QUAD_POLYGONS[4] = new Polygon(RenderableQuad.QUAD_EDGE_COUNT);
		QUAD_POLYGONS[5] = new Polygon(RenderableQuad.QUAD_EDGE_COUNT);
		
		QUAD_POLYGONS[1].setEdgePosition(0, 0.5f, 0.5f, 0.5f);
		QUAD_POLYGONS[1].setEdgePosition(1, 0.5f, 0.5f, -0.5f);
		QUAD_POLYGONS[1].setEdgePosition(2, 0.5f, -0.5f, -0.5f);
		QUAD_POLYGONS[1].setEdgePosition(3, 0.5f, -0.5f, 0.5f);
		QUAD_POLYGONS[1].setEdgeColor(0, 0.0f, 1.0f, 0.0f, 1.0f);
		QUAD_POLYGONS[1].setEdgeColor(1, 0.0f, 1.0f, 0.0f, 1.0f);
		QUAD_POLYGONS[1].setEdgeColor(2, 0.0f, 1.0f, 0.0f, 1.0f);
		QUAD_POLYGONS[1].setEdgeColor(3, 0.0f, 1.0f, 0.0f, 1.0f);
		
		QUAD_POLYGONS[2].setEdgePosition(0, 0.5f, 0.5f, 0.5f);
		QUAD_POLYGONS[2].setEdgePosition(1, 0.5f, 0.5f, -0.5f);
		QUAD_POLYGONS[2].setEdgePosition(2, -0.5f, 0.5f, -0.5f);
		QUAD_POLYGONS[2].setEdgePosition(3, -0.5f, 0.5f, 0.5f);
		QUAD_POLYGONS[2].setEdgeColor(0, 0.0f, 1.0f, 0.0f, 1.0f);
		QUAD_POLYGONS[2].setEdgeColor(1, 0.0f, 1.0f, 0.0f, 1.0f);
		QUAD_POLYGONS[2].setEdgeColor(2, 0.0f, 1.0f, 0.0f, 1.0f);
		QUAD_POLYGONS[2].setEdgeColor(3, 0.0f, 1.0f, 0.0f, 1.0f);
		/**
		QUAD_POLYGONS[2].setEdgeColor(0, 0.0f, 0.0f, 1.0f, 1.0f);
		QUAD_POLYGONS[2].setEdgeColor(1, 0.0f, 0.0f, 1.0f, 1.0f);
		QUAD_POLYGONS[2].setEdgeColor(2, 0.0f, 0.0f, 1.0f, 1.0f);
		QUAD_POLYGONS[2].setEdgeColor(3, 0.0f, 0.0f, 1.0f, 1.0f);
		**/
		
		QUAD_POLYGONS[3].setEdgePosition(0, -0.5f, 0.5f, 0.5f);
		QUAD_POLYGONS[3].setEdgePosition(1, -0.5f, 0.5f, -0.5f);
		QUAD_POLYGONS[3].setEdgePosition(2, -0.5f, -0.5f, -0.5f);
		QUAD_POLYGONS[3].setEdgePosition(3, -0.5f, -0.5f, 0.5f);
		QUAD_POLYGONS[3].setEdgeColor(0, 0.0f, 1.0f, 0.0f, 1.0f);
		QUAD_POLYGONS[3].setEdgeColor(1, 0.0f, 1.0f, 0.0f, 1.0f);
		QUAD_POLYGONS[3].setEdgeColor(2, 0.0f, 1.0f, 0.0f, 1.0f);
		QUAD_POLYGONS[3].setEdgeColor(3, 0.0f, 1.0f, 0.0f, 1.0f);
		/**
		QUAD_POLYGONS[3].setEdgeColor(0, 0.33f, 0.33f, 0.33f, 1.0f);
		QUAD_POLYGONS[3].setEdgeColor(1, 0.33f, 0.33f, 0.33f, 1.0f);
		QUAD_POLYGONS[3].setEdgeColor(2, 0.33f, 0.33f, 0.33f, 1.0f);
		QUAD_POLYGONS[3].setEdgeColor(3, 0.33f, 0.33f, 0.33f, 1.0f);
		**/
		
		QUAD_POLYGONS[4].setEdgePosition(0, -0.5f, -0.5f, 0.5f);
		QUAD_POLYGONS[4].setEdgePosition(1, -0.5f, -0.5f, -0.5f);
		QUAD_POLYGONS[4].setEdgePosition(2, 0.5f, -0.5f, -0.5f);
		QUAD_POLYGONS[4].setEdgePosition(3, 0.5f, -0.5f, 0.5f);
		QUAD_POLYGONS[4].setEdgeColor(0, 0.0f, 1.0f, 0.0f, 1.0f);
		QUAD_POLYGONS[4].setEdgeColor(1, 0.0f, 1.0f, 0.0f, 1.0f);
		QUAD_POLYGONS[4].setEdgeColor(2, 0.0f, 1.0f, 0.0f, 1.0f);
		QUAD_POLYGONS[4].setEdgeColor(3, 0.0f, 1.0f, 0.0f, 1.0f);
		/**
		QUAD_POLYGONS[4].setEdgeColor(0, 0.67f, 0.42f, 0.11f, 1.0f);
		QUAD_POLYGONS[4].setEdgeColor(1, 0.67f, 0.42f, 0.11f, 1.0f);
		QUAD_POLYGONS[4].setEdgeColor(2, 0.67f, 0.42f, 0.11f, 1.0f);
		QUAD_POLYGONS[4].setEdgeColor(3, 0.67f, 0.42f, 0.11f, 1.0f);
		**/

		QUAD_POLYGONS[5].setEdgePosition(0, -0.5f, -0.5f, -0.5f);
		QUAD_POLYGONS[5].setEdgePosition(1, -0.5f, 0.5f, -0.5f);
		QUAD_POLYGONS[5].setEdgePosition(2, 0.5f, 0.5f, -0.5f);
		QUAD_POLYGONS[5].setEdgePosition(3, 0.5f, -0.5f, -0.5f);
		QUAD_POLYGONS[5].setEdgeColor(0, 0.0f, 1.0f, 0.0f, 1.0f);
		QUAD_POLYGONS[5].setEdgeColor(1, 0.0f, 1.0f, 0.0f, 1.0f);
		QUAD_POLYGONS[5].setEdgeColor(2, 0.0f, 1.0f, 0.0f, 1.0f);
		QUAD_POLYGONS[5].setEdgeColor(3, 0.0f, 1.0f, 0.0f, 1.0f);
		/**
		QUAD_POLYGONS[5].setEdgeColor(0, 1.0f, 0.0f, 0.0f, 1.0f);
		QUAD_POLYGONS[5].setEdgeColor(1, 0.0f, 1.0f, 0.0f, 1.0f);
		QUAD_POLYGONS[5].setEdgeColor(2, 0.0f, 0.0f, 1.0f, 1.0f);
		QUAD_POLYGONS[5].setEdgeColor(3, 0.33f, 0.33f, 0.33f, 1.0f);
		**/
	}
	
	public RenderableCube() { 
		super(CUBE_POLYGON_COUNT, new Polygon[] {QUAD_POLYGONS[0], QUAD_POLYGONS[1], QUAD_POLYGONS[2]
				, QUAD_POLYGONS[3], QUAD_POLYGONS[4], QUAD_POLYGONS[5]});
	}

}
