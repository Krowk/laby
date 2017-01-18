package entities;



import java.util.HashMap;

import Main.Main;
import Main.Maze;


/**
 * A Character is a Bloc with a life
 */
public class Character extends Bloc {
	/*
	public static void main (String[] args){
		Character c = new Character(0,0,10,10,100,50,3);
		c.affiche();
		Food f = new Food(1,false,0,0,0,0,"Bouf1");
		Food f1 = new Food(1,false,0,0,0,0,"Bouf2");
		Food f2 = new Food(1,false,0,0,0,0,"Bouf3");
		Food f3 = new Food(1,false,0,0,0,0,"Bouf4");
		c.putInInventory(f);
		c.putInInventory(f1);
		c.putInInventory(f2);
		c.putInInventory(f3);
		c.affiche();
		
	}
	 */
// Fields------------------------------------------------------------------------
	// static fields
	public static int numId = 0;
	public static HashMap<Integer,Character> characters = new HashMap<Integer,Character>();
	
	// dynamic fields
	private int id;
	protected int life;
	protected int lifeMax;
	protected int force;
	protected int forceMax;
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
	public Character(int posX, int posY, int length, int width, int life, int force) {
		super(posX, posY, length, width, 0, true);
		this.id = numId;
		this.life = life;
		this.lifeMax = life;
		this.force = force;
		this.forceMax = force;
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
		if (life <= 0){
			this.death();
		}
	}

	/**
	 * Gaining life
	 * @param x: x is the gain of life (int)
	 */
	public void gainLife(int x) {
		this.life = this.life + x;
		if (life > lifeMax){
			life = lifeMax;
		}
	}
	
	/**
	 * Gaining force
	 * @param x : x is the gain of force (int)
	 */
	public void gainForce(int x) {
		this.force = this.force + x;
		if (force > forceMax){
			force = forceMax;
		}
	}
		
	/**
	 * Losing life
	 * @param x : x is the force losed (int)
	 */
	public void loseForce(int x) {
		this.force = this.force - x;
	}
		
	/**
	 * Kil a character
	 */
	public void death(){
		
		if(this.life <= 0){
			characters.remove(this.id);
			Maze.deleteEntity(this);
			if (this instanceof Player){
				Main.state = 1;
			}
		}
		
	}
	
	/**
	 * Gain of life and force for a character
	 * @param f1
	 * 			: f1 is the food (Food)
	 */
	public void eat(Food f1){
		this.life = this.life + f1.getLifeGain();
		this.force = this.force + f1.getForceGain(); 
	}
	
	public void info() {
		System.out.println("life: " + this.life);
		System.out.println("force" + this.force);
	}

	public void update(){
		super.update();
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
		
		int attackC1 = c1.force;
		
		int attackC2 = c2.force;
		

		int res = (int)( Math.random() * attackC1);
		int res2 = (int)( Math.random() * attackC2);

		int damage = Math.abs(res - res2);
		if (res > res2) {
			c2.loseLife(damage);
		}

		if (res2 > res){
			c1.loseLife(damage);
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
	
	public int getLifeMax(){
		return lifeMax;
	}
	
	public int getForce(){return force;}
	public int getForceMax(){return forceMax;}
	
	public static HashMap<Integer, Character> getCharacters(){
		return characters;
	}

// Setters ---------------------------------------------------------------------

}
