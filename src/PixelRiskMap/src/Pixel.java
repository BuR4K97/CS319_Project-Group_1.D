import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Pixel {
	static int red = 255;
	static int green= 0;
	static int blue = 0;
	static int counterForSea = 0;
	static ArrayList<Coordinate> list = new ArrayList<Coordinate>();
	static ArrayList<Coordinate> listCopy = new ArrayList<Coordinate>();
	public static void main(String[] args) throws IOException{

		
		// frame
		JFrame frame = new JFrame("alper");
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true); // last
		// file
		File file = new File("maps//Risk.png");
		BufferedImage image = ImageIO.read(file);
		// create rectangle map
		int x = 9;
		int y = x;
		int jump = 12;
		Color c = new Color(0,0,0);
		// initialize coordinate array
		for (int i = 0; i < 1920; i+= jump) {
			for(int j = 0; j< 1080; j+= jump) {
				c = new Color(image.getRGB(i, j));
				if(c.getRed() != 0 || c.getGreen() != 0 || c.getBlue() != 0) {
					list.add(new Coordinate(i, j));
					listCopy.add(new Coordinate(i, j));
				}else {}
			}
		}
		// draw
		JPanel panel = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				for(int i = 0;i < list.size(); i++) { // - or +
					g.setColor(new Color(  Math.abs(red + list.get(i).x/ jump) % 255, 
							Math.abs(green + list.get(i).x/ jump) % 255, Math.abs(blue + list.get(i).x/ jump) % 255));
					g.fillRect(list.get(i).x, list.get(i).y, x, y);
				}
			}
		};
		panel.setPreferredSize(new Dimension(1920, 1080));
		panel.setBackground(Color.BLACK);
		// timer for diassemble
		Timer di = new Timer(5, new ActionListener() {
			int movement = 10;
			public void actionPerformed(ActionEvent e) {
				// random
				if((int)(Math.random() * 1) == 0) {
					list.get((int)(Math.random() * list.size())).move = true;
				}
				for(int i = 0;i < list.size(); i++) {
					if(list.get(i).move) {
						list.get(i).y = list.get(i).y + movement;
						if(list.get(i).y >= 1080) {
							list.remove(i);
							if(list.size() == 0)
								list = getCopyList(listCopy);
						}
					}
				}
			}
		});
		// timer for territories
		Timer t = new Timer(1, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(red == 255 && green == 0 && blue >= 0 && blue < 255) {
					blue++;
				} else if(red > 0 && red <= 255 && green == 0 && blue == 255) {
					red--;
				} else if(red == 0 && green >= 0 && green < 255 && blue == 255) {
					green++;
				} else if(red == 0 && green == 255 && blue > 0 && blue <= 255) {
					blue--;
				} else if(red >= 0 && red < 255 && green == 255 && blue == 0) {
					red++;
				} else if(red == 255 && green > 0 && green <= 255 && blue == 0) {
					green--;
				}
				panel.repaint();
			}
		});
		t.start();
		// button
		JButton b = new JButton("act");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(di.isRunning())
					di.stop();
				else
					di.start();
			}
		});
		panel.add(b);
		frame.add(panel);
		frame.setVisible(true);
	}
	public static ArrayList<Coordinate> getCopyList(ArrayList<Coordinate> list) {
		ArrayList<Coordinate> newl = new ArrayList<Coordinate>();
		for(int i = 0; i < list.size(); i++) {
			newl.add(new Coordinate(list.get(i).x, list.get(i).y));
		}
		return newl;
	}
}