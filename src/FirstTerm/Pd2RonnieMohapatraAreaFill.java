package FirstTerm;
//name: Ronnie Mohapatra
//date: October 21, 2018
   
   import java.util.Scanner;
   import java.io.*;
    public class Pd2RonnieMohapatraAreaFill
   {
    	
      public static char[][] grid = null;
      public static Scanner sc = new Scanner(System.in);
       
     public static void main(String[] args) throws FileNotFoundException
      {
         int option = 0;
         do {
        	 try {
		         System.out.print("Filename: ");
		         String filename = sc.next();
		         read(filename);
		
		         display(grid);
		         System.out.print("\nEnter ROW COL to fill from: ");
		         int row = sc.nextInt();
		         int col = sc.nextInt(); 
		         fill(grid, row, col, grid[row][col]);
		         display(grid);
		         System.out.print("Enter 0 to go again or 1 to quit.");
		         option = sc.nextInt();
        	 } catch(NullPointerException e) {
        		 System.out.println("File not found.");
        	 }
         } while(option != 1);
         sc.close(); 
      }
      
       public static char[][] read(String filename) throws FileNotFoundException
      {
    	   try {
	    	   File file = new File(filename);
	    	   FileReader fr = new FileReader(file);
	    	   BufferedReader br = new BufferedReader(fr);
	    	   String line = br.readLine();
	    	   //System.out.println(line);
	    	   grid = new char[Integer.parseInt(line.substring(0, line.indexOf(" ")))][Integer.parseInt(line.substring(line.indexOf(" ") + 1))];
	    	   
	    	   int row = 0;
	    	   
	    	   while((line = br.readLine()) != null)
	    	   {
	    		   for(int i = 0; i < line.length(); i++)
	    		   {
	    			   grid[row][i] = line.charAt(i);
	    		   }
	    		   row++;
	    	   }
	    	   br.close();
    	   } catch(IOException io) {
    		   io.getStackTrace();
    		   //System.out.println("sike");
    	   }
    	   
    	   return grid;
      }
      
       public static void display(char[][] g)
      {              
         for(char[] arr : g)
         {
        	 for(char c : arr)
        	 {
        		 System.out.print(c + " ");
        	 }
        	 System.out.println();
         }
      }
       
      public static void fill(char[][] g, int r, int c, char ch) //recursive method
      {       
         if(r < 0 || r > grid.length - 1|| c < 0 || c > grid[0].length - 1 || grid[r][c] != ch)
         {
        	 return;
         }
         
         if(grid[r][c] == ch)
        	 grid[r][c] = '*';
         
         fill(g, r + 1, c, ch);
         fill(g, r - 1, c, ch);
         fill(g, r, c + 1, ch);
         fill(g, r, c - 1, ch);
      }
   }