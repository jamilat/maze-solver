/**
 * MazeLocation
 *
 * Created for UVic CSC 115 Assignment Three (Spring 2017)
 */

/**
 * MazeLocation objects contain a row & column value indicating
 * a cell position in a 2D maze.
 */
public class MazeLocation {
    private int row;
    private int column;
    private boolean isWall;

    MazeLocation(int row, int column) {
        this.row = row;
        this.column = column;
        this.isWall = false;
    }

    MazeLocation(int row, int column, boolean isWall) {
        this.row = row;
        this.column = column;
        this.isWall = isWall;
    }

    public String toString() {
        return "(" + this.row + "," + this.column + ")";
    }

    public boolean equals(MazeLocation loc) {
        return (this.row == loc.row && this.column == loc.column);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return column;
    }

    public boolean isWall() {
        return isWall;
    }
}
