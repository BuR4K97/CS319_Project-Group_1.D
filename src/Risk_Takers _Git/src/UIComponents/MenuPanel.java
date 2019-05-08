package UIComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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
	private int mouseX, mouseY;

	public MenuPanel() {
		this.setPreferredSize(new Dimension(1920, 1080));
		setLayout(null);

		stringList = new ArrayList<VisualString>();
		mouseX = 0;
		mouseY = 0;
		
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

		// timer for draw sea using mouse
		Timer seaMouse = new Timer(50, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapAnimation.listCircle.add(new Circle( mouseX, mouseY));

			}
		});

		MouseListener ml = new MouseListener() {
			public void mouseReleased(MouseEvent e) {
				seaMouse.stop();
			}
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
				mapAnimation.listCircle.add(new Circle( mouseX, mouseY));
			}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
			}
		};
		MouseMotionListener mml = new MouseMotionListener() {
			public void mouseMoved(MouseEvent e) {}
			public void mouseDragged(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
				seaMouse.start();
			}
		};
		addMouseListener(ml);
		addMouseMotionListener(mml);

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
