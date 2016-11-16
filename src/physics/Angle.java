package physics;

public class Angle {
	// Fields
	private int degree;
	private double radian;
	
	//Constructors
	public Angle(int degree){
		this.degree = degree;
		this.radian = degreeToRadian(degree);
	}
	
	public Angle(double radian){
		this.radian = radian;
		this.degree = radianToDegree(radian);
	}
	
	//Dynamic methods
	
	//Static methods
	
	public static double degreeToRadian(int degree){
		return Math.PI * degree / 180;
	}
	
	public static int radianToDegree(double radian){
		return (int)(180 * radian / Math.PI);
	}
	
	//getters
	public int getDegree(){ return this.degree;}
	public double getRadian(){ return this.radian;}
}
