package UIComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import UIComponents.Fireworks.Fireworks;

public class AboutUsPanel extends JPanel {

	private JLabel lblBack;
	private ArrayList<VisualString> stringList;
	private Fireworks fireWorks;

	public AboutUsPanel(){

		this.setPreferredSize(new Dimension(1920, 1080));
		setLayout(null);
		setBackground(Color.BLACK);

		stringList = new ArrayList<VisualString>();

		lblBack = new JLabel("");
		lblBack.setFont(new Font("Algerian", Font.BOLD, 50));
		lblBack.setBounds(47, 989, 139, 66);
		add(lblBack);

	}

	public void initialize() {
		stringList.add(new VisualString(750, 126, 14, "About Us"));
		stringList.add(new VisualString(47, 989, 10, "Back"));
		stringList.add(new VisualString(790, 370, 7, "Anar Huseynov"));
		stringList.add(new VisualString(818, 440, 7, "Burak Mutlu"));
		stringList.add(new VisualString(833, 510, 7, "Burak Yeni"));
		stringList.add(new VisualString(691, 580, 7, "Esad Burak Altinyazar"));
		stringList.add(new VisualString(747, 650, 7, "Nurlan Farzaliyev"));
		stringList.add(new VisualString(730, 720, 7, "Yigit Kutay Gulben"));
		fireWorks = new Fireworks(); // I guess we dont need animation here, keep it simple))
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for(int i = 0; i < stringList.size(); i++)
			stringList.get(i).paint(g);
		fireWorks.paint(g);
	}

	public JLabel getBackLabel()
	{
		return lblBack;
	}

	public void setStringList() {
		this.stringList = new ArrayList<VisualString>();
	}
}
