package entities;

public abstract class Lootable extends Entity {
	
// Fields -------------------------------------
	
	private int id;
	private String name;
	private int lenght;
	private int widht;

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
	public Lootable(int posX, int posY, int id, String name, boolean isSolid, int lenght, int widht) {
		super(posX, posY, isSolid);
		this.id = id;
		this.name = name;
		this.lenght = lenght;
		this.widht = widht;
	}

	public String getName() {
		return name;
	}
	
	public int getId(){
		return id;
	}
	
	public abstract void use();
	

}