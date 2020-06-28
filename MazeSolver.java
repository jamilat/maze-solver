/*
 * MazeSolver.java
 *
 * UVic CSC 115, Spring 2017
 *
 * Purpose:
 *   class that contains a single public static method called
 *   "findPath". To involve the method one must have already created
 *   an instance of Maze. The method must return a path through the
 *   maze (if it exists) in the format shown within the Assignment #3
 *   description.
 *
 * Note: You are free to add to this class whatever other methods will
 * help you in writing a solution to A#3 part 2.
 */

public class MazeSolver {

/* Purpose: To take a maze and output the path of the maze.
 * Params:
 *  maze(Maze) - the maze containing on the 1st, 2nd, 3rd lines:
 *              dimensions (row, col)
 *              entrance (row, col)
 *              exit (row, col)
 * Returns:
 *  (String): the path that you must take to get from Start of the maze
 *            to the end of the maze
 * Examples:
 *  If text file containing maze:
 *  Ex 1) is blank or non-exisent then return nothing
 *  Ex 2) does not have a path from start to finish then throws a new mazeNoPathException()
 *  Ex 3) is:
 *    * *
 *    * *
 *    * *
 *    then return the path: "(0,1) (1,1) (2,1)"
 *
 *  Ex 4) has dead ends then backtrack to nearest fork in the path, 
 *    if there is an unvisited spot of the fork then go there
 *    else go to nearest unvisited fork
 *          if no more unvisited forks
 *                then throw new mazeNoPathException()
 */


public static String findPath(Maze maze) {
  boolean[][] unvis = getUnvis(maze);
  Stack<MazeLocation> vis = new StackRefBased<MazeLocation>();
  String result = "";
  MazeLocation entry = maze.getEntry();
  vis.push(entry);
  int numRows = maze.getNumRows();
  int numCols = maze.getNumCols();

           
  try {
    while ((!vis.isEmpty() && !vis.peek().equals(maze.getExit()))) {
        MazeLocation curr = vis.peek();
        unvis[curr.getRow()][curr.getCol()] = true;

        if (open(getDown(curr), unvis, numRows, numCols)) {
              vis.push(getDown(curr));
        }
        else if (open(getLeft(curr), unvis, numRows, numCols)) {
              vis.push(getLeft(curr));
        }
        else if (open(getRight(curr), unvis, numRows, numCols)) {
              vis.push(getRight(curr));
        }
        else if (open(getUp(curr), unvis, numRows, numCols)) {
              vis.push(getUp(curr));
        }

        else {
              vis.pop();
        }
    }
  }
  catch (StackEmptyException e) {
    System.out.println("There is no path from start to finish");
  }
  Stack<MazeLocation> newS = new StackRefBased<MazeLocation>();
  while (vis.size() > 0) {
      try {
              newS.push(vis.pop());
      }
      catch (StackEmptyException e) {
      }
  }
  while (newS.size() > 0) {
      try {
              if (newS.size() == 1)
                result += newS.pop();
              result += newS.pop() + " ";
       }
       catch (StackEmptyException e) {
              //System.out.println("Thrown: " + e);
       }
  }
  //System.out.println("end result: " + result);
  return result;
}
public static Boolean open(MazeLocation pos, boolean[][] unvis, int numRows, int numCols) {
  int row = pos.getRow();
  int col = pos.getCol();
  if (row < 0 || col < 0 || row > numRows-1 || col > numCols-1) {
    return false;
  }
  if (unvis[row][col])
    return false;
  return true;
}
 /*Purpose:
  * Returns the MazeLocation for the tile below the current value
  * passed in as an argument
  *
  */
public static MazeLocation getDown(MazeLocation curr) {
  int downRow = curr.getRow() + 1;
  int downCol = curr.getCol();
  return new MazeLocation(downRow,downCol,false);
}
/*Purpose:
 * Returns the MazeLocation for the tile to the right of the current value
 * passed in as an argument
 *
 */
public static MazeLocation getRight(MazeLocation curr) {
  int rightRow = curr.getRow();
  int rightCol = curr.getCol() + 1;
 return new MazeLocation(rightRow,rightCol,false);
}
/*Purpose:
 * Returns the MazeLocation for the tile to the left of the current value
 * passed in as an argument
 *
 */
public static MazeLocation getLeft(MazeLocation curr) {
  int leftRow = curr.getRow();
  int leftCol = curr.getCol() - 1;
 return new MazeLocation(leftRow,leftCol,false);
}
/*Purpose:
 * Returns the MazeLocation for the tile above the current value
 * passed in as an argument
 *
 */
public static MazeLocation getUp(MazeLocation curr) {
  int upRow = curr.getRow() - 1;
  int upCol = curr.getCol();
 return new MazeLocation(upRow,upCol,false);
}
/*Purpose:
 *  Returns a 2D-boolean array, a of locations that are not yet visited on the map;
 *  If the area in the maze is a wall then that location is marked as visited
 *  since you cannot visit that location; thus, is TRUE;
 *  Else if the area in the maze is NOT a wall then that location is marked
 *  as unvisited; thus, is FALSE;
 *Pre-conditions:
 *  None
 *Params:
 *  maze(Maze)
 *Returns:
 *  (boolean[row][col]) - a boolean 2D-array
 */

public static boolean[][] getUnvis(Maze maze) {
  int numRows = maze.getNumRows();
  int numCols = maze.getNumCols();
  boolean[][] a = new boolean[numRows][numCols];
  for (int row = 0; row < numRows; row++) {
    for (int col = 0; col < numCols; col++) {
      if (maze.isWall(row, col)) {
        a[row][col] = true;
      }
      else {
        a[row][col] = false;
      }
    }
  }
  return a;
}




}
