package physics;

import java.util.ArrayList;

import Main.Maze;
import entities.Bloc;
import entities.Door;
import entities.Entity;
import entities.Line;
import entities.Wall;


public class Collision {
// Fields -----------------------------------------------------
	public static final double EPSILON = 0.0001;
	
// Static methods ---------------------------------------------
	/**
	 * Check if two Entities are colliding
	 * 
	 * @param a
	 *            an Entity
	 * @param b
	 *            an Entity
	 * @return true if colliding, else false
	 */
	public static boolean entityEntity(Entity a, Entity b) {
		return (a.getPosX() == b.getPosX() && a.getPosY() == b.getPosY());
	}

	/**
	 * Check if a Line and an Entity are colliding
	 * @param a the line
	 * @param b the entity
	 * @return true if they collide
	 */
	public static boolean lineEntity(Line a, Entity b) {
		int angle = a.getAngle().getDegree();
		double x, y;
		boolean exept1 = false, exept2 = false;
		if (angle == 90 || angle == 270) {
			x = b.getPosX();
			exept1 = true;
		} else {
			x =(int)((b.getPosX() - a.getPosX()) / Math.cos(a.getAngle().getRadian()));
		}

		if (angle == 0 || angle == 180) {
			y = b.getPosY();
			exept2 = true;
		} else {
			y =(int)((b.getPosY() - a.getPosY()) / Math.sin(a.getAngle().getRadian()));
		}

		if (exept1) {
			if (y >= 0 && y <= a.getLength() && x == a.getPosX())
				return true;
			else
				return false;
		}
		if (exept2) {
			if (x >= 0 && x <= a.getLength() && y == a.getPosY())
				return true;
			else
				return false;
		}
		if (x == y && x >= 0 && x <= a.getLength() && y >= 0 && y <= a.getLength()) {
			return true;
		} else
			return false;
	}

	/**
	 * Check if twoLines are colliding
	 * @param a a line
	 * @param b an other line
	 * @return true if there are colliding
	 */
	public static boolean lineLine(Line a, Line b) {
		
		
		String t1, t2 , t3, t4;
		
		
		double a1x = a.getPosX(), a1y = a.getPosY(), b1x = b.getPosX(), b1y = b.getPosY();
		int a2x =(int)(a1x + Math.cos(a.getAngle().getRadian()) * a.getLength());
		int a2y =(int)(a1y + Math.sin(a.getAngle().getRadian()) * a.getLength());
		int b2x =(int)(b1x + Math.cos(b.getAngle().getRadian()) * a.getLength());
		int b2y =(int)(b1y + Math.sin(b.getAngle().getRadian()) * a.getLength());
		
		if (b.getAngle().getDegree() != 0 && b.getAngle().getDegree() != 180){
			double b3y	= a1y;
			double n	=  (b3y - b1y) / Math.sin(b.getAngle().getRadian());
			int b3x	= (int)( b1x + Math.cos(b.getAngle().getRadian()) * n);	
			if (a1x > b3x) { t1 = "sup";}
			else if (a1x < b3x){ t1 = "inf";}
			else { t1 = "equal";}
			
			
			int b4y = a2y;
			n	= (b4y - b1y) / Math.sin(b.getAngle().getRadian());
			int b4x = (int)( b1x + Math.cos(b.getAngle().getRadian()) * n);
			if (a2x > b4x) { t2 = "sup";}
			else if (a2x < b4x){ t2 = "inf";}
			else { t2 = "equal";}
		}
		else{
			double b3x	= a1x;
			double n	=  (b3x - b1x) / Math.cos(b.getAngle().getRadian());
			int b3y	= (int)( b1y + Math.sin(b.getAngle().getRadian()) * n);	
			if (a1y > b3y) { t1 = "sup";}
			else if (a1y < b3y){ t1 = "inf";}
			else { t1 = "equal";}
			
			
			int b4x = a2x;
			n	= (b4x - b1x) / Math.cos(b.getAngle().getRadian());
			int b4y = (int)( b1y + Math.sin(b.getAngle().getRadian()) * n);
			if (a2y > b4y) { t2 = "sup";}
			else if (a2y < b4y){ t2 = "inf";}
			else { t2 = "equal";}
		}
		
		if (a.getAngle().getDegree() != 0 && a.getAngle().getDegree() != 180){
			double a3y	= b1y;
			double n	=  (a3y - a1y) / Math.sin(a.getAngle().getRadian());
			int a3x	= (int)( a1x + Math.cos(a.getAngle().getRadian()) * n);	
			if (b1x > a3x) { t3 = "sup";}
			else if (b1x < a3x){ t3= "inf";}
			else { t3 = "equal";}
			
			
			int a4y	= b2y;
			n	=  (a4y - a1y) / Math.sin(a.getAngle().getRadian());
			int a4x	= (int)( a1x + Math.cos(a.getAngle().getRadian()) * n);	
			if (b2x > a4x) { t4 = "sup";}
			else if (b2x < a4x){ t4 = "inf";}
			else { t4 = "equal";}
		}
		else{
			double a3x	= b1x;
			double n	=  (a3x - a1x) / Math.cos(a.getAngle().getRadian());
			int a3y	= (int)( a1y + Math.sin(a.getAngle().getRadian()) * n);	
			if (b1y > a3y) { t3 = "sup";}
			else if (b1y <a3y){ t3 = "inf";}
			else { t3 = "equal";}
			
			
			int a4x	= b2x;
			n	=  (a4x - a1x) / Math.cos(a.getAngle().getRadian());
			int a4y	= (int)( a1y + Math.sin(a.getAngle().getRadian()) * n);	
			if (b2y > a4y) { t4 = "sup";}
			else if (b2y < a4y){ t4 = "inf";}
			else { t4 = "equal";}
		}
		
		
		
		boolean x, y;
		if (t1 == "equal" || t2 == "equal"){
			x = true;
		}
		else if (t1 == t2){
			x = false;
		}
		else {
			x = true;
		}
		
		if (t3 == "equal" || t4 == "equal"){
			y = true;
		}
		else if (t3 == t4){
			y = false;
		}
		else {
			y = true;
		}
		
		return (x && y);
	}

	/**
	 * Check if a Bloc and an Entity are colliding
	 * @param a the Bloc
	 * @param b the Entity
	 * @return true if they are colliding
	 */
	public static boolean blocEntity(Bloc a, Entity b) {
		boolean faces[] = new boolean[8];
		faces[0] = (b.getPosX() >= Math.min(a.getCornerX(1), a.getCornerX(2))
				&& (b.getPosX() <= Math.max(a.getCornerX(1), a.getCornerX(2))));
		faces[1] = (b.getPosX() >= Math.min(a.getCornerX(2), a.getCornerX(3))
				&& (b.getPosX() <= Math.max(a.getCornerX(2), a.getCornerX(3))));
		faces[2] = (b.getPosX() >= Math.min(a.getCornerX(3), a.getCornerX(4))
				&& (b.getPosX() <= Math.max(a.getCornerX(3), a.getCornerX(4))));
		faces[3] = (b.getPosX() >= Math.min(a.getCornerX(4), a.getCornerX(1))
				&& (b.getPosX() <= Math.max(a.getCornerX(4), a.getCornerX(1))));

		faces[4] = (b.getPosY() >= Math.min(a.getCornerY(1), a.getCornerY(2))
				&& (b.getPosY() <= Math.max(a.getCornerY(1), a.getCornerY(2))));
		faces[5] = (b.getPosY() >= Math.min(a.getCornerY(2), a.getCornerY(3))
				&& (b.getPosY() <= Math.max(a.getCornerY(2), a.getCornerY(3))));
		faces[6] = (b.getPosY() >= Math.min(a.getCornerY(3), a.getCornerY(4))
				&& (b.getPosY() <= Math.max(a.getCornerY(3), a.getCornerY(4))));
		faces[7] = (b.getPosY() >= Math.min(a.getCornerY(4), a.getCornerY(1))
				&& (b.getPosY() <= Math.max(a.getCornerY(4), a.getCornerY(1))));
		int x = 0;
		for (int i = 0; i < faces.length - 1; i++) {
			if (faces[i]) {
				x++;
			}
		}
		if (x == 0) {
			return false;
		}
		if (x % 2 == 0) {
			return true;
		} else
			return false;
	}
	
	
	public static boolean collision(Bloc a){
		ArrayList<Entity> maze = Maze.activeMaze.getMazeContent();
		boolean bool = false;
		boolean res = false;
		double d = 0;
		for (Entity e : maze) {
			if (e instanceof Bloc && e != a){
				d = distance(a, e);
				Bloc b = (Bloc) e; 
				if( d <= Math.sqrt(Math.pow((b.getLength() + b.getWidth()),2)) ){
					bool = true;	
				}
				if(bool){
					if(a.collision(b)){
						if (b instanceof Wall){
							Wall w = (Wall) b;
							if( w.getdoor() != null ){
								Door door =w.getdoor();
								if (a.collision(door)){
									return true;
								}
							}
							return true;
						}
						return true;
						
					}
					else{
						bool = false;
					}
					
				}
			}
		}
		return res;
	}

	public static double distance(Entity a, Entity b){
		return Math.sqrt(Math.pow(b.getPosX() - a.getPosX(), 2)+ Math.pow(b.getPosY() - a.getPosY(),2));
	}
	
	
	/**
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param a an angle
	 * @return the coordinate of the point once the scene as been rotated
	 */
	public static double[] rotateCoordinate(double x, double y, Angle a){
		double[] res = new double[2];
		double rad = a.getRadian();
		double t1, t2;
		t1 = Math.cos(rad);
		t2 = Math.sin(rad);
		res[0] = rounding(x * t1 - y * t2);
		res[1] = rounding(x * t2 + y * t1);
		return res;
	}

	/**
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param cos the cosinus of the angle
	 * @param sin the sin if the angle
	 * @return the coordinate of the point once the scene as rotated
	 */
	public static double[] rotateCoordinate(double x, double y, double cos, double sin){
		double[] res = new double[2];
		res[0] = rounding( x * cos + y * sin);
		double a= -(x * sin) + y * cos;
		res[1] = rounding( -(x * sin) + y * cos);
		return res;
	}
	
	/**
	 * Transform a double in an int with rounding
	 * exemple: 2.6 -> 3; 2.4 -> 2
	 * @param d the double
	 * @return an int corresponding to the double
	 */
	public static int toInt(double d){
		double cent =d - (int)d;
		if (cent >0.50){ return (int)(d+1);}
		return ((int)d);
	}
	
	public static double rounding(double d){
		if (d > 0){
			if (d +EPSILON > (int)(d+1)){
				return (int)(d+1);
			}
			if (d - EPSILON < (int)(d)){
				return (int)(d);
			}			
		}
		else if (d <0){
			if (d +EPSILON > (int)(d)){
				return (int)(d);
			}
			if (d - EPSILON < (int)(d-1)){
				return (int)(d-1);
			}
		}
		
		return d;
	}
	
}
