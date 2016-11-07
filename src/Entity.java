/**
 * an Entity is anything that has a position
 */
public abstract class Entity {
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
	
	public void show(){
		System.out.println(posX);
		System.out.println(posY);
	}
	
	
	// static methods
	
	// getters
	public int getPosX(){ return this.posX;}
	public int getPosY(){ return this.posY;}
	
	// setters

}
