/*
 * a4tester.java
 *
 * Tester for UVic CSC 115 A#4, Spring 2019
 *
 *
 */

import java.io.*;

public class a4tester
{
    private static int     testCount = 0;
    private static boolean testArraySolution = false;
    private static int     stressTestSize = 200;
    private static String  testDir = "tests/";


    public static void displayResults (boolean passed)
	{
        /* Using some ideas from http://bit.ly/2jCNQ1L that helps
         * identify the line number corresponding to some run-time
         * code point. Exceptions are needed here (i.e., we need
         * to throw an exception at the code point of interest).
         */

        if (passed) {
            System.out.println ("Passed test: " + testCount);
        } else {
            System.out.println ("Failed test: " + testCount
                + " at line " +
                Thread.currentThread().getStackTrace()[2].getLineNumber()
            );
            System.exit(1);
		}
		testCount++;
	}


    public static void performSanityCheck() {
        File f = new File(testDir);

        if (!f.exists() || !f.isDirectory()) {
            System.out.println(
                "Before testing can proceed, please create a subdirectory\n"
              + "names tests/ in which are copied the test files (those\n"
              + "beginning with 'maze...') for assignment #3."
            );
            displayResults(false);
        }
    }


    public static void testBasicStack() {
        System.out.println("testBasicStack: start");

        Stack<Integer> s;
        int subtestSize;
        boolean subtestResult;

        s = new StackRefBased<Integer>();
        displayResults(s.isEmpty());
        displayResults(s.size() == 0);

        s = new StackRefBased<Integer>();
        s.push(22);
        displayResults(!s.isEmpty());
        displayResults(s.size() == 1);
  //System.out.println("Before try: ");
        try {
            s = new StackRefBased<Integer>();
            subtestSize = 10;
            for (int i = 0; i < subtestSize; i++) {
                s.push(i);
            }
            displayResults(!s.isEmpty());
            displayResults(s.size() == subtestSize);
            subtestResult = true;
            for (int i = subtestSize - 1; i >= 0; i--) {
                Integer ii = s.pop();
                subtestResult = subtestResult && (ii == i);
            }
            displayResults(subtestResult);
            displayResults(s.isEmpty());
            displayResults(s.size() == 0);
        }
        catch (StackEmptyException see) {
            displayResults(false);
        }
//System.out.println("before try: ");
        try {
            s = new StackRefBased<Integer>();
            //System.out.println("before push: ");
            s.push(33);
            //System.out.println("before peek: ");
            s.peek();
            //System.out.println("before isEmpty: ");
            displayResults(!s.isEmpty());
            //System.out.println("before size: ");
            displayResults(s.size() == 1);
        }
        catch (StackEmptyException see) {
            displayResults(false);
        }
//System.out.println("before try: ");
        try {
            s = new StackRefBased<Integer>();
            s.pop();
            displayResults(false);
            s.peek();
            displayResults(false);
        }
        catch (StackEmptyException see) {
          //System.out.println("in catch StackEmptyException: ");
            displayResults(true);
        }

//System.out.println("before try: ");
        try {
            s = new StackRefBased<Integer>();
            subtestSize = 10;
            for (int i = 0; i < subtestSize; i++) {
                s.push(i);
            }
            displayResults(!s.isEmpty());
            displayResults(s.size() == subtestSize);
            subtestResult = true;
            for (int i = subtestSize - 1; i >= 0; i--) {
                Integer ii = s.peek();
                subtestResult = subtestResult && (ii == (subtestSize - 1));
            }
            displayResults(subtestResult);
            displayResults(!s.isEmpty());
            displayResults(s.size() == subtestSize);
        }
        catch (StackEmptyException see) {
            displayResults(false);
        }

        s = new StackRefBased<Integer>();
        subtestSize = 10;
        for (int i = 0; i < subtestSize; i++) {
            s.push(i);
        }
        s.makeEmpty();
        displayResults(s.isEmpty());

        s = new StackRefBased<Integer>();
        subtestSize = 10;
        for (int i = 0; i < subtestSize; i++) {
            s.push(i);
        }
        s.makeEmpty();
        for (int i = 0; i < subtestSize; i++) {
            s.push(i);
        }
        displayResults(!s.isEmpty());
        displayResults(s.size() == subtestSize);
//System.out.println("Before try: ");
        try {
            s = new StackRefBased<Integer>();
            subtestSize = 10;
            for (int i = 0; i < subtestSize; i++) {
                s.push(i);
            }
            s.makeEmpty();
            for (int i = 0; i < subtestSize; i++) {
                s.push(i);
            }
            displayResults(s.peek() == (subtestSize - 1));
            s.pop();
            displayResults(s.peek() == (subtestSize - 2));
        }
        catch (StackEmptyException see) {
            displayResults(false);
        }

        System.out.println("testBasicStack: end");
        System.out.println();
    }


    public static void testMazeLocationStack() {
        System.out.println("testMazeLocationStack: start");

        StackRefBased<MazeLocation> s;
        MazeLocation mp1, mp2;

        s = new StackRefBased<MazeLocation>();
        displayResults(s.isEmpty());

        s = new StackRefBased<MazeLocation>();
        mp1 = new MazeLocation(-100, 42);
        s.push(mp1);
        displayResults(!s.isEmpty());
        displayResults(s.size() == 1);

        try {
            s = new StackRefBased<MazeLocation>();
            mp1 = new MazeLocation(99, 0);
            s.push(mp1);
            mp2 = s.pop();
            displayResults(mp2.equals(new MazeLocation(99, 0)));
            displayResults(!mp2.equals(new MazeLocation(-100, 42)));
        }
        catch (StackEmptyException see) {
            displayResults(false);
        }

        System.out.println("testMazeLocationStack: end");
        System.out.println();
    }


    public static void testMazeSolver() {
        System.out.println("testMazeSolver: start");

        performSanityCheck();

        Maze maze;
        String path;

        maze = new Maze(testDir + "maze00.txt");
        path = MazeSolver.findPath(maze);//should find the path where you can go
        //System.out.println("Expected path: (0,1) (1,1) (2,1) Actual path: " + path + ".");

        displayResults(path.equals("(0,1) (1,1) (2,1)"));

        maze = new Maze(testDir + "maze01.txt");
        path = MazeSolver.findPath(maze);
        displayResults(path.equals(
            "(0,1) (1,1) (1,2) (1,3) (1,4) (1,5) (1,6) (2,6)"
        ));

        maze = new Maze(testDir + "maze02.txt");
        path = MazeSolver.findPath(maze);
        displayResults(path.equals(
            "(0,1) (1,1) (2,1) (3,1) (3,2) (3,3) (3,4) (3,5) "
          + "(3,6) (4,6)"
        ));

        maze = new Maze(testDir + "maze03.txt");
        path = MazeSolver.findPath(maze);

        displayResults(path.equals(""));

        maze = new Maze(testDir + "maze04.txt");
        path = MazeSolver.findPath(maze);
        displayResults(path.equals(""));

        maze = new Maze(testDir + "maze05.txt");
        path = MazeSolver.findPath(maze);
        displayResults(path.equals(""));

        maze = new Maze(testDir + "maze06.txt");
        path = MazeSolver.findPath(maze);
        displayResults(path.equals(
            "(0,1) (1,1) (1,2) (1,3) (1,4) (1,5) (2,5) "
          + "(3,5) (3,6) (3,7) (4,7) (5,7) (5,8) (5,9) "
          + "(6,9) (7,9) (8,9)"
        ));

        maze = new Maze(testDir + "maze07.txt");
        path = MazeSolver.findPath(maze);
        displayResults(path.equals(
            "(0,1) (1,1) (1,2) (1,3) (1,4) (1,5) (1,6) "
          + "(1,7) (1,8) (1,9) (1,10) (1,11) (1,12) (1,13) "
          + "(2,13) (3,13) (4,13) (5,13) (6,13) (7,13) (8,13) "
          + "(9,13) (10,13) (11,13) (12,13) (13,13) (14,13)"
        ));

        maze = new Maze(testDir + "maze08.txt");
        path = MazeSolver.findPath(maze);
        //System.out.println("Actual path: " + path);
        displayResults(path.equals(
              "(0,1) (1,1) (2,1) (3,1) (4,1) (5,1) "
            + "(6,1) (7,1) (8,1) (9,1) (10,1) (11,1) "
            + "(11,2) (11,3) (12,3) (13,3) (13,4) "
            + "(13,5) (14,5) (15,5) (16,5) (17,5) "
            + "(17,4) (17,3) (17,2) (17,1) (18,1) "
            + "(19,1) (19,2) (19,3) (19,4) (19,5) "
            + "(19,6) (19,7) (18,7) (17,7) (17,8) "
            + "(17,9) (18,9) (19,9) (19,10) (19,11) "
            + "(19,12) (19,13) (18,13) (17,13) (17,12) "
            + "(17,11) (16,11) (15,11) (14,11) (13,11) "
            + "(13,10) (13,9) (14,9) (15,9) (15,8) "
            + "(15,7) (14,7) (13,7) (12,7) (11,7) "
            + "(10,7) (9,7) (9,8) (9,9) (8,9) (7,9) "
            + "(7,8) (7,7) (6,7) (5,7) (5,8) (5,9) "
            + "(5,10) (5,11) (6,11) (7,11) (8,11) "
            + "(9,11) (10,11) (11,11) (11,12) (11,13) "
            + "(11,14) (11,15) (11,16) (11,17) (12,17) "
            + "(13,17) (14,17) (15,17) (15,18) (15,19) "
            + "(16,19) (17,19) (17,18) (17,17) (17,16) "
            + "(17,15) (18,15) (19,15) (19,16) (19,17) "
            + "(19,18) (19,19) (20,19)"
        ));

        maze = new Maze(testDir + "maze09.txt");
        path = MazeSolver.findPath(maze);
        displayResults(path.equals(
            "(0,1) (1,1) (1,2) (1,3) (1,4) (1,5) "
            + "(1,6) (1,7) (1,8) (1,9) (2,9) (3,9) "
            + "(3,8) (3,7) (3,6) (3,5) (4,5) (5,5) "
            + "(5,6) (5,7) (5,8) (5,9) (5,10) (5,11) "
            + "(4,11) (3,11) (3,12) (3,13) (3,14) "
            + "(3,15) (2,15) (1,15) (1,16) (1,17) "
            + "(2,17) (3,17) (3,18) (3,19) (4,19) "
            + "(5,19) (5,20) (5,21) (5,22) (5,23) "
            + "(6,23) (7,23) (8,23) (9,23) (10,23) "
            + "(11,23) (12,23) (13,23) (13,24) (13,25) "
            + "(12,25) (11,25) (11,26) (11,27) (10,27) "
            + "(9,27) (9,28) (9,29) (10,29) (11,29) "
            + "(12,29) (13,29) (14,29) (15,29) (16,29) "
            + "(17,29) (17,28) (17,27) (18,27) (19,27) "
            + "(19,28) (19,29) (20,29) (21,29) (21,28) "
            + "(21,27) (22,27) (23,27) (23,26) (23,25) "
            + "(24,25) (25,25) (25,26) (25,27) (26,27) "
            + "(27,27) (28,27) (29,27) (29,28) (29,29) "
            + "(30,29)"
        ));

        maze = new Maze(testDir + "maze10.txt");
        path = MazeSolver.findPath(maze);
        displayResults(path.equals(
            "(0,1) (1,1) (1,2) (1,3) (2,3) (3,3) "
            + "(3,4) (3,5) (4,5) (5,5) (5,4) (5,3) "
            + "(5,2) (5,1) (6,1) (7,1) (7,2) (7,3) "
            + "(8,3) (9,3) (10,3) (11,3) (12,3) (13,3) "
            + "(13,2) (13,1) (14,1) (15,1) (16,1) "
            + "(17,1) (18,1) (19,1) (20,1) (21,1) "
            + "(22,1) (23,1) (24,1) (25,1) (26,1) "
            + "(27,1) (28,1) (29,1) (29,2) (29,3) "
            + "(30,3) (31,3) (32,3) (33,3) (33,2) "
            + "(33,1) (34,1) (35,1) (35,2) (35,3) "
            + "(35,4) (35,5) (34,5) (33,5) (33,6) "
            + "(33,7) (33,8) (33,9) (33,10) (33,11) "
            + "(34,11) (35,11) (35,10) (35,9) (35,8) "
            + "(35,7) (36,7) (37,7) (37,8) (37,9) "
            + "(37,10) (37,11) (37,12) (37,13) (37,14) "
            + "(37,15) (37,16) (37,17) (37,18) (37,19) "
            + "(38,19) (39,19) (39,20) (39,21) (38,21) "
            + "(37,21) (37,22) (37,23) (36,23) (35,23) "
            + "(35,24) (35,25) (35,26) (35,27) (36,27) "
            + "(37,27) (37,28) (37,29) (38,29) (39,29) "
            + "(39,30) (39,31) (39,32) (39,33) (39,34) "
            + "(39,35) (38,35) (37,35) (37,36) (37,37) "
            + "(38,37) (39,37) (39,38) (39,39) (40,39)"
        ));

        System.out.println("testMazeSolver: end");
        System.out.println();
    }


	public static void main (String[] args)
	{
		try
		{
            testBasicStack();
            testMazeLocationStack();
            testMazeSolver();
		}
		catch (Exception e)
		{
			System.out.println("Your code threw an Exception.");
			System.out.println("Perhaps a stack trace will help:");
			e.printStackTrace(System.out);
		}
	}
}
