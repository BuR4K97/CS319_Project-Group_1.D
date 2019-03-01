public class VertexData {

	public float[] position = {0.0f, 0.0f, 0.0f, 1.0f};
	public float[] colors = {0.0f, 0.0f, 0.0f, 1.0f};
	public float[] normals = {0.0f, 0.0f, 0.0f};
	
	public static final int FLOAT_BYTES_COUNT = 4;
	
	public static final int POSITION_ELEMENT_COUNT = 4;
	public static final int COLOR_ELEMENT_COUNT = 4;
	public static final int NORMAL_ELEMENT_COUNT = 3;
	
	//Here, such hierarchy means that the position data buffered first and then color data
	//Offset basically means after how many bytes the specified data indexed
	public static final int POSITION_OFFSET = 0;
	public static final int COLOR_OFFSET = POSITION_OFFSET + FLOAT_BYTES_COUNT * POSITION_ELEMENT_COUNT;
	public static final int NORMAL_OFFSET = COLOR_OFFSET + FLOAT_BYTES_COUNT * COLOR_ELEMENT_COUNT;
	public static final int BYTE_PER_VERTEX = FLOAT_BYTES_COUNT * (POSITION_ELEMENT_COUNT + COLOR_ELEMENT_COUNT
			+ NORMAL_ELEMENT_COUNT);
	
	public VertexData setXYZ(float x, float y, float z) {
		position[0] = x; position[1] = y; position[2] = z;
		return this;
	}
	
	public VertexData setRGBA(float r, float g, float b, float a) {
		colors[0] = r; colors[1] = g; colors[2] = b; colors[3] = a;
		return this;
	}
	
	public VertexData setNormal(float x, float y, float z) {
		normals[0] = x; normals[1] = y; normals[2] = z;
		return this;
	}

	//Returns the integrated bytes with the mentioned hierarchy as float[]
	public float[] getIntegratedArray() {
		float[] integratedArray = new float[POSITION_ELEMENT_COUNT + COLOR_ELEMENT_COUNT + NORMAL_ELEMENT_COUNT];
		
		int currIndex = 0;
		for(int i = 0; i < POSITION_ELEMENT_COUNT; i++)
			integratedArray[currIndex++] = position[i];
		for(int i = 0; i < COLOR_ELEMENT_COUNT; i++)
			integratedArray[currIndex++] = colors[i];
		for(int i = 0; i < NORMAL_ELEMENT_COUNT; i++)
			integratedArray[currIndex++] = normals[i];
		
		return integratedArray;
	}
	
}//endClass
