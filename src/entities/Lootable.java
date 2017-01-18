package entities;

public abstract class Lootable extends Bloc {
	
// Fields -------------------------------------
	
	private boolean onFloor = true;
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
	public Lootable(int posX, int posY, int widht,int lenght,boolean onFloor, boolean isSolid, int id) {
		super(posX, posY, widht, lenght, 0, isSolid);
		this.onFloor = onFloor;
		this.id = id;
	}

	
	
	public abstract void use();



	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}
	

}