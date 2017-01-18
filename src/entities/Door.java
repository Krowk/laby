package entities;
import java.util.ArrayList;

/**
 * A door is an object linked to a wall, its position depends of the wall suporting it
 */
public class Door  extends Bloc{
	// Fields
	private int id;
	private Wall wall;
	private int position;
	private int length;
	protected boolean open;
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
	public Door(Wall wall, int position, int length, boolean open) {
		super((int)wall.getPosX()+(int)Math.cos(wall.angle.getRadian())*position, (int)wall.getPosY()+(int)Math.sin(wall.angle.getRadian())*position, length, wall.width,wall.angle, true);
		this.id = doors.size();
		this.wall = wall;
		wall.setDoor(this);
		this.position = position;
		this.length = length;
		this.open = open;
		
		doors.add(this);
	}
	
	
	// dynamic methods
	
	// static methods
	
	public static Door createDoor(int wallid, int position, int length, boolean open){
		Wall wall = Wall.getWallFromId(wallid);
		return new Door(wall, position, length, open);
		
	}
	
	// getters
	
	public int getId(){	return this.id;	}
	
	public boolean getOpen(){ return open;}
	// setters
}
