package entities;


/**
 * An Entity is anything that has a position
 */
public abstract class Entity {
	
// Fields -----------------------------------------------------
	/**
	 * The x coordinate of the Entity
	 */
	protected int posX;
	/**
	 * The y coordinate of the Entity
	 */
	protected int posY;
	
// Constructors -----------------------------------------------
	/**
	 * create a Entity
	 * @param posX : a x position: int
	 * @param posY : a y position: int
	 */
	public Entity(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
// dynamic methods --------------------------------------------
	/**
	 * Show the position of the Entity
	 */
	public void show(){
		System.out.println("posX:\t"+ posX);
		System.out.println("posY:\t"+ posY);
	}
	
	/**
	 * change the x coordinate of the Entity
	 * @param deplacement
	 */
	public void moveX(int deplacement){
		this.posX += deplacement;
	}
	
	/**
	 * change the y coordinate of the Entity
	 * @param deplacement 
	 */
	public void moveY(int deplacement){
		this.posY += deplacement;
	}
	
// static methods ---------------------------------------------
	
// getters ----------------------------------------------------
	/**
	 * Get the x coordinate of the Entity
	 * @return
	 */
	public int getPosX(){ return this.posX;}

	/**
	 * Get the y coordinate of the Entity
	 * @return
	 */
	public int getPosY(){ return this.posY;}
	
// setters ----------------------------------------------------
}
