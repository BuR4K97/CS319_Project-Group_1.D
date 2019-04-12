package UIComponents;

import java.awt.Dimension;
import javax.swing.JFrame;

public class ApplicationFrame extends JFrame {
	
	public static int width = 1920;
	public static int height = 1080;
	
	public DynamicPanel focusPanel;
	
	public ApplicationFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setPreferredSize(new Dimension(width, height));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setVisible(true);
	}
	
	public void initializeMenu() {
		removePanel();
		
		//focusPanel = new ApplicationPanel();
		
		updatePanel();
	}
	
	public void initializeGame() {
		removePanel();
		focusPanel = new GamePanel();
		updatePanel();
	}
	
	public void destroyFocusPanel() {
		removePanel();
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
