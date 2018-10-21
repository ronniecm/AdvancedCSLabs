package FirstTerm;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Pd2RonnieMohapatraMazeGui extends JPanel {
	private Pd2RonnieMohapatraMaze maze;
	private JButton[][] mazeGrid;
	private JPanel grid;
	private JPanel south;
	private JButton reset;
	private JButton saveBut;
	private JButton clearBut;
	private boolean saveMode = false;
	private JPanel east;
	private JButton black;
	private JButton white;
	private boolean isBlack = true;
	private JPanel north;
	private JLabel nLabel;
	
	public Pd2RonnieMohapatraMazeGui()
	{
		setLayout(new BorderLayout());		
		maze = new Pd2RonnieMohapatraMaze();
		grid = new JPanel();
		grid.setLayout(new GridLayout(maze.getRows(), maze.getCols()));
		add(grid, BorderLayout.CENTER);
		
		mazeGrid = new JButton[maze.getRows()][maze.getCols()];
		
		for(int r = 0; r < maze.getRows(); r++)
		{
			for(int c = 0; c < maze.getCols(); c++)
			{
				mazeGrid[r][c] = new JButton();
				mazeGrid[r][c].setOpaque(true);
				
				if(maze.getGrid()[r][c] == 0)
					mazeGrid[r][c].setBackground(Color.black);
				else
					mazeGrid[r][c].setBackground(Color.white);
				
				grid.add(mazeGrid[r][c]);
				
				mazeGrid[r][c].addActionListener(new Listener1(r, c));
			}
		}
		
		south = new JPanel();
		south.setLayout(new GridLayout(1, 3));
		add(south, BorderLayout.SOUTH);
		reset = new JButton("Reset Maze");
		reset.addActionListener(new Reset());
		south.add(reset);
		
		saveBut = new JButton("Edit Maze Mode");
		saveBut.addActionListener(new Save());
		south.add(saveBut);
		
		clearBut = new JButton("Clear");
		clearBut.addActionListener(new Clear());
		south.add(clearBut);
		
		east = new JPanel();
		east.setLayout(new GridLayout(2, 1));
		add(east, BorderLayout.EAST);
		
		black = new JButton("BLACK");
		white = new JButton("WHITE");
		black.setVisible(saveMode);
		white.setVisible(saveMode);
		black.addActionListener(new ColorLogic());
		white.addActionListener(new ColorLogic());
		east.add(black);
		east.add(white);
		
		nLabel = new JLabel("Click a your starting point and witness magic!");
		add(nLabel, BorderLayout.NORTH);
		nLabel.setFont(new Font("Calibri", 0, 20));
		nLabel.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	private class Pd2RonnieMohapatraMaze
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
	}
	
	private class Listener1 implements ActionListener
	{
		private int myRow, myCol;
		public Listener1(int r, int c)
		{
			myRow = r;
			myCol = c;
		}
		
		public void actionPerformed(ActionEvent e)
		{
			if(saveMode == true)
			{
				maze.setCoordinate(myRow, myCol, isBlack ? 0 : 1);
				generate();
			}
			else
			{
				resetMaze();
				if(!maze.findAnExit(myRow, myCol))
					nLabel.setText("No solution from: (" + myRow + ", " + myCol + "). Try another point or edit the maze.");
				else
				{			
					generate();
					nLabel.setText("Solution found from (" + myRow + ", " + myCol + ")!");
				}
			}
		}
	}
	
	private class Reset implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			resetMaze();
		}
	}
	
	private class Save implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(!saveMode)
			{
				nLabel.setText("Click the 'black' button to add a wall and the 'white' button to add an opening");
				saveMode = true;
				black.setVisible(saveMode);
				white.setVisible(saveMode);
				resetMaze();
			}
			else
			{
				nLabel.setText("Click a your starting point and witness magic!");
				saveMode = false;
				black.setVisible(saveMode);
				white.setVisible(saveMode);
			}
		}
	}
	
	private class ColorLogic implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == black)
				isBlack = true;
			else
				isBlack = false;
		}
	}
	
	private class Clear implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			nLabel.setText("Create your own maze and let the computer solve it!");
			maze.clearGrid();
			generate();
			saveMode = true;
			black.setVisible(saveMode);
			white.setVisible(saveMode);
		}
	}
	
	public void resetMaze()
	{
		maze.resetGrid();
		generate();
	}
	
	public void generate()
	{
		for(int r = 0; r < mazeGrid.length; r++)
		{
			for(int c = 0; c < mazeGrid[0].length; c++)
			{
				if(maze.getGrid()[r][c] == 0)
					mazeGrid[r][c].setBackground(Color.black);
				else if(maze.getGrid()[r][c] == 7)
					mazeGrid[r][c].setBackground(Color.green);
				else
					mazeGrid[r][c].setBackground(Color.white);
			}
		}
	}
	
	public static void main(String[] args)
	{
		if(true)
		{
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch(Exception e) {
				e.getStackTrace();
			}
		}
		
		JFrame frame = new JFrame("Maze Solver");
		frame.setSize(1280, 740);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new Pd2RonnieMohapatraMazeGui());
		frame.setVisible(true);
		frame.setLocation(0, 0);
	}
}
