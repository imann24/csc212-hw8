public class MazeLocation {
	private int column;
	private int row;
	
	public MazeLocation(int col, int row) {
		this.column = col;
		this.row = row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public int getRow() {
		return row;
	}
	
	public boolean equals(Object o) {
		if(o instanceof MazeLocation) {
			MazeLocation ml = (MazeLocation) o;
			return ml.column == column && ml.row == row;
		} else {
			return false;
		}
	}
	
	public String toString() {
		return "(" + column + ", " + row + ")";
	}

}
