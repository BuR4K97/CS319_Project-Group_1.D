package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import Controller.GameController.GAME_MODE;
import UIComponents.ApplicationFrame;
import UIComponents.GamePanel;
import UIComponents.MenuPanel;

public class MainApplication {
	
	public static ApplicationFrame frame;
	public static final int FRAME_PER_SECOND = 10;
	public static final int MODEL_UPDATE_FREQUENCY = 100;
	public static final int ONE_SEC = 1000;
	
	private static Timer visualDisplayTimer;
	private static Timer modelDataTimer;
	
			
	
	public static void initialize() {
		frame = new ApplicationFrame();
		frame.initialize();
		
		visualDisplayTimer = new Timer(ONE_SEC / FRAME_PER_SECOND, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) { frame.render(); }
			
		});
		modelDataTimer = new Timer(ONE_SEC / MODEL_UPDATE_FREQUENCY, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) { GameController.updateGame(); }
			
		});
		visualDisplayTimer.start();
	}
	
	
	public static boolean initializeGame() {
		visualDisplayTimer.stop();
		
		if(frame.focusPanel != null) frame.focusPanel.destroy();
		frame.focusPanel = new GamePanel();
		if(!GameController.initializeGame(2, GAME_MODE.DEFAULT)) return false;
		frame.updatePanel();
		
		modelDataTimer.restart();
		visualDisplayTimer.restart();
		frame.initialize();
		return true;
	}
	
	public static boolean initializeMenu() {
		visualDisplayTimer.stop();
		
		if(frame.focusPanel != null) frame.focusPanel.destroy();
		frame.focusPanel = new MenuPanel();
		frame.focusPanel.initialize();
		frame.updatePanel();
		
		visualDisplayTimer.restart();
		frame.initialize();
		return true;
	}
	
	public static void destroyGame() {
		visualDisplayTimer.stop();
		modelDataTimer.stop();
		
		GameController.destroyGame();
		frame.remove(frame.focusPanel);
		frame.focusPanel = null;
	}
}
