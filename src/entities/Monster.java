package entities;

public class Monster extends Character implements Updatable{
// Fields------------------------------------------------------------------------
	private double speedMax = 0.5;
	private int counter = 0;
// Constructors -----------------------------------------------------------------
	public Monster(int posX, int posY, int length, int width, int life, int force) {
		super(posX, posY, length, width, life, force);
	}
	
// Dynamic methods --------------------------------------------------------------
	
	public void IA(){
		Player p = Player.getPlayer();
		double x = p.getPosX();
		double y = p.getPosY();
		if (Math.sqrt(Math.pow(Math.abs(posX - x),2) + Math.pow(Math.abs(posY - y),2)) < 30 && counter ==0 ){
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
		if (counter > 0){
			counter--;
		}
		super.update();
	}
	
	public boolean collision(Bloc b){
		boolean res = super.collision(b);
		if (res){
			if(b instanceof Player){
				Player p = (Player) b;
				attack(p, this);
				counter = 128*1;
			}
		}
		return res;
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
