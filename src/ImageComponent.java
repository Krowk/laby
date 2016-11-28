import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageComponent extends Component {
	private BufferedImage img;
	
	public void paint(Graphics g){
		g.drawImage(img, 0, 0, null);
	}
	
	public ImageComponent(String path){
		try{
			img = ImageIO.read(new File(path));
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public Dimension getPreferredSize(){
		if (img == null){
			return new Dimension(100,100);
		}
		else{
			return new Dimension(img.getWidth(), img.getHeight());
		}
	}
}
