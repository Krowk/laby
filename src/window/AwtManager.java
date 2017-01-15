package window;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.sun.javafx.scene.input.KeyCodeMap;

import Main.Maze;
import entities.Bloc;
import entities.Camera;
import entities.Entity;
import entities.Lootable;
import entities.Player;
import entities.Character;
import javafx.scene.input.KeyCode;

/**
 * Take care of all the 2Ds stuff
 */
public class AwtManager {
// Fields -----------------------------------------------------	
	/**
	 * The window
	 */
	private Frame frame;
	/**
	 * the thing to get the bufferStrategy
	 */
	private Canvas canvas;
	/**
	 * the thing that does magic 
	 */
	private BufferStrategy bs;
	private BufferedImage img;
	private ArrayList<Integer> keys = new ArrayList<Integer>();
	private ArrayList<Integer> thing = new ArrayList<Integer>();

	
// Constructors -----------------------------------------------
	/**
	 * create an AwtManager, which open a window and set it up
	 */
	public AwtManager(){
		try {
			img = ImageIO.read(new File("src/Food1.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frame = new Frame("Game");
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(800, 450));
		frame.add(canvas);
		canvas.setFocusable(false);
		
		frame.setLocation(0, 0);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEnvent){
				System.exit(0);
			}
		});
		
		
		
		frame.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				keys.add(e.getKeyCode());
				thing.add(0);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				keys.add(e.getKeyCode());
				thing.add(1);
			}
		});
		frame.setFocusable(true);
		frame.getFocusTraversalKeysEnabled();
		
		frame.setVisible(true);
		frame.pack();		
		
		canvas.createBufferStrategy(3);
		bs = canvas.getBufferStrategy();
		
		
		
	}
	
// Dynamic methods --------------------------------------------	
	/**
	 * print all the maze in the window 
	 * @param maze the maze to print
	 * @param FPS 
	 * @param TPS
	 */
	public void render(Maze maze, double FPS, double TPS ){
		do {
			do{
				ArrayList<Entity> content = maze.getMazeContent();
				Graphics2D g = (Graphics2D) bs.getDrawGraphics();
				Camera c = Camera.getCamera();
				int w = frame.getWidth();
				int h = frame.getHeight();
				
				// #antialiasing #ezpz
				g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, w, h);
				
				g.setColor(Color.BLACK);
				//resizing
				if (c != null){
					g.scale(w/c.getWidth(), h/c.getHeight());
					g.translate(-c.getX()+(c.getWidth()/2),- c.getY()+(c.getHeight()/2));
					g.setClip(c.getX()-(c.getWidth()/2), c.getY()-(c.getHeight()/2), c.getWidth(), c.getHeight());
				}
				
				for (Entity prout : content) {
					Bloc b = (Bloc) prout;
					double radian = b.getAngle().getRadian();
					g.rotate(radian, b.getPosX(), b.getPosY());
					if ( b instanceof Player) g.setColor(Color.RED);
					else g.setColor(Color.BLACK);						
					g.fill(new Rectangle(b.getPosX(), b.getPosY(), b.getLength(), b.getWidth()));
					g.rotate(-radian, b.getPosX(), b.getPosY());
					
				}
				//un-resizing
				if (c != null){
					
					
					//g.setClip(c.getX()-(c.getWidth()/2), c.getY()-(c.getHeight()/2), c.getWidth(), c.getHeight());
					g.translate(+c.getX()-(c.getWidth()/2),+ c.getY()-(c.getHeight()/2));
					g.scale(((double)c.getWidth()/w), ((double)c.getHeight()/h));
				}
				
				// HUD
				g.setColor(Color.BLACK);
				g.drawString("TPS: " +String.valueOf((int)TPS), 0, 20);
				g.drawString("FPS: " +String.valueOf((int)FPS), 0, 40);
				
				Player p = c.getPlayer();
				double l =(double)  p.getLife()/p.getLifeMax();
				g.setColor(Color.RED);
				g.drawRect(2*w/16, 1*h/36, 2*w/16, w/36);
				g.setColor(Color.PINK);
				g.fillRect((2*w/16)+1, 1*h/36, ((int) ((double)(2*w/16)*l))-1, w/36);
				
				double f =(double)  p.getForce()/p.getForceMax();
				g.setColor(Color.BLACK);
				g.drawRect(5*w/16, 1*h/36, 2*w/16, w/36);
				g.setColor(new Color(195,143,38));
				g.fillRect((5*w/16)+1, 1*h/36, ((int) ((double)(2*w/16)*f))-1, w/36);
				
				Lootable[] loots = p.getInventory();
				int size = loots.length;
				for (int i =0; i < size; i++) {
					if (loots[i] != null) g.drawImage(img, ((8+i)*w/16), 1*h/36, 2*w/36, 2*w/36, null); 
				}
				
				g.dispose();
			} while (bs.contentsRestored());
			bs.show();
		} while (bs.contentsLost());
	}

	
	
// Static methods ---------------------------------------------
	
// Getters ----------------------------------------------------
	/**
	 * get the window's Width
	 * @return
	 */
	public int getWidth(){
		return frame.getWidth();
	}

	/**
	 * get the window's Height
	 * @return
	 */
	public int getHeight(){
		return frame.getHeight();
	}
	
	/**
	 * get the Keys and delete it
	 * @return an arraylist of the keys stroked
	 */
	public ArrayList<ArrayList<Integer>> getKeys(){
		ArrayList<Integer> a = (ArrayList<Integer>) keys.clone();
		ArrayList<Integer> b = (ArrayList<Integer>) thing.clone();
		ArrayList<ArrayList<Integer>> key = new ArrayList<>();
		key.add(a);
		key.add(b);
		
		keys.clear();
		thing.clear();
		return key;
	}
// Setters ----------------------------------------------------
	
}