package UIComponents;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.concurrent.Phaser;

import javax.sound.sampled.FloatControl;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import SoundComponent.SoundHandler;
import UIComponents.Fireworks.Fireworks;
import UIComponents.QuitAnimation.QuitAnimation;


public class QuitPanel extends JPanel {
	Timer t;
	QuitAnimation qa;
	public QuitPanel() {
		this.setPreferredSize(new Dimension(1920, 1080));
		setLayout(null);
		setBackground(Color.BLACK);
		
		t = new Timer(16, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			repaint();
			}
		});

	}

	public void initialize()
	{
		qa = new QuitAnimation();
		t.start();
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		qa.paint(g);
	}
}
