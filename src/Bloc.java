/**
 * a Block is an entity with a length and a width
 */
public abstract class Bloc extends Entity{
	// Fields
	private int length;
	private int width;
	
	// Constructors
	/**
	 * create a block
	 * @param posX : a x position : int
	 * @param posY : a y position : int
	 * @param length : a length : int
	 * @param width : a width : int
	 * @see Entity
	 */
	public Bloc(int posX, int posY, int length, int width) {
		super(posX, posY);
		this.length = length;
		this.width = width;
	}
	
	// dynamic methods
	
	// static methods
	
	// getters
	public int getLength(){ return this.length;}
	public int getWidth(){ return this.width;}
	
	// setters
}
