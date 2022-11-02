package ui;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import map.Map;
import moveable_entity.Celebrity;
import moveable_entity.Paparazzi;
import util.InputHandler;

public class GamePanel extends JPanel {
	private Celebrity celebrity;
	private Paparazzi paparazzi;
	private Map map;
	private GameFrame gFrame;

	public GamePanel(GameFrame gFrame) {
		this.gFrame = gFrame;
		this.celebrity = new Celebrity(96, 96, gFrame);
		this.paparazzi = new Paparazzi(320, 416, gFrame);
		this.map = new Map(gFrame);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2d = (Graphics2D) g;
		map.draw(g2d);
		celebrity.draw(g2d);
		paparazzi.draw(g2d);
		g2d.dispose();
	}

	public void update() {
		if (!gFrame.isPaused()) {
			celebrity.update();
			paparazzi.update(celebrity.getPositionX(), celebrity.getPositionY());
		}

	}

	public int[][] getMapArray() {
		return map.getMapArray();
	}

	public boolean isCollidable(int type) {
		return map.checkColidable(type);
	}
}
