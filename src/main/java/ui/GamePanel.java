package ui;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import map.Map;
import moveable_entity.Celebrity;
import moveable_entity.Paparazzi;

/**
 * Handles the drawing of the in game screen
 * 
 * @author Gurinder Bhogal
 * 
 */
public class GamePanel extends JPanel {
	private Celebrity celebrity;
	private Paparazzi[] paparazzi;
	private Map map;
	private GameFrame gFrame;
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
	 * Get elements ready to be drawn
	 */
	public void placeElements() {
		this.celebrity = new Celebrity(1080, 630, gFrame);
		paparazzi[0] = new Paparazzi(288, 416, gFrame);
		paparazzi[1] = new Paparazzi(576, 576, gFrame);
		this.map = new Map(gFrame);
		this.hud = new Hud(gFrame);
	}

	/**
	 * Draws everything on the game panel display
	 * 
	 * @param g Graphics object
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
		// HUD
		hud.draw(g2d);
		g2d.dispose();
	}

	/**
	 * calls celebrity and paparazzi update methods
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
	 * Gets the the games map
	 * 
	 * @return a 2 dimensional array of integers representing cell types
	 */
	public int[][] getMapArray() {
		return map.getMapArray();
	}

	/**
	 * checks if the cell type is collidable
	 * 
	 * @param type integer representing cell type
	 * @return true if the integer represents a collidable cell type, false
	 *         otherwise
	 */
	public boolean isCollidable(int type) {
		return map.checkColidable(type);
	}

	/**
	 * for the given the given number it loads corresponding reward
	 * 
	 * @param num corresponds to the reward number to load
	 */
	public void loadBonusRewards(int num) {
		map.loadBonusRewards(num);
	}

	/**
	 * Returns all the paparazzi in the game
	 * 
	 * @return an array of type paparazzi
	 */
	public Paparazzi[] getPaparazzis() {
		return paparazzi;
	}
}
