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
		stringList.add(new VisualString(670, 370, 4, "The implementation is not finished yet,"));
		stringList.add(new VisualString(670, 400, 4, "We will fill this screen when the hole "));
		stringList.add(new VisualString(670, 430, 4, "implementation of the game is finished."));
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
