package entities;

public class Monster extends Character implements Updatable{
// Fields------------------------------------------------------------------------
	private double speedMax = 0.5;
// Constructors -----------------------------------------------------------------
	public Monster(int posX, int posY, int length, int width, int life, int force) {
		super(posX, posY, length, width, life, force);
	}
	
// Dynamic methods --------------------------------------------------------------
	
	public void IA(){
		Player p = Player.getPlayer();
		double x = p.getPosX();
		double y = p.getPosY();
		int a = (int)Math.sqrt(Math.pow(Math.abs(posX - x),2) + Math.pow(Math.abs(posY - y),2));
		if ( Math.sqrt(Math.pow(Math.abs(posX - x),2) + Math.pow(Math.abs(posY - y),2)) < 30){
			setSpeedX(-Math.signum(posX - x) * speedMax);
			setSpeedY(-Math.signum(posY - y) * speedMax);
		}
		else{
			setSpeedX(0);
			setSpeedY(0);
		}
	}
	
	public void update(){
		IA();
		super.update();
	}
	
// Static methods ---------------------------------------------------------------	
		
// Getters ----------------------------------------------------------------------
	
// Setters ----------------------------------------------------------------------
	public void setSpeedX(double i){
		if (i == 0){
			speedX = 0;
		}
		else{
			speedX += i; 
			if (speedX > speedMax){
				speedX = speedMax;
			}
			else if (speedX < -speedMax){
				speedX = -speedMax;
			}
		}	
	}
	
	public void setSpeedY(double i){
		if (i == 0){
			speedY = 0;
		}
		else{
			speedY += i;
			if (speedY > speedMax){
				speedY = speedMax;
			}
			else if (speedY < -speedMax){
				speedY = -speedMax;
			}
		}
	}
}
