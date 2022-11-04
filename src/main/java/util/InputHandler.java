package util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ui.GameFrame;

public class InputHandler implements KeyListener {
	public Direction direction;
	private GameFrame gFrame;

	public InputHandler(GameFrame gFrame) {
		this.gFrame = gFrame;
	}

	public void keyTyped(KeyEvent e) {
	}

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
			gFrame.togglePause();
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_W || key == KeyEvent.VK_A || key == KeyEvent.VK_S || key == KeyEvent.VK_D
				|| key == KeyEvent.VK_UP || key == KeyEvent.VK_LEFT || key == KeyEvent.VK_DOWN
				|| key == KeyEvent.VK_RIGHT) {
			direction = Direction.NONE;
		}
	}

	public Direction getDirection() {
		if (direction == null) {
			return Direction.NONE;
		}
		return direction;

	}

}
