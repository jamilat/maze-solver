# maze_solver
<H1>About</H1>
<body>
A program written in Java as part of my Fundamentals of Programming II class. This program receives a text file containing a maze and outputs a path from start to finish.
  <H2>Information</H2>
  Compilation:  javac MazeSolver.java <br>
  Execution:    java MazeSolver input.txt <br>
  Dependencies: Maze.java MazeLocation.java Stack.java StackRefBased.java <br>

  Maze.java:
  <ul>
    <li>Used to read in maze data from plain-text files, stores the maze in a some useful representation that permits querying the state of the maze</li>
  </ul>
  MazeLocation.java:
  <ul>
    <li>contain a row & column value indicating a cell position in a 2D maze</li>
  </ul>
  StackRefBased.java:
  <ul>
    <li>is a generic Stack, implemented using a linked list</li>
    <li>implements Stack.java</li>
  </ul>

</body>

<H1>Input</H1>
<body>
  Samples mazes are located in the tests directory of this repository. The maze is represented textually by a matrix of characters; the (“*”) character represents a wall, and space (“ “) represents an open square. The first three lines of the text file have specific meanings and each number is seperated by a single space:
<ul>
  <li>Line 1: dimensions (length x and width y)</li>
  <li>Line 2: start coordinates (x,y)</li>
  <li>Line 3: end coordinates (x,y)</li> 
</ul>
</body>
