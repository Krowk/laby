package entities;

import java.util.ArrayList;

public class Cook extends Character implements Updatable{
// Fields------------------------------------------------------------------------
	
	private int nombreFoodsMax;
	private int nombreFoods;
	
// Constructors -----------------------------------------------------------------
	public Cook(int posX, int posY, int length, int width, int life, int force, int nombreFoods, int nombreFoodsMax) {
		super(posX, posY, length, width, life, force);
		this.nombreFoods = nombreFoods;
		this.nombreFoodsMax = nombreFoodsMax;
	}
	
// Dynamic methods --------------------------------------------------------------	
	/**
	 * All 5 minutes he earns 1 food while he isn't full
	 */
	public void GainFood(){

		if(nombreFoods < nombreFoodsMax){
			int timeInMillisecondes = 1000;
			int i = 300;
			
			while (i != 0){
				try{
					Thread.sleep(timeInMillisecondes);
				}
				catch(InterruptedException e){
					
				}
				i--;
			}
			
			nombreFoods = nombreFoods + 1;
			
		}
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