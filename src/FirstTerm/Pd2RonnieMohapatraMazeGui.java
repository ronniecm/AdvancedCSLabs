package FirstTerm;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Scanner;
import java.util.Timer;

public class Pd2RonnieMohapatraMazeGui extends JPanel {
	private Pd2RonnieMohapatraMaze maze;
	private JButton[][] mazeGrid;
	private JPanel centerPanel;
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
	private Timer timer = new Timer();
	
	public Pd2RonnieMohapatraMazeGui()
	{
		setLayout(new BorderLayout());		
		maze = new Pd2RonnieMohapatraMaze();
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(maze.getRows(), maze.getCols()));
		add(centerPanel, BorderLayout.CENTER);
		
		mazeGrid = new JButton[maze.getRows()][maze.getCols()];

		for(int r = 0; r < maze.getRows(); r++)
		{
			for(int c = 0; c < maze.getCols(); c++)
			{
				mazeGrid[r][c] = new JButton();
				mazeGrid[r][c].setOpaque(true);
				mazeGrid[r][c].setBorderPainted(false);
				
				if(maze.getGrid()[r][c] == 0)
					mazeGrid[r][c].setBackground(Color.black);
				else
					mazeGrid[r][c].setBackground(Color.white);
				
				centerPanel.add(mazeGrid[r][c]);
				
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
				if(!solveWithColor(myRow, myCol))
					nLabel.setText("No solution from: (" + myRow + ", " + myCol + "). Try another point or edit the maze.");
				else
				{
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
	
	public boolean solveWithColor(int r, int c)
	{
		return true;
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
					mazeGrid[r][c].setBackground(Color.GREEN);
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
		
		// Assume that the exit of the maze is at the lower right hand corner of 
		// the grid

	}
}
