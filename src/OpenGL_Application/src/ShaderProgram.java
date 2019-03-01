import java.lang.StringBuilder;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL11;

public class ShaderProgram {
	
	private static class ShaderSourceDataBuffer {
		
		private static final String VERTEX_FILE = "C:\\Users\\burak\\workspace\\OpenGL_Application\\src\\Vertex_Shader.glsl";
		private static final String FRAGMENT_FILE = "C:\\Users\\burak\\workspace\\OpenGL_Application\\src\\Fragment_Shader.glsl";
		private static final StringBuilder VERTEX_SOURCE = new StringBuilder();
		private static final StringBuilder FRAGMENT_SOURCE = new StringBuilder();
		
		private static boolean constructData() {
			try {
				BufferedReader loader = new BufferedReader(new FileReader(VERTEX_FILE));
				String sourceLine = loader.readLine();
				while(sourceLine != null) {
					VERTEX_SOURCE.append(sourceLine + "\n");
					sourceLine = loader.readLine();
				}
				loader.close();
				
				loader = new BufferedReader(new FileReader(FRAGMENT_FILE));
				sourceLine = loader.readLine();
				while(sourceLine != null) {
					FRAGMENT_SOURCE.append(sourceLine + "\n");
					sourceLine = loader.readLine();
				}
				loader.close();
			}
			catch(IOException exception) {
				System.out.println(exception.getMessage());
				return false;
			}
			return true;
		}
		
	}//endClass
	
	private static int vertexID, fragmentID;
	public static int programID;
	public final static int POSITION_ATTR = 0, COLOR_ATTR = 1, NORMAL_ATTR = 2;
	public static int modelUniform, viewUniform, projectionUniform;
	
	private static boolean loadShaders() {
		if(ShaderSourceDataBuffer.constructData()) {
			vertexID = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
			GL20.glShaderSource(vertexID, ShaderSourceDataBuffer.VERTEX_SOURCE);
			GL20.glCompileShader(vertexID);
	
			fragmentID = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
			GL20.glShaderSource(fragmentID, ShaderSourceDataBuffer.FRAGMENT_SOURCE);
			GL20.glCompileShader(fragmentID);
			return true;
		}
		return false;
	}
	
	public static boolean initialize() {
		if(loadShaders()) {	
			if(GL20.glGetShader(vertexID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
				System.out.println("Could not compiled vertex shader");
				return false;
			}
			else if(GL20.glGetShader(fragmentID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
				System.out.println("Could not compiled fragment shader");
				return false;
			}
			
			programID = GL20.glCreateProgram();
			GL20.glAttachShader(programID, vertexID);
			GL20.glAttachShader(programID, fragmentID);
			
			/** Here, set the location of the attribute variables **/
			GL20.glBindAttribLocation(programID, POSITION_ATTR, "positionAttr");
			GL20.glBindAttribLocation(programID, COLOR_ATTR, "colorAttr");
			GL20.glBindAttribLocation(programID, NORMAL_ATTR, "vertexNormal");
			
			GL20.glLinkProgram(programID);
			GL20.glValidateProgram(programID);
			GL20.glUseProgram(programID);
			
			/** Here, get the location of the uniform variables **/
			modelUniform = GL20.glGetUniformLocation(programID, "modelMatrix");
			viewUniform = GL20.glGetUniformLocation(programID, "viewMatrix");
			projectionUniform = GL20.glGetUniformLocation(programID, "projectionMatrix");
			
			return true;
		}
		return false;
	}
	
	public static boolean destroy() {
		GL20.glUseProgram(0);
		GL20.glDeleteProgram(programID);
		return true;
	}
	
}//endClass
