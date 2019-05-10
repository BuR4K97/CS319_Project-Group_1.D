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
	private JComboBox comboBoxTurnOnOffMusic;
	private JComboBox comboBoxChangeDifficulty;
	private JLabel lblBack;
	private ArrayList<VisualString> stringList;
	private Fireworks fireWorks;
	/**
	 * Create the panel.
	 */
	public OptionsPanel() {
		this.setPreferredSize(new Dimension(1920, 1080));
		setLayout(null);
		setBackground(Color.BLACK);

		stringList = new ArrayList<VisualString>();

		labelSettingsGif = new JLabel("");
		labelSettingsGif.setBackground(new Color(0,0,0,0));
		labelSettingsGif.setForeground(new Color(0,0,0,0));
		labelSettingsGif.setIcon(new ImageIcon("images\\settings.gif"));
		labelSettingsGif.setBounds(436, 480, 148, 126);
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
		comboBoxMusic.setBounds(840, 412, 248, 30);
		add(comboBoxMusic);

		comboBoxTurnOnOffMusic = new JComboBox();
		comboBoxTurnOnOffMusic.setToolTipText("Turn On Off Music");
		comboBoxTurnOnOffMusic.setSelectedIndex(-1);
		comboBoxTurnOnOffMusic.setBackground(new Color(254,233,222,100));
		comboBoxTurnOnOffMusic.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		comboBoxTurnOnOffMusic.setForeground(new Color(254,233,222,100));
		comboBoxTurnOnOffMusic.setAlignmentX (Component.CENTER_ALIGNMENT);
		comboBoxTurnOnOffMusic.setBounds(840, 528, 248, 30);
		add(comboBoxTurnOnOffMusic);

		comboBoxChangeDifficulty = new JComboBox();
		comboBoxChangeDifficulty.setToolTipText("Change Difficulty");
		comboBoxChangeDifficulty.setSelectedIndex(-1);
		comboBoxChangeDifficulty.setForeground(new Color(254, 233, 222, 100));
		comboBoxChangeDifficulty.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		comboBoxChangeDifficulty.setBackground(new Color(254, 233, 222, 100));
		comboBoxTurnOnOffMusic.setAlignmentX (Component.CENTER_ALIGNMENT);
		comboBoxChangeDifficulty.setBounds(840, 644, 248, 30);
		add(comboBoxChangeDifficulty);
		
		JSlider slider = new JSlider();
		slider.setBounds(126, 120, 283, 45);
		add(slider);

	}

	public void initialize()
	{
		stringList.add(new VisualString(770, 126, 14, "Options"));
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
