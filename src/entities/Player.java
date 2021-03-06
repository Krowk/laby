package entities;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;

import Main.Main;
import physics.Collision;
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
			if(tabItems[i] != null){
				if(x.getId() == tabItems[i].getId()){
					tabItems[i]=null;
				}
				
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
			else{
				setSpeedY(0);
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
			else{
				setSpeedX(0);
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
					
					putInInventory(new Food(0,0,0,0,20,30,true,i));
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
				
				if(m.getVisite() > 0 && life != lifeMax){
					gainLife(lifeMax);
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
				
				if (d.open == true){
					
					if(d.angle.getDegree() == 0 && d.posY<posY){
						posY = this.posY-d.width-this.length;
						
					}
					
					else if(d.angle.getDegree() == 0 && d.posY>posY){
						posY = this.posY+d.width+this.length;
					}
					
					if(d.angle.getDegree() == 180 && d.posY<posY){
						posY = this.posY-d.width-this.length;
						
					}
					
					else if(d.angle.getDegree() == 180 && d.posY>posY){
						posY = this.posY+d.width+this.length;
					}
					
					else if(b.angle.getDegree() == 270 && b.posX<this.posX){
						posX = this.posX-b.width-this.length;
					}
					
					else if(d.angle.getDegree() == 270 && d.posX>this.posX){
						posX = this.posX+this.length+d.width;
					}
					
					else if(d.angle.getDegree() == 90){
						if(this.speedX<0){
							posX = this.posX - d.width - this.length;
						}
						if(this.speedX>0){
							posX = this.posX + d.width + this.length;
						}
					}
					else{
						double playerMidX =(posX-speedX) + length/2;
						double playerMidY =(posY-speedY) + width/2;
						double doorDemiWidth = d.getWidth()/2;
						double doorDemiLength=  d.getLength()/2;
						
						double doorMidX= (d.posX +(doorDemiLength * Math.cos(d.angle.getRadian()) - doorDemiWidth * Math.sin(d.angle.getRadian())));
						double doorMidY= (d.posY +(doorDemiLength * Math.sin(d.angle.getRadian()) + doorDemiWidth * Math.cos(d.angle.getRadian())));
						
						double a = playerMidX - doorMidX;
						double c = playerMidY - doorMidY;
						posX -= (int)((a*2)+Math.signum(a)*1);
						posY -= (int)((c*2)+Math.signum(c)*1);
					}
				}
				
				else if(d.open == false){
					
					if (b.angle.getDegree() == 270 && b.posX<this.posX){
						for (int j = 0; j < tabItems.length; j++) {
							if (tabItems[j] != null) {
								if(tabItems[j].getId() == d.getId()){
									posX = this.posX-b.width-this.length;
									d.open = true;
									deletItem(tabItems[j]);
								}
							}
							
							
						}
					}
					
					else if(d.angle.getDegree() == 270 && d.posX>this.posX){
							for (int j = 0; j < tabItems.length; j++) {
								if (tabItems[j] != null) {
									if(tabItems[j].getId() == d.getId()){
										posX = this.posX+this.length+d.width;
										d.open = true;
										deletItem(tabItems[j]);
									}
								}	
							}
					}
					
					else if(d.angle.getDegree() == 90 && this.speedX<0 ){
						for (int j = 0; j < tabItems.length; j++) {
							if (tabItems[j] != null) {
								if(tabItems[j].getId() == d.getId()){
									posX = this.posX-this.length-d.width;
									d.open = true;
									deletItem(tabItems[j]);
								}
							}		
						}
					}
					
					else if(d.angle.getDegree() == 90 && this.speedX>0 ){
						for (int j = 0; j < tabItems.length; j++) {
							if (tabItems[j] != null) {
								if(tabItems[j].getId() == d.getId()){
									posX = this.posX+this.length+d.width;
									d.open = true;
									deletItem(tabItems[j]);
								}
							}				
						}
					}
					
					else if(d.angle.getDegree() == 0 && this.speedY<0 ){
						for (int j = 0; j < tabItems.length; j++) {
							if (tabItems[j] != null) {
								if(tabItems[j].getId() == d.getId()){
									posY = this.posY-this.length-d.width;
									d.open = true;
									deletItem(tabItems[j]);
								}
							}		
						}
					}
					
					else if(d.angle.getDegree() == 0 && this.speedY>0 ){
						for (int j = 0; j < tabItems.length; j++) {
							if (tabItems[j] != null) {
								if(tabItems[j].getId() == d.getId()){
									posY = this.posY+this.length+d.width;
									d.open = true;
									deletItem(tabItems[j]);
								}
							}				
						}
					}
					
					else if(d.angle.getDegree() == 180 && this.speedY<0 ){
						for (int j = 0; j < tabItems.length; j++) {
							if (tabItems[j] != null) {
								if(tabItems[j].getId() == d.getId()){
									posY = this.posY-this.length-d.width;
									d.open = true;
									deletItem(tabItems[j]);
								}
							}		
						}
					}
					
					else if(d.angle.getDegree() == 180 && this.speedY>0 ){
						for (int j = 0; j < tabItems.length; j++) {
							if (tabItems[j] != null) {
								if(tabItems[j].getId() == d.getId()){
									posY = this.posY+this.length+d.width;
									d.open = true;
									deletItem(tabItems[j]);
								}
							}				
						}
					}
					else{
						for (int i = 0; i < tabItems.length; i++) {
							if (tabItems[i] != null){
								if (tabItems[i] instanceof Key){
									Key k = (Key) tabItems[i];
									if (k.getId() == d.getId()){
										d.open = true;
										deletItem(k);
									}
								}
							}
						}
					}
				}
				
				
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
