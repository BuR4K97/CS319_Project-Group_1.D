
public class LoadedMeshObjects {
	
	public static final ObjectMeshLoader MESH_LOADER = new ObjectMeshLoader();
	public static final String ARAGORN_MESH_SOURCE_FILE = "C:\\Users\\burak\\Desktop\\Aragorn.obj";
	
	public static void initialize() {
		MESH_LOADER.setSourceFile(ARAGORN_MESH_SOURCE_FILE);
		RenderableAragornObject.aragornGeometricObjects = MESH_LOADER.loadMeshObject();
	}
	
}
