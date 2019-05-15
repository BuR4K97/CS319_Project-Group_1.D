package UIComponents;

import java.awt.Dimension;
import javax.swing.JFrame;

import SoundComponent.SoundHandler;

public class ApplicationFrame extends JFrame {
	
	public static int width = 1920;
	public static int height = 1080;
	
	public DynamicPanel focusPanel;
	
	public ApplicationFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(ApplicationFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setVisible(true);
	}
	
	public void initializeMenu() {
		removePanel();
		focusPanel = new ApplicationPanel();
		updatePanel();
		setVisible(true);
	}
	
	public void initializeGame() {
		removePanel();
		focusPanel = new GamePanel();
		updatePanel();
		setVisible(true);
	}
	
	public void destroyFocusPanel() {
		removePanel();
		setVisible(true);
	}
	
	private void updatePanel() {
		if(focusPanel != null) 
			add(focusPanel);
	}
	
	private void removePanel() {
		if(focusPanel == null) return;
		
		focusPanel.destroy();
		remove(focusPanel);
		focusPanel = null;
	}
	
}
