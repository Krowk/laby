package entities;
import java.util.ArrayList;

/**
 * An elevator is a bloc which can move an Entity through different floor 
 */
public class Elevator extends Bloc {
	// Fields
	private int id;
	private int floor;
	public static ArrayList<Elevator> elevators = new ArrayList<Elevator>();
	// Constructors
	/**
	 * create an elevator
	 * @param x : a x position : int
	 * @param y : a y position : int
	 * @param length : the length of the elevator : int
	 * @param width : the width of the elevator : int
	 * @param id : 
	 * @param floor
	 */
	public Elevator(int x, int y, int length, int width, int floor) {
		super(x, y, length, width, 0, true);
		this.id	= elevators.size();
		this.floor	= floor;
		elevators.add(this);
	}
	
	// dynamic methods
	
	/*
	public goTo(int floorTarget){
		
	}
	*/
	
	// static methods
	
	// getters
	
	// setters
}
