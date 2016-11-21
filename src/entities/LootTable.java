package entities;

public abstract class LootTable extends Entity {
	
// Fields -------------------------------------
	
	private int id;

//Constructor ---------------------------------
	
	/**
	 * Create a object lootable
	 * @param posX
	 * 			: x position of the object
	 * 
	 * @param posY
	 * 			: y position of the object
	 * 
	 * @param id
	 * 			: id of the object
	 */
	public LootTable(int posX, int posY, int id) {
		super(posX, posY);
		this.id = id;
	}
	
	
	
	

}
