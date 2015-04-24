import java.io.FileNotFoundException;


public class TestMaze {
	public static void main (String ars[]) throws FileNotFoundException {
		MazeModel m = new MazeModel("file.dat");
		System.out.println(m.getTitle());
		System.out.println(m.getWidth());
		System.out.println(m.getHeight());
		System.out.println(m.getStartLocation());
		System.out.println(m.getGoalLocation());
		System.out.println(m.test);
		System.out.println(m.getSolution());
	}
}
