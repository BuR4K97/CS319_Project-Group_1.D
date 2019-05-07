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
		
		add(menuPanel, "menuPanel");
		add(settingsPanel, "settingsPanel");
		add(howToPlayPanel, "howToPlayPanel");
		add(aboutUsPanel, "aboutUsPanel");
		
		// beginning of the game
		layout.show(this, "menuPanel");
		menuPanel.requestFocusInWindow();
		((MenuPanel)menuPanel).initialize();
		
		/**---------------------------------------------------LISTENERS---------------------------------------*/
		
		// Mouse Listener to the Play Game label in Main Panel
		MouseListener listenerToPlayGameLabelInMenuPanel = new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				((MenuPanel)menuPanel).setStringList();
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
		MouseListener listenerToBackLabelInSettingsHowTPAboutUsPanel = new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				((HowToPlayPanel)howToPlayPanel).setStringList();
				((AboutUsPanel)aboutUsPanel).setStringList();
				((OptionsPanel)settingsPanel).setStringList();
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
		((OptionsPanel)settingsPanel).getBackLabel().addMouseListener(listenerToBackLabelInSettingsHowTPAboutUsPanel);
		((HowToPlayPanel)howToPlayPanel).getBackLabel().addMouseListener(listenerToBackLabelInSettingsHowTPAboutUsPanel);
		((AboutUsPanel)aboutUsPanel).getBackLabel().addMouseListener(listenerToBackLabelInSettingsHowTPAboutUsPanel);
	}

	@Override
	public void update() {}

	@Override
	public void destroy() {}
}
