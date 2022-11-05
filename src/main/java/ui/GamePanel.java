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

	// UI
	private Hud hud;

	public GamePanel(GameFrame gFrame) {
		this.gFrame = gFrame;
		placeElements();
	}

	public void placeElements() {
		this.celebrity = new Celebrity(1080, 630, gFrame);
		this.paparazzi = new Paparazzi(700, 600, gFrame);
		this.map = new Map(gFrame);
		this.hud = new Hud(gFrame);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2d = (Graphics2D) g;
		map.draw(g2d);
		celebrity.draw(g2d);
		paparazzi.draw(g2d);

		// UI
		hud.draw(g2d);
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

	public void loadBonusRewards(int num) {
		map.loadBonusRewards(num);
	}
}
