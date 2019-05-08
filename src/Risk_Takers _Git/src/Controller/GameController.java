package Controller;

import GameAssets.GameConstants;
import GameAssets.DefaultRiskMode.DefaultRiskMode;
import ModelClasses.Game;
import UIComponents.GamePanel;

public class GameController {
	
	public static enum GAME_MODE{ DEFAULT, ANY_TYPE }
	public static GameMode activeMode;
	public static GameInteractions interactions;
	
	public static boolean initializeGame(int playerNumber, GAME_MODE loadMode) {
		loadGameMode(loadMode);
		interactions = new GameInteractions();
		if(!Game.initialize(playerNumber)) {
			interactions = null;
			return false;
		}
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
		GameConstants.loadGameConstants();
	}
	
	private static void destroyGameMode() {
		GameConstants.destroyGameConstants();
		activeMode.destroyGameMode();
		activeMode = null;
	}
	
}//endClass