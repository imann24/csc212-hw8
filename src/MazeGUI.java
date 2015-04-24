import java.awt.BorderLayout;
import java.awt.Container;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MazeGUI {
	private GUIMazeView mazeView;
	private JButton solutionToggle;
	

	public MazeGUI(String filename, int w, int h) throws FileNotFoundException {
		mazeView = new GUIMazeView(new MazeModel(filename), w, h);
	}

	public void createAndShowGUI() {
		// Make sure we have nice window decorations.
		JFrame.setDefaultLookAndFeelDecorated(true);

		// Create and set up the window.
		JFrame frame = new JFrame("Sample GUI Application");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add components
		createComponents(frame.getContentPane());

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public GUIMazeView getMazeView() {
		return mazeView;
	}
	public JButton getSolutionToggle() {
		return solutionToggle;
	}
	
	public void createComponents(Container pane) {
		pane.setLayout(new BorderLayout());
		pane.add(mazeView, BorderLayout.CENTER);
		solutionToggle = new JButton("Show Solution");
		solutionToggle.addMouseListener(new MazeController(this));
		pane.add(solutionToggle, BorderLayout.SOUTH);
		
	}


	public static void main(String[] args) {
		try {
			final String filename = args[0];
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				// Schedule a job for the event-dispatching thread:
				// creating and showing this application's GUI.
				public void run() {
					try {
						new MazeGUI(filename, 400, 500).createAndShowGUI();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("You must specify an filename as an argument as in:\n"
					+ "   java MazeGUI maze.txt");
		}

	}

}