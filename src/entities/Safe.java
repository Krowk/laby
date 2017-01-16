package entities;

import physics.Angle;

public class Safe extends Bloc{
	
	private boolean finish = false;

	public Safe(int posX, int posY, int length, int width, Angle angle, boolean isSolid, boolean finish) {
		super(posX, posY, length, width, angle, isSolid);
		this.finish = finish;
	}
	
	

}
