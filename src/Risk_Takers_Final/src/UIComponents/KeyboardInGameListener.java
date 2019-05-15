package UIComponents;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.KeyStroke;

public class KeyboardInGameListener extends KeyAdapter
{
	public boolean escPressed;
	
	public KeyboardInGameListener() {
		escPressed = false;
	}
	
	@Override
	public void keyPressed(KeyEvent event)
	{
		//System.err.println("Inside key pressed");
		//String key = KeyStroke.getKeyStrokeForEvent(event).toString();
		//System.out.println(key);
		//key = key.replace("pressed ", "");
		if(event.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			if (escPressed)
				escPressed = false;
			else
				escPressed = true;
		}
	}
	
	public boolean getEscPressed() {
		return escPressed;
	}

}
