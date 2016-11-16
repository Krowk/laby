package entities;
import java.util.ArrayList;
/**
 * A Character is a Bloc with a life
 */
public class Character extends Bloc{
	// Fields
	private int id;
	private int life;
	public static ArrayList<Character> characters = new ArrayList<Character>();
	
	// Constructors
	/**
	 * create a Character
	 * @param posX : a x position : int
	 * @param posY : a y position : int
	 * @param length : a length : int
	 * @param width : a width : int
	 * @param life : the life of the character : int
	 */
	public Character(int posX, int posY, int length, int width, int life) {
		super(posX, posY, length, width, 0);
		this.id = characters.size();
		this.life = life;
		characters.add(this);
	}
	// Dynamic methods

	// 3- methode qui fait perde n point de vie au personnage
	// 4- methode qui fait gagner n point de vie au personnage
	
	public void info(){
		System.out.println("life: "+this.life);
	}
	
	// Static methods
	/**
	 * return a Character given its id
	 * @param id : the id of a character
	 * @return a Character
	 */
	public static Character getCharacterFromId(int id){
		return characters.get(id);
	}
	
	// getters
	public int getId(){ return this.id;}
	
	// setters

}
