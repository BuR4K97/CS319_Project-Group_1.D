import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class main {
	static int red = 255;
	static int green= 0;
	static int blue = 0;
	static int mouseX = 0;
	static int mouseY = 0;
	static int counterForSea = 0;
	static ArrayList<Coordinate> list = new ArrayList<Coordinate>();
	static ArrayList<Coordinate> listCopy = new ArrayList<Coordinate>();
	static ArrayList<Coordinate> listSea = new ArrayList<Coordinate>();
	static ArrayList<Circle> listCircle = new ArrayList<Circle>();

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
		int jump = 12;  // 12
		Color c = new Color(0,0,0);
		// initialize coordinate array
		for (int i = 0; i < 1920; i+= jump) {
			for(int j = 0; j< 1080; j+= jump) {
				c = new Color(image.getRGB(i, j));
				if(c.getRed() != 0 || c.getGreen() != 0 || c.getBlue() != 0) {
					list.add(new Coordinate(i, j));
					listCopy.add(new Coordinate(i, j));
				}else {
					listSea.add(new Coordinate(i, j));
				}
			}
		}
		// draw
		JPanel panel = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				for(int i = 0;i < list.size(); i++) { // - or +
					g.setColor(new Color(  Math.abs(red - list.get(i).x/ jump) % 255, 
							Math.abs(green - list.get(i).x/ jump) % 255, Math.abs(blue - list.get(i).x/ jump) % 255));
					g.fillRect(list.get(i).x, list.get(i).y, x, y);
				}
				for(int i = 0;i < listSea.size(); i++) {
					for(int j = 0;j < listCircle.size(); j++)
					if(listCircle.get(j).isInCircle(listSea.get(i))) {
						g.setColor(new Color(50, 170, 250,listCircle.get(j).seaOpacity ));
						//g.setColor(new Color(255, 255, 255,listCircle.get(j).seaOpacity ));
						g.drawOval(listSea.get(i).x, listSea.get(i).y, x, y);
					}
				}
				//circle.paint(g);
			}
		};
		panel.setPreferredSize(new Dimension(1920, 1080));
		panel.setBackground(Color.BLACK);
		// timer for circle and sea
		Timer seaUpdater = new Timer(15, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < listCircle.size(); i++) {
					listCircle.get(i).update();
					if(listCircle.get(i).done)
						listCircle.remove(i);
				}
				if(Math.random()* 40 < 1)
					listCircle.add(new Circle( (int)(Math.random()* 1920), (int)(Math.random()* 1080)));
				panel.repaint();
			}
		});
		seaUpdater.start();
		// timer for draw sea using mouse
		Timer seaMouse = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					listCircle.add(new Circle( mouseX, mouseY));
				panel.repaint();
			}
		});
		
		MouseListener ml = new MouseListener() {
			public void mouseReleased(MouseEvent e) {
				seaMouse.stop();
			}
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
				listCircle.add(new Circle( mouseX, mouseY));
			}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
			}
		};
		MouseMotionListener mml = new MouseMotionListener() {
			public void mouseMoved(MouseEvent e) {}
			public void mouseDragged(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
				seaMouse.start();
			}
		};
		panel.addMouseListener(ml);
		panel.addMouseMotionListener(mml);
		frame.add(panel);
		frame.setVisible(true);
	}
}