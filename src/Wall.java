import java.util.ArrayList;
/**
 * 
 *
 */
public class Wall extends Entity{
	// Fields
	private int id;
	private int length;
	private int angle; //�
	public static ArrayList<Wall> walls = new ArrayList<Wall>();
	
	// Constructors
	/**
	 * create a Wall
	 * @param posX the x position: int 
	 * @param posY the y position:  int
	 * @param length the length of the wall: int
	 * @param angle the inclination on the wall in �: int
	 */
	public Wall(int posX, int posY, int length, int angle){
		super(posX, posY);
		this.id	= walls.size();
		this.length	= length;
		this.angle	= angle;
		walls.add(this);
	}
	
	// Dynamic methods
	
	
	// Static methods
	/**
	 * get a wall from its id
	 * @param id :the id of the wall
	 * @return the Wall at the "id" indice
	 */
	public static Wall getWallFromId(int id){
		return walls.get(id);
	}
	
	// getters
	public int getId(){ return this.id;}
	
	// setters
}