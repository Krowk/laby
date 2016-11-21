package entities;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A Character is a Bloc with a life
 */
public class Character extends Bloc {
	// Fields
	private int id;
	private int life;
	private int force;
	public static int numId = 0;
	public static HashMap<Integer,Character> characters = new HashMap<Integer,Character>();

	// Constructors
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
	public Character(int posX, int posY, int length, int width, int life, int force) {
		super(posX, posY, length, width, 0);
		this.id = numId;
		this.life = life;
		this.force = force;
		characters.put(id,this);
		numId+=1;
	}
	// Dynamic methods

	public void loseLife(int x) {
		this.life = this.life - x;
	}

	public void winLife(int x) {
		this.life = this.life + x;
	}

	public void winForce(int x) {
		this.force = this.force + x;
	}

	public void loseForce(int x) {
		this.force = this.force - x;
	}

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
	
	
	public void death(){
		
		if(this.life <= 0){
			characters.remove(this.id);
		}
		
	}
	
	
	public void eat(Food f1){
		this.life = this.life + f1.getLifeGain();
		this.force = this.force + f1.getForceGain(); 
	}
	
	

	public void info() {
		System.out.println("life: " + this.life);
	}

	// Static methods
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

	// getters
	public int getId() {
		return this.id;
	}

	public int getLife() {
		return this.life;
	}

	// setters

}
