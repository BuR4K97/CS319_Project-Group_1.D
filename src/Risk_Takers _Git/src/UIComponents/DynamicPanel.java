package UIComponents;

import javax.swing.JPanel;

public abstract class DynamicPanel extends JPanel {
	
	public abstract void update();
	public abstract void destroy();
	
}
