package UIComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import GameAssets.GameConstants;
import UIComponents.BoxAni.JumpingBox;
import UIComponents.Fireworks.Fireworks;

public class SelectGameModePanel extends JPanel{

	private JLabel singlePaleyr;
	
	private JLabel singlePlayer;
	private JLabel multiPlayer;
	private JLabel lblBack;

	private ArrayList<VisualString> stringList;
	private JumpingBox jumpingBox;
	// new
	public static int maxNumberOfHuman = 4;
	public static int minNumberOfHuman = 2;
	public static int totalNumberOfHuman = 2;
	public static int totalNumber = 2;
	public static int totalNumberOfAI = totalNumber - totalNumberOfHuman;


	// mouseListener
	MouseListener ml;

	public SelectGameModePanel() {

		this.setPreferredSize(new Dimension(1920, 1080));
		setLayout(null);
		stringList = new ArrayList<VisualString>();


		singlePlayer = new JLabel("");
		singlePlayer.setFont(new Font("Baskerville Old Face", Font.BOLD, 50));
		singlePlayer.setBounds(265, 500, 430, 51);
		add(singlePlayer);

		multiPlayer = new JLabel("");
		multiPlayer.setFont(new Font("Baskerville Old Face", Font.BOLD, 50));
		multiPlayer.setBounds(1210, 500, 430, 51);
		add(multiPlayer);

		lblBack = new JLabel("");
		lblBack.setFont(new Font("Algerian", Font.BOLD, 50));
		lblBack.setBounds(47, 989, 139, 66);
		add(lblBack);

		setBackground(Color.BLACK);

		ml = new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				if(stringList.get(stringList.size()-2).getRectangle().contains(getMousePosition())) {
					if(totalNumber < maxNumberOfHuman) {
						totalNumber++;
						stringList.get(stringList.size()-1).updateStringNoAnimation( "" + totalNumber);
					}
				}
				if(stringList.get(stringList.size()-3).getRectangle().contains(getMousePosition())) {
					if(totalNumber > minNumberOfHuman) {
						totalNumber--;
						stringList.get(stringList.size()-1).updateStringNoAnimation( "" + totalNumber);
					}
				}
				if(jumpingBox.getRightRectangle().contains(getMousePosition()) && SwingUtilities.isLeftMouseButton(e)) {
					if(totalNumberOfHuman < totalNumber) {
						totalNumberOfHuman++;
						jumpingBox.addBox();
					}
				}else if(jumpingBox.getRightRectangle().contains(getMousePosition()) && SwingUtilities.isRightMouseButton(e)) {
					if(totalNumberOfHuman > minNumberOfHuman) {
						totalNumberOfHuman--;
						jumpingBox.removeBox();

					}
				}
			}
		};
		this.addMouseListener(ml);
	}

	public void initialize()
	{
		
		stringList.add(new VisualString(530, 126, 14, "Select Game Mode"));
		stringList.add(new VisualString(265, 500, 10, "Singleplayer"));
		stringList.add(new VisualString(1210, 500, 10, "Multiplayer"));
		stringList.add(new VisualString(750, 800, 7, "Number of humans"));
		stringList.add(new VisualString(47, 989, 10, "Back"));

		stringList.add(new VisualString(550, 325,7, "Number of players", true));
		stringList.add(new VisualString(1100, 325, 10, "<", true));
		stringList.add(new VisualString(1300, 325, 10, ">", true));

		stringList.add(new VisualString(1200, 325,13, "" + totalNumber, true)); // must be last !!

		jumpingBox = new JumpingBox();
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//mapAnimation.paint(g);
		for(int i = 0; i < stringList.size(); i++)
			stringList.get(i).paint(g);
		jumpingBox.paint(g);
		System.out.println(totalNumber + " " + totalNumberOfHuman);
	}

	public JLabel getSingleplayerLabel()
	{
		return singlePlayer;
	}

	public JLabel getMultiplayerLabel()
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
