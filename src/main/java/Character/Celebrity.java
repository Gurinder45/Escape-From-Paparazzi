package Character;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ui.GameFrame;
import util.Direction;
import util.InputHandler;

public class Celebrity extends Character {
	// celebrity should be singleton

	GameFrame gPanel;
	InputHandler inpHandler;

	public Celebrity(int x, int y, GameFrame gP, InputHandler inpH) {
		super(x, y);
		this.gPanel = gP;
		this.inpHandler = inpH;
		this.speed = 4;
	}

	public void update() {
		if (inpHandler.getDirection() == Direction.UP) {
			position.addY(speed);
			System.out.println(position.getX() + "," + position.getY());
		}
		if (inpHandler.getDirection() == Direction.DOWN) {
			position.subtractY(speed);
			System.out.println(position.getX() + "," + position.getY());
		}
		if (inpHandler.getDirection() == Direction.LEFT) {
			position.subtractX(speed);
			System.out.println(position.getX() + "," + position.getY());
		}
		if (inpHandler.getDirection() == Direction.RIGHT) {
			position.addX(speed);
			System.out.println(position.getX() + "," + position.getY());
		}
	}

	public void draw(Graphics graphics) {
		
	}

}
