package Drawing;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import Main.Maze;
import entities.Bloc;
import entities.Entity;

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

// Constructors -----------------------------------------------
	/**
	 * create an AwtManager, which open a window and set it up
	 */
	public AwtManager(){
		frame = new Frame("Game");
		
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(800, 600));
		frame.add(canvas);
		
		
		frame.setLocation(100, 100);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEnvent){
				System.exit(0);
			}
		});
		
		
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
				g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, getWidth(), getHeight());
				
				g.setColor(Color.BLACK);
				g.drawString("TPS: " +String.valueOf(TPS), 0, 20);
				g.drawString("FPS: " +String.valueOf(FPS), 0, 100);
				
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
	

// Setters ----------------------------------------------------
	
}