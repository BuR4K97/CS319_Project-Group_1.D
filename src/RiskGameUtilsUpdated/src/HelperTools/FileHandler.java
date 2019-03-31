package HelperTools;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class FileHandler {
	
	private String fileName;
	
	public FileHandler(String fileName) { this.fileName = fileName; }
	
	public ArrayList<Serializable> loadDataFromFile() {
		ArrayList<Serializable> objects = new ArrayList<Serializable>();
		try {
			FileInputStream inBuffer = new FileInputStream(fileName);
			ObjectInputStream objectBuffer = new ObjectInputStream(inBuffer); 
			objects = (ArrayList<Serializable>) objectBuffer.readObject();
			objectBuffer.close();
			inBuffer.close();
		}
		catch(Exception exception) { 
			System.out.println(exception.getMessage());
			return null;
		}
		return objects;
	}
	
	public boolean saveDataToFile(ArrayList<Serializable> data) {
		if(data.size() == 0) return false; 
		try {
			FileOutputStream outBuffer = new FileOutputStream(fileName);
			ObjectOutputStream objectBuffer = new ObjectOutputStream(outBuffer);
			objectBuffer.writeObject(data);
			objectBuffer.close();
			outBuffer.close();
		} 
		catch(Exception exception) { 
			System.out.println(exception.getMessage());
		}
		return true;
	}

}
