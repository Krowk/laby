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
		int x = p.getposX();
		int y = p.getposY();
		}
	}
	
	public void update(){
		super.update();
	}
	
// Static methods ---------------------------------------------------------------	
		
// Getters ----------------------------------------------------------------------
	
// Setters ----------------------------------------------------------------------

}
