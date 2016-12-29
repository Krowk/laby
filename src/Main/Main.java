package Main;

import java.util.ArrayList;

import Drawing.AwtManager;


public class Main {
// Constantes -----------------------------------------------------
	private static int FPS_target	= 32;
	private static int TPS_target	= 32;
	private static int FrameRate	= 1000000000 / FPS_target;
	private static int TickRate		= 1000000000 / TPS_target;

	public static void main(String[] args) {
		AwtManager awtManager = new AwtManager();
		
		Maze maze = Maze.createMaze("src/test.txt");
		if (maze != null){
			// FPS calculations ---------------------------------------
			long currentTime;
			long lastTime = System.nanoTime();
			long lastTick = lastTime;
			long lastFrame = lastTime;
			long elapsedTime1 = 0;
			long elapsedTime2 = 0;
			long elapsedTimeSinceFrame;
			long elapsedTimeSinceTick;
			int actualFPS = 32;
			int actualTPS = 32;
			double FPS = 0;
			double TPS = 0;
			//int[] tab = new int[8];
			ArrayList<Integer> FPS_tab = new ArrayList<>(32);
			for (int i = 0; i < 32; i++) {
				FPS_tab.add(0);
			}
			ArrayList<Integer> TPS_tab = new ArrayList<>(32);
			for (int i = 0; i < 32; i++) {
				TPS_tab.add(0);
			}
			// Main loop ----------------------------------------------
			while (true){
				
				currentTime = System.nanoTime();
				elapsedTime1 += currentTime - lastTime;
				elapsedTime2 += currentTime - lastTime;
				lastTime = currentTime; 
				
				
				while (elapsedTime2 >= FrameRate) {
					actualFPS--;
					elapsedTime2 -= TickRate;
				}
				while (elapsedTime1 >= TickRate) {
					actualTPS--;
					elapsedTime1 -= TickRate;
				}
				
				
				
				elapsedTimeSinceTick = System.nanoTime() - lastTick;
				if (elapsedTimeSinceTick >= TickRate ) {
					actualTPS++;
					TPS_tab.add(actualTPS);
					TPS_tab.remove(0);
					//update()
					
					lastTick = System.nanoTime();
					actualTPS =0;
				}
				
				
				
				elapsedTimeSinceFrame = System.nanoTime() - lastFrame;
				if (elapsedTimeSinceFrame >= FrameRate){
					actualFPS++;
					FPS_tab.add(actualFPS);
					FPS_tab.remove(0);
					FPS = 0;
					TPS = 0;
					for (int i : FPS_tab) {
						FPS+= i;
					}
					for (int i : TPS_tab) {
						TPS+= i;
					}
					FPS /= 32;
					TPS /= 32;
					awtManager.render(maze, (32 / (-(FPS-1))), 32 / (-(TPS-1)) );
					lastFrame = System.nanoTime();
					actualFPS = 0;
				}
				
			}
			
		}
		
		
		
	}

}
