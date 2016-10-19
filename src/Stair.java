
public class Stair extends Elevator{
	private boolean up;

	public Stair(int x, int y, int length, int width, int id, int floor, boolean up) {
		super(x, y, length, width, floor);
		this.up	= up;
	}

	
}
