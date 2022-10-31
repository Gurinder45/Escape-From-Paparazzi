package moveable_entity;

import entity.Entity;
import game.CollisionFinder;
import ui.GameFrame;
import util.Direction;
import util.Point;

public abstract class MoveableEntity extends Entity {
	protected Direction direction;
	protected int speed;
	protected boolean collided;
	protected GameFrame gFrame;
	CollisionFinder collisionFinder;
	
	
	
	public MoveableEntity(int x, int y, GameFrame gameFrame) {
		position = new Point(x,y);
		this.gFrame = gameFrame;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setCollided(boolean bool) {
		collided = bool;
	}
	
	

	

	
	
	
	
}
