package window;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import com.sun.javafx.scene.input.KeyCodeMap;

import Main.Maze;
import entities.Bloc;
import entities.Entity;
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
	
	private ArrayList<Integer> keys = new ArrayList<Integer>();
	private ArrayList<Integer> thing = new ArrayList<Integer>();

	
// Constructors -----------------------------------------------
	/**
	 * create an AwtManager, which open a window and set it up
	 */
	public AwtManager(){
		frame = new Frame("Game");
		
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(600, 650));
		frame.add(canvas);
		canvas.setFocusable(false);
		
		frame.setLocation(600, 50);
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
				// #antialiasing #ezpz
				g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, getWidth(), getHeight());
				
				g.setColor(Color.BLACK);
				g.drawString("TPS: " +String.valueOf((int)TPS), 0, 20);
				g.drawString("FPS: " +String.valueOf((int)FPS), 0, 100);
				
				for (Entity prout : content) {
					Bloc b = (Bloc) prout;
					double radian = b.getAngle().getRadian();
					g.rotate(radian, b.getPosX(), b.getPosY());
					g.setColor(Color.black);						
					g.fill(new Rectangle(b.getPosX(), b.getPosY(), b.getLength(), b.getWidth()));
					g.rotate(-radian, b.getPosX(), b.getPosY());
					
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