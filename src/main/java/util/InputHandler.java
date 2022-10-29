package util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
	public Direction direction;

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_W) {
			direction = Direction.UP;
			System.out.println(direction);

		}
		if (key == KeyEvent.VK_A) {
			direction = Direction.LEFT;
			System.out.println(direction);
		}
		if (key == KeyEvent.VK_S) {
			direction = Direction.DOWN;
			System.out.println(direction);
		}
		if (key == KeyEvent.VK_D) {
			direction = Direction.RIGHT;
			System.out.println(direction);
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_W || key == KeyEvent.VK_A || key == KeyEvent.VK_S || key == KeyEvent.VK_D) {
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
