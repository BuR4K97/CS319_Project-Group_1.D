public class RenderableQuad extends RenderableType {
	
	public static final Polygon QUAD_POLYGON;
	public static final int QUAD_EDGE_COUNT = 4;
	static {
		QUAD_POLYGON = new Polygon(QUAD_EDGE_COUNT);
		QUAD_POLYGON.setEdgePosition(0, -0.5f, -0.5f, 0.5f);
		QUAD_POLYGON.setEdgePosition(1, -0.5f, 0.5f, 0.5f);
		QUAD_POLYGON.setEdgePosition(2, 0.5f, 0.5f, 0.5f);
		QUAD_POLYGON.setEdgePosition(3, 0.5f, -0.5f, 0.5f);
		QUAD_POLYGON.setEdgeColor(0, 0.0f, 1.0f, 0.0f, 1.0f);
		QUAD_POLYGON.setEdgeColor(1, 0.0f, 1.0f, 0.0f, 1.0f);
		QUAD_POLYGON.setEdgeColor(2, 0.0f, 1.0f, 0.0f, 1.0f);
		QUAD_POLYGON.setEdgeColor(3, 0.0f, 1.0f, 0.0f, 1.0f);	
	}
	
	public static final int QUAD_POLYGON_COUNT = 1;
	
	public RenderableQuad() { super(QUAD_POLYGON_COUNT, new Polygon[] {QUAD_POLYGON}); }

}//endClass
