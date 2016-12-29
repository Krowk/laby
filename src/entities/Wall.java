package entities;
import java.util.ArrayList;

import physics.Angle;
/**
 * A wall is a Bloc which cannot move he can have a door
 * @see bloc
 *
 */
public class Wall extends Bloc{
// Fields -----------------------------------------------------
	/**
	 * The identificator of the wall
	 */
	private int id; //°
	/**
	 * The list of all the wall
	 */
	public static ArrayList<Wall> walls = new ArrayList<Wall>();
	
// Constructors -----------------------------------------------
	/**
	 * create a Wall
	 * @param posX the x position: int 
	 * @param posY the y position:  int
	 * @param length the length of the wall: int
	 * @param angle the inclination of the wall: Angle
	 * @see Entity
	 * @see bloc
	 * @see Angle
	 */
	public Wall(int posX, int posY, int length, int width, Angle angle){
		super(posX, posY, length, width, angle);
		this.id	= walls.size();
		walls.add(this);
	}
	
	/**
	 * create a Wall
	 * @param posX the x position: int 
	 * @param posY the y position:  int
	 * @param length the length of the wall: int
	 * @param angle the inclination of the wall: int
	 * @see Entity
	 * @see bloc
	 * @see Angle
	 */
	public Wall(int posX, int posY, int length, int width, int angleDegree){
		super(posX, posY, length, width, angleDegree);
		this.id	= walls.size();
		walls.add(this);
	}
	
// Dynamic methods --------------------------------------------
	/**
	 * print in console the fields of the wall
	 */
	public void show(){
		super.show();
		
	}

	
// Static methods ---------------------------------------------
	/**
	 * get a wall from its id
	 * @param id :the id of the wall
	 * @return the Wall at the "id" indice
	 */
	public static Wall getWallFromId(int id){
		return walls.get(id);
	}
	
// getters ----------------------------------------------------
	/**
	 * get the id of the wall
	 * @return the id of this wall
	 */
	public int getId(){ return this.id;}
	
// setters ----------------------------------------------------
}
