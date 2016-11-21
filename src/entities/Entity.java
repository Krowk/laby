package entities;
/**
 * An Entity is anything that has a position
 */
public abstract class Entity {
	
// Fields -----------------------------------------------------
	
	protected int posX;
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
	
	
// static methods ---------------------------------------------
	
// getters ----------------------------------------------------
	
	/**
	 * Getting the position X of the Entity
	 * @return
	 */
	public int getPosX(){ return this.posX;}

	/**
	 * Getting the position Y of the Entity
	 * @return
	 */
	public int getPosY(){ return this.posY;}
	
// setters ----------------------------------------------------

}
