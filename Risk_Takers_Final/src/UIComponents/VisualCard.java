package UIComponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
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
	
	public static final int width = 160;
	public static final int height = 220;
	public static enum COLOR_MATCHES {
		YELLOW(CARD_TYPES.EXTREME_UNIT), VIOLET(CARD_TYPES.HARD_UNIT), BLUE(CARD_TYPES.MODERATE_UNIT)
				, GREEN(CARD_TYPES.EASY_UNIT);
		
		private Color color;
		private CARD_TYPES cardType;
		
		private COLOR_MATCHES(CARD_TYPES cardType) {
			this.cardType = cardType;
			if(cardType == CARD_TYPES.EASY_UNIT)
				color = Color.GREEN;
			else if(cardType == CARD_TYPES.MODERATE_UNIT)
				color = Color.BLUE;
			else if(cardType == CARD_TYPES.HARD_UNIT)
				color = new Color(148, 0, 211);
			else 
				color = Color.YELLOW;
		}
	}
	
	public CARD_TYPES cardType;
	private COLOR_MATCHES drawColor;
	
	public VisualCard(CARD_TYPES cardType) {
		this.cardType = cardType;
		if(cardType == CARD_TYPES.EASY_UNIT)
			drawColor = COLOR_MATCHES.GREEN;
		else if(cardType == CARD_TYPES.MODERATE_UNIT)
			drawColor = COLOR_MATCHES.BLUE;
		else if(cardType == CARD_TYPES.HARD_UNIT)
			drawColor = COLOR_MATCHES.VIOLET;
		else 
			drawColor = COLOR_MATCHES.YELLOW;
	}
	
	public void paint(Graphics painter, Coordinate drawCoordinate) {
		final int level = 2;
		
		Graphics2D g2d = (Graphics2D)painter;
		g2d.setStroke(new BasicStroke(level * 2));
		g2d.setColor(Color.DARK_GRAY);
		g2d.fillRect(drawCoordinate.xCoord, drawCoordinate.yCoord, width, height);
		g2d.setColor(drawColor.color);
		g2d.drawRect(drawCoordinate.xCoord, drawCoordinate.yCoord, width, height);
		
		g2d.setColor(Color.LIGHT_GRAY);
		painter.setFont(new Font("pixel", Font.BOLD, 20));
		String cardTag = getCardTag().substring(0);
		int underscroreIndex = cardTag.indexOf("_");
		if(underscroreIndex == -1)
			painter.drawString(cardTag, drawCoordinate.xCoord + level*4, (int)(drawCoordinate.yCoord + (1.0/10.0) * height));
		else {
			String upperLine = cardTag.substring(0, underscroreIndex);
			painter.drawString(upperLine, drawCoordinate.xCoord + level*4, (int)(drawCoordinate.yCoord + (1.0/10.0) * height));
			
			String downLine = cardTag.substring(underscroreIndex + 1);
			underscroreIndex = downLine.indexOf("_");
			if(underscroreIndex == -1)
				painter.drawString(downLine, drawCoordinate.xCoord + level*4, (int)(drawCoordinate.yCoord + (2.0/10.0) * height));
			else {
				upperLine = downLine.substring(0, underscroreIndex);
				painter.drawString(upperLine, drawCoordinate.xCoord + level*4, (int)(drawCoordinate.yCoord + (2.0/10.0) * height));
				downLine = downLine.substring(underscroreIndex + 1);
				painter.drawString(downLine, drawCoordinate.xCoord + level*4, (int)(drawCoordinate.yCoord + (3.0/10.0) * height));
			}
		}
		
		ArrayList<Coordinate> characteristicData = getCharacteristicVisualData();
		for(Coordinate coord : characteristicData)
			painter.fillRect((int)(drawCoordinate.xCoord + (1.0/5.0)*width +level*4) + coord.xCoord
					, (int)(drawCoordinate.yCoord + (2.0/6.0) * height) + coord.yCoord, VisualTerritory.DEFAULT_DRAW_SIZE
					, VisualTerritory.DEFAULT_DRAW_SIZE);
		
		painter.setFont(new Font("pixel", Font.BOLD, 40));
		painter.drawString(Integer.toString(CARD_TYPES.getUnitBuff(cardType))
				, drawCoordinate.xCoord + 7 * width / 8 - level*4, drawCoordinate.yCoord + height - level*4);
		
		painter.drawImage(getSymbolicData(), drawCoordinate.xCoord + level*4, (int)(drawCoordinate.yCoord + (5.0/6.0) * height) - level*4
				, drawCoordinate.xCoord + width/4 + level*4, drawCoordinate.yCoord + height - level*4, 0, 0
				, getSymbolicData().getWidth(), getSymbolicData().getHeight(), null);
	}
	
	protected abstract String getCardTag();
	protected abstract ArrayList<Coordinate> getCharacteristicVisualData();
	protected abstract BufferedImage getSymbolicData();
	public abstract VisualCard copy();
	public abstract boolean checkItsCorresponding(String checkTag);
	public abstract String getCorrespondingTag();
	public abstract void print();
	
}