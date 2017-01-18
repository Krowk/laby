package entities;

/**
 * key is a Entity with a id (to open doors)
 * can be on the floor or in the inventory
 * 
 *
 */
public class Key extends Lootable {
	
//Fields -----------------------------------------------------
	
	
	
	
// Constructor------------------------------------------------
	
	/**
	 * Create a key
	 * @param id 
	 * 			: a id is the number of the key (int)
	 * 
	 * @param onFloor
	 * 			: on the floor (boolean)
	 * 
	 * @param posX
	 * 			: x position (int)
	 * 
	 * @param posY
	 * 			: y position (int)
	 */
	public Key(int posX, int posY, int widht , int lenght, boolean onFloor,int id) {
		super(posX, posY, widht, lenght, onFloor, true, id);
		
	}


	public void use() {
		
		
	}
	
	
}
