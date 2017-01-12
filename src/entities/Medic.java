package entities;

import java.util.ArrayList;

public class Medic extends Character implements Updatable{
// Fields------------------------------------------------------------------------
	
// Constructors -----------------------------------------------------------------	
	public Medic(int posX, int posY, int length, int width, int life, int force, int nombreFoods, int nombreFoodsMax) {
		super(posX, posY, length, width, life, force);
	}

// Dynamic methods --------------------------------------------------------------
	
// Static methods ---------------------------------------------------------------	
	
// Getters ----------------------------------------------------------------------	
	public int getposX(){
		return this.posX;
	}
	
	public int getposY(){
		return this.posY;
	}

// Setters ----------------------------------------------------------------------
}
