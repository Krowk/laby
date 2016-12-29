package entities;

import java.util.HashMap;


/**
 * A Character is a Bloc with a life
 */
public class Character extends Bloc {
	
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

// Fields------------------------------------------------------------------------
	
	private int id;
	private int life;
	private int force;
	private Lootable tabItems[];
	//private int itemsMax;
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
		this.tabItems = new Lootable[itemsMax];
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
	 * @param x : x is the force losed (int)
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
	
	/**
	 * Put a element lootable in the inventory
	 * 
	 * @param x 
	 * 			: x is the element lootable
	 */
	public void putInInventory(Lootable x){
		int i = 0;
		int item = 1;
		
		while( i < tabItems.length && item > 0){
			
			if(tabItems[i] == null){
				tabItems[i]=x;
				item = item-1;
			}
			
			i++;
		}
	}
	
	public void affiche(){
		for (int i = 0; i < tabItems.length; i++) {
			if(tabItems[i]==null){
				System.out.println("Element "+i+" : est vide");
			}
			else{
			System.out.println("Element "+i+" : "+ tabItems[i].getName());
			}
		}
	}
	
	public void deletItem(Lootable x){
		for (int i = 0; i < tabItems.length; i++) {
			if(x.getId() == tabItems[i].getId()){
				tabItems[i]=null;
			}
		}
		
		
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
