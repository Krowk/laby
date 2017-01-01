package entities;

import physics.Collision;

/**
 * An Entity is anything that has a position
 */
public abstract class Entity {
	
// Fields -----------------------------------------------------
	// static fields
	
	
	// dynamic fields
	/**
	 * The x coordinate of the Entity
	 */
	protected int posX;
	/**
	 * The y coordinate of the Entity
	 */
	protected int posY;
	protected double speedX;
	protected double speedY;
	protected double speedMax = 1;
	protected boolean isSolid;
	
// Constructors -----------------------------------------------
	/**
	 * create a Entity
	 * @param posX : a x position: int
	 * @param posY : a y position: int
	 */
  	public Entity(int posX, int posY, boolean isSolid) {
		this.posX = posX;
		this.posY = posY;
		this.isSolid = isSolid;
	}
	
// dynamic methods --------------------------------------------
	/**
	 * Show the position of the Entity
	 */
	public void show(){
		System.out.println("posX:\t"+ posX);
		System.out.println("posY:\t"+ posY);
	}
	
	/**
	 * change the x coordinate of the Entity
	 * @param deplacement
	 */
	public void moveX(){
		posX += speedX;
		if (this instanceof Bloc){
			if(Collision.collision((Bloc)this)){
				posX -= speedX;
				
			}
		}
		
	}
	
	/**
	 * change the y coordinate of the Entity
	 * @param deplacement 
	 */
	public void moveY(){
		this.posY += speedY;
		if (this instanceof Bloc){
			if (Collision.collision((Bloc)this)){
				posY -= speedY;
			}
		}
	}
	
	public void update(){
		if (speedX !=0){
			moveX();
		}
		if ( speedY !=0){
			moveY();			
		}
		
	}
// static methods ---------------------------------------------
	
// getters ----------------------------------------------------
	/**
	 * Get the x coordinate of the Entity
	 * @return
	 */
	public int getPosX(){ return this.posX;}

	/**
	 * Get the y coordinate of the Entity
	 * @return
	 */
	public int getPosY(){ return this.posY;}

	
// setters ----------------------------------------------------
	public void setSpeedX(double i){
		speedX += i; 
		if (speedX > speedMax){
			speedX = speedMax;
		}
		else if (speedX < -speedMax){
			speedX = -speedMax;
		}
	}
	
	public void setSpeedY(double i){
		speedY += i;
		if (speedY > speedMax){
			speedY = speedMax;
		}
		else if (speedY < -speedMax){
			speedY = -speedMax;
		}
	}

}
