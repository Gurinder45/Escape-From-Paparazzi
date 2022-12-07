package util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import ui.GameFrame;

/**
 * Handles all keyboard input
 * 
 * @author Gurinder Bhogal
 */
public class InputHandler extends KeyAdapter {
	private static InputHandler instance = null;
	public Direction direction;

	private InputHandler() {
		instance = this;
	}

	public static InputHandler getInstance() {
		if (instance == null) {
			new InputHandler();
		}
		return instance;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
			direction = Direction.UP;
		}
		if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
			direction = Direction.LEFT;
		}
		if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
			direction = Direction.DOWN;
		}
		if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
			direction = Direction.RIGHT;
		}
		if (key == KeyEvent.VK_ESCAPE) {
			GameFrame.getInstance().togglePause();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_W || key == KeyEvent.VK_A || key == KeyEvent.VK_S || key == KeyEvent.VK_D
				|| key == KeyEvent.VK_UP || key == KeyEvent.VK_LEFT || key == KeyEvent.VK_DOWN
				|| key == KeyEvent.VK_RIGHT) {
			direction = Direction.NONE;
		}
	}

	/**
	 * Manually set the direction of the InputHandler
	 * 
	 * @param direction Direction for the entity to move of type Direction
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	/**
	 * Gets the direction of the InputHandler
	 * 
	 * @return Type Direction
	 */
	public Direction getDirection() {
		if (direction == null) {
			return Direction.NONE;
		}
		return direction;
	}
}
