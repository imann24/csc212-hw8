import java.io.File;
import java.io.FileNotFoundException;
import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class MazeModel {
	private int height;
	private int width;
	private String title;
	private List<MazeLocation> solution = new ArrayList<MazeLocation>();
	private MazeLocation[][] maze;
	public ArrayList<MazeLocation> test;
	public MazeModel(String filename) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(filename));
		title = scanner.nextLine();
		width = Integer.parseInt(scanner.nextLine());
		height = Integer.parseInt(scanner.nextLine());
		maze = new MazeLocation[width][height];
		for (int y = 0; y < height; y++) {
			String s = scanner.nextLine();
			for (int x = 0; x < width; x++) {
				if (s.charAt(x) == ' ' || s.charAt(x) == 'S' || s.charAt(x) == 'G') {
					maze[x][y] = new MazeLocation(x, y);
					if (s.charAt(x) == 'S')
						solution.add(0, maze[x][y]);
					else if (s.charAt(x) == 'G')
						solution.add(maze[x][y]);
				}
			}
		}
		System.out.println(maze[1][2]);
		System.out.println(validSpot(1, 2));
		solveMaze();
	}
	
	public int getHeight () {
		return height;
	}
	
	public int getWidth () {
		return width;
	}
	
	public MazeLocation getGoalLocation () {
		return solution.get(solution.size() - 1);
	}
	
	public MazeLocation getStartLocation () {
		return solution.get(0);
	}
	
	public String getTitle () {
		return title;
	}
	
	public boolean isSpace(MazeLocation location) {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (location.equals(maze[x][y]))
					return true;
			}
		}
		return false;
	}
	
	public List<MazeLocation> getSolution () {
		return solution;
	}
	
	private void solveMaze () {
		ArrayList<MazeLocation> path = new ArrayList<MazeLocation>();
		path.add(getStartLocation());
		//recursively calls step until there is path from start to finish
		path = search(0, path, new LinkedList<MazeLocation>());
		solution = condenseSolution(path);
	}

	
	private ArrayList<MazeLocation> condenseSolution (ArrayList<MazeLocation> path) {
		//if a location appears twice in the solution, remove all of the locations between its two appearances, and one of its occurences
		test = new ArrayList(path);
		ArrayList<MazeLocation> shortcuts = new ArrayList<MazeLocation>();
		for (MazeLocation m1 : path) {
			MazeLocation second = null;
			for (MazeLocation m2: path) {
				if (m2 != m1 && shortCut(m1, m2, path)) {
					second = m2;
				}
			}
			if (second != null) {
				shortcuts.add(m1);
				shortcuts.add(second);
			}
		}
		System.out.println(shortcuts);
		
		for (int i = 0; i < shortcuts.size(); i+=2) {
			if (path.contains(shortcuts.get(i)) && path.contains(shortcuts.get(i+1))) {
				for (int j = path.indexOf(shortcuts.get(i))+1; j < path.indexOf(shortcuts.get(i+1)); j++)
					path.remove(j); //replace remove with contains for debugging
			}
		}
		return path;
	}
	
	private ArrayList<MazeLocation> search (int count, ArrayList<MazeLocation> path, LinkedList<MazeLocation> frontier) {
		if (!frontier.isEmpty())
			path.add(frontier.removeFirst());
		count++;
		if (!path.contains(getGoalLocation()) && count < 500) {
			MazeLocation current = path.get(path.size()-1);
			List<MazeLocation> children = getChildren(current.getColumn(), current.getRow());
			children = filterChildren(path, children);
			for (MazeLocation c: children)
				frontier.addLast(c);
			System.out.println(count);
			return search(count, path, frontier);
		} else return path;
		
		
	}
	
	private boolean validSpot (int x, int y) {
		if (x >= width || x < 0)
			return false;
		if (y >= height || y < 0)
			return false;
		if (maze[x][y] == null)
			return false;
		return true;
	}
	
	private List<MazeLocation> filterChildren (List<MazeLocation> path, List<MazeLocation> children)  {
		for (MazeLocation c: path) {
			if (path.contains(c))
				children.remove(c);
		}
		return children;
	}
	

	private List<MazeLocation> getChildren (int x, int y) {
		List<MazeLocation> children = new ArrayList<MazeLocation>();
		if (validSpot(x, y+1))
			children.add(maze[x][y+1]);
		if (validSpot(x+1, y))
			children.add(maze[x+1][y]);
		if (validSpot(x, y-1))
			children.add(maze[x][y-1]);
		if (validSpot(x-1, y))
			children.add(maze[x-1][y]);
		return children;
	}
	
	private boolean shortCut (MazeLocation l1, MazeLocation l2, ArrayList<MazeLocation>path) {
		if (Math.abs(l1.getColumn()-l2.getColumn()) + Math.abs(l1.getRow()-l2.getRow()) <= 1 && Math.abs(path.indexOf(l1) - path.indexOf(l2)) > 1) {
			System.out.println(l1 + " --> " + l2);
			return true;
		}
		else return false;
	}
	
	//	public boolean isSpace(MazeLocation location) returns true if the location is a space and returns false if the location is a wall. (Note: The start and goal locations are spaces.)
	//	public List<MazeLocation> getSolution() returns a list of MazeLocations that form a path from the start to the goal. It no solution exits getSolution should return null.
}
