package entities;

/**
 * key is a Entity with a id (to open doors)
 * can be on the floor or in the inventory
 * 
 *
 */
public class Key extends Lootable {
	
//Fields -----------------------------------------------------
	
	private boolean onFloor = true;
	
	
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
	public Key(int id, boolean onFloor,int posX, int posY,String name) {
		super(posX,posY,id,name, false);
		this.onFloor = onFloor;
	}


	public void use() {
		
		
	}
	
	
}
