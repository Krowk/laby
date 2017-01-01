package entities;
import physics.Angle;


public abstract class Line extends Entity{
	// Fields
	private int length;
	private Angle angle;

	// constructors
	public Line(int posX, int posY, int length, Angle angle, boolean isSolid){
		super(posX, posY, isSolid);
		this.length = length;
		this.angle = angle;
		
	}
	public Line(int posX, int posY, int length, int angle,boolean isSolid){
		super(posX, posY, isSolid);
		this.length = length;
		this.angle = new Angle(angle);
		
	}
	
	// getters
	public int getLength(){ return this.length;}
	public Angle getAngle(){return this.angle;}
	
	
}
