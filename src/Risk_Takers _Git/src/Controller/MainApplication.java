package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import AnimationComponents.AnimationHandler;
import Controller.GameController.GAME_MODE;
import UIComponents.ApplicationFrame;
import UIComponents.GamePanel;

public class MainApplication {
	
	public static ApplicationFrame frame;
	public static final int FRAME_PER_SECOND = 60;
	public static final int MODEL_UPDATE_FREQUENCY = 60;
	public static final int ANIMATION_UPDATE_FREQUENCY = 60;
	public static final int ONE_SEC = 1000;
	
	private static Timer visualDisplayTimer;
	private static Timer modelDataTimer;
	private static Timer animationTimer;
	
	public static void initialize() {
		frame = new ApplicationFrame();
		
		visualDisplayTimer = new Timer(ONE_SEC / FRAME_PER_SECOND, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) { frame.repaint(); }
			
		});
		modelDataTimer = new Timer(ONE_SEC / MODEL_UPDATE_FREQUENCY, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) { GameController.updateGame(); }
			
		});
		animationTimer = new Timer(ONE_SEC / ANIMATION_UPDATE_FREQUENCY, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) { AnimationHandler.executeAnimations(); }
			
		});
		visualDisplayTimer.start();
	}
	
	public static boolean initializeMenu() {
		visualDisplayTimer.stop();
		
		frame.initializeMenu();
		
		visualDisplayTimer.restart();
		frame.setVisible(true);
		return true;
	}
	
	public static void destroyMenu() {
		visualDisplayTimer.stop();
		frame.destroyFocusPanel();
		frame.setVisible(true);
	}
	
	public static boolean initializeGame() {
		visualDisplayTimer.stop();
		
		if(!GameController.initializeGame(2, GAME_MODE.DEFAULT)) return false;
		frame.initializeGame();
		
		animationTimer.restart();
		modelDataTimer.restart();
		visualDisplayTimer.restart();
		frame.setVisible(true);
		return true;
	}
	
	public static void destroyGame() {
		visualDisplayTimer.stop();
		modelDataTimer.stop();
		
		GameController.destroyGame();
		frame.destroyFocusPanel();
		frame.setVisible(true);
	}

}
