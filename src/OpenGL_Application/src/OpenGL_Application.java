public class OpenGL_Application {

	public static void main(String[] args) {
		
		if (OpenGLWindow.initialize())
			while(!OpenGLWindow.isCloseRequested()) OpenGLWindow.update();
		OpenGLWindow.destroy();
		
	} //main
	
} //endClass
