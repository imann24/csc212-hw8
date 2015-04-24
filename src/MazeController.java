import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MazeController implements MouseListener {
	MazeGUI g;
	
	public MazeController (MazeGUI g) {
		this.g = g;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
//		mouseClicked should :
//		hide/show the solution
//		change the button text
//		call GUIMazeView repaint method. (repaint it not a method you wrote. It is part of JComponent and it causes the paintComponent method you wrote to get called).

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
