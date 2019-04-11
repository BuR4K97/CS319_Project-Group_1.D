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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class main {
	static int red = 255;
	static int green= 0;
	static int blue = 0;
	static int minSpeed = 8;
	static int maxSpeed = 16;
	static ArrayList<Coordinate> list = new ArrayList<Coordinate>();
	static ArrayList<Coordinate> listCopy = new ArrayList<Coordinate>();
	public static void main(String[] args) throws IOException {
		// file
		File file = new File("maps//Risk.png");
		BufferedImage image = ImageIO.read(file);
		// create rectangle map
		int x = 9;
		int y = x;
		int jump = 12;  // 12
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
		// list of fireworks
		ArrayList<ExplodeOne> explodeOneList = new ArrayList<ExplodeOne>();
		// frame
		JFrame frame = new JFrame("alper");
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true); // last
		// draw
		JPanel panel = new JPanel() {
			Color c;
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				for(int i = 0;i < list.size(); i++) { // - or +
					c = new Color(  Math.abs(red - list.get(i).x/ jump) % 255, 
							Math.abs(green - list.get(i).x/ jump) % 255, Math.abs(blue - list.get(i).x/ jump) % 255);
					g.setColor(c);
					g.fillRect(list.get(i).x, list.get(i).y, x, y);
				}
				for(ExplodeOne ro : explodeOneList) {
					ro.paint(g);
				}
			}
		};
		panel.setBackground(Color.BLACK);
		// add firework timer
		Timer add = new Timer(1, new ActionListener() { // 125
			int selectedOne= 0;
			Color c;
			public void actionPerformed(ActionEvent e) {
				c = new Color((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255));
				selectedOne = (int)(Math.random() * list.size());
				list.get(selectedOne).move = true;
				for(int xMovement = -1; xMovement <= 1; xMovement++) {
					for(int yMovement = -5; yMovement <= -3; yMovement++) {
						explodeOneList.add(new ExplodeOne(list.get(selectedOne).x,
								list.get(selectedOne).y, 
								xMovement,
								yMovement,
								9,
								c ));
					}
				}
				list.remove(selectedOne);
				if(list.size() == 0)
					list = getCopyList(listCopy);

				panel.repaint();
			}
		});
		add.start();
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
		// update firework timer
		Timer update = new Timer(15, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// explode one
				for(int i = 0; i < explodeOneList.size(); i++) {
					if(!explodeOneList.get(i).done) {
						explodeOneList.get(i).update();
					}
					else {
						explodeOneList.remove(i);
					}
				}
				panel.repaint();
			}

		});
		update.start();
		panel.setPreferredSize(new Dimension(1920, 1080));
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
