package moveable_entity;

import entity.Entity;
import util.Direction;
import util.Point;

public abstract class MoveableEntity extends Entity {
	protected Direction direction;
	protected int speed;
	protected boolean collided;
	
	
	
	public MoveableEntity(int x, int y) {
		position = new Point(x,y);
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
