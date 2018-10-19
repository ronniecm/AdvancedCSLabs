package FirstTerm;
import java.util.Scanner;
public class Pd2RonnieMohapatraMaze
{
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
		   return false;
	   
	   if(x == grid.length - 1 && y == grid[0].length - 1)
	   {
		   grid[x][y] = 7;
		   path += "[" + x + ", " + y + "]";
		   System.out.println(path);
		   return true;
	   }
	   
	   if(grid[x][y] != 1)
		   return false;
	   
	   grid[x][y] = 7;
	   
		  
	   path += "[" + x + ", " + y + "], ";

	   
	   int up = x - 1, down = x + 1, right = y + 1, left = y - 1;
	   
	   if(findAnExitHelper(up, y, path))
		   return true;
	   if(findAnExitHelper(down, y, path))
		   return true;
	   if(findAnExitHelper(x, left, path))
		   return true;
	   if(findAnExitHelper(x, right, path))
		   return true;
	   
	   grid[x][y] = 3;	 
	   
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
			   if(grid[r][c] == 7 || grid[r][c] == 3)
				   grid[r][c] = 1;
		   }
	   }
   }
   
   public void clearGrid()
   {
	   for(int r = 0; r < grid.length; r++)
	   {
		   for(int c = 0; c < grid[0].length; c++)
		   {
			   grid[r][c] = 1;
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
} // Maze
