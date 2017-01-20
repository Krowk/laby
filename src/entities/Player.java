package entities;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;

import Main.Main;
public class Player extends Character implements Updatable{
// Fields------------------------------------------------------------------------
	// static fields
	private static Player player;
	// dynamic fields	
	private Camera camera;
	private Lootable[] tabItems;
	private int itemsMax;
	private int counterForce;
	private int counterFight;
	private boolean canFight= true;
	
// Constructors -----------------------------------------------------------------
	public Player(int posX, int posY, int length, int width, int life, int force, int itemsMax) {
		super(posX, posY, length, width, life, force);
		this.camera = new Camera(this);
		this.itemsMax = itemsMax;
		this.tabItems = new Lootable[itemsMax];
		player = this;
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
			
			if(tabItems[i] == null && x.isOnFloor()){
				tabItems[i]=x;
				x.pickup();
				item = 0;
				x.width=0;
				x.length=0;
			}
			
			i++;
		}
		
	}
	/*
	public void printInventory(){
		for (int i = 0; i < tabItems.length; i++) {
			if(tabItems[i]==null){
				System.out.println("Element "+i+" : est vide");
			}
			else{
			System.out.println("Element "+i+" : "+ tabItems[i].getName());
			}
		}
	}*/
	
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
				tabItems[n] = null;
			}
			
			
		}
	}
	
	
	public void update(){
		HashSet<Integer>keys = Main.getKeyEvents();
		ArrayList<Integer> mouse = Main.getMouseEvents();
		
		if (keys.contains(KeyEvent.VK_Z)){
			if (!keys.contains(KeyEvent.VK_S)){
				setSpeedY(-speedMax);
			}
		}
		else if (keys.contains(KeyEvent.VK_S)){
			setSpeedY(speedMax);
		}
		else{
			setSpeedY(0);
		}
		if (keys.contains(KeyEvent.VK_Q)){
			if (!keys.contains(KeyEvent.VK_D)){
				setSpeedX(-speedMax);
			}
		}
		else if (keys.contains(KeyEvent.VK_D)){
			setSpeedX(speedMax);
		}
		else{
			setSpeedX(0);
		}
		
		for (int i : mouse) {
			if (i < itemsMax) useInventory(i);
		}
		
		counterForce++;
		if (counterForce> 128*3){
			loseForce(1);
			counterForce = 0;
		}
		counterFight++;
		if (counterFight> 128*0.5 && !canFight){
			canFight = true;
			counterFight = 0;
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
		gainLife(f1.getLifeGain());
		gainForce(f1.getForceGain()); 
	}

	
	public boolean collision(Bloc b){
		boolean res = super.collision(b);
		if (res){
			if(b instanceof Cook){
				Cook c = (Cook) b;
				int i =0;
				while(c.getNombreFoods()>0){
					
					putInInventory(new Food(0,0,0,0,50,50,false,i));
					c.removeFood();
					i++;
				}		
			}
			
			else if (b instanceof Monster){
				if (canFight){
					Monster m = (Monster) b;
					attack(this,m);	
					canFight = false;
				}
				
			}
			
			else if (b instanceof Medic){
				Medic m = (Medic) b;
				
				if(m.getVisite() != 0 ){
					gainLife(50);
					m.havebeenvisited();
				}
			}
			
			
			else if (b instanceof SecretDoor){
				SecretDoor s = (SecretDoor) b;
				
				this.posX = ((SecretDoor) b).getTpPosX();
				this.posY = ((SecretDoor) b).getTpPosY();
				
			}
			else if(b instanceof Door){
				Door d = (Door) b;
				
				if(d.angle.getDegree() == 0 && d.posY<posY){
					posY = this.posY-d.width-this.length;
				}
				
				else if(d.angle.getDegree() == 0 && d.posY>posY){
					posY = this.posY+d.width+this.length;
				}
				

				else if (b.angle.getDegree() == 270 && b.posX<this.posX){
					posX = this.posX-b.width-this.length;
				}
				else if (d.angle.getDegree() == 270 && d.posX>this.posX){
					posX = this.posX+this.length+d.width;
				}
				
				
				
				/*
				if(b.length<b.width){
					if (Math.abs(b.getPosX())<Math.abs(this.posX)){
						this.posX = this.posX-b.length-this.length;
					}
					
					else if (Math.abs(b.getPosX())>Math.abs(this.posX)){
						this.posX = this.posX+b.length+this.length;
					}
				}
				else{
					if(Math.abs(b.getPosY()) < Math.abs(this.posY)){
						this.posY=this.posY-Math.abs(b.width)-Math.abs(this.length);
					}
					else if(Math.abs(b.getPosY()) > Math.abs(this.posY)){
						this.posY=this.posY+Math.abs(this.length)+Math.abs(b.width);
					}
				}*/

				
			}
			
			
			
			
			else if (b instanceof Safe){
				Safe s = (Safe) b;
				Main.state = 2;

			}
			
			else if (b instanceof Lootable){
				Lootable l = (Lootable) b;
				putInInventory(l);
			}
		}
		return res;
	}
// Static methods ---------------------------------------------------------------	
	
// Getters ----------------------------------------------------------------------	
	
	public Lootable[] getInventory(){
		return tabItems;
	}
	
	public static Player getPlayer(){
		return player;
	}
// Setters ----------------------------------------------------------------------
}
