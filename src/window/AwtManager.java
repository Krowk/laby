package window;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import Main.Main;
import Main.Maze;
import entities.Bloc;
import entities.Camera;
import entities.Entity;
import entities.Lootable;
import entities.Monster;
import entities.Player;
import entities.Character;

import entities.Cook;
import entities.Door;


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
	private ImagesManager im;
	private HashSet<Integer> keys = new HashSet<Integer>();
	private ArrayList<Integer> actions = new ArrayList<Integer>();
	
	
// Constructors -----------------------------------------------
	/**
	 * create an AwtManager, which open a window and set it up
	 */
	public AwtManager(){
		im = new ImagesManager();
		frame = new Frame("Game");
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(800, 450));
		frame.add(canvas);
		canvas.setFocusable(true);
		
		frame.setLocation(0, 0);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEnvent){
				System.exit(0);
			}
		});
		
		
		
		canvas.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				keys.remove(e.getKeyCode());
				//thing.add(0);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				keys.add(e.getKeyCode());
				//thing.add(1);
			}
			
			
		});
		
		canvas.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				
				int x = e.getPoint().x;
				int y = e.getPoint().y;
				int w = frame.getWidth();
				int h = frame.getHeight();
				if (Main.state == 0){					
					for (int i = 0; i <= 4; i++) {
						if (x >((8+i)*w/16)  &&  x <((8+i)*w/16 + 2*w/36)){
							if (y > 1*h/36 && y < 1*h/36 + 2*w/36){
								actions.add(i);
							}
						}	
					}
				}
				if (Main.state == 1){
					if ( x > (w/3)-(w/6)  &&  x < (w/3)+(w/6)){
						if (y > (2*h/3)-(h/6) && y < (2*h/3)+(h/6)){
							actions.add(-1);
						}
					}
					if ( x > (2*w/3)-(w/6)  &&  x < (2*w/3)+(w/6)){
						if (y > (2*h/3)-(h/6) && y < (2*h/3)+(h/6)){
							actions.add(-2);
						}
					}
				}
				if (Main.state == 2){
					if ( x > (w/3)-(w/6)  &&  x < (w/3)+(w/6)){
						if (y > (2*h/3)-(h/6) && y < (2*h/3)+(h/6)){
							actions.add(-1);
						}
					}
					if ( x > (2*w/3)-(w/6)  &&  x < (2*w/3)+(w/6)){
						if (y > (2*h/3)-(h/6) && y < (2*h/3)+(h/6)){
							actions.add(-2);
						}
					}
				}
			}
		
		}	
		);
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
					//g.setClip(c.getX()-(c.getWidth()/2), c.getY()-(c.getHeight()/2), c.getWidth(), c.getHeight());
				}
				
				for (Entity prout : content) {
					Bloc b = (Bloc) prout;
					double radian = b.getAngle().getRadian();
					g.rotate(radian, b.getPosX(), b.getPosY());
					g.setColor(Color.BLACK);
					if (b instanceof Cook) g.drawImage(im.getImage("Cook"),(int)b.getPosX(),(int) b.getPosY(), b.getLength(), b.getWidth(),null);
					else if (b instanceof Monster) g.drawImage(im.getImage("Monster"),(int)b.getPosX(),(int) b.getPosY(), b.getLength(), b.getWidth(),null);
					else{
						if (b instanceof Player) g.setColor(Color.RED);
						if (b instanceof Door) g.setColor(new Color(195,143,38));
						g.fill(new Rectangle((int)b.getPosX(),(int) b.getPosY(), b.getLength(), b.getWidth()));
					}
					g.rotate(-radian, b.getPosX(), b.getPosY());
				
				}
				//un-resizing
				if (c != null){
					
					
					//g.setClip(c.getX()-(c.getWidth()/2), c.getY()-(c.getHeight()/2), c.getWidth(), c.getHeight());
					g.translate(+c.getX()-(c.getWidth()/2),+ c.getY()-(c.getHeight()/2));
					g.scale(((double)c.getWidth()/w), ((double)c.getHeight()/h));
				}
				
				HashMap<Integer, Character>  characters= maze.getCharacters();
				for (Map.Entry<Integer, Character> entry : characters.entrySet()) {					
					Character character = entry.getValue();
					if ( character.getLife() < character.getLifeMax()){
						double l =(double)  character.getLife()/character.getLifeMax();
						g.setColor(Color.BLACK);
						int r = (int)(character.getPosX() * (double)w/c.getWidth());
						int t = (int)((character.getPosY()+character.getLongest()) * h/c.getHeight());
						int u =((2*w/16));
						int i = ((w/36));
						g.drawRect((int)((character.getPosX() - c.getX()) * w/c.getWidth() +(w/2))+1 ,(int)((character.getPosY() - c.getY())* h/c.getHeight()+(h/2))-25, ((2*w/16)), ((w/36)));
						g.setColor(Color.PINK);
						g.fillRect((int)((character.getPosX() - c.getX()) * w/c.getWidth() +(w/2))+1,(int)((character.getPosY() - c.getY())* h/c.getHeight()+(h/2))-25 ,((int) ((double)(2*w/16)*l))-1, ((w/36)));
					}
				}
				
				// HUD
				g.setColor(Color.BLACK);
				g.drawString("TPS: " +String.valueOf((int)TPS), 0, 20);
				g.drawString("FPS: " +String.valueOf((int)FPS), 0, 40);
				g.drawString("x: "+c.getX(), 0, 60);
				g.drawString("y: "+c.getY(), 0, 80);
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
					if (loots[i] != null) g.drawImage(im.getImage("Food1"), ((8+i)*w/16), 1*h/36, 2*w/36, 2*w/36, null); 
				}
				
				g.dispose();
			} while (bs.contentsRestored());
			bs.show();
		} while (bs.contentsLost());
	}

	public void loose(){
		do {
			do{
				Graphics2D g = (Graphics2D) bs.getDrawGraphics();
				g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g.setColor(Color.BLACK);
				int w = frame.getWidth();
				int h = frame.getHeight();
				g.fillRect(0, 0, w, h);
				g.setColor(Color.WHITE);
				g.setFont(new Font(g.getFont().getName(), Font.BOLD, 60));
				g.drawString("YOU DIED", (w/2)-(g.getFontMetrics().stringWidth("YOU DIED")/2), h/2);
				g.setFont(new Font(g.getFont().getName(), Font.BOLD, 30));
				g.drawString("RETRY", (w/3)-(g.getFontMetrics().stringWidth("RETRY")/2), 2*h/3);
				g.drawString("QUIT", (2*w/3)-(g.getFontMetrics().stringWidth("RETRY")/2), 2*h/3);
				g.dispose();
			} while (bs.contentsRestored());
			bs.show();
		} while (bs.contentsLost());
	}
	
	public void win(){
		do {
			do{
				Graphics2D g = (Graphics2D) bs.getDrawGraphics();
				g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g.setColor(Color.BLACK);
				int w = frame.getWidth();
				int h = frame.getHeight();
				g.fillRect(0, 0, w, h);
				g.setColor(Color.WHITE);
				g.setFont(new Font(g.getFont().getName(), Font.BOLD, 60));
				g.drawString("YOU WON", (w/2)-(g.getFontMetrics().stringWidth("YOU WON")/2), h/2);
				g.setFont(new Font(g.getFont().getName(), Font.BOLD, 30));
				g.drawString("AGAIN", (w/3)-(g.getFontMetrics().stringWidth("AGAIN")/2), 2*h/3);
				g.drawString("QUIT", (2*w/3)-(g.getFontMetrics().stringWidth("RETRY")/2), 2*h/3);
				g.dispose();
			} while (bs.contentsRestored());
			bs.show();
		} while (bs.contentsLost());
	}

	public void close(){
		System.exit(0);
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
	public HashSet<Integer> getKeyEvents(){
		return keys;
	}
	
	public ArrayList<Integer> getMouseEvents(){
		ArrayList<Integer>  res = (ArrayList<Integer>) actions.clone();
		actions.clear();
		return res;
	}
	
// Setters ----------------------------------------------------
	
}