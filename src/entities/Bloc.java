package entities;
import java.util.ArrayList;

import Main.Maze;
import physics.Angle;
import physics.Collision;

/**
 * a Bloc is an entity with a length and a width
 */
public abstract class Bloc extends Entity{
// Fields -----------------------------------------------------
	// static fields
	// dynamic fields
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
	protected int longest;
	
	
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
	public Bloc(int posX, int posY, int length, int width, Angle angle, boolean isSolid) {
		super(posX, posY, isSolid);
		this.length = length;
		this.width = width;
		this.angle	= angle;
		if (width >= length)  longest = width;
		else longest = length;
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
	public Bloc(int posX, int posY, int length, int width, int angleDegree, boolean isSolid) {
		super(posX, posY, isSolid);
		this.length = length;
		this.width = width;
		this.angle	= new Angle(angleDegree);
		if (width >= length)  longest = width;
		else longest = length;
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
		res += Collision.rounding((Math.cos(this.angle.getRadian()))* this.length);
		if (corner == 2){ return res;}
		res += Collision.rounding(Math.cos(this.angle.getRadian()+Math.PI/2)* this.width);
		if (corner == 3){ return res;}
		else{
			return Collision.rounding(res + Math.cos(this.angle.getRadian()+Math.PI)* this.length);
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
		res += Collision.rounding(Math.sin(this.angle.getRadian())* this.length);
		if (corner == 2){ return res;}
		res += Collision.rounding(Math.sin(this.angle.getRadian()+Math.PI/2)* this.width);
		if (corner == 3){ return res;}
		else{
			return Collision.rounding(res + Math.sin(this.angle.getRadian()+Math.PI)* this.length);
		}
	}

	public double[] getCollisionArea(){
		double[] res = new double[4];
		int x;
		if (width >= length)  x = width;
		else x = length;
		
		
		res[0] = posX - x;
		res[1] = posX + x;
		res[2] = posY - x;
		res[3] = posY + x;
		return res;
	}
	

	
	public boolean collision(Bloc b){
		double cos = Math.cos(angle.getRadian());
		double sin = Math.sin(angle.getRadian());
		
		double[] a1 = Collision.rotateCoordinate(getCornerX(1), getCornerY(1), cos, sin);
		double[] a2 = Collision.rotateCoordinate(getCornerX(2), getCornerY(2), cos, sin);
		double[] a4 = Collision.rotateCoordinate(getCornerX(4), getCornerY(4), cos, sin);
		
		double[][] bn = new double[4][2];
		bn[0] = Collision.rotateCoordinate(b.getCornerX(1), b.getCornerY(1), cos, sin);
		bn[1] = Collision.rotateCoordinate(b.getCornerX(2), b.getCornerY(2), cos, sin);
		bn[2] = Collision.rotateCoordinate(b.getCornerX(3), b.getCornerY(3), cos, sin);
		bn[3] = Collision.rotateCoordinate(b.getCornerX(4), b.getCornerY(4), cos, sin);
		
		boolean contact = false;
		int i = 0;
		while (!contact && i<4) {
			if ( a1[0] < bn[i][0] && bn[i][0] < a2[0] && a1[1] < bn[i][1] && bn[i][1] < a4[1]){
				contact = true;
			}
			i++;
		}
		
		cos = Math.cos(b.getAngle().getRadian());
		sin = Math.sin(b.getAngle().getRadian());
		
		double[] b1 = Collision.rotateCoordinate(b.getCornerX(1), b.getCornerY(1), cos, sin);
		double[] b2 = Collision.rotateCoordinate(b.getCornerX(2), b.getCornerY(2), cos, sin);
		double[] b3 = Collision.rotateCoordinate(b.getCornerX(4), b.getCornerY(4), cos, sin);
		
		double[][] an = new double[4][2];
		an[0] = Collision.rotateCoordinate(getCornerX(1), getCornerY(1), cos, sin);
		an[1] = Collision.rotateCoordinate(getCornerX(2), getCornerY(2), cos, sin);
		an[2] = Collision.rotateCoordinate(getCornerX(3), getCornerY(3), cos, sin);
		an[3] = Collision.rotateCoordinate(getCornerX(4), getCornerY(4), cos, sin);
		
		i = 0;
		while (!contact && i<4) {
			if ( b1[0] < an[i][0] && an[i][0] < b2[0] && b1[1] < an[i][1] && an[i][1] < b3[1]){
				contact = true;
			}
			i++;
		}
		return contact;
	}

	
	public void update(){
		super.update();
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
	
	public int getLongest(){ return this.longest;}
// setters ----------------------------------------------------
}
