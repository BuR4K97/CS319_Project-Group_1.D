package UIComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SelectGameModePanel extends JPanel{
	
	private JLabel singlePaleyr;
	private JLabel multiPlayer;
	private JLabel lblBack;
	private ArrayList<VisualString> stringList;
	//private Pixel mapAnimation;
	private int mouseX, mouseY;
	
	
	public SelectGameModePanel() {
		
		this.setPreferredSize(new Dimension(1920, 1080));
		setLayout(null);
		stringList = new ArrayList<VisualString>();
		//mapAnimation = new Pixel();
		mouseX = 0;
		mouseY = 0;

		singlePaleyr = new JLabel("");
		singlePaleyr.setFont(new Font("Baskerville Old Face", Font.BOLD, 50));
		singlePaleyr.setBounds(50, 737, 245, 51);
		add(singlePaleyr);

		multiPlayer = new JLabel("");
		multiPlayer.setFont(new Font("Baskerville Old Face", Font.BOLD, 50));
		multiPlayer.setBounds(50, 991, 104, 51);
		add(multiPlayer);
		
		lblBack = new JLabel("");
		lblBack.setFont(new Font("Algerian", Font.BOLD, 50));
		lblBack.setBounds(47, 989, 139, 66);
		add(lblBack);
		
		setBackground(Color.BLACK);
	}
	
	public void initialize()
	{
		stringList.add(new VisualString(530, 126, 14, "Select Game Mode"));
		stringList.add(new VisualString(265, 500, 10, "Singleplayer"));
		stringList.add(new VisualString(1210, 500, 10, "Multiplayer"));
		stringList.add(new VisualString(47, 989, 10, "Back"));
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//mapAnimation.paint(g);
		for(int i = 0; i < stringList.size(); i++)
			stringList.get(i).paint(g);
	}
	
	public JLabel getSingleplayerLabel()
	{
		return singlePaleyr;
	}

	public JLabel getMulJLabelLabel()
	{
		return multiPlayer;
	}

	public JLabel getBackLabel()
	{
		return lblBack;
	}

	public void setStringList() {
		this.stringList = new ArrayList<VisualString>();
	}
}