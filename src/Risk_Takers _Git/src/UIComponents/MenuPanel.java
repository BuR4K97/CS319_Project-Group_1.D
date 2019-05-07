package UIComponents;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class MenuPanel extends JPanel {

	private JLabel lblPlayGame;
	private JLabel lblQuit;
	private JLabel lblSettings;
	private JLabel lblHowToPlay;
	private JLabel lblAboutUs;
	private Pixel mapAnimation;
	private ArrayList<VisualString> stringList;
	private Fireworks fireWorks;

	public MenuPanel() {
		this.setPreferredSize(new Dimension(1920, 1080));
		setLayout(null);

		stringList = new ArrayList<VisualString>();

		lblPlayGame = new JLabel("");
		lblPlayGame.setFont(new Font("Baskerville Old Face", Font.BOLD, 50));
		lblPlayGame.setBounds(50, 737, 245, 51);
		add(lblPlayGame);

		lblQuit = new JLabel("");
		lblQuit.setFont(new Font("Baskerville Old Face", Font.BOLD, 50));
		lblQuit.setBounds(50, 991, 104, 51);
		add(lblQuit);


		lblSettings = new JLabel("");
		lblSettings.setFont(new Font("Baskerville Old Face", Font.BOLD, 50));
		lblSettings.setBounds(50, 804, 176, 51);
		add(lblSettings);

		lblHowToPlay = new JLabel("");
		lblHowToPlay.setFont(new Font("Baskerville Old Face", Font.BOLD, 50));
		lblHowToPlay.setBounds(50, 867, 305, 51);
		add(lblHowToPlay);

		lblAboutUs = new JLabel("");
		lblAboutUs.setFont(new Font("Baskerville Old Face", Font.BOLD, 50));
		lblAboutUs.setBounds(50, 927, 253, 51);
		add(lblAboutUs);

		setBackground(Color.BLACK);

		mapAnimation = new Pixel();

	}

	public void initialize()
	{
		stringList.add(new VisualString(860, 0, 14, "Risk"));
		stringList.add(new VisualString(864, 75, 3, "Mind Blowing Game"));
		stringList.add(new VisualString(50, 737, 8, "Play Game"));
		stringList.add(new VisualString(50, 991, 8, "Quit"));
		stringList.add(new VisualString(50, 804, 8, "Options"));
		stringList.add(new VisualString(50, 867, 8, "How To Play"));
		stringList.add(new VisualString(50, 927, 8, "About us"));
		//fireWorks = new Fireworks(); // I guess we dont need animation here, keep it simple))
	}


	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		mapAnimation.paint(g);
		for(int i = 0; i < stringList.size(); i++)
			stringList.get(i).paint(g);
		//fireWorks.paint(g);
	}

	public JLabel getPlayGameLabel()
	{
		return lblPlayGame;
	}

	public JLabel getSettingsLabel()
	{
		return lblSettings;
	}

	public JLabel getHowToPlayLabel()
	{
		return lblHowToPlay;
	}

	public JLabel getAboutUsLabel()
	{
		return lblAboutUs;
	}

	public JLabel getQuitLabel()
	{
		return lblQuit;
	}

	public void setStringList() {
		this.stringList = new ArrayList<VisualString>();
	}
}
