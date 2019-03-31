package UIComponents;

import javax.swing.JPanel;

public abstract class DynamicPanel extends JPanel {
	
	public abstract boolean initialize();
	public abstract void update();
	public abstract void render();
	public abstract void destroy();
	
}
