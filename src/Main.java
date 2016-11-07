import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Maze.createMaze("src/test.txt");
		Maze.mazes.get(0).showAll();
	}
	
	
}
