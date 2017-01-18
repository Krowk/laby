package entities;

/**
 * Food is a Entity with gain of force and life
 * 
 *
 */
public class Food extends Lootable {

//Fields -----------------------------------------------------------------------
	
	private int lifeGain;
	
	private int forceGain;

	
//Constructor ------------------------------------------------------------------
	
	/**
	 * Create a food
	 * @param lifeGain 
	 * 			: gain of life (int)
	 * 
	 * @param onFloor 
	 * 			: on the floor (boolean)
	 * 
	 * @param forceGain 
	 * 			: gain of force (int)
	 * 
	 * @param posX 
	 * 			: x position (int)
	 * 
	 * @param posY 
	 * 			: y position (int)
	 * 
	 * @param id
	 * 			: id of the food (int)
	 */
	public Food( int posX, int posY, int widht, int lenght, int lifeGain,int forceGain, boolean onFloor, int id) {
		super(posX,posY,widht,lenght,onFloor, true, id);
		this.lifeGain = lifeGain;
		this.forceGain = forceGain;

	}

//Getters ----------------------------------------------------------------------
	
	/**
	 * Get the gain of life of the food
	 * @return
	 */
	public int getLifeGain() {
		return lifeGain;
	}

	/**
	 * Get the gain of force of the food
	 * @return
	 */
	public int getForceGain() {
		return forceGain;
	}


	public void use(){
		
	}
	
	
	

}
