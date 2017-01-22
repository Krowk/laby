package entities;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Camera {
	private Player player;
	private static Camera camera;
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = (int)(((double)d.width/d.height)*50.0);
	private int height = 50;
	
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
	public int getPlayerLength(){
		return player.length;
	}
	public int getPlayerWidth(){
		return player.width;
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
