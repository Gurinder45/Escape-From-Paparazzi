package Character;

import java.awt.Color;
import java.awt.Graphics;

import ui.GamePanel;
import util.Direction;
import util.InputHandler;

public class Celebrity extends Character {
	// celebrity should be singleton

	GamePanel gPanel;
	InputHandler inpHandler;

	public Celebrity(int x, int y, GamePanel gP, InputHandler inpH) {
		super(x, y);
		this.gPanel = gP;
		this.inpHandler = inpH;
		this.speed = 4;
	}

	public void update() {
		if (inpHandler.getDirection() == Direction.UP) {
			position.subtractY(speed);
			System.out.println(position.getX() + "," + position.getY());
		}
		if (inpHandler.getDirection() == Direction.DOWN) {
			position.addY(speed);
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

	public void draw(Graphics g2d) {
		g2d.setColor(Color.black);
		g2d.fillRect(this.getPositionX(),this.getPositionY(),48,48);
		
		
	}

}
