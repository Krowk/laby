package entities;
import java.util.ArrayList;

import Main.Maze;
import physics.Angle;
import physics.Collision;



public class Line extends Entity{
	// Fields
	private double length;
	private Angle angle;

	// constructors
	public Line(double posX, double posY, double length, Angle angle, boolean isSolid){
		super(posX, posY, isSolid);
		this.length = length;
		this.angle = angle;
		
	}
	public Line(int posX, int posY, int length, int angle,boolean isSolid){
		super(posX, posY, isSolid);
		this.length = length;
		this.angle = new Angle(angle);	
	}
	
	// dynamic methods
	public ArrayList<Object> getNextBloc(double[] target, Bloc b){
		ArrayList<Object> res = new ArrayList<>();
		ArrayList<Bloc> blocs = Maze.blocs;
		double[] init = new double[]{b.posX, b.posY};
		for (int l = 0; l < length; l++) {
			Collision.collision(b);
			for (Bloc bloc : blocs) {
				if (bloc != b){
					double[] p = getPointFromlength(l);
					b.posX = p[0];
					b.posY = p[1];
					if (Math.sqrt(Math.pow(bloc.posX - p[0] , 2) + Math.pow(bloc.posY - p[1], 2)) < Math.sqrt(Math.pow(bloc.width, 2) + Math.pow(bloc.length, 2))){
						if (b.collision(bloc)){
							if(Collision.blocPoint(bloc, target)){
								res.add(target);
								res.add(bloc);
								return res;
							}
							res.add(p);
							res.add(bloc);
							return res;
						}
						
					}	
				}
				
			}
		}
		res.add(target);
		return res;
	}
	
	public double[] getPointFromlength(double l){
		return new double[]{(posX * Math.cos(l)),(posY * Math.sin(l))};
	}
	// static methods
	public static Line getLineBetweenPoints(double[] a, double[] b){
		double adjacent = b[1] - a[1];
		double opposite = b[0] - a[0];
		double hypotenuse = Math.sqrt(Math.pow(adjacent, 2) + Math.pow(opposite, 2));
		Angle alpha = new Angle(Math.asin(opposite / hypotenuse));
		return new Line(a[0], a[1], hypotenuse, alpha, false);
	}
	
	
	// getters
	public double getLength(){ return this.length;}
	public Angle getAngle(){return this.angle;}
	
	
}
