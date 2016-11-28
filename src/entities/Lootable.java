package entities;

public abstract class Lootable extends Entity {
	
// Fields -------------------------------------
	
	private int id;
	private String name;

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
	public Lootable(int posX, int posY, int id, String name) {
		super(posX, posY);
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public int getId(){
		return id;
	}
	
	public abstract void use();
	

}