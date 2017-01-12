package entities;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Main.Main;
public class Player extends Character implements Updatable{
// Fields------------------------------------------------------------------------
	// static fields
	
	// dynamic fields	
	private Lootable tabItems[];
	private int itemsMax;
	
// Constructors -----------------------------------------------------------------
	public Player(int posX, int posY, int length, int width, int life, int force, int itemsMax) {
		super(posX, posY, length, width, life, force);
		this.itemsMax = itemsMax;
		this.tabItems = new Lootable[itemsMax];
	}
	
// Dynamic methods --------------------------------------------------------------	
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
	
	public void printInventory(){
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

	public void update(){
		ArrayList<Integer>keys = Main.getKeys().get(0);
		ArrayList<Integer>thing = Main.getKeys().get(1);
		int size = keys.size();
		int a = 2; 
		if (size > 0){
			a=thing.get(0);
			
		}
		for (int i = 0; i < size; i++) {
			
			switch (keys.get(i)) {
			case KeyEvent.VK_Z:
				if (thing.get(i) == 1){
					setSpeedY(-speedMax);
				}
				else{
					setSpeedY(speedMax);
				}
				
				break;
			case KeyEvent.VK_S:
				if (thing.get(i) == 1){
					setSpeedY(speedMax);					
				}
				else{
					setSpeedY(-speedMax);
				}
				break;	
			case KeyEvent.VK_Q:
				if (thing.get(i) == 1){
					setSpeedX(-speedMax);					
				}
				else{
					setSpeedX(speedMax);
				}
				break;
			case KeyEvent.VK_D:
				if (thing.get(i) == 1){
					setSpeedX(speedMax);					
				}
				else{
					setSpeedX(-speedMax);
				}
				break;
			default:
				break;
			}
		}
		super.update();
	}
		
	public boolean useKey(Key k,Door d){
		if(k.getId()==d.getId()){
			return true;
		}
		return false;
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

	public void medic(Medic m){
		if (m.getposX() == this.posX && m.getposY() == this.posY){
			
			
			// A FINIR ///////////////////////////////////////
			
		}
	}
	
	public boolean collision(Bloc b){
		boolean res = super.collision(b);
		// met tes trucs ici genre:
		// if (b instanceof Cook){
		//   Cook c = (Cook) b
		//   a.voleDeLaBouf(c)
		// }
		// else if ( b instanceof Monstre){
		//  Monstre m = (Monstre) m
		// 	a.attaque(b)
		// }
		// si tu veux qu'un autre objet initie un truc quand il entre en collision tu copie cette fonction dans l'objet en question
		
		return res;
	}
// Static methods ---------------------------------------------------------------	
	
// Getters ----------------------------------------------------------------------	
	public int getposX(){
		return this.posX;
	}
	
	public int getposY(){
		return this.posY;
	}
	
	
// Setters ----------------------------------------------------------------------
}
