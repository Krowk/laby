package entities;


public class Camera {
	private Player player;
	private static Camera camera;
	private int width = 160;
	private int height = 90;
	
	public Camera(Player player){
		this.player = player;
		camera = this;
	}

	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	
	public double getX(){
		return player.getPosX();
	}
	public double getY(){
		return player.getPosY();
	}
	public static Camera getCamera(){
		return camera;
	}
	
	public Player getPlayer(){
		return player;
	}
	
}
