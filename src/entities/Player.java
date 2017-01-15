package entities;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Main.Main;
public class Player extends Character implements Updatable{
// Fields------------------------------------------------------------------------
	// static fields
	
	// dynamic fields	
	private Camera camera;
	private Lootable[] tabItems;
	private int itemsMax;
	private int counterForce;
	
// Constructors -----------------------------------------------------------------
	public Player(int posX, int posY, int length, int width, int life, int force, int itemsMax) {
		super(posX, posY, length, width, life, force);
		this.camera = new Camera(this);
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

	
	public void useInventory(int n){
		if (tabItems[n] != null){
			Lootable l =tabItems[n];			
			if (l instanceof Food){
				Food f = (Food) l;
				eat(f);
			}
			tabItems[n] = null;
		}
	}
	
	
	public void update(){
		ArrayList<Integer>keys = Main.getKeyEvents().get(0);
		ArrayList<Integer>thing = Main.getKeyEvents().get(1);
		ArrayList<Integer> mouse = Main.getMouseEvents();
		int keySize = keys.size();
		
		for (int i = 0; i < keySize; i++) {
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
		
		for (int i : mouse) {
			useInventory(i);
		}
		
		counterForce++;
		if (counterForce> 128*3){
			loseForce(1);
			counterForce = 0;
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

	
	public boolean collision(Bloc b){
		boolean res = super.collision(b);
		if (res){
			if(b instanceof Cook){
				Cook c = (Cook) b;
				
				while(c.getNombreFoods()>0){
					putInInventory(new Food(10,false,10,0,0,0,"bouf"));
					c.removeFood();
				}		
			}
			
			else if (b instanceof Monster){
				Monster m = (Monster) b;
				attack(this,(Character) b);			
				
			}
			
			else if (b instanceof Medic){
				Medic m = (Medic) b;
				
				if(m.getVisite() != 0 ){
					this.life = life + 50;
					m.havebeenvisited();
				}
			}
		}
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
	
	public Lootable[] getInventory(){
		return tabItems;
	}
	
	
// Setters ----------------------------------------------------------------------
}
