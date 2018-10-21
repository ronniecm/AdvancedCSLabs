package FirstTerm;

import java.util.Scanner;

public class Pd2RonnieMohapatraMaze {
	 private final int VISITED = 3;
	   private final int PATH = 7;
	   private final int NOT_YET = 1;
	   private final int BLOCKED = 0;

	   private int [] [] grid = 
	   {
	      {1,1,1,0,1,1,0,0,0,1,1,1,1},
	      {1,0,1,1,1,0,1,1,1,1,0,0,1},
	      {0,0,0,0,1,0,1,0,1,0,1,0,0},
	      {1,1,1,0,1,1,1,0,1,0,0,1,1},
	      {1,0,1,0,0,0,0,1,1,1,0,0,1},
	      {1,0,1,1,1,1,1,1,0,1,1,1,0},
	      {1,0,0,0,0,0,0,0,0,0,0,0,0},
	      {1,1,1,1,1,1,1,1,1,1,1,1,1}
	    }; 
	   
	   
	    
	// Use a smaller maze first
	   /*
	   private int [] [] grid = 
	   {
	      {1,1,1},
	      {1,0,1},
	      {0,0,1}
	   }; 
		*/
	   // Assumption: the exit is at the bottom right corner of the grid
	   //
	   public boolean findAnExit (int x, int y)
	   {
	      String path = "";
	      return findAnExitHelper(x, y, path);
	   }  // FindAnExit


	   public boolean findAnExitHelper (int x, int y, String path)
	   {
		   if(x < 0 || y < 0 || x > grid.length - 1 || y > grid[0].length - 1) 
			   return false; //if x or y is out of bounds
		   
		   if(x == grid.length - 1 && y == grid[0].length - 1) //if you have reached the bottom right corner
		   {
			   grid[x][y] = PATH; //add to visual path
			   path += "[" + x + ", " + y + "]"; //add point to textual path
			   System.out.println(path); //print path
			   return true; 
		   }
		   
		   if(grid[x][y] != 1)
			   return false; //if cell is visited or blocked
		   
		   grid[x][y] = PATH; //automatically add to path
		   
			  
		   path += "[" + x + ", " + y + "], ";

		   
		   int up = x - 1, down = x + 1, right = y + 1, left = y - 1; //directional assignments
		   
		   //keeps recursing up down left and right until it return false so it moves on to the next point
		   if(findAnExitHelper(up, y, path))
			   return true;
		   if(findAnExitHelper(down, y, path))
			   return true;
		   if(findAnExitHelper(x, left, path))
			   return true;
		   if(findAnExitHelper(x, right, path))
			   return true;
		   
		   grid[x][y] = VISITED;	 
		   
		   return false;
	   } // findAnExitHelper
	   
	   public int getRows()
	   {
		   return grid.length;
	   }
	   
	   public int getCols()
	   {
		   return grid[0].length;
	   }
	   
	   public int[][] getGrid()
	   {
		   return grid;
	   }
	   
	   public void setCoordinate(int r, int c, int val)
	   {
		   grid[r][c] = val;
	   }
	   
	   public void resetGrid()
	   {
		   for(int r = 0; r < grid.length; r++)
		   {
			   for(int c = 0; c < grid[0].length; c++)
			   {
				   if(grid[r][c] == PATH || grid[r][c] == VISITED)
					   grid[r][c] = NOT_YET;
			   }
		   }
	   }
	   
	   public void clearGrid()
	   {
		   for(int r = 0; r < grid.length; r++)
		   {
			   for(int c = 0; c < grid[0].length; c++)
			   {
				   grid[r][c] = PATH;
			   }
		   }
	   }
	   public String toString()
	   {
	      String result = "";
	      for(int r = 0; r < grid.length; r++)
	      {
	    	  for(int c = 0; c < grid[0].length; c++)
	    	  {
	    		  result += grid[r][c]+ " ";
	    	  }
	    	  result += "\n";
	      }
	      
	      return result;
	   } // toString
	   
	   public static void main (String [] args)
	    {
	       // Assume that the exit of the maze is at the lower right hand corner of 
	       // the grid
	       Pd2RonnieMohapatraMaze m = new Pd2RonnieMohapatraMaze();
	       
	       // display the maze  
	       System.out.println (m);
	       Scanner input = new Scanner (System.in);
	       
	       System.out.println ("Start location coordinates (separated by a space): ");
	       int startX = input.nextInt();
	       int startY = input.nextInt();
	         
	       while (!m.findAnExit(startX, startY))
	       {
	          System.out.println ("Still trapped inside!");
	          System.out.println (m);
	          
	          System.out.println ("Start location coordinates (separated by a space): ");
	          startX = input.nextInt();
	          startY = input.nextInt();
	       }
	          
	       System.out.println ("Successfully exit the maze!!!");
	               
	       // display the path (indicated by 7) that leads to the exit of the maze
	       // also display locations tried
	       System.out.println (m);
	    } // main 
}

/* Output
 * 1 1 1 0 1 1 0 0 0 1 1 1 1 
1 0 1 1 1 0 1 1 1 1 0 0 1 
0 0 0 0 1 0 1 0 1 0 1 0 0 
1 1 1 0 1 1 1 0 1 0 0 1 1 
1 0 1 0 0 0 0 1 1 1 0 0 1 
1 0 1 1 1 1 1 1 0 1 1 1 0 
1 0 0 0 0 0 0 0 0 0 0 0 0 
1 1 1 1 1 1 1 1 1 1 1 1 1 

Start location coordinates (separated by a space): 
0 0
[0, 0], [0, 1], [0, 2], [1, 2], [1, 3], [1, 4], [2, 4], [3, 4], [3, 5], [3, 6], [2, 6], [1, 6], [1, 7], 
[1, 8], [2, 8], [3, 8], [4, 8], [4, 7], [5, 7], [5, 6], [5, 5], [5, 4], [5, 3], [5, 2], [4, 2], [3, 2], 
[3, 1], [3, 0], [4, 0], [5, 0], [6, 0], [7, 0], [7, 1], [7, 2], [7, 3], [7, 4], [7, 5], [7, 6], [7, 7], [7, 8], 
[7, 9], [7, 10], [7, 11], [7, 12]
Successfully exit the maze!!!
7 7 7 0 3 3 0 0 0 1 1 1 1 
3 0 7 7 7 0 7 7 7 1 0 0 1 
0 0 0 0 7 0 7 0 7 0 1 0 0 
7 7 7 0 7 7 7 0 7 0 0 1 1 
7 0 7 0 0 0 0 7 7 1 0 0 1 
7 0 7 7 7 7 7 7 0 1 1 1 0 
7 0 0 0 0 0 0 0 0 0 0 0 0 
7 7 7 7 7 7 7 7 7 7 7 7 7 

1 1 1 0 1 1 0 0 0 1 1 1 1 
1 0 1 1 1 0 1 1 1 1 0 0 1 
0 0 0 0 1 0 1 0 1 0 1 0 0 
1 1 1 0 1 1 1 0 1 0 0 1 1 
1 0 1 0 0 0 0 1 1 1 0 0 1 
1 0 1 1 1 1 1 1 0 1 1 1 0 
1 0 0 0 0 0 0 0 0 0 0 0 0 
1 1 1 1 1 1 1 1 1 1 1 1 1 

Start location coordinates (separated by a space): 
3 10
Still trapped inside!
1 1 1 0 1 1 0 0 0 1 1 1 1 
1 0 1 1 1 0 1 1 1 1 0 0 1 
0 0 0 0 1 0 1 0 1 0 1 0 0 
1 1 1 0 1 1 1 0 1 0 0 1 1 
1 0 1 0 0 0 0 1 1 1 0 0 1 
1 0 1 1 1 1 1 1 0 1 1 1 0 
1 0 0 0 0 0 0 0 0 0 0 0 0 
1 1 1 1 1 1 1 1 1 1 1 1 1 

Start location coordinates (separated by a space): 
7 0
[7, 0], [7, 1], [7, 2], [7, 3], [7, 4], [7, 5], [7, 6], [7, 7], [7, 8], [7, 9], [7, 10], [7, 11], [7, 12]
Successfully exit the maze!!!
3 3 3 0 3 3 0 0 0 3 3 3 3 
3 0 3 3 3 0 3 3 3 3 0 0 3 
0 0 0 0 3 0 3 0 3 0 1 0 0 
3 3 3 0 3 3 3 0 3 0 0 1 1 
3 0 3 0 0 0 0 3 3 3 0 0 1 
3 0 3 3 3 3 3 3 0 3 3 3 0 
3 0 0 0 0 0 0 0 0 0 0 0 0 
7 7 7 7 7 7 7 7 7 7 7 7 7 

1 1 1 0 1 1 0 0 0 1 1 1 1 
1 0 1 1 1 0 1 1 1 1 0 0 1 
0 0 0 0 1 0 1 0 1 0 1 0 0 
1 1 1 0 1 1 1 0 1 0 0 1 1 
1 0 1 0 0 0 0 1 1 1 0 0 1 
1 0 1 1 1 1 1 1 0 1 1 1 0 
1 0 0 0 0 0 0 0 0 0 0 0 0 
1 1 1 1 1 1 1 1 1 1 1 1 1 

Start location coordinates (separated by a space): 
2 10
Still trapped inside!
1 1 1 0 1 1 0 0 0 1 1 1 1 
1 0 1 1 1 0 1 1 1 1 0 0 1 
0 0 0 0 1 0 1 0 1 0 3 0 0 
1 1 1 0 1 1 1 0 1 0 0 1 1 
1 0 1 0 0 0 0 1 1 1 0 0 1 
1 0 1 1 1 1 1 1 0 1 1 1 0 
1 0 0 0 0 0 0 0 0 0 0 0 0 
1 1 1 1 1 1 1 1 1 1 1 1 1 

Start location coordinates (separated by a space): 
0 12
[0, 12], [0, 11], [0, 10], [0, 9], [1, 9], [1, 8], [2, 8], [3, 8], [4, 8], [4, 7], [5, 7], [5, 6], [5, 5], 
[5, 4], [5, 3], [5, 2], [4, 2], [3, 2], [3, 1], [3, 0], [4, 0], [5, 0], [6, 0], [7, 0], [7, 1], [7, 2], 
[7, 3], [7, 4], [7, 5], [7, 6], [7, 7], [7, 8], [7, 9], [7, 10], [7, 11], [7, 12]
Successfully exit the maze!!!
1 1 1 0 1 1 0 0 0 7 7 7 7 
1 0 1 1 1 0 1 1 7 7 0 0 3 
0 0 0 0 1 0 1 0 7 0 3 0 0 
7 7 7 0 1 1 1 0 7 0 0 1 1 
7 0 7 0 0 0 0 7 7 1 0 0 1 
7 0 7 7 7 7 7 7 0 1 1 1 0 
7 0 0 0 0 0 0 0 0 0 0 0 0 
7 7 7 7 7 7 7 7 7 7 7 7 7 
*/
