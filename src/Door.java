import java.util.ArrayList;

/**
 * A door is an object linked to a wall, its position depends of the wall suporting it
 */
public class Door {
	// Fields
	private int id;
	private int wallId;
	private int position;
	private int length;
	private boolean open;
	private boolean needKey;
	public static ArrayList<Door> doors = new ArrayList<Door>();
	
	// Constructors
	/**
	 * Create a door 
	 * @param wallId : the id of the Wall suporting the Door: int 
	 * @param position : the position of the Door relative to the wall: int
	 * @param length : the length of the Door: int
	 * @param open : the state of the Door: boolean
	 * @param needKey : true if the Door need a key to be opened : boolean 
	 */
	public Door(int wallId, int position, int length, boolean open, boolean needKey) {
		this.id = doors.size();
		this.wallId = wallId;
		this.position = position;
		this.length = length;
		this.open = open;
		this.needKey = needKey;
		doors.add(this);
	}
	
	//Jaime la voiture	!
	
	// dynamic methods
	
	// static methods
	
	// getters
	
	// setters
}
