package entities;
import physics.Angle;

/**
 * a Block is an entity with a length and a width
 */
public abstract class Bloc extends Entity{
	// Fields
	protected int length;
	protected int width;
	protected Angle angle;
	
	// Constructors
	/**
	 * create a block
	 * @param posX : a x position : int
	 * @param posY : a y position : int
	 * @param length : a length : int
	 * @param width : a width : int
	 * @param angle : an angle : Angle
	 * @see Entity
	 * @see Angle
	 */
	public Bloc(int posX, int posY, int length, int width, Angle angle) {
		super(posX, posY);
		this.length = length;
		this.width = width;
		this.angle	= angle;
	}
	
	/**
	 * create a block
	 * @param posX : a x position : int
	 * @param posY : a y position : int
	 * @param length : a length :int
	 * @param width : a width : int
	 * @param angleDegree : an angle : int
	 * @see Entity
	 * @see Angle
	 */
	public Bloc(int posX, int posY, int length, int width, int angleDegree) {
		super(posX, posY);
		this.length = length;
		this.width = width;
		this.angle	= new Angle(angleDegree);
	}
	
	// dynamic methods
	public void show(){
		super.show();
		System.out.println("length:\t" + this.length);
		System.out.println("width:\t" + this.width);
		System.out.println("angle:\t"+ this.angle);
	}
	
	public int getCornerX(int corner){
		int res = this.posX;
		
		if (corner == 1){ return res;}
		res += (int)((Math.cos(this.angle.getRadian()))* this.length);
		
		if (corner == 2){ return res;}
		res += (int)(Math.cos(this.angle.getRadian()+Math.PI/2)* this.width);
		
		if (corner == 3){ return res;}
		else{
			return (int)(res + Math.cos(this.angle.getRadian()+Math.PI)* this.length);
		}
	}
	
	public int getCornerY(int corner){
		int res = this.posY;
		if (corner == 1){ return res;}
		res += (int)(Math.sin(this.angle.getRadian())* this.width);
		if (corner == 2){ return res;}
		res += (int)(Math.sin(this.angle.getRadian()+Math.PI/2)* this.length);
		if (corner == 3){ return res;}
		else{
			return (int)(res + Math.sin(this.angle.getRadian()+Math.PI)* this.width);
		}
	}
	// static methods
	
	// getters
	public int getLength(){ return this.length;}
	public int getWidth(){ return this.width;}
	public Angle getAngle(){return this.angle;}
	
	// setters
}
