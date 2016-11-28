package physics;

import java.sql.Time;
import entities.Bloc;
import entities.Entity;
import entities.Line;

public class Collision {

	/**
	 * Check if two Entities are colliding
	 * 
	 * @param a
	 *            an Entity
	 * @param b
	 *            an Entity
	 * @return true if colliding, else false
	 */
	public static boolean EntityEntity(Entity a, Entity b) {
		return (a.getPosX() == b.getPosX() && a.getPosY() == b.getPosY());
	}

	public static boolean LineEntity(Line a, Entity b) {
		int angle = a.getAngle().getDegree();
		int x, y;
		boolean exept1 = false, exept2 = false;
		if (angle == 90 || angle == 270) {
			x = b.getPosX();
			exept1 = true;
		} else {
			x = (int) ((b.getPosX() - a.getPosX()) / Math.cos(a.getAngle().getRadian()));
		}

		if (angle == 0 || angle == 180) {
			y = b.getPosY();
			exept2 = true;
		} else {
			y = (int) ((b.getPosY() - a.getPosY()) / Math.sin(a.getAngle().getRadian()));
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

	public static void LineLine(Line a, Line b) {
		
		
		String t1, t2 , t3, t4;
		
		
		int a1x = a.getPosX(), a1y = a.getPosY(), b1x = b.getPosX(), b1y = b.getPosY();
		int a2x =(int)(a1x + Math.cos(a.getAngle().getRadian()) * a.getLength());
		int a2y =(int)(a1y + Math.sin(a.getAngle().getRadian()) * a.getLength());
		int b2x =(int)(b1x + Math.cos(b.getAngle().getRadian()) * a.getLength());
		int b2y =(int)(b1y + Math.sin(b.getAngle().getRadian()) * a.getLength());
		
		if (b.getAngle().getDegree() != 0 && b.getAngle().getDegree() != 180){
			int b3y	= a1y;
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
			int b3x	= a1x;
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
			int a3y	= b1y;
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
			int a3x	= b1x;
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
		
		//System.out.println(x);
		//System.out.println(y);
		//System.out.println(x && y);
		
	}

	public static boolean BlocEntity(Bloc a, Entity b) {
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
}
