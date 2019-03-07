import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Panel extends JPanel{
	// territory visuals
	TerritoryVisual a1 = new TerritoryVisual(100, 100, 200, 200);
	TerritoryVisual a2 = new TerritoryVisual(100, 300, 200, 200);
	TerritoryVisual a3 = new TerritoryVisual(100, 500, 200, 200);
	TerritoryVisual a4 = new TerritoryVisual(100, 700, 200, 200);

	TerritoryVisual b1 = new TerritoryVisual(300, 100, 200, 200);
	TerritoryVisual b2 = new TerritoryVisual(300, 300, 200, 200);
	TerritoryVisual b3 = new TerritoryVisual(300, 500, 200, 200);
	TerritoryVisual b4 = new TerritoryVisual(300, 700, 200, 200);

	TerritoryVisual c1 = new TerritoryVisual(500, 100, 200, 200);
	TerritoryVisual c2 = new TerritoryVisual(500, 300, 200, 200);
	TerritoryVisual c3 = new TerritoryVisual(500, 500, 200, 200);
	TerritoryVisual c4 = new TerritoryVisual(500, 700, 200, 200);

	TerritoryVisual d1 = new TerritoryVisual(700, 100, 200, 200);
	TerritoryVisual d2 = new TerritoryVisual(700, 300, 200, 200);
	TerritoryVisual d3 = new TerritoryVisual(700, 500, 200, 200);
	TerritoryVisual d4 = new TerritoryVisual(700, 700, 200, 200);

	TerritoryVisual s1 = new TerritoryVisual(1000, 100, 200, 200);
	TerritoryVisual s2 = new TerritoryVisual(1250, 100, 200, 200);
	
	// territories array
	ArrayList<TerritoryVisual> territoryVisuals = new ArrayList<TerritoryVisual>();
	// players
	Player p1 = new Player("p1", Color.CYAN);
	Player p2 = new Player("p2", Color.RED);
	// turn
	Turn turn = new Turn();
	// listener
	MouseListener mouseListener;
	MouseMotionListener mouseMotionListener;
	MouseWheelListener mouseWheelListener;
	private int mouseX = 0;
	private int mouseY = 0;
	// boolean
	boolean thereIsEmptyLocation = true;
	boolean inCombat = false;
	boolean thereIsSelectedTerritories = false;
	// timer
	Timer timer;
	public Panel() {
		setBackground(Color.LIGHT_GRAY);
		setBounds(100, 100, 1500, 1000);
		addTerritoriesToArrayList();
		//p1.captureTerritory(a1.getTerritory());
		//	p2.captureTerritory(d4.getTerritory());
		mouseListener();
		turn.addPlayer(p1);
		turn.addPlayer(p2);
		
		timer = new Timer(16, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		timer.start();
		
	}
	public void update() {
		// starting
		if(thereIsEmptyLocation) {
			for(TerritoryVisual current : territoryVisuals) {
				if(current.getTerritory().getPlayer() == null) {
					thereIsEmptyLocation = true;
					break;
				}
				thereIsEmptyLocation = false;
			}
		} else {
			// in combat
			if(inCombat) {
					// s1 s2
				s1.getTerritory().getPlayer().attackTerritory(s1.getTerritory(), s2.getTerritory());
				inCombat = false;
			}
			// after combat
			if (!inCombat && thereIsSelectedTerritories) {
				for(TerritoryVisual cur : territoryVisuals) {
					if(cur.getSelected() == true && turn.getWhoseTurn() == cur.getTerritory().getPlayer()) {
						cur.setTerritory(s1.getTerritory());
						cur.setSelected(false);
					} else if(cur.getSelected() == true && turn.getWhoseTurn() != cur.getTerritory().getPlayer()) {
						cur.setTerritory(s2.getTerritory());
						cur.setSelected(false);
					}
					thereIsSelectedTerritories = false;
				}
				s1.setTerritory(new Territory());
				s2.setTerritory(new Territory());
			}
		}

		repaint();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(TerritoryVisual current : territoryVisuals) {
			current.draw(g);
		}
		s1.draw(g);
		s2.draw(g);
		g.setColor(turn.getWhoseTurn().getColor());
		g.drawString("TURN: ", 400, 50);
	}

	public void mouseListener() {
		mouseListener = new MouseListener() {
			public void mouseReleased(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {
				mouseX = arg0.getX();
				mouseY = arg0.getY();
				// capture territory when beginning of the game
				if(thereIsEmptyLocation) {
					for(TerritoryVisual cur : territoryVisuals) {
						if(cur.getRectangle().contains(mouseX, mouseY) && cur.getTerritory().getPlayer() == null) {
							turn.getWhoseTurn().captureTerritory(cur.getTerritory());
							cur.getTerritory().getPlayer().addUnitsToTerritory(cur.getTerritory(), 4);
							turn.nextTurn();
						}
					}
				}
				// select location of turn player with attack
				if(!thereIsEmptyLocation && !inCombat) {
					for(TerritoryVisual cur : territoryVisuals) {
						if(cur.getRectangle().contains(mouseX, mouseY) && cur.getTerritory().getPlayer() == turn.getWhoseTurn() && cur.getTerritory().getUnitNumber() > 1) {
							if(s1.getTerritory().getPlayer() == null) {
								cur.setSelected(true);
								s1.setTerritory(cur.getTerritory());
							}
						} else if(cur.getRectangle().contains(mouseX, mouseY) && cur.getTerritory().getPlayer() != turn.getWhoseTurn()) {
							if(s1.getTerritory().getPlayer() != null) {
								cur.setSelected(true);
								s2.setTerritory(cur.getTerritory());
								inCombat = true;
								thereIsSelectedTerritories = true;
							}
						}
					}
				}
				// after combat


			}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {
			}
			public void mouseClicked(MouseEvent arg0) {}
		};
		mouseMotionListener = new MouseMotionListener() {
			public void mouseMoved(MouseEvent arg0) {
				mouseX = arg0.getX();
				mouseY = arg0.getY();
				for(TerritoryVisual cur : territoryVisuals) {
					if(cur.getRectangle().contains(mouseX, mouseY)) {
						cur.setMouseEntered(true);
					} else {
						cur.setMouseEntered(false);
					}
				}
			}
			public void mouseDragged(MouseEvent arg0) {
			}
		};
		mouseWheelListener = new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {

			}
		};
		this.addMouseListener(mouseListener);
		this.addMouseMotionListener(mouseMotionListener);
		this.addMouseWheelListener(mouseWheelListener);

	}

	public void addTerritoriesToArrayList() {
		territoryVisuals.add(a1);
		territoryVisuals.add(a2);
		territoryVisuals.add(a3);
		territoryVisuals.add(a4);
		territoryVisuals.add(b1);
		territoryVisuals.add(b2);
		territoryVisuals.add(b3);
		territoryVisuals.add(b4);
		territoryVisuals.add(c1);
		territoryVisuals.add(c2);
		territoryVisuals.add(c3);
		territoryVisuals.add(c4);
		territoryVisuals.add(d1);
		territoryVisuals.add(d2);
		territoryVisuals.add(d3);
		territoryVisuals.add(d4);
	}

}
