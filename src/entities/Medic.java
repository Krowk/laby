package entities;

import java.util.ArrayList;

public class Medic extends Character implements Updatable{
// Fields------------------------------------------------------------------------
	// transforme �a en boolean non?
	private int visite;
	
// Constructors -----------------------------------------------------------------	
	public Medic(int posX, int posY, int length, int width, int life, int force) {
		super(posX, posY, length, width, life, force);
		this.visite = 1;
	}

// Dynamic methods --------------------------------------------------------------
	
	public int havebeenvisited(){
		return visite = 0;
	}
	
// Static methods ---------------------------------------------------------------	
	
// Getters ----------------------------------------------------------------------	
	public int getposX(){
		return this.posX;
	}
	
	public int getposY(){
		return this.posY;
	}
	
	public int getVisite(){
		return this.visite;
	}

// Setters ----------------------------------------------------------------------
}
