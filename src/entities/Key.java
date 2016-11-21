package entities;

public class Key extends Entity {
	private int id;
	private boolean onFloor = true;
	
	
	
	public Key(int id, boolean onFloor,int posX, int posY) {
		super(posX,posY);
		this.id = id;
		this.onFloor = onFloor;
	}
	
	
}
