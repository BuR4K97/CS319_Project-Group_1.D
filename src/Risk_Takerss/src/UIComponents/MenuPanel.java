package UIComponents;

import javax.swing.*;

import HelperTools.ImageHandler;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.*;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import Controller.MainApplication;

import javax.swing.GroupLayout.Alignment;

public class MenuPanel extends DynamicPanel {
	/**
	public MenuPanel() {
		this.setPreferredSize(new Dimension(1920, 1080));
		setLayout(null);
		try {
			backgroundPhoto = new ImageIcon("C:\\Users\\User\\Desktop\\Risk_Takerss\\backgroundImages\\MenuBackground.jpg").getImage();
		} catch (Exception e) {
			e.printStackTrace();
		}
		JButton btnPlayGame = new JButton("");
		btnPlayGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainApplication.initializeGame();
			}
		});
		btnPlayGame.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\Risk_Takerss\\backgroundImages\\PlayGameButton.png"));
		btnPlayGame.setBounds(819, 682, 299, 65);
		btnPlayGame.setBackground(new Color(0,0,0,0));
		add(btnPlayGame);
	}**/

	private Image backgroundPhoto;
	
	@Override
	public boolean initialize() {
		this.setPreferredSize(new Dimension(1920, 1080));
		setLayout(null);
		try {
			backgroundPhoto = new ImageIcon("C:\\Users\\User\\Desktop\\Risk_Takerss\\backgroundImages\\MenuBackground.jpg").getImage();
		} catch (Exception e) {
			e.printStackTrace();
		}
		JButton btnPlayGame = new JButton("");
		btnPlayGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainApplication.initializeGame();
			}
		});
		btnPlayGame.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\Risk_Takerss\\backgroundImages\\PlayGameButton.png"));
		btnPlayGame.setBounds(819, 682, 299, 65);
		btnPlayGame.setBackground(new Color(0,0,0,0));
		btnPlayGame.setBorderPainted(false);
		add(btnPlayGame);
		return false;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		repaint();
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		g.drawImage(backgroundPhoto, 0, 0, this);
	}
}
