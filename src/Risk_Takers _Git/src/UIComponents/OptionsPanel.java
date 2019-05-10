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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import SoundComponent.SoundHandler;
import UIComponents.Fireworks.Fireworks;


public class OptionsPanel extends JPanel {

	private JLabel labelSettingsGif;
	private JComboBox comboBoxMusic;
	private JComboBox comboBoxChangeDifficulty;
	private JLabel lblPlay;
	private JLabel lblPause;
	private boolean playing;
	private JLabel lblBack;
	private JSlider slider;
	private JLabel volumeLabel;
	private float volumeChange;
	private String[] musicNames;
	private String currentMusicLoc;
	private FloatControl gainControl;
	private ArrayList<VisualString> stringList;
	private Fireworks fireWorks;
	
	
	public OptionsPanel() {
		this.setPreferredSize(new Dimension(1920, 1080));
		setLayout(null);
		setBackground(Color.BLACK);

		stringList = new ArrayList<VisualString>();
		musicNames = new String[] {"The Long Dark Wintermute Soundtrack", "Frostpunk Soundtrack"};
		
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

		comboBoxMusic = new JComboBox(musicNames);
		comboBoxMusic.setToolTipText("Change Music");
		comboBoxMusic.setSelectedIndex(-1);
		//comboBoxMusic.setBackground(new Color(254,233,222,100));
		comboBoxMusic.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		comboBoxMusic.setForeground(Color.BLACK);
		comboBoxMusic.setSelectedIndex(0);
		comboBoxMusic.setAlignmentX (Component.CENTER_ALIGNMENT);
		comboBoxMusic.setBounds(200, 600, 248, 30);
		
		
		comboBoxMusic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxMusic.getSelectedIndex() == 0){
					currentMusicLoc = "sounds\\the-long-dark-wintermute-main-menu-theme-hq-tld-unost.wav";
					SoundHandler.stopMusic();
					SoundHandler.playMusic(currentMusicLoc);
					System.out.println(0);
				}
				else {
					currentMusicLoc = "sounds\\frostpunk-soundtrack-main-theme.wav";
					SoundHandler.stopMusic();
					SoundHandler.playMusic(currentMusicLoc);
					System.out.println(1);
				}
			}
		});
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
		slider.setValue(100);
		slider.setMinimum(0);
		slider.setMaximum(100);
		//slider.setMajorTickSpacing(5);
		slider.setBackground(null);
		
		volumeLabel = new JLabel(slider.getValue() + "");
		volumeLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		volumeLabel.setForeground(Color.WHITE);
		volumeLabel.setBounds(1092, 604, 38, 16);
		add(volumeLabel);
		
		
		
		
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				gainControl = (FloatControl) SoundHandler.getClip().getControl(FloatControl.Type.MASTER_GAIN);
				final IntClass currentValue = new IntClass(slider.getValue());
				double upBound = Math.log(101);
				double lowBound = Math.log(1);
				double diffBounds = upBound - lowBound;
				double volume = (Math.log(slider.getValue() + 1) * 86) / diffBounds;
				
				volumeLabel.setText(slider.getValue() + "");
				gainControl.setValue(((float)volume) - 80);
			}
		});
		add(slider);
		
		
		lblPlay = new JLabel("");
		lblPlay.setForeground(Color.WHITE);
		lblPlay.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblPlay.setBounds(200, 558, 85, 29);
		
		
		lblPause = new JLabel("");
		lblPause.setForeground(Color.WHITE);
		lblPause.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblPause.setBounds(375, 558, 73, 29);
		
		
		playing  = true;
		lblPlay.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				if(!playing) {
					SoundHandler.playMusic(currentMusicLoc);
					playing = true;
				}
			}
		});
		add(lblPlay);
		
		lblPause.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				SoundHandler.pauseMusic();
				playing = false;
			}
		});
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
	
	private static class IntClass
	{
		private int value;
		
		private IntClass(int value) {
			this.value = value;
		}
		
		
	}
}
