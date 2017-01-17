package entities;

public class SecretDoor extends Door{
	private int tpPosX;
	private int tpPosY;
	
	
	
	
	public SecretDoor(Wall wall, int position, int length, boolean open, int tpPosX, int tpPosY) {
		super(wall, position, length, open);
		this.tpPosX = tpPosX;
		this.tpPosY = tpPosY;
	}




	public int getTpPosX() {
		return tpPosX;
	}




	public int getTpPosY() {
		return tpPosY;
	}
	
	
	
	
}
