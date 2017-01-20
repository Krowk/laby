package entities;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Camera {
	private Player player;
	private static Camera camera;
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = d.width/10;
	private int height = d.height/10;
	
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
