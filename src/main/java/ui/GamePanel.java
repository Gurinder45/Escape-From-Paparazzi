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
	private Paparazzi[] paparazzi;
	private Map map;
	private GameFrame gFrame;

	// UI
	private Hud hud;

	/**
	 * 
	 * @param gFrame
	 */
	public GamePanel(GameFrame gFrame) {
		this.gFrame = gFrame;
		this.paparazzi = new Paparazzi[2];
		placeElements();
	}

	/**
	 * 
	 */
	public void placeElements() {
		this.celebrity = new Celebrity(1080, 630, gFrame);
		paparazzi[0] = new Paparazzi(288, 416, gFrame);
		paparazzi[1] = new Paparazzi(576, 576, gFrame);
		this.map = new Map(gFrame);
		this.hud = new Hud(gFrame);
	}

	/**
	 * this method draws neccesasary things such as map and celebrity om JPanel
	 *   @param g the Graphics class object 
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2d = (Graphics2D) g;
		map.draw(g2d);
		celebrity.draw(g2d);
		for (int i = 0; i < paparazzi.length; i++) {
			paparazzi[i].draw(g2d);

		}

		// UI
		hud.draw(g2d);
		g2d.dispose();
	}

	/**
	 * 
	 */
	public void update() {
		if (!gFrame.isPaused()) {
			celebrity.update();
			for (int i = 0; i < paparazzi.length; i++) {
				paparazzi[i].update(celebrity.getPositionX(), celebrity.getPositionY());
			}
		}

	}

	/**
	 * 
	 * @return
	 */
	public int[][] getMapArray() {
		return map.getMapArray();
	}

	/**
	 * 
	 * @return
	 */
	public boolean isCollidable(int type) {
		return map.checkColidable(type);
	}

	/**
	 * 
	 * @return
	 */
	public void loadBonusRewards(int num) {
		map.loadBonusRewards(num);
	}

	/**
	 * 
	 * @return
	 */
	public Paparazzi[] getPaparazzis() {
		return paparazzi;
	}
}
