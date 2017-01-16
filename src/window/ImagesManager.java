package window;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImagesManager {
	ArrayList<String> names = new ArrayList<>();
	HashMap<String, BufferedImage> Images = new HashMap<>();
	
	public ImagesManager(){
		setNames();
		LoadImages();
	}
	
	public void LoadImages(){
		for (String name : names) {
			try {
				Images.put(name, ImageIO.read(new File("src/"+name+".png")));
			} catch (Exception e) {
				// TODO: handle exception
			}			
		}
	}
	
	public void setNames(){
		names.add("Food1");
		names.add("Cook");
		names.add("Monster");
	}
	
	public BufferedImage getImage(String name){
		return Images.get(name);
	}
}
