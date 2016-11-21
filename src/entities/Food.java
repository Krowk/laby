package entities;

public class Food extends Entity {
	private int lifeGain;
	private boolean onFloor = true;
	private int forceGain;

	
	
	public Food(int lifeGain, boolean onFloor, int forceGain,int posX, int posY) {
		super(posX,posY);
		this.lifeGain = lifeGain;
		this.onFloor = onFloor;
		this.forceGain = forceGain;

	}


	public int getLifeGain() {
		return lifeGain;
	}


	public int getForceGain() {
		return forceGain;
	}


	
	
	
	

}
