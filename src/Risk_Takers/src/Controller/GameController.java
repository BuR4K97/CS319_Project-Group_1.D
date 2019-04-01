package Controller;

import GameAssets.DefaultRiskMode.DefaultRiskMode;
import ModelClasses.Game;
import UIComponents.GamePanel;

public class GameController {
	
	public static enum GAME_MODE{ DEFAULT, ANY_TYPE }
	public static GameMode activeMode;
	public static GameInteractions interactions;
	
	public static boolean initializeGame(int playerNumber, GAME_MODE loadMode) {
		loadGameMode(loadMode);
		if(!Game.initialize(playerNumber)) return false;
		if(!(MainApplication.frame.focusPanel instanceof GamePanel)) return false;
		if(!MainApplication.frame.focusPanel.initialize()) return false;
		interactions = new GameInteractions();
		return true;
	}
	
	public static void updateGame() {
		Game.update();
		MainApplication.frame.focusPanel.update();
	}
	
	public static void destroyGame() {
		if(!(MainApplication.frame.focusPanel instanceof GamePanel)) return;
		MainApplication.frame.focusPanel.destroy();
		Game.destroy();
		interactions = null;
		destroyGameMode();
	}
	
	private static void loadGameMode(GAME_MODE loadMode) {
		if(loadMode == GAME_MODE.DEFAULT) activeMode = new DefaultRiskMode();
		
		activeMode.loadGameMode();
	}
	
	private static void destroyGameMode() {
		activeMode.destroyGameMode();
		activeMode = null;
	}
	
}//endClass
