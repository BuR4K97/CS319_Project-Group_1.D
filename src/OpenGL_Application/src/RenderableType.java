import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Vector3f;

public class RenderableType extends RenderableObject {

	public RenderableType(int polygonCount, Polygon[] polygons) { super(polygonCount, polygons); }
	
	@Override
	protected void constructVerticesData() {
		super.verticesFloatBuffer = BufferUtils.createByteBuffer(VertexData.BYTE_PER_VERTEX * vertices.length)
				.asFloatBuffer();
		for(int currIndex = 0; currIndex < vertices.length; currIndex++)
			super.verticesFloatBuffer.put(vertices[currIndex].getIntegratedArray());
		super.verticesFloatBuffer.flip();
	}

	@Override
	protected void constructModelUniformData() {
		super.modelUniformBuffer = BufferUtils.createFloatBuffer(VertexData.FLOAT_BYTES_COUNT 
				* Transformable.MATRIX4_ELEMENT_COUNT);
		super.getModelTransform().store(super.modelUniformBuffer); 
		super.modelUniformBuffer.flip();
	}

	@Override
	protected void updateVerticesData() {
		super.vertices = new VertexData[super.getEdgeCount()];
		int currIndex = 0; Polygon currPolygon; int edgeIndex = 0, colorIndex = 0;
		for(int i = 0; i < super.getPolygonCount(); i++) {
			currPolygon = super.getPolygon(i); edgeIndex = 0; colorIndex = 0;
			for(int n = 0; n < currPolygon.getEdgeCount(); n++) {
				super.vertices[currIndex] = new VertexData();
				super.vertices[currIndex++].setXYZ(currPolygon.getEdgePoints()[edgeIndex++],
						currPolygon.getEdgePoints()[edgeIndex++], currPolygon.getEdgePoints()[edgeIndex++])
						.setRGBA(currPolygon.getEdgeColors()[colorIndex++], currPolygon.getEdgeColors()[colorIndex++]
						,currPolygon.getEdgeColors()[colorIndex++], currPolygon.getEdgeColors()[colorIndex++])
						.setNormal(currPolygon.getNormalVector()[0], currPolygon.getNormalVector()[1]
								, currPolygon.getNormalVector()[2]);
			}
		}
		super.verticesDataUpdated = true;
	}

	@Override
	public void update() {
		super.rotateObject(3.0f, new Vector3f(0, 1, 0));
		super.modelUniformDataUpdated = true;
	}

}
