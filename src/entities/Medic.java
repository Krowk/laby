package entities;

import java.util.ArrayList;

public class Medic extends Character{
	
	private ArrayList<Food> tabFood;

	public Medic(int posX, int posY, int length, int width, int life, int force, ArrayList<Food> tabFood) {
		super(posX, posY, length, width, life, force);
		this.tabFood = tabFood;
	}
	
	public int getposX(){
		return this.posX;
	}
	
	
	public int getposY(){
		return this.posY;
	}
	
	
	public ArrayList gettabFood(){
		return this.gettabFood();
	}

}
