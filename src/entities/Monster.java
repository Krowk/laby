package entities;

public class Monster extends Character implements Updatable{
// Fields------------------------------------------------------------------------

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
			setSpeedX(-Math.signum(posX - x) * 0.05);
			setSpeedY(-Math.signum(posY - y) * 0.05);
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

}
