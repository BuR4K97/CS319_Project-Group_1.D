package UIComponents;

import java.awt.Dimension;
import javax.swing.JFrame;

public class ApplicationFrame extends JFrame {
	
	private int width = 1920;
	private int height = 1080;
	
	public DynamicPanel focusPanel;
	
	public void initialize() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(width, height));
		setVisible(true);
		pack();
	}
	
	public void render() {
		if(focusPanel != null) focusPanel.render(); 
	}
	
	public void updatePanel() {
		if(focusPanel != null) add(focusPanel);
	}
	
}
