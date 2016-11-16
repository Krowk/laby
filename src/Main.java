import java.util.ArrayList;

import entities.Line;
import entities.Wall;
import physics.Collision;

public class Main {

	public static void main(String[] args) {
		// Maze.createMaze("src/test.txt");
		// Maze.mazes.get(0).showAll();
		/*
		 * Wall a = new Wall(0, 0, 3, 3, 0); Wall b = new Wall(0, 0, 3, 3, 0);
		 * System.out.println(Collision.BlocEntity(a, b)); b =new Wall(1, 0, 3,
		 * 3, 0); System.out.println(Collision.BlocEntity(a, b)); b =new Wall(2,
		 * 0, 3, 3, 0); System.out.println(Collision.BlocEntity(a, b)); b =new
		 * Wall(3, 0, 3, 3, 0); System.out.println(Collision.BlocEntity(a, b));
		 * b= new Wall(0, 1, 3, 3, 0);
		 * System.out.println(Collision.BlocEntity(a, b)); b =new Wall(1, 1, 3,
		 * 3, 0); System.out.println(Collision.BlocEntity(a, b)); b =new Wall(2,
		 * 1, 3, 3, 0); System.out.println(Collision.BlocEntity(a, b)); b =new
		 * Wall(3, 1, 3, 3, 0); System.out.println(Collision.BlocEntity(a, b));
		 * b= new Wall(0, 2, 3, 3, 0);
		 * System.out.println(Collision.BlocEntity(a, b)); b =new Wall(1, 2, 3,
		 * 3, 0); System.out.println(Collision.BlocEntity(a, b)); b =new Wall(2,
		 * 2, 3, 3, 0); System.out.println(Collision.BlocEntity(a, b)); b =new
		 * Wall(3, 2, 3, 3, 0); System.out.println(Collision.BlocEntity(a, b));
		 * b= new Wall(0, 3, 3, 3, 0);
		 * System.out.println(Collision.BlocEntity(a, b)); b =new Wall(1, 3, 3,
		 * 3, 0); System.out.println(Collision.BlocEntity(a, b)); b =new Wall(2,
		 * 3, 3, 3, 0); System.out.println(Collision.BlocEntity(a, b)); b =new
		 * Wall(3, 3, 3, 3, 0);
		 */

		/*
		 * Line l = new Line(0,0,13,0); Wall p = new Wall(0,11,0,0,0);
		 * System.out.println(Collision.LineEntity(l, p));
		 */

		Line l = new Line(0, 1, 0, 0);
		Line m = new Line(0, 0, 1, 90);
		Collision.LineLine(m,l);
	}

}
