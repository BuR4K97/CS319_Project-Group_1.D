package UIComponents.BoxAni;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import GameAssets.GameConstants;

public class JumpingBox {
	// lines
	static int lineLength = 450;
	static int y = 900;
	static int diffFromBorders = 265;
	static int yMax = -200;
	
	static int leftLineX = diffFromBorders;
	static int rightLineX = 1920 - diffFromBorders - lineLength;
	// timer
	Timer t, timerForSpeed;
	// single box
	Box singleBox = new Box( JumpingBox.diffFromBorders + JumpingBox.lineLength/2, GameConstants.PLAYER_COLORS[0]);
	ArrayList<Box> multiList = new ArrayList<Box>();
	public JumpingBox() {
		multiList.add(new Box( rightLineX + lineLength/5, GameConstants.PLAYER_COLORS[1]));
		multiList.add(new Box( rightLineX + lineLength/3, GameConstants.PLAYER_COLORS[2]));
		multiList.add(new Box( rightLineX + lineLength/4, GameConstants.PLAYER_COLORS[3]));
		multiList.add(new Box( rightLineX + lineLength/2, GameConstants.PLAYER_COLORS[0]));
		
		t = new Timer(16, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				singleBox.update();
				for(int i = 0; i < multiList.size(); i++)
					multiList.get(i).update();
			}
		});
		t.start();
	}

	public void paint(Graphics g) {
		// lines
		g.setColor(Color.LIGHT_GRAY);
		// bottom lines
		g.drawLine(leftLineX, y, leftLineX + lineLength, y);
		g.drawLine(rightLineX, y, rightLineX + lineLength, y);
		// left lines
		g.drawLine(leftLineX, y, leftLineX, y + yMax);
		g.drawLine(rightLineX, y, rightLineX, y + yMax);
		// right lines
		g.drawLine(leftLineX + lineLength, y, leftLineX + lineLength, y + yMax);
		g.drawLine(rightLineX + lineLength, y, rightLineX + lineLength, y + yMax);
		
		singleBox.paint(g);
		for(int i = 0; i < multiList.size(); i++)
			multiList.get(i).paint(g);
	}
}
