package Main;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import entities.*;
import entities.Character;

import java.io.BufferedReader;

public class Maze {
// Fields -----------------------------------------------------
	/**
	 * The width of the maze
	 */
	private int width;
	/**
	 * The length of the of the maze
	 */
	private int length;
	/**
	 * The list of all the Entity in the maze
	 */
	private ArrayList<Entity> maze;
	/**
	 * The list of all the mazes 
	 */
	public static ArrayList<Maze> mazes = new ArrayList<Maze>();
	public static ArrayList<Bloc> blocs = new ArrayList<>();
	public static HashMap<Integer, Character> characters;
	public static Maze activeMaze;
	
// Constructors -----------------------------------------------
	/**
	 * Create a maze of width width, and of length length, then add it to the Maze.mazes
	 * @param width  the width of the maze: int
	 * @param length the length of the maze: int
	 */
	public Maze(int width, int length){
		this.width	= width;
		this.length	= length;
		this.maze = new ArrayList<Entity>();
		mazes.add(this);
	}
	
// Dynamic methods --------------------------------------------	
	/**
	 * Add an Entity the the Maze
	 * @param entity the Entity to be added: Entity
	 */
	public void addEntityToMaze(Entity entity){
		this.maze.add(entity);
		if (entity instanceof Bloc){
			blocs.add((Bloc)entity);
		}
	}

	
	/**
	 * Call the methods show() of all entities in the maze
	 */
	public void showAll(){
		for (int i = 0; i < maze.size(); i++) {
			maze.get(i).show();
			System.out.println("");
		}
	}
	
	/**
	 * get all updatable item of the maze
	 * @return an arraylist of all runnable
	 */
	public ArrayList<Updatable> getUpdatable(){
		ArrayList<Updatable> updatables = new ArrayList<>();
		for (Entity e : maze) {
			if (e instanceof Updatable){
				updatables.add((Updatable) e);
			}
		}
		return updatables;
	}
// Static methods ---------------------------------------------	
	/**
	 * extract the values given in a String
	 * ex: "1\t2\t3" -> {1,2,3}
	 * @param str the String contening values 
	 * @return an arrayList with all the values
	 */
	public static ArrayList<Integer> lineToInt(String str){
		ArrayList<Integer> x = new ArrayList<Integer>();
		int last = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '\t' ){
				x.add(Integer.parseInt(str.substring(last, i)));
				last = i+1;
			}
			else if (i == str.length()-1){
				x.add(Integer.parseInt(str.substring(last, i+1)));
			}
		}
		return x;
	}
	
	// A optimiser pour les espaces impromptus
	/**
	 * Create a maze from a File
	 * The file should be formated like that (replace int by a real number):
	 * width	int\n
	 * length	int\n
	 * 
	 * wall\n
	 * int	int	int	int\n
	 * int	int	int	int\n
	 * ..\n
	 * 
	 * character\n
	 * int	int	int	int\n
	 * @param fileName nom du fichier
	 */
	public static Maze createMaze(String fileName){
		BufferedReader c;
		
		try {
			String currentLine;
			c =new BufferedReader( new FileReader(fileName));
			int tempLength = 0;
			int tempWidth = 0;
			currentLine = c.readLine();		
			tempWidth = Integer.parseInt(currentLine.substring(6));
			currentLine = c.readLine();
			tempLength = Integer.parseInt(currentLine.substring(7));
			Maze maze = new Maze(tempWidth, tempLength);
			String type = "";
			ArrayList<Integer> buff = new ArrayList<Integer>(); 
			while ((currentLine = c.readLine()) != null){
				if (currentLine.equals("wall") || currentLine.equals("door") || currentLine.equals("stair")
						|| currentLine.equals("elevator") || currentLine.equals("key") || currentLine.equals("character")
						|| currentLine.equals("player") || currentLine.equals("cook") || currentLine.equals("medic")
						|| currentLine.equals("monster") || currentLine.equals("food") || currentLine.equals("safe")
						|| currentLine.equals("secretDoor")){
					type = currentLine;
				}
				else{
					buff = lineToInt(currentLine);
					switch (type){
						case "wall":
							maze.addEntityToMaze(new Wall(buff.get(0),buff.get(1),buff.get(2),buff.get(3),buff.get(4)));
							break;
						case "door":
							maze.addEntityToMaze(Door.createDoor(buff.get(0),buff.get(1),buff.get(2),false));
							break;
						case "character":
							maze.addEntityToMaze(new entities.Character(buff.get(0), buff.get(1), buff.get(2), buff.get(3), buff.get(4), buff.get(5)));
							break;
						case "player":
							maze.addEntityToMaze(new Player(buff.get(0), buff.get(1), buff.get(2), buff.get(3), buff.get(4), buff.get(5), buff.get(6)));
							break;
						case "cook":
							maze.addEntityToMaze(new Cook(buff.get(0), buff.get(1), buff.get(2), buff.get(3), buff.get(4), buff.get(5),buff.get(6), null));
							break;
						case "medic":
							maze.addEntityToMaze(new Medic(buff.get(0), buff.get(1), buff.get(2), buff.get(3), buff.get(4), buff.get(5)));
							break;
						case "monster":
							maze.addEntityToMaze(new Monster(buff.get(0), buff.get(1), buff.get(2), buff.get(3), buff.get(4), buff.get(5)));
							break;
						case "food":
							maze.addEntityToMaze(new Food(buff.get(0), buff.get(1), buff.get(2), buff.get(3), buff.get(4), buff.get(5), true, buff.get(6)));
							break;
						case "safe":
							maze.addEntityToMaze(new Safe(buff.get(0),buff.get(1),buff.get(2),buff.get(3),buff.get(4), true, false));
							break;
						case "key":
							maze.addEntityToMaze(new Key(buff.get(0), buff.get(1), buff.get(2), buff.get(3), true, buff.get(4)));
							break;
						case "secretDoor":
							maze.addEntityToMaze(SecretDoor.createDoor(buff.get(0), buff.get(1), buff.get(2), buff.get(3), buff.get(4)));
							break;
						default:
					}
					
				}
					
			}
			c.close();
			maze.setCharacters(Character.getCharacters());
			return maze;
		} catch (IOException e) {
			System.out.println("erreur de lecture de fichier "+ fileName);
			e.printStackTrace();
		}
		return null;
		
	}

	public static void deleteEntity(Entity e){
		activeMaze.maze.remove(e);
	}
	
	public static void deleteAll(){
		Wall.walls.clear();
		characters.clear();
		Door.doors.clear();
		Elevator.elevators.clear();
	}
// Getters -----------------------------------------------------	
	/**
	 * 
	 * @return The arraylist with all Entity in the maze
	 */
	public ArrayList<Entity> getMazeContent(){
		return maze;
	}
	
	public HashMap<Integer, Character> getCharacters(){
		return characters;
	}

	public int getWidth(){ return this.width; }
	public int getLength(){ return this.length;}
// Setters -----------------------------------------------------
	public void setCharacters(HashMap<Integer, Character> characters){
		this.characters = characters;
	}
}
