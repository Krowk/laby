package entities;

public class SecretDoor extends Door{
	private int tpPosX;
	private int tpPosY;
	
	
	
	
	public SecretDoor(Wall wall, int position, int length, int tpPosX, int tpPosY) {
		super(wall, position, length, true);
		this.tpPosX = tpPosX;
		this.tpPosY = tpPosY;
		
	}

	public static SecretDoor createDoor(int wallid, int position, int length, int tpPosX, int tpPosY){
		Wall wall = Wall.getWallFromId(wallid);
		return new SecretDoor(wall, position, length, tpPosX, tpPosY);
	}


	public int getTpPosX() {
		return tpPosX;
	}




	public int getTpPosY() {
		return tpPosY;
	}
	
	
	
	
}
