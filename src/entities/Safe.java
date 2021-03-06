package entities;

import physics.Angle;

public class Safe extends Bloc{
	
	private boolean finish = false;

	public Safe(int posX, int posY, int width, int length, int angleDegree, boolean isSolid, boolean finish) {
		super(posX, posY, length, width, angleDegree, isSolid);
		this.finish = finish;
	}
	
	public Safe(int posX, int posY, int width, int length, Angle angle, boolean isSolid, boolean finish) {
		super(posX, posY, length, width, angle, isSolid);
		this.finish = finish;
	}
	
	

}
