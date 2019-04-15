import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import FireWorks.Fireworks;
import RainMap.Pixel;

public class main {

	static char characters[] = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

	static ArrayList<DrawingString> list = new ArrayList<DrawingString>();
	static boolean exitProgress = false;
	static boolean close = true;
	static boolean inMainMenu = true;
	static boolean inExtrasMenu = false;
	static JPanel panel;
	static int buttonLength = 12;
	static int nameLength = 20;
	static int xmenu = 10;
	static int ymenu = 1080 - buttonLength * 5 * 6;
	public static void main(String[] args) throws IOException{
	

		Pixel pixel = new Pixel();
		Fireworks fw = new Fireworks();
		// frame
		JFrame frame = new JFrame("alper");
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true); // last

		// draw
		panel = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if(inMainMenu) {
					pixel.paint(g);
					
				}else if(inExtrasMenu) {
					fw.paint(g);
					g.setColor(new Color(0,0, 0, 50));
					g.fillRect(0, 0, 1920, 1080);
				}
				for(int i = 0; i < list.size(); i++) {

					list.get(i).paint(g);
				}
			}
		};
		// timer for animations
		Timer t = new Timer(15, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < list.size(); i++) {
					if(list.get(i).end)
						list.remove(i);
				}
				if(exitProgress) {
					System.out.println(list.size());
					close = true;
					if(list.size() > 0) {
						for(int i = 0; i < list.size(); i++) {
							if(!list.get(i).end)
								close = false;
							if(close)
								System.exit(1);
						}
					}else
						System.exit(1);
				}
				panel.repaint();
			}
		});
		t.start();

		list.add(new DrawingString(xmenu, ymenu + 0 , buttonLength, "play"));
		list.add(new DrawingString(xmenu, ymenu + buttonLength * 7 , buttonLength, "settings"));
		list.add(new DrawingString(xmenu, ymenu + buttonLength * 14, buttonLength, "extras"));
		list.add(new DrawingString(xmenu, ymenu + buttonLength * 21, buttonLength, "exit"));
		// others
		panel.setPreferredSize(new Dimension(1920, 1080));
		panel.setBackground(Color.BLACK);
		frame.add(panel);
		frame.setVisible(true);
		// mouse
		MouseMotionListener mml = new MouseMotionListener() {
			public void mouseMoved(MouseEvent e) {
				for(int i = 0; i < list.size(); i++) {
					if(list.get(i).string == "play" && list.get(i).getRectangle().contains(e.getX(), e.getY()))
						list.get(i).mouseEntered = true;
					else if (list.get(i).string == "play" && !list.get(i).getRectangle().contains(e.getX(), e.getY()))
						list.get(i).mouseEntered = false;

					else if(list.get(i).string == "settings" && list.get(i).getRectangle().contains(e.getX(), e.getY()))
						list.get(i).mouseEntered = true;
					else if(list.get(i).string == "settings" && !list.get(i).getRectangle().contains(e.getX(), e.getY()))
						list.get(i).mouseEntered = false;

					else if(list.get(i).string == "extras" && list.get(i).getRectangle().contains(e.getX(), e.getY()))
						list.get(i).mouseEntered = true;
					else if(list.get(i).string == "extras" && !list.get(i).getRectangle().contains(e.getX(), e.getY()))
						list.get(i).mouseEntered = false;

					else if(list.get(i).string == "exit" && list.get(i).getRectangle().contains(e.getX(), e.getY()))
						list.get(i).mouseEntered = true;
					else if(list.get(i).string == "exit" && !list.get(i).getRectangle().contains(e.getX(), e.getY()))
						list.get(i).mouseEntered = false;

					else if(list.get(i).string == "back" && list.get(i).getRectangle().contains(e.getX(), e.getY()))
						list.get(i).mouseEntered = true;
					else if(list.get(i).string == "back" && !list.get(i).getRectangle().contains(e.getX(), e.getY()))
						list.get(i).mouseEntered = false;
				}
			}
			public void mouseDragged(MouseEvent e) {
				pixel.startDraggedMouse(e.getX(), e.getY());
			}
		};
		panel.addMouseMotionListener(mml);
		MouseListener ml = new MouseListener() {
			public void mouseReleased(MouseEvent e) {
				pixel.seaMouse.stop();
			}
			public void mousePressed(MouseEvent e){
				pixel.addCircle(e.getX(), e.getY());
				for(int i = 0; i < list.size(); i++) {
					if(list.get(i).string == "exit" && list.get(i).getRectangle().contains(e.getX(), e.getY()) && !list.get(i).startAnimation.isRunning()) {
						for(int j = 0; j < list.size(); j++) {
							list.get(j).endAnimation.start();
						}
						exitProgress = true;
						break;
					} else if(list.get(i).string == "extras" && list.get(i).getRectangle().contains(e.getX(), e.getY())) {
						for(int j = 0; j < list.size(); j++) {
							list.get(j).endAnimation.start();
						}
						list.add(new DrawingString(xmenu, ymenu + buttonLength * 21, buttonLength, "back"));
						
						list.add(new DrawingString(50, 15 + 0 , nameLength, "Anar Huseynov"));
						list.add(new DrawingString(50, 15 + buttonLength * 12 , nameLength, "Burak Mutlu"));
						list.add(new DrawingString(50, 15 + buttonLength * 24, nameLength, "Burak Yeni"));
						list.add(new DrawingString(50, 15 + buttonLength * 36, nameLength, "Esad Burak Altinyazar"));
						list.add(new DrawingString(50, 15 + buttonLength * 48, nameLength, "Nurlan Farzaliyev"));
						list.add(new DrawingString(50, 15 + buttonLength * 60, nameLength, "Yigit Kutay Gulben"));
						inExtrasMenu = true;
						inMainMenu = false;

					}else if(list.get(i).string == "back" && list.get(i).getRectangle().contains(e.getX(), e.getY())) {
						for(int j = 0; j < list.size(); j++) {
							list.get(j).endAnimation.start();
						}
						pixel.restart();
						list.add(new DrawingString(xmenu, ymenu + 0 , buttonLength, "play"));
						list.add(new DrawingString(xmenu, ymenu + buttonLength * 7 , buttonLength, "settings"));
						list.add(new DrawingString(xmenu, ymenu + buttonLength * 14, buttonLength, "extras"));
						list.add(new DrawingString(xmenu, ymenu + buttonLength * 21, buttonLength, "exit"));
						panel.repaint();
						System.out.println("a");
						inExtrasMenu = false;
						inMainMenu = true;

					}
				}
			}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
		};
		panel.addMouseListener(ml);
	}
}