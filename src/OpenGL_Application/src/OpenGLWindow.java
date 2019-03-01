import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;
import java.util.ArrayList;

public class OpenGLWindow {
	
	public static int resolutionWidth = 3840, resolutionHeight = 2160;
	private static int framePerSec = 60;
	private static int frameCount = 0;
	private static OpenGLScene scene;
	
	public static boolean initialize() {
		try {
			PixelFormat pixelFormat = new PixelFormat();
			ContextAttribs contextAttribs = new ContextAttribs(3, 2)
					.withForwardCompatible(true).withProfileCore(true);
			
 			Display.setDisplayMode(new DisplayMode(resolutionWidth, resolutionHeight));
 			Display.setTitle("OpenGL_Application");
			Display.create(pixelFormat, contextAttribs);
			GL11.glViewport(0, 0, resolutionWidth, resolutionHeight);
		}
		catch(LWJGLException exception) { System.out.println(exception.getMessage()); }
		GL11.glClearColor(0.298f, 0.662f, 0.682f, 0.0f);
		boolean success = ShaderProgram.initialize();
		scene = new OpenGLScene(); scene.initialize();
		return success;
	}
	
	public static boolean isCloseRequested() {
		return Display.isCloseRequested();
	}
	
	public static void update() {
		//if(frameCount % 1 == 0) 
		scene.update(); //Not visual update but the geometrical model data
		render();
		Display.sync(framePerSec);
		Display.update();
	}
	
	public static void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthFunc(GL11.GL_LEQUAL);
		//GL11.glEnable(GL11.GL_BLEND);
		//GL11.glBlendFunc(GL11.GL_ONE_MINUS_DST_COLOR, GL11.GL_ONE_MINUS_SRC_COLOR);
		scene.render();
		if(frameCount < 60) frameCount++;
		else frameCount = 0;
	}
	
	public static void destroy() {
		scene.destroy();
		ShaderProgram.destroy();
		Display.destroy();
	}

}
