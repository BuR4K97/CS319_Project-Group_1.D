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
		stringList.add(new VisualString(247, 240, 5, "TO ENTER THE GAME"));
		stringList.add(new VisualString(47, 280, 4, "you can select singleplayer or multiplayer mode in play game menu",1));
		stringList.add(new VisualString(47, 310, 4, "you can increase or decrease total player number and total human number",1));
		stringList.add(new VisualString(47, 340, 4, "increase humans by left click the box under multiplayer and decrease by right click",1));
		stringList.add(new VisualString(247, 380, 5, "FORTIFY"));
		stringList.add(new VisualString(47, 420, 4, "territories are randomly distributed after game starts and you can fortify your territories",1));
		stringList.add(new VisualString(47, 450, 4, "left click to get unit from your inventory right click to release unit",1));
		stringList.add(new VisualString(47, 480, 4, "on your territory left click to add all units and right click to get back all units to invertory",1));
		stringList.add(new VisualString(247, 520, 5, "ATTACK"));
		stringList.add(new VisualString(47, 560, 4, "you first choose which territory you use then select target territory and click attack button",1));
		stringList.add(new VisualString(47, 590, 4, "in attack screen you can attack per roll you can attack till capture or you can terminate attack",1));
		stringList.add(new VisualString(47, 620, 4, "you can get the card of this territory automatically in draft mode if you can capture a territory",1));
		stringList.add(new VisualString(247, 660, 5, "DRAFT"));
		stringList.add(new VisualString(47, 700, 4, "You will Receive the Cards of the Territories You Have Recently Captured after You Win a Battle",1));
		stringList.add(new VisualString(47, 730, 4, "you can combine the 3 cards with the same color to get the units displayed on cards",1));
		stringList.add(new VisualString(47, 760, 4, "you can have maximum 9 cards so using cards is best option",1));
		stringList.add(new VisualString(47, 790, 4, "you can move units from your territory to nearest territory you have by left and right clicks",1));
		
		stringList.add(new VisualString(147, 850, 5, "Do not Forget Your Main Goal is to conquer the Whole World with Your own Strategy"));
		stringList.add(new VisualString(147, 900, 5, "Be Quick There will ONLY one WINNER"));
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
