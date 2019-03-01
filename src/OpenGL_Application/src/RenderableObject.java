import java.nio.FloatBuffer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public abstract class RenderableObject extends GeometricObject implements SceneElements {
	
	protected VertexData[] vertices; //Should be initialized through updateVerticesData()
	protected FloatBuffer verticesFloatBuffer; //Should be initialized through constructVerticesData()
	protected FloatBuffer modelUniformBuffer; //Should be initialized through constructModelUniformData()
	protected boolean verticesDataUpdated;
	protected boolean modelUniformDataUpdated;
	
	private int vertexArrayID, vertexBufferID;
	private boolean buffersCreated;
	
	public RenderableObject(int polygonCount, Polygon[] polygons) {
		super(polygonCount, polygons);
		this.vertexArrayID = 0;
		this.vertexBufferID = 0;
		this.buffersCreated = false;
		modelUniformDataUpdated = true;
		updateVerticesData();
	}
	
	private void createBuffers() {
		if(!buffersCreated) {
			vertexArrayID = GL30.glGenVertexArrays();
			vertexBufferID = GL15.glGenBuffers();
			buffersCreated = true;
		}
	}
	
	//Constructs geometric vertex data of the renderable object and store them in the verticesFloatBuffer
	protected abstract void constructVerticesData();
	protected abstract void constructModelUniformData();
	protected abstract void updateVerticesData(); 	//Should be called in constructor after super-GeometricObject initialized
	public abstract void update();
	
	private void bufferData() {
		if(!buffersCreated) createBuffers();
		
		if(modelUniformDataUpdated) { constructModelUniformData(); modelUniformDataUpdated = false; }
		GL20.glUniformMatrix4(ShaderProgram.modelUniform, false, modelUniformBuffer);
		
		if(!verticesDataUpdated) return;
		constructVerticesData();
		GL30.glBindVertexArray(vertexArrayID);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexBufferID);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verticesFloatBuffer, GL15.GL_STATIC_DRAW);
		
		GL20.glVertexAttribPointer(ShaderProgram.POSITION_ATTR, VertexData.POSITION_ELEMENT_COUNT,
				GL11.GL_FLOAT, false, VertexData.BYTE_PER_VERTEX, VertexData.POSITION_OFFSET);
		GL20.glVertexAttribPointer(ShaderProgram.COLOR_ATTR, VertexData.COLOR_ELEMENT_COUNT,
				GL11.GL_FLOAT, false, VertexData.BYTE_PER_VERTEX, VertexData.COLOR_OFFSET);
		GL20.glVertexAttribPointer(ShaderProgram.NORMAL_ATTR, VertexData.NORMAL_ELEMENT_COUNT,
				GL11.GL_FLOAT, false, VertexData.BYTE_PER_VERTEX, VertexData.NORMAL_OFFSET);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);
		verticesDataUpdated = false;
	}
	
	public void render() {
		bufferData();
		GL30.glBindVertexArray(vertexArrayID);
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		
		int currIndex = 0; Polygon currPolygon;
		for(int i = 0; i < super.getPolygonCount(); i++) {
			currPolygon = super.getPolygon(i);
			GL11.glDrawArrays(GL11.GL_TRIANGLE_FAN, currIndex, currPolygon.getEdgeCount());
			currIndex += currPolygon.getEdgeCount();
		}
		
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		GL30.glBindVertexArray(0);
	}
	
	public void destroy() {
		GL30.glBindVertexArray(vertexArrayID);
        
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL15.glDeleteBuffers(vertexBufferID);
        
        GL30.glBindVertexArray(0);
        GL30.glDeleteVertexArrays(vertexArrayID);
	}

}//endClass
