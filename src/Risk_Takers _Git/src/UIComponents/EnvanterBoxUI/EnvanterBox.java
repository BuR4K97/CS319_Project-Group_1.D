package UIComponents.EnvanterBoxUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class EnvanterBox extends JLabel{

	int x = 80;
	int y = 880;
	int borderLength = 120;// 120
	int openingAmountLeft = 0; // 0   -50
	int openingAmountRigth = borderLength / 2; // 50  100
	final int OPENING_AMOUNT_LEFT_OPEN = -borderLength/2;
	final int OPENING_AMOUNT_LEFT_CLOSE = 0;
	final int OPENING_AMOUNT_RIGHT_OPEN = borderLength;
	final int OPENING_AMOUNT_RIGHT_CLOSE = borderLength/2;
	int movingAmount = 10;
	boolean inOpening = false;
	Timer opening, closing;
	Timer movingBoxes;
	Color color = Color.WHITE;
	int unitNumber = 50;
	ArrayList<SmallBox> list = new ArrayList<SmallBox>();
	ArrayList<SmallBox> unitsInHand = new ArrayList<SmallBox>();
	int mouseX = 0, mouseY = 0;
	
	public EnvanterBox() {
		super();

		this.setBounds(x, y, borderLength, borderLength);
		this.setForeground(new Color(0, 0, 0, 0));
		this.setBackground(new Color(0, 0, 0, 0));
		//this.setFont(new Font("Calibri", Font.BOLD, 32));

		Timer t = new Timer(16, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseOnBox(mouseX, mouseY))
					open();
				else
					close();

				for(int i = 0; i < unitsInHand.size();i++) {
					unitsInHand.get(i).goTarget(mouseX + i * 20, mouseY);
				}
			}
		});
		t.start();

		for(int i = 0; i < unitNumber; i++)
			list.add(new SmallBox(x + borderLength/2, y + borderLength/2));
		movingBoxes = new Timer(16,  new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < list.size(); i++) {
					list.get(i).move(new Rectangle(x, y, borderLength, borderLength));
				}
			}
		});
		movingBoxes.start();
		opening = new Timer(16,  new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Math.abs(openingAmountLeft - OPENING_AMOUNT_LEFT_OPEN) <= movingAmount) {
					openingAmountLeft = OPENING_AMOUNT_LEFT_OPEN;
				} else if(openingAmountLeft > OPENING_AMOUNT_LEFT_OPEN) {
					openingAmountLeft -= movingAmount;
				} else if(openingAmountLeft < OPENING_AMOUNT_LEFT_OPEN) {
					openingAmountLeft += movingAmount;
				}
				if(Math.abs(openingAmountRigth - OPENING_AMOUNT_RIGHT_OPEN) <= movingAmount) {
					openingAmountRigth = OPENING_AMOUNT_RIGHT_OPEN;
				} else if(openingAmountRigth < OPENING_AMOUNT_RIGHT_OPEN) {
					openingAmountRigth += movingAmount;
				} else if(openingAmountRigth > OPENING_AMOUNT_RIGHT_OPEN) {
					openingAmountRigth -= movingAmount;
				}
			}
		});
		closing = new Timer(16,  new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Math.abs(openingAmountLeft - OPENING_AMOUNT_LEFT_CLOSE) <= movingAmount) {
					openingAmountLeft = OPENING_AMOUNT_LEFT_CLOSE;
				} else if(openingAmountLeft > OPENING_AMOUNT_LEFT_CLOSE) {
					openingAmountLeft -= movingAmount;
				} else if(openingAmountLeft < OPENING_AMOUNT_LEFT_CLOSE) {
					openingAmountLeft += movingAmount;
				}
				if(Math.abs(openingAmountRigth - OPENING_AMOUNT_RIGHT_CLOSE) <= movingAmount) {
					openingAmountRigth = OPENING_AMOUNT_RIGHT_CLOSE;
				} else if(openingAmountRigth < OPENING_AMOUNT_RIGHT_CLOSE) {
					openingAmountRigth += movingAmount;
				} else if(openingAmountRigth > OPENING_AMOUNT_RIGHT_CLOSE) {
					openingAmountRigth -= movingAmount;
				}
			}
		});
	}
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(3));
		g2d.setColor(color);
		// boxes
		for(int i = 0; i < list.size(); i++) {
			g2d.fillRect(list.get(i).x, list.get(i).y, list.get(i).length, list.get(i).length);
		}
		// semi transparent panel rear unit number
		g2d.setColor(new Color(0, 0, 0, 200));
		g2d.fillRect(x, y, borderLength, borderLength);
		g2d.setColor(color);
		// left border
		g2d.drawLine(x, y, x, y + borderLength);
		// bottom border
		g2d.drawLine(x, y + borderLength, x + borderLength, y + borderLength);
		// right border
		g2d.drawLine(x + borderLength, y + borderLength, x + borderLength, y);
		// left open close
		g2d.drawLine(x + openingAmountLeft, y, x + openingAmountLeft + borderLength/2, y);
		//right open close
		g2d.drawLine(x + openingAmountRigth, y, x + openingAmountRigth + borderLength/2, y);


		// unit number
		g2d.setColor(color);
		g2d.setFont(new Font("Calibri", Font.BOLD, borderLength/2));
		g2d.drawString("" + unitNumber, x + borderLength/4, y + 2*borderLength/3);
		
		// box in hand
		for(int i = 0; i < unitsInHand.size();i++) {
			g.fillRect(unitsInHand.get(i).x, unitsInHand.get(i).y, 15, 15);
		}

	}
	public void open() {
		closing.stop();
		opening.start();
	}
	public void close() {
		opening.stop();
		closing.start();
	}
	public void addUnit(int number) {
		unitNumber += number;
		for(int i = 0; i < number; i++)
			list.add(new SmallBox(x + borderLength/2, y + borderLength/2));
	}
	public void removeUnit(int number) {
		if((unitNumber - number) > 0) {
			unitNumber -= number;
			for(int i = 0; i < number; i++)
				list.remove(list.size()-1);
		} else {
			unitNumber = 0;
			list = new ArrayList<SmallBox>();
		}
	}
	public void removeAll() {
		unitNumber = 0;
		list = new ArrayList<SmallBox>();
	}
	public boolean mouseOnBox(int x, int y) {
		if(x > this.x && (x < this.x + borderLength) && y > this.y && (y < this.y + borderLength))
			return true;
		return false;
	}
}
