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

import Controller.GameInteractions;
import GameAssets.GameConstants;
import GameAssets.SoundConstants;
import ModelClasses.Game;
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
	public static int maxNumberOfHuman = Game.MAX_PLAYER_NUMBER;
	public static int minNumberOfHuman = Game.MIN_PLAYER_NUMBER;
	public static int totalNumberOfHuman = Game.PLAYER_MODE.MULTIPLAYER.playerNumber;
	public static int totalNumber = 2;

	int singleMUltiY = 500;
	int singleX = 195;
	int multiX = 1280;
	// mouseListener
	MouseListener ml;

	public SelectGameModePanel() {

		this.setPreferredSize(new Dimension(1920, 1080));
		setLayout(null);
		stringList = new ArrayList<VisualString>();


		singlePlayer = new JLabel("");
		singlePlayer.setFont(new Font("Baskerville Old Face", Font.BOLD, 50));
		singlePlayer.setBounds(singleX, singleMUltiY, 430, 51);
		add(singlePlayer);

		multiPlayer = new JLabel("");
		multiPlayer.setFont(new Font("Baskerville Old Face", Font.BOLD, 50));
		multiPlayer.setBounds(multiX, singleMUltiY, 430, 51);
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
			public void mouseEntered(MouseEvent e) {
				SoundConstants.menuMouseOnButtonSound();
			}
			public void mouseClicked(MouseEvent e) {
				if(stringList.get(stringList.size()-2).getRectangle().contains(getMousePosition())) {
					if(totalNumber < maxNumberOfHuman) {
						totalNumber++;
						stringList.get(stringList.size()-1).updateStringNoAnimation( "" + totalNumber);
						stringList.get(stringList.size()-6).updateStringNoAnimation("" + totalNumberOfHuman);
						stringList.get(stringList.size()-5).updateStringNoAnimation("" + (totalNumber - 1));
						stringList.get(stringList.size()-4).updateStringNoAnimation(  "" + (totalNumber - totalNumberOfHuman));
						SoundConstants.menuClickSound();
					}
				}
				if(stringList.get(stringList.size()-3).getRectangle().contains(getMousePosition())) {
					if(totalNumber > minNumberOfHuman) {
						totalNumber--;
						if(totalNumber < totalNumberOfHuman) {
							totalNumberOfHuman--;
							jumpingBox.removeBox();
						}
						stringList.get(stringList.size()-1).updateStringNoAnimation( "" + totalNumber);
						stringList.get(stringList.size()-6).updateStringNoAnimation("" + totalNumberOfHuman);
						stringList.get(stringList.size()-5).updateStringNoAnimation("" + (totalNumber - 1));
						stringList.get(stringList.size()-4).updateStringNoAnimation(  "" + (totalNumber - totalNumberOfHuman));
						SoundConstants.menuClickSound();
					}
				}
				if(jumpingBox.getRightRectangle().contains(getMousePosition()) && SwingUtilities.isLeftMouseButton(e)) {
					if(totalNumberOfHuman < totalNumber) {
						totalNumberOfHuman++;
						jumpingBox.addBox();
						stringList.get(stringList.size()-1).updateStringNoAnimation( "" + totalNumber);
						stringList.get(stringList.size()-6).updateStringNoAnimation("" + totalNumberOfHuman);
						stringList.get(stringList.size()-5).updateStringNoAnimation("" + (totalNumber - 1));
						stringList.get(stringList.size()-4).updateStringNoAnimation(  "" + (totalNumber - totalNumberOfHuman));
						SoundConstants.menuClickSound();
					}else if(totalNumberOfHuman == totalNumber && totalNumber < maxNumberOfHuman) {
						totalNumberOfHuman++;
						jumpingBox.addBox();
						totalNumber++;
						stringList.get(stringList.size()-1).updateStringNoAnimation( "" + totalNumber);
						stringList.get(stringList.size()-6).updateStringNoAnimation("" + totalNumberOfHuman);
						stringList.get(stringList.size()-5).updateStringNoAnimation("" + (totalNumber - 1));
						stringList.get(stringList.size()-4).updateStringNoAnimation(  "" + (totalNumber - totalNumberOfHuman));
						SoundConstants.menuClickSound();
					}
				}else if(jumpingBox.getRightRectangle().contains(getMousePosition()) && SwingUtilities.isRightMouseButton(e)) {
					if(totalNumberOfHuman > minNumberOfHuman) {
						totalNumberOfHuman--;
						jumpingBox.removeBox();
						SoundConstants.menuClickSound();
						stringList.get(stringList.size()-1).updateStringNoAnimation( "" + totalNumber);
						stringList.get(stringList.size()-6).updateStringNoAnimation("" + totalNumberOfHuman);
						stringList.get(stringList.size()-5).updateStringNoAnimation("" + (totalNumber - 1));
						stringList.get(stringList.size()-4).updateStringNoAnimation(  "" + (totalNumber - totalNumberOfHuman));
					}
				}
				GameInteractions.requestResetMultiplayerMode(totalNumberOfHuman);
			}
		};
		this.addMouseListener(ml);
	}

	public void initialize()
	{
		
		stringList.add(new VisualString(530, 126, 14, "Select Game Mode"));
		stringList.add(new VisualString(singleX, singleMUltiY, 10, "Singleplayer"));
		stringList.add(new VisualString(multiX, singleMUltiY, 10, "Multiplayer"));
		stringList.add(new VisualString(singleX,  singleMUltiY + 295, 5, "Number of humans"));
		stringList.add(new VisualString(multiX,  singleMUltiY + 295, 5, "Number of humans"));
		stringList.add(new VisualString(singleX, singleMUltiY + 340, 5, "Number of AI"));
		stringList.add(new VisualString(multiX, singleMUltiY + 340, 5, "Number of AI"));
		stringList.add(new VisualString(47, 989, 10, "Back"));

		stringList.add(new VisualString(550, 325,7, "Number of players", true));
		
		stringList.add(new VisualString(singleX + 440, singleMUltiY + 295, 5, "1", true));
		stringList.add(new VisualString(multiX + 425, singleMUltiY +  295 , 5, "" + totalNumberOfHuman, true));
		stringList.add(new VisualString(singleX + 440, singleMUltiY + 340, 5, "" + (totalNumber - 1), true));
		stringList.add(new VisualString(multiX + 425, singleMUltiY +  340, 5, "" + (totalNumber - totalNumberOfHuman), true));
		
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
