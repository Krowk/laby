import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import entities.Entity;
import entities.Wall;

import java.io.BufferedReader;

public class Maze {
	private int length;
	private int width;
	private ArrayList<Entity> maze;
	public static ArrayList<Maze> mazes = new ArrayList<Maze>();
	
	
	public Maze(int length, int width){
		this.length	= length;
		this.width	= width;
		this.maze = new ArrayList<Entity>();
		mazes.add(this);
	}
	
	public void addEntityToMaze(Entity entity){
		this.maze.add(entity);
	}
	
	public void showAll(){
		for (int i = 0; i < maze.size(); i++) {
			maze.get(i).show();
			System.out.println("");
		}
	}
	
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
	 * 
	 * @param fileName nom du fichier
	 */
	public static void createMaze(String fileName){
		BufferedReader c;
		System.out.println(fileName);
		try {
			String currentLine;
			c =new BufferedReader( new FileReader(fileName));
			int tempLength = 0;
			int tempWidth = 0;
			currentLine = c.readLine();		
			tempLength = Integer.parseInt(currentLine.substring(7));
			currentLine = c.readLine();
			tempLength = Integer.parseInt(currentLine.substring(6));
			Maze maze = new Maze(tempLength, tempWidth);
			String type = "";
			ArrayList<Integer> buff = new ArrayList<Integer>(); 
			while ((currentLine = c.readLine()) != null){
				if (currentLine.equals("wall") || currentLine.equals("door") || currentLine.equals("stair")
						|| currentLine.equals("elevator") || currentLine.equals("key") || currentLine.equals("character")){
					type = currentLine;
				}
				else{
					switch (type){
						case "wall":
							buff = lineToInt(currentLine);
							maze.addEntityToMaze(new Wall(buff.get(0),buff.get(1),buff.get(2),buff.get(3),buff.get(4)));
							break;
						case "door":
							break;
						default:
					}
					
				}
					
			}	
			
		} catch (IOException e) {
			System.out.println("erreur de lecture de fichier "+ fileName);
			e.printStackTrace();
		}
	}
}
