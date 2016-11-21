package entities;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A Character is a Bloc with a life
 */
public class Character extends Bloc {

// Fields------------------------------------------------------------------------
	
	private int id;
	private int life;
	private int force;
	private LootTable tabItems[];
	private int itemsMax;
	public static int numId = 0;
	public static HashMap<Integer,Character> characters = new HashMap<Integer,Character>();

// Constructors ------------------------------------------------------------------
	
	/**
	 * create a Character
	 * 
	 * @param posX
	 *            : a x position : int
	 * @param posY
	 *            : a y position : int
	 * @param length
	 *            : a length : int
	 * @param width
	 *            : a width : int
	 * @param life
	 *            : the life of the character : int
	 */
	public Character(int posX, int posY, int length, int width, int life, int force, int itemsMax) {
		super(posX, posY, length, width, 0);
		this.id = numId;
		this.life = life;
		this.force = force;
		this.tabItems = new LootTable[itemsMax];
		characters.put(id,this);
		numId+=1;
	}
	
// Dynamic methods ---------------------------------------------------------------

	/**
	 * Losing life
	 * @param x : x is the life losed (int)
	 */
	public void loseLife(int x) {
		this.life = this.life - x;
	}

	/**
	 * Winning life
	 * @param x: x is the gain of life (int)
	 */
	public void winLife(int x) {
		this.life = this.life + x;
	}

	
	/**
	 * Winning force
	 * @param x : x is the gain of force (int)
	 */
	public void winForce(int x) {
		this.force = this.force + x;
	}
	
	
	/**
	 * Losing life
	 * @param x : is the force losed (int)
	 */
	public void loseForce(int x) {
		this.force = this.force - x;
	}
	
	
	/**
	 * Killing a character
	 */
	public void death(){
		
		if(this.life <= 0){
			characters.remove(this.id);
		}
		
	}
	
	/**
	 * Gain of life and force for a character
	 * @param f1: is the food (Food)
	 */
	public void eat(Food f1){
		this.life = this.life + f1.getLifeGain();
		this.force = this.force + f1.getForceGain(); 
	}
	
	public void info() {
		System.out.println("life: " + this.life);
	}

	public void putInInventory(LootTable x){
		
		// a faire
	}
	
// Static methods --------------------------------------------------------------
	
	/**
	 * return a Character given its id
	 * 
	 * @param id
	 *            : the id of a character
	 * @return a Character
	 */
 	public static Character getCharacterFromId(int id) {

		return characters.get(id);
	}
	

	/**
	 * Fighting between two characters
	 * @param c1: first character (Character)
	 * @param c2: seconde character (Character)
	 */
	public static void attack(Character c1, Character c2) {
		int healthC1 = c1.life;
		int attackC1 = c1.force;
		int healthC2 = c2.life;
		int attackC2 = c2.force;
		

		int res = (int) Math.random() * attackC1 - (int) Math.random() * healthC2;
		int res2 = (int) Math.random() * attackC2 - (int) Math.random() * healthC1;

		if (res > 0) {
			c2.loseLife(attackC1);
		}

		if (res2 > 0){
			c1.loseLife(attackC2);

		}

	}

// getters ---------------------------------------------------------------------
	
	/**
	 * Getting the Id of a character
	 * @return (int)
	 */
	public int getId() {
		return this.id;
	}

	
	/**
	 * Getting the life of a character
	 * @return (int)
	 */
	public int getLife() {
		return this.life;
	}

// setters ---------------------------------------------------------------------

}
