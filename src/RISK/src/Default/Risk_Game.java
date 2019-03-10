package Default;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import Visual.Frame;

public class Risk_Game{

	public static void main(String[] args) {
		
		Game.playerNumber = 4;
		Game.loadGame();
		Frame frame = new Frame();
		
		Timer gameModelTimer = new Timer(1, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Game.update();
			frame.getPanel().repaint();
			}
		});
		gameModelTimer.start();
		
	}//main

}//endClass