package entities;


public class Stair extends Elevator{
	
//Fields -------------------------------------------
	
	private boolean up;
	
//Constructor --------------------------------------
	
	/**
	 * Create stair
 	* @param x
 	* 			: x position (int)
 	* 
 	* @param y
 	* 			: y position (int)
 	* 
 	* @param length
 	* 			: lenght of the stair (int)
 	* 
 	* @param width
 	* 			: width of the stair (int)
 	* 
 	* @param id
 	* 			: id of the stair
 	* 
 	* @param floor
 	* 			: floor of the stair (int)
 	* 
 	* @param up
 	* 			: up if the stair go up or go down (boolean)
 	*/
	public Stair(int x, int y, int length, int width, int id, int floor, boolean up) {
		super(x, y, length, width, floor);
		this.up	= up;
	}

	
}
