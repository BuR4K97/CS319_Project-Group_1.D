import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ObjectMeshLoader {
	
	private static class MeshDataBuffer {
		
		private String meshSourceFile;
		private StringBuilder meshSource;
		
		private boolean constructData() {
			if(meshSourceFile == null) return false;
			
			try {
				BufferedReader loader = new BufferedReader(new FileReader(meshSourceFile));
				meshSource = new StringBuilder();
				String sourceLine = loader.readLine();
				while(sourceLine != null) {
					meshSource.append(sourceLine + "\n");
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
	}
	
	private MeshDataBuffer meshBuffer;
	
	public ObjectMeshLoader() { meshBuffer = new MeshDataBuffer(); }
	public void setSourceFile(String meshSourceFile) { meshBuffer.meshSourceFile = meshSourceFile; }
	
	public GeometricObject[] loadMeshObject() {
		if(meshBuffer.meshSource == null) 
			if(!meshBuffer.constructData()) return null;
		
		Scanner scan = new Scanner(meshBuffer.meshSource.toString());
		ArrayList<GeometricObject> loadObjects = new ArrayList<GeometricObject>();
		ArrayList<Vertex> loadVertices = new ArrayList<Vertex>();
		ArrayList<VertexOrder> vertexOrders = new ArrayList<VertexOrder>();
	
		String sourceLine = scan.nextLine();
		boolean startLoadObject = false; int lastOrderIndex = 0; int spaceIndex = 0; int slashIndex = 0;
		Vertex loadVertex; VertexOrder vertexOrder; Polygon loadPolygon;
		while(scan.hasNextLine()) {
			if(sourceLine.indexOf("object Mesh") != -1) {
				if(startLoadObject) {
					Polygon[] polygons = new Polygon[vertexOrders.size() - lastOrderIndex];
					for(int i = lastOrderIndex; i < vertexOrders.size(); i++) {
						vertexOrder = vertexOrders.get(i);
						loadPolygon = new Polygon(VertexOrder.TRIANGULAR_ORDER);
						for(int n = 0; n < VertexOrder.TRIANGULAR_ORDER; n++) {
							loadVertex = loadVertices.get(vertexOrder.order[n]);
							loadPolygon.setEdgePosition(n, loadVertex.positions[0], loadVertex.positions[1]
									, loadVertex.positions[2]);
							loadPolygon.setEdgeColor(n, 0.24f, 0.24f, 0.24f, 1.0f);
						}
						polygons[i - lastOrderIndex] = loadPolygon;
					}
					lastOrderIndex = vertexOrders.size();
					loadObjects.add(new GeometricObject(polygons.length, polygons));
				}
				startLoadObject = true;
			}
			else if(startLoadObject) {
				spaceIndex = sourceLine.indexOf(" ");
				if(spaceIndex == -1) { sourceLine = scan.nextLine(); continue; }
				
				if(sourceLine.substring(0, spaceIndex).equals("v")) {
					sourceLine = sourceLine.substring(spaceIndex + 2);
					loadVertex = new Vertex();
					while(loadVertex.currIndex < 3) {
						try {
							spaceIndex = sourceLine.indexOf(" ");
							if(spaceIndex != -1)
								loadVertex.positions[loadVertex.currIndex++] = Float.parseFloat(sourceLine
										.substring(0, spaceIndex));
							else 
								loadVertex.positions[loadVertex.currIndex++] = Float.parseFloat(sourceLine);
							
							sourceLine = sourceLine.substring(spaceIndex + 1);
						}
						catch(NumberFormatException exception) { 
							System.out.println(exception.getMessage());
						}
					}
					loadVertices.add(loadVertex);
				}
				else if(sourceLine.substring(0, spaceIndex).equals("f")) {
					sourceLine = sourceLine.substring(spaceIndex + 1);
					vertexOrder = new VertexOrder();
					while(vertexOrder.currIndex < 3) {
						try {
							spaceIndex = sourceLine.indexOf(" ");
							
							slashIndex = sourceLine.indexOf("/");
							vertexOrder.order[vertexOrder.currIndex++] = Integer.parseInt(sourceLine
									.substring(0, slashIndex)) - 1;
							
							sourceLine = sourceLine.substring(spaceIndex + 1);
						}
						catch(NumberFormatException exception) { 
							System.out.println(exception.getMessage());
						}
					}
					vertexOrders.add(vertexOrder);
				}
			}
			
			sourceLine = scan.nextLine();
		}
		scan.close();
		if(startLoadObject) {
			Polygon[] polygons = new Polygon[vertexOrders.size() - lastOrderIndex];
			for(int i = lastOrderIndex; i < vertexOrders.size(); i++) {
				vertexOrder = vertexOrders.get(i);
				loadPolygon = new Polygon(VertexOrder.TRIANGULAR_ORDER);
				for(int n = 0; n < VertexOrder.TRIANGULAR_ORDER; n++) {
					loadVertex = loadVertices.get(vertexOrder.order[n]);
					loadPolygon.setEdgePosition(n, loadVertex.positions[0], loadVertex.positions[1]
							, loadVertex.positions[2]);
					loadPolygon.setEdgeColor(n, 0.24f, 0.24f, 0.24f, 1.0f);
				}
				polygons[i - lastOrderIndex] = loadPolygon;
			}
			lastOrderIndex = vertexOrders.size();
			loadObjects.add(new GeometricObject(polygons.length, polygons));
		}
		
		GeometricObject[] geometricObjects = new GeometricObject[loadObjects.size()];
		for(int i = 0; i < loadObjects.size(); i++) geometricObjects[i] = loadObjects.get(i);
		return geometricObjects;
	}
	
	private static class Vertex { 
		private float[] positions = new float[Polygon.POSITION_DIMENSION];
		private int currIndex = 0;
	}
	
	private static class VertexOrder {
		private static int TRIANGULAR_ORDER = 3;
		private int[] order = new int[TRIANGULAR_ORDER];
		private int currIndex = 0;
	}
	
}//endClass
