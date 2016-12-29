package entities;
import physics.Angle;

/**
 * a Bloc is an entity with a length and a width
 */
public abstract class Bloc extends Entity{
// Fields -----------------------------------------------------
	/**
	 * The width of the Bloc
	 */
	protected int width;
	/**
	 * The length of the Bloc
	 */
	protected int length;
	/**
	 * The angle of the Bloc
	 */
	protected Angle angle;
	
// Constructors -----------------------------------------------
	/**
	 * create a bloc
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
	 * create a bloc
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
	
// dynamic methods --------------------------------------------
	/**
	 * print in console the fields of this bloc
	 */
	public void show(){
		super.show();
		System.out.println("length:\t" + this.length);
		System.out.println("width:\t" + this.width);
		System.out.println("angle:\t"+ this.angle);
	}

	/**
	 * get the nth's corner x coordinate
	 * @param corner the corner of which you want the coordinate
	 * @return the x coordinate of the nth corner
	 */
	public double getCornerX(int corner){
		double res = this.posX;
		if (corner == 1){ return res;}
		res += ((Math.cos(this.angle.getRadian()))* this.length);
		if (corner == 2){ return res;}
		res += (Math.cos(this.angle.getRadian()+Math.PI/2)* this.width);
		if (corner == 3){ return res;}
		else{
			return (res + Math.cos(this.angle.getRadian()+Math.PI)* this.length);
		}
	}

	/**
	 * get the nth's corner y coordinate
	 * @param corner the corner of which you want the coordinate
	 * @return the x coordinate of the nth corner
	 */
	public double getCornerY(int corner){
		double res = this.posY;
		if (corner == 1){ return res;}
		res += (Math.sin(this.angle.getRadian())* this.length);
		if (corner == 2){ return res;}
		res += (Math.sin(this.angle.getRadian()+Math.PI/2)* this.width);
		if (corner == 3){ return res;}
		else{
			return (res + Math.sin(this.angle.getRadian()+Math.PI)* this.length);
		}
	}
// static methods ---------------------------------------------
	
// getters ----------------------------------------------------
	/**
	 * get the length of the bloc
	 * @return the length of the bloc
	 */
	public int getLength(){ return this.length;}
	/**
	 * get the width of the bloc
	 * @return the width of the bloc
	 */
	public int getWidth(){ return this.width;}
	/**
	 * get the Angle of the bloc
	 * @return the angle of the bloc
	 */
	public Angle getAngle(){return this.angle;}
	
// setters ----------------------------------------------------
}
