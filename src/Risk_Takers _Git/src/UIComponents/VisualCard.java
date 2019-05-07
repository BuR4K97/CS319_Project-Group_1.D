package UIComponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import GameAssets.DefaultRiskMode.DefaultRiskMode;
import HelperTools.PixelString;
import ModelClasses.Card;
import ModelClasses.Card.CARD_TYPES;

public abstract class VisualCard implements Serializable {
	
	private static final int width = 200;
	private static final int height = 275;
	
	public CARD_TYPES cardType;
	
	public VisualCard(CARD_TYPES cardType) {
		this.cardType = cardType;
	}
	
	public void paint(Graphics g, Coordinate drawCoordinate) {
		final int level = 2;
		/**
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(level * 2));
		g2d.setColor(Color.DARK_GRAY);
		g2d.fillRect(drawCoordinate.xCoord, drawCoordinate.yCoord, width, height);
		g.drawImage(plane, x + level*4, (int)(y + (5.0/6.0) * height) - level*4, x + width/4 + level*4, y + height - level*4, 0, 0, plane.getWidth(), plane.getHeight(), null);
		g2d.setColor(color);
		// border
		g2d.drawRect(x, y, width, height);
		for(int i = 0; i < list.size(); i++) {
			g2d.fillRect(list.get(i).xCoord, list.get(i).yCoord, 5, 5);
		}
		
		g2d.setColor(Color.WHITE);
		//g2d.drawRect(x, y, width, numberY);
		pixelString.paint(g);
		pixelUnit.paint(g);
		**/
	}
	
	protected abstract String getCardTag();
	protected abstract ArrayList<Coordinate> getCharacteristicVisualData();
	protected abstract BufferedImage getSymbolicData();
	public abstract boolean checkItsCorresponding(String checkTag);
	public abstract String getCorrespondingTag();
	public abstract void print();
	
}