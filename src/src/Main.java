import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Main {
	public static void main(String []args) {
		BufferedImage colorMap = null;
		try {
			File file = new File("maps\\Risk.png");
			colorMap = ImageIO.read(file);
		} catch(IOException e) {}
		int width = 0;
		int height = 0;
		
		ArrayList<VisualTerritory> list = new ArrayList<VisualTerritory>();
		for(int i = 0; i < 42; i++) {
			list.add(new VisualTerritory(VisualTerritory.TERRITORIES.values()[i], new Color(0,0,0)));
		}
		Color c = null;
		int r, g, b, currentIndex = 0;
		
		while(width < colorMap.getWidth()) {
			while (height < colorMap.getHeight()) {
				c = new Color(colorMap.getRGB(width, height));
				 r = c.getRed();
				 g = c.getGreen();
				 b = c.getBlue();
				// na
					if(r == 128 && g == 128 && b == 0) {
						currentIndex = 0;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					else if(r == 	80	 	&& g == 	80 		&& b == 	39	)	{
						currentIndex = 1;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					else if(r == 	255 	&& g == 	255		 && b == 	0	) 	{
						currentIndex = 2;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					else if(r == 	255 	&& g == 	200 	&& b == 	0	) {
						currentIndex = 3;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					else if(r == 	148 	&& g == 	148 	&& b == 	73	) 	{
						currentIndex = 4;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					else if(r == 	255 	&& g == 	255 	&& b == 	128	) 	{
						currentIndex = 5;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					else if(r == 	80 		&& g == 	80 		&& b == 	30	) 	{
						currentIndex = 6;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					else if(r == 	255 	&& g == 	180 	&& b == 	0	) 	{
						currentIndex = 7;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					else if(r == 	255 	&& g == 	220 	&& b == 	0	) 	{
						currentIndex = 8;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					// sa
					else if(r == 	255 	&& g == 	128 	&& b == 	128	) 	{
						currentIndex = 9;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					else if(r == 	128 	&& g == 	0 		&& b == 	0	) 	{
						currentIndex = 10;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					else if(r == 	128 	&& g == 	64 		&& b == 	64	) 	{
						currentIndex = 11;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					else if(r == 	255 	&& g == 	0	 	&& b == 	0	) {
						currentIndex = 12;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					// eu
					else if(r == 	0 		&& g == 	0 		&& b == 	255	) 	{
						currentIndex = 13;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					else if(r == 	0	 	&& g == 	64	 	&& b == 	128	) 	{
						currentIndex = 14;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					else if(r == 	0	 	&& g == 	128 	&& b == 	255	) 	{
						currentIndex = 15;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					else if(r == 	0	 	&& g == 	0	 	&& b == 	128	) 	{
						currentIndex = 16;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					else if(r == 	0	 	&& g == 	0	 	&& b == 	255	) 	{
						currentIndex = 17;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					else if(r == 	0	 	&& g == 	50	 	&& b == 	128	) 	{
						currentIndex = 18;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					else if(r == 	0	 	&& g == 	120 	&& b == 	255	) 	{
						currentIndex = 19;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					// africa
					else if(r == 	255 	&& g == 	145 	&& b == 	91	) 	{
						currentIndex = 20;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					else if(r == 	128 	&& g == 	64	 	&& b == 	0	) 	{
						currentIndex = 21;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					else if(r == 	255 	&& g == 	128 	&& b == 	0	) {
						currentIndex = 22;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					else if(r == 	174 	&& g == 	87	 	&& b == 	0	) 	{
						currentIndex = 23;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					else if(r == 	128 	&& g == 	60	 	&& b == 	0	) 	{
						currentIndex = 24;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					else if(r == 	174 	&& g == 	80	 	&& b == 	0	) 	{
						currentIndex = 25;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					// as
					else if(r == 	0 		&& g == 	128 	&& b == 	0	) 	{
						currentIndex = 26;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					else if(r == 	128 	&& g == 	255 	&& b == 	120	) 	{
						currentIndex = 27;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					else if(r == 	0	 	&& g == 	128	 	&& b == 	0	) {
						currentIndex = 28;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					else if(r == 	0	 	&& g == 	128 	&& b == 	10	) 	{
						currentIndex = 29;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					else if(r == 	0	 	&& g == 	128 	&& b == 	120	){
						currentIndex = 30;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					else if(r == 	128 	&& g == 	255 	&& b == 	20	) 	{
						currentIndex = 31;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					else if(r == 	0	 	&& g == 	64	 	&& b == 	0	) 	{
						currentIndex = 32;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					else if(r == 	0	 	&& g == 	128 	&& b == 	64	) 	{
						currentIndex = 33;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					else if(r == 	0	 	&& g == 	128 	&& b == 	128	) 	{
						currentIndex = 34;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					else if(r == 	128 	&& g == 	255 	&& b == 	128	) 	{
						currentIndex = 35;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					else if(r == 	128 	&& g == 	255 	&& b == 	0	) 	{
						currentIndex = 36;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					else if(r == 	0	 	&& g == 	128 	&& b == 	55	) 	{
						currentIndex = 37;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					// au
					else if(r == 	255 	&& g == 	0	 	&& b == 	255	) 	{
						currentIndex = 38;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					else if(r == 	128 	&& g == 	0	 	&& b == 	255	) 	{
						currentIndex = 39;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					else if(r == 	128 	&& g == 	0	 	&& b == 	64	) 	{
						currentIndex = 40;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					else if(r == 	64	 	&& g == 	0	 	&& b == 	64	) 	{
						currentIndex = 41;
						list.get(currentIndex).coordinates.add(new Coordinate(width, height));
						list.get(currentIndex).color = c;
					}
					height++;
			}
			width++;
			height = 0;
		}
		//Scanner scan = new Scanner(System.in);
		/*
		for(int i = 0; i < 42; i++) {
			list.get(i).print();
		//	scan.nextLine();
		}
		*/
		
		try {
			FileOutputStream outFile = new FileOutputStream("visualTerritories.so");
			ObjectOutputStream out = new ObjectOutputStream(outFile);
			
			for(int i = 0; i < 42; i++) {
				out.writeObject(list.get(i));
			}
			
			out.close();
			outFile.close();
			
			ArrayList<VisualTerritory> readList  = new ArrayList<VisualTerritory>();
			FileInputStream inFile = new FileInputStream("visualTerritories.so");
			ObjectInputStream in = new ObjectInputStream(inFile);
			VisualTerritory current = (VisualTerritory)in.readObject();
			
			while(current != null) {
				readList.add(current);
				current = (VisualTerritory)in.readObject();
			}
			for(int i = 0; i < 42; i++) {
				readList.get(i).print();
				System.out.println("biti");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		
		
		
		
	}
}
