package entities;

import java.util.ArrayList;

public class Cook extends Character implements Updatable{
// Fields------------------------------------------------------------------------
	
	private int nombreFoodsMax = 5;
	private int nombreFoods;
	private Food food;
	private int counter = 0;
	
// Constructors -----------------------------------------------------------------
	public Cook(int posX, int posY, int length, int width, int life, int force, int nombreFoods, Food food) {
		super(posX, posY, length, width, life, force);
		this.nombreFoods = 3;
		
		this.food = food;
	}
	
// Dynamic methods --------------------------------------------------------------	
	/**
	 * All 5 minutes he earns 1 food while he isn't full
	 */
	public void gainFood(){
		// utiliser counter pour gérer le temps, ça compte le nombre de tick
		if(nombreFoods < nombreFoodsMax){
			if (counter > 128* 10){
				nombreFoods = nombreFoods + 1;	
				counter = 0;
			}
			
		}
	}
	
	public void removeFood(){
		this.nombreFoods = nombreFoods-1;
	}
	
	public void update(){
		counter++;
		gainFood();
	}
	
// Static methods ---------------------------------------------------------------	
	
// Getters ----------------------------------------------------------------------	
	
	
	public Food getFood(){
		return this.food;
	}
	
	public int getNombreFoods(){
		return this.nombreFoods;
	}
// Setters ----------------------------------------------------------------------
	
	


}