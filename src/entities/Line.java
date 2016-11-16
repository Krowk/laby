package entities;
import physics.Angle;

//should be abstract but I wanna do some tests
public class Line extends Entity{
	// Fields
	private int length;
	private Angle angle;

	// constructors
	public Line(int posX, int posY, int length, Angle angle){
		super(posX, posY);
		this.length = length;
		this.angle = angle;
		
	}
	public Line(int posX, int posY, int length, int angle){
		super(posX, posY);
		this.length = length;
		this.angle = new Angle(angle);
		
	}
	
	// getters
	public int getLength(){ return this.length;}
	public Angle getAngle(){return this.angle;}
	
	
}
