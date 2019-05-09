package UIComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import UIComponents.Fireworks.Fireworks;
import UIComponents.RectAnimation.RectAnimation;

public class HowToPlayPanel extends JPanel{

	private JLabel lblHowToPlay;
	private JLabel lblBack;
	private JTextArea txtHowToPlay;
	private ArrayList<VisualString> stringList;
	//private Fireworks fireWorks;
	RectAnimation ra;

	public HowToPlayPanel(){

		this.setPreferredSize(new Dimension(1920, 1080));
		setLayout(null);
		setBackground(Color.BLACK);

		stringList = new ArrayList<VisualString>();
		
			
		/*
		txtHowToPlay = new JTextArea();
		txtHowToPlay.setEditable(false);
		txtHowToPlay.setFont(new Font("Times New Roman", Font.BOLD, 50));
		txtHowToPlay.setForeground(Color.WHITE);
		txtHowToPlay.append("Even we dunno how to play this shit \n");
		txtHowToPlay.append("\n");
		txtHowToPlay.append("\n");
		txtHowToPlay.append("PS: Game is not ready for fuck sake,\n");
		txtHowToPlay.append("hayir how to play'i bilipte napcan \n");
		txtHowToPlay.setRows(10);
		txtHowToPlay.setColumns(10);
		txtHowToPlay.setBounds(677, 446, 790, 291);
		txtHowToPlay.setOpaque(false);
		add(txtHowToPlay);*/

		lblBack = new JLabel("");
		lblBack.setFont(new Font("Algerian", Font.BOLD, 50));
		lblBack.setBounds(47, 989, 139, 66);
		add(lblBack);

	}

	public void initialize()
	{
		stringList.add(new VisualString(670, 126, 14, "How To Play"));
		stringList.add(new VisualString(47, 989, 10, "Back"));
		stringList.add(new VisualString(500, 370, 4, "After Play Game section Choose the Mode You Want to Play,"));
		stringList.add(new VisualString(330, 400, 4, "Choose the Number of Players and Select either SINGLEPLAYER or MULTIPLAYER "));
		stringList.add(new VisualString(350, 430, 4, "When the Game Starts You Need to Place Your Soldiers to Your Territories"));
		stringList.add(new VisualString(300, 460, 4, "After Placing Soldiers by Clicking Next Phase You May Choose a Target Territory to Attack"));
		stringList.add(new VisualString(310, 490, 4, "When You Win a Battle You will Receive the Card of the Territory You Have Recently Captured"));
		stringList.add(new VisualString(250, 520, 4, "Your Cards are Extreamly Important because You May Gain new Soldiers by the Combinations of Your Cards"));
		stringList.add(new VisualString(5340, 550, 4, "Don t Forget Your Main Goal is to Concur the Whole World with Your perfect Strategy"));
		stringList.add(new VisualString(610, 580, 4, "Be Quick There will ONLY one WINNER"));
		//fireWorks = new Fireworks(); // I guess we dont need animation here, keep it simple))
		ra = new RectAnimation();
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for(int i = 0; i < stringList.size(); i++)
			stringList.get(i).paint(g);
		//fireWorks.paint(g);
		ra.paint(g);
	} 

	public JLabel getBackLabel()
	{
		return lblBack;
	}

	public void setStringList() {
		this.stringList = new ArrayList<VisualString>();
	}
}
