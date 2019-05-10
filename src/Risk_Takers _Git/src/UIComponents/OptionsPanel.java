package UIComponents;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import UIComponents.Fireworks.Fireworks;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.JScrollBar;
import javax.swing.JSlider;

public class OptionsPanel extends JPanel {

	private JLabel labelSettingsGif;
	private JComboBox comboBoxMusic;
	private JComboBox comboBoxChangeDifficulty;
	private JLabel lblPlay;
	private JLabel lblPause;
	private JLabel lblBack;
	private JSlider slider;
	private ArrayList<VisualString> stringList;
	private Fireworks fireWorks;
	
	
	
	public OptionsPanel() {
		this.setPreferredSize(new Dimension(1920, 1080));
		setLayout(null);
		setBackground(Color.BLACK);

		stringList = new ArrayList<VisualString>();

		labelSettingsGif = new JLabel("");
		labelSettingsGif.setBackground(new Color(0,0,0,0));
		labelSettingsGif.setForeground(new Color(0,0,0,0));
		labelSettingsGif.setIcon(new ImageIcon("images\\settings.gif"));
		labelSettingsGif.setBounds(648, 103, 148, 126);
		add(labelSettingsGif);

		lblBack = new JLabel("");
		lblBack.setFont(new Font("Algerian", Font.BOLD, 50));
		lblBack.setBounds(47, 989, 139, 66);
		add(lblBack);

		comboBoxMusic = new JComboBox();
		comboBoxMusic.setToolTipText("Change Music");
		comboBoxMusic.setSelectedIndex(-1);
		comboBoxMusic.setBackground(new Color(254,233,222,100));
		comboBoxMusic.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		comboBoxMusic.setForeground(new Color(254,233,222,100));
		comboBoxMusic.setAlignmentX (Component.CENTER_ALIGNMENT);
		comboBoxMusic.setBounds(200, 600, 248, 30);
		add(comboBoxMusic);

		comboBoxChangeDifficulty = new JComboBox();
		comboBoxChangeDifficulty.setToolTipText("Change Difficulty");
		comboBoxChangeDifficulty.setSelectedIndex(-1);
		comboBoxChangeDifficulty.setForeground(new Color(254, 233, 222, 100));
		comboBoxChangeDifficulty.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		comboBoxChangeDifficulty.setBackground(new Color(254, 233, 222, 100));
		comboBoxChangeDifficulty.setBounds(1472, 600, 248, 30);
		add(comboBoxChangeDifficulty);
		
		slider = new JSlider();
		slider.setBounds(832, 600, 248, 30);
		slider.setBackground(null);
		add(slider);
		
		lblPlay = new JLabel("");
		lblPlay.setForeground(Color.WHITE);
		lblPlay.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblPlay.setBounds(200, 558, 85, 29);
		add(lblPlay);
		
		lblPause = new JLabel("");
		lblPause.setForeground(Color.WHITE);
		lblPause.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblPause.setBounds(375, 558, 73, 29);
		add(lblPause);

	}

	public void initialize()
	{
		stringList.add(new VisualString(810, 126, 14, "Options"));
		stringList.add(new VisualString(255, 445, 7, "Music"));
		stringList.add(new VisualString(202, 562, 4, "Play"));
		stringList.add(new VisualString(373, 562, 4, "Pause"));
		stringList.add(new VisualString(875, 445, 7, "Volume"));
		stringList.add(new VisualString(1462, 445, 7, "Difficulty"));
		stringList.add(new VisualString(47, 989, 10, "Back"));
		//fireWorks = new Fireworks(); // I guess we dont need animation here, keep it simple))
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for(int i = 0; i < stringList.size(); i++)
			stringList.get(i).paint(g);
		//fireWorks.paint(g);
	}

	public JLabel getBackLabel()
	{
		return lblBack;
	}

	public void setStringList() {
		this.stringList = new ArrayList<VisualString>();
	}
}
