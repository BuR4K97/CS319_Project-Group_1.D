import java.nio.FloatBuffer;
import java.util.ArrayList;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Vector3f;

public class OpenGLScene {

	
	private ArrayList<SceneElements> sceneElements = new ArrayList<SceneElements>();
	private Camera camera = new Camera();

	public void initialize() {
		FloatBuffer uniformBuffer = BufferUtils.createFloatBuffer(Transformable.MATRIX4_ELEMENT_COUNT);
		camera.getViewMatrix().store(uniformBuffer); uniformBuffer.flip();
		GL20.glUniformMatrix4(ShaderProgram.viewUniform, false, uniformBuffer);
		
		uniformBuffer = BufferUtils.createFloatBuffer(Transformable.MATRIX4_ELEMENT_COUNT);
		camera.getProjectionMatrix().store(uniformBuffer); uniformBuffer.flip();
		GL20.glUniformMatrix4(ShaderProgram.projectionUniform, false, uniformBuffer);
		
		LoadedMeshObjects.initialize();
		
		sceneElements.add(new RenderableAragornObject());
		((RenderableAragornObject)sceneElements.get(0)).moveObject(new Vector3f(-30, 20, 150));
		((RenderableAragornObject)sceneElements.get(0)).scaleObject(0.3f);
		
		sceneElements.add(new RenderableAragornObject());
		((RenderableAragornObject)sceneElements.get(1)).moveObject(new Vector3f(30, 20, 150));
		((RenderableAragornObject)sceneElements.get(1)).scaleObject(0.3f);
		
		sceneElements.add(new RenderableAragornObject());
		((RenderableAragornObject)sceneElements.get(2)).moveObject(new Vector3f(0, -40, 150));
		((RenderableAragornObject)sceneElements.get(2)).scaleObject(0.3f);
		
		//sceneElements.add(new RenderableCube());
		//((RenderableObject)sceneElements.get(0)).moveObject(new Vector3f(0, 0, 10));
	}
	
	public void update() {
		for(int currIndex = 0; currIndex < sceneElements.size(); currIndex++)
			sceneElements.get(currIndex).update();
	}

	public void render() {
		for(int currIndex = 0; currIndex < sceneElements.size(); currIndex++)
			sceneElements.get(currIndex).render();
	}

	public void destroy() {
		for(int currIndex = 0; currIndex < sceneElements.size(); currIndex++)
			sceneElements.get(currIndex).destroy();
	}


}
