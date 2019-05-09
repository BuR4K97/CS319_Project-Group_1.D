package UIComponents;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.JPanel;

import Controller.MainApplication;

public class ApplicationPanel extends DynamicPanel {
	
	private JPanel self;
	private JPanel menuPanel;
	private JPanel settingsPanel;
	private JPanel howToPlayPanel;
	private JPanel aboutUsPanel;
	private JPanel gamePanelTest;
	private JPanel selectGameModePanel;
	
	public ApplicationPanel() {
		// current panel in the frame
		self = this;
		CardLayout layout = new CardLayout();
		setLayout(layout);
		
		// panels
		menuPanel = new MenuPanel();
		settingsPanel = new OptionsPanel();
		howToPlayPanel = new HowToPlayPanel();
		aboutUsPanel = new AboutUsPanel();
		selectGameModePanel = new SelectGameModePanel();
		
		add(menuPanel, "menuPanel");
		add(settingsPanel, "settingsPanel");
		add(howToPlayPanel, "howToPlayPanel");
		add(aboutUsPanel, "aboutUsPanel");
		add(selectGameModePanel, "selectGameModePanel");
		
		// beginning of the game
		layout.show(this, "menuPanel");
		menuPanel.requestFocusInWindow();
		((MenuPanel)menuPanel).initialize();
		
//		layout.show(this, "selectGameModePanel");
//		selectGameModePanel.requestFocusInWindow();
//		((SelectGameModePanel)selectGameModePanel).initialize();
		
		
		/**---------------------------------------------------LISTENERS---------------------------------------*/
		
		// Mouse Listener to the Play Game label in Main Panel
		MouseListener listenerToPlayGameLabelInMenuPanel = new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				((MenuPanel)menuPanel).setStringList();
				layout.show(self, "selectGameModePanel");
				((SelectGameModePanel)selectGameModePanel).requestFocusInWindow();
				((SelectGameModePanel)selectGameModePanel).initialize();
			}
		};
		
		// Mouse Listener to the Single Player label in Main Panel
		MouseListener listenerToSinglePlayerLabelInSelectGameModePanel = new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				((SelectGameModePanel)selectGameModePanel).setStringList();
				layout.show(self, "gamePanel");
				MainApplication.destroyMenu();
				MainApplication.initializeGame();
			}
		};
		
		// Mouse Listener to the Multiplayer label in Main Panel
		MouseListener listenerToMultiplayerLabelInSelectGameModePanel = new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				((SelectGameModePanel)selectGameModePanel).setStringList();
				layout.show(self, "gamePanel");
				MainApplication.destroyMenu();
				MainApplication.initializeGame();
			}
		};
		
		// Mouse Listener to the Quit label in Main Panel
		MouseListener listenerToQuitLabelInMenuPanel = new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				System.exit(1);
			}
		};
		
		// Mouse Listener to the Settings label in Main Panel
		MouseListener listenerToSettingsLabelInMenuPanel = new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				((MenuPanel)menuPanel).setStringList();
				layout.show(self, "settingsPanel");
				((OptionsPanel)settingsPanel).requestFocusInWindow();
				((OptionsPanel)settingsPanel).initialize();
			}
		};
		
		// Mouse Listener to the How To Play label in Main Panel
		MouseListener listenerToHowToPlayLabelInMenuPanel = new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				((MenuPanel)menuPanel).setStringList();
				layout.show(self, "howToPlayPanel");
				((HowToPlayPanel)howToPlayPanel).requestFocusInWindow();
				((HowToPlayPanel)howToPlayPanel).initialize();
			}
		};
		
		// Mouse Listener to the About Us label in Main Panel
		MouseListener listenerToAboutUsLabelInMenuPanel = new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				((MenuPanel)menuPanel).setStringList();
				layout.show(self, "aboutUsPanel");
				((AboutUsPanel)aboutUsPanel).requestFocusInWindow();
				((AboutUsPanel)aboutUsPanel).initialize();
			}
		};		
				
		// Mouse Listener to the About Us label in Main Panel
		MouseListener listenerToBackLabelInSettingsHowTPAboutUsSelectGameModePanel = new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				((HowToPlayPanel)howToPlayPanel).setStringList();
				((AboutUsPanel)aboutUsPanel).setStringList();
				((OptionsPanel)settingsPanel).setStringList();
				((SelectGameModePanel)selectGameModePanel).setStringList();
				layout.show(self, "menuPanel");
				((MenuPanel)menuPanel).requestFocusInWindow();
				((MenuPanel)menuPanel).initialize();
			}
		};	
		
		
		
		// Listeners to the buttons
		((MenuPanel)menuPanel).getPlayGameLabel().addMouseListener(listenerToPlayGameLabelInMenuPanel);
		((MenuPanel)menuPanel).getQuitLabel().addMouseListener(listenerToQuitLabelInMenuPanel);
		((MenuPanel)menuPanel).getSettingsLabel().addMouseListener(listenerToSettingsLabelInMenuPanel);
		((MenuPanel)menuPanel).getHowToPlayLabel().addMouseListener(listenerToHowToPlayLabelInMenuPanel);
		((MenuPanel)menuPanel).getAboutUsLabel().addMouseListener(listenerToAboutUsLabelInMenuPanel);
		((OptionsPanel)settingsPanel).getBackLabel().addMouseListener(listenerToBackLabelInSettingsHowTPAboutUsSelectGameModePanel);
		((HowToPlayPanel)howToPlayPanel).getBackLabel().addMouseListener(listenerToBackLabelInSettingsHowTPAboutUsSelectGameModePanel);
		((AboutUsPanel)aboutUsPanel).getBackLabel().addMouseListener(listenerToBackLabelInSettingsHowTPAboutUsSelectGameModePanel);
		((SelectGameModePanel)selectGameModePanel).getBackLabel().addMouseListener(listenerToBackLabelInSettingsHowTPAboutUsSelectGameModePanel);
		((SelectGameModePanel)selectGameModePanel).getMultiplayerLabel().addMouseListener(listenerToMultiplayerLabelInSelectGameModePanel);
		((SelectGameModePanel)selectGameModePanel).getSingleplayerLabel().addMouseListener(listenerToMultiplayerLabelInSelectGameModePanel);
	}

	@Override
	public void update() {}

	@Override
	public void destroy() {}
}
