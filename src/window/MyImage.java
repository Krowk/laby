package window;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;

import javax.imageio.ImageIO;

public class MyImage extends Image {
	BufferedImage img = null;
	
	public MyImage(String str){
		try {
			img = ImageIO.read(new File(str));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	
	@Override
	public Graphics getGraphics() {
		// TODO Auto-generated method stub
		return img.getGraphics();
	}

	@Override
	public int getHeight(ImageObserver observer) {
		if (img != null) return img.getHeight();
		else return 0;
	}

	@Override
	public Object getProperty(String name, ImageObserver observer) {
		if (img != null) return img.getProperty(name, observer);
		else return null;
	}

	@Override
	public ImageProducer getSource() {
		if (img != null) return img.getSource();
		else return null;
	}

	@Override
	public int getWidth(ImageObserver observer) {
		
		if (img != null) return img.getWidth();
		return 0;
	}

}
