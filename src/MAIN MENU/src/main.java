import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class main {

	static char characters[] = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

	static ArrayList<DrawingString> list = new ArrayList<DrawingString>();
	static boolean exitProgress = false;
	static boolean close = true;
	public static void main(String[] args){

		// frame
		JFrame frame = new JFrame("alper");
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true); // last

		// draw
		final JPanel panel = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				for(int i = 0; i < list.size(); i++)
					list.get(i).paint(g);
			}
		};
		// timer for animations
		Timer t = new Timer(20, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(exitProgress) {
					close = true;
					for(int i = 0; i < list.size(); i++) {
						if(list.get(i).endAnimation.isRunning())
							close = false;
						if(close)
							System.exit(1);
					}
				}
					panel.repaint();
			}
		});
		t.start();
		int xmenu = 90;
		int ymenu = 700;
		list.add(new DrawingString(12, 12, 12, "1234567890qazwsxedcrfvtgbyhnujmikol0p"));
		list.add(new DrawingString(0, 80, 12, "duanla yasamadim ki bedduanla oleyim"));
		list.add(new DrawingString(xmenu, ymenu + 0 , 12, "play"));
		list.add(new DrawingString(xmenu, ymenu + 60 + 10 , 12, "settings"));
		list.add(new DrawingString(xmenu, ymenu + 120  + 20, 12, "extras"));
		list.add(new DrawingString(xmenu, ymenu + 180 + 30 , 12, "exit"));
		// others
		panel.setPreferredSize(new Dimension(1920, 1080));
		panel.setBackground(Color.BLACK);
		frame.add(panel);
		frame.setVisible(true);
		// mouse
		MouseListener ml = new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {

				for(int i = 0; i < list.size(); i++) {
					if(list.get(i).string == "exit" && list.get(i).getRectangle().contains(e.getX(), e.getY())) {
						for(int j = 0; j < list.size(); j++) {
							list.get(j).endAnimation.start();
						}
						exitProgress = true;
						break;
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