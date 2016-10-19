/**
 * an Entity is anything that has a position
 */
public class Entity {
	// Fields
	private int posX;
	private int posY;
	
	// Constructors
	/**
	 * create a Entity
	 * @param posX : a x position: int
	 * @param posY : a y position: int
	 */
	public Entity(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	// dynamic methods
	
	// static methods
	
	// getters
	public int getPosX(){ return this.posX;}
	public int getPosY(){ return this.posY;}
	
	// setters

}
