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
	
	public int getX(){
		return player.getposX();
	}
	public int getY(){
		return player.getposY();
	}
	public static Camera getCamera(){
		return camera;
	}
	
	public Player getPlayer(){
		return player;
	}
	
}
