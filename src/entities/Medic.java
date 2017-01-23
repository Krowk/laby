package entities;



public class Medic extends Character implements Updatable{
// Fields------------------------------------------------------------------------
	// transforme ça en boolean non?
	private int visite;
	
// Constructors -----------------------------------------------------------------	
	public Medic(int posX, int posY, int length, int width, int life, int force) {
		super(posX, posY, length, width, life, force);
		this.visite = 3;
	}

// Dynamic methods --------------------------------------------------------------
	
	public int havebeenvisited(){
		return visite -=1;
	}
	
// Static methods ---------------------------------------------------------------	
	
// Getters ----------------------------------------------------------------------	

	
	public int getVisite(){
		return this.visite;
	}

// Setters ----------------------------------------------------------------------
}
