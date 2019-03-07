/*****************************************************************************************************************
NAME: Ronnie Mohapatra
PERIOD: 2
DUE DATE: January 7th, 2018
ASSIGNMENT: Maze Lab Using Stacks and Queues

PURPOSE: Solving mazes with a stack or a queue

LEARNED: a). I learned that the difference between using a Stack and using a Queue to solve a maze is that using a
			 stack implements a depth-first traversal and using a queue implements a breadth-first traversal. In a
			 depth first search, you go along one path until you are stuck and backtrack to last valid point and in
			 a breadth first search, you visit a point along all paths before backtracking to the last valid point.
			 Therefore, in my program, the stack approach shows the only path in green, while the queue approach
			 shows how the computer navigated through every direction to solve the maze.
			 
		 b). I learned about Swing Timers and how they work for a certain amount of time. This is best used for GUI programs
		 	 like this where the step by step visual needs to be shown.
            
CREDITS: www.stackoverflow.com

****************************************************************************************************************/

package Stack;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Pd2RonnieMohapatraMazeGUI extends JPanel {
	private int[][] maze1 =	{
							  	{1,1,1,0,1,1,0,0,0,1,1,1,1},
							  	{1,0,1,1,1,0,1,1,1,1,0,0,1},
							  	{0,0,0,0,1,0,1,0,1,0,1,0,0},
							 	{1,1,1,0,1,1,1,0,1,0,0,1,1},
								{1,0,1,0,0,0,0,1,1,1,0,0,1},
								{1,0,1,1,1,1,1,1,0,1,1,1,0},
								{1,0,0,0,0,0,0,0,0,0,0,0,0},
								{1,1,1,1,1,1,1,1,1,1,1,1,1}
							}; 
	private int[][] maze2 = {
								{0, 1, 1, 1, 1, 1, 1},
								{0, 0, 0, 0, 0, 1, 1},
								{1, 1, 1, 1, 1, 1, 0},
								{0, 0, 0, 1, 0, 0, 0},
								{0, 1, 0, 1, 0, 1, 0},
								{0, 1, 0, 1, 0, 1, 0},
								{1, 1, 1, 1, 0, 0, 1},
								{0, 0, 0, 1, 1, 1, 1},
							};
	private int[][] maze3 = {
								{1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1},
								{1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0},
								{1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1},
								{1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1},
								{1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1},
								{1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0},
								{1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1},
								{0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0},
								{1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1},
								{1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0},
								{1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1},
								{1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1},
								{1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1},
								{1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1},
								{1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1},
								{0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
								{1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1},
								{1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1},
								{1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1},
							};
	private int[][] maze4 = {
								{ 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
								{ 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0 },
								{ 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0 },
								{ 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0 },
								{ 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0 },
								{ 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1 },
								{ 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
								{ 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0 },
								{ 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
								{ 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1 },
								{ 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1 },
							};
	
	 private int[][] maze5 = {
								 {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,0,1,1,1,0,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,1,1,0,1,1,1,0,1,1,1,1,1,1,1,1,1,0,1,1,1},
								 {1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,1,0,1,0,1,0,1,0,1,0,0,0,0,0,1,0,1,0,1,0,1,0,1,0,0,0,1,0,1,0,0,0,1,0,0,0,1,0,1,0,0,0,1,0,1,0,0,0,0,0,1,0,0,0,1},
								 {1,0,1,1,1,1,1,1,1,0,1,1,1,0,1,1,1,1,1,1,1,0,1,0,1,0,1,0,1,1,1,0,1,1,1,1,1,0,1,0,1,0,1,0,1,0,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,0,1,1,1,0,1,0,1,1,1,1,1,1,1,1,1},
								 {1,0,1,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,1,0,1,0,1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0},
								 {1,0,1,0,1,1,1,1,1,0,1,0,1,1,1,0,1,1,1,0,1,1,1,0,1,0,1,0,1,1,1,0,1,1,1,0,1,1,1,1,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1,1,1,1,1,1,1,0,1,0,1,0,1,1,1,1,1,0,1,1,1,1,1},
								 {1,0,1,0,1,0,0,0,1,0,1,0,1,0,0,0,0,0,1,0,1,0,1,0,0,0,1,0,1,0,1,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,1,0,1,0,0,0,0,0,0,0,1,0,1,0,1,0,0,0,0,0,1,0,1,0,0,0,1},
								 {1,1,1,0,1,1,1,0,1,0,1,0,1,1,1,1,1,0,1,0,1,0,1,0,1,1,1,0,1,0,1,1,1,0,1,1,1,0,1,1,1,0,1,0,1,1,1,1,1,0,1,0,1,0,1,0,1,1,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1,1,1,0,1},
								 {0,0,1,0,0,0,1,0,1,0,1,0,0,0,0,0,1,0,1,0,1,0,1,0,1,0,0,0,1,0,1,0,1,0,0,0,1,0,1,0,1,0,0,0,1,0,0,0,0,0,1,0,1,0,1,0,1,0,0,0,1,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,1},
								 {1,1,1,1,1,0,1,0,1,0,1,1,1,1,1,0,1,1,1,0,1,0,1,0,1,1,1,0,1,0,1,0,1,1,1,0,1,1,1,0,1,1,1,1,1,1,1,0,1,1,1,0,1,1,1,0,1,0,1,1,1,0,1,1,1,0,1,1,1,1,1,1,1,0,1,1,1},
								 {1,0,0,0,0,0,1,0,1,0,0,0,1,0,1,0,0,0,1,0,1,0,0,0,0,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,1,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,1,0,0},
								 {1,1,1,0,1,1,1,0,1,0,1,1,1,0,1,1,1,0,1,0,1,0,1,1,1,1,1,0,1,0,1,0,1,1,1,0,1,1,1,1,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1,0,1,0,1,1,1,1,1,0,1,0,1,0,1,1,1,0,1,0,1,1,1},
								 {0,0,0,0,1,0,0,0,1,0,1,0,1,0,0,0,0,0,1,0,1,0,1,0,0,0,0,0,1,0,1,0,1,0,1,0,1,0,0,0,0,0,1,0,1,0,1,0,0,0,1,0,1,0,0,0,1,0,1,0,0,0,0,0,1,0,1,0,1,0,1,0,1,0,0,0,0},
								 {1,1,1,1,1,0,1,1,1,1,1,0,1,0,1,1,1,1,1,0,1,0,1,1,1,1,1,0,1,0,1,1,1,0,1,1,1,1,1,0,1,1,1,0,1,0,1,1,1,1,1,0,1,0,1,0,1,1,1,0,1,1,1,1,1,1,1,0,1,0,1,0,1,1,1,0,1},
								 {1,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,1,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,1,0,0,0,1,0,1,0,1,0,0,0,0,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,1,0,1,0,1},
								 {1,0,1,1,1,1,1,1,1,1,1,0,1,0,1,1,1,1,1,0,1,1,1,1,1,0,1,0,1,1,1,0,1,1,1,0,1,0,1,1,1,0,1,0,1,1,1,1,1,0,1,0,1,0,1,1,1,1,1,1,1,1,1,0,1,0,1,1,1,0,1,1,1,0,1,0,1},
								 {1,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,1,0,1,0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,1,0,1,0,1,0,1,0,1,0,1,0,0,0,1,0,1},
								 {1,0,1,1,1,1,1,0,1,0,1,1,1,1,1,1,1,0,1,0,1,1,1,0,1,1,1,0,1,1,1,0,1,0,1,0,1,1,1,0,1,1,1,0,1,0,1,1,1,0,1,1,1,0,1,1,1,1,1,0,1,0,1,0,1,1,1,0,1,0,1,0,1,0,1,0,1},
								 {1,0,0,0,0,0,1,0,1,0,0,0,1,0,0,0,0,0,1,0,1,0,1,0,1,0,0,0,0,0,0,0,1,0,1,0,1,0,0,0,1,0,0,0,1,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,1,0,1,0,0,0,0,0,0,0,1,0,1,0,1,0,1},
								 {1,1,1,1,1,1,1,0,1,1,1,0,1,1,1,1,1,1,1,0,1,0,1,0,1,1,1,0,1,1,1,0,1,0,1,1,1,0,1,0,1,0,1,1,1,0,1,0,1,1,1,0,1,1,1,0,1,1,1,0,1,0,1,0,1,1,1,1,1,1,1,0,1,0,1,1,1},
								 {1,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,1,0,1,0,1,0,0,0,0,0,1,0,1,0,1,0,1,0,1,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,1,0,1,0,0,0,0,0,0,0,1,0,0,0,1},
								 {1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,0,1,1,1,0,1,1,1,1,1,1,1,1,1,0,1,0,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,0,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1},
	 };
	 
	 private int [] [] grid;
	 private JButton[][] buttonGrid;
	 private Timer t;
	 private JPanel mazePanel = new JPanel(), southPanel = new JPanel(), eastPanel = new JPanel();
	 private JButton stop, reset, stackButton, queueButton, newMazeButton;
	 private boolean stackMode = true;
	
	/*
	 * pre-condition: 0 <= maze <= 4
	 * post-condition: generates buttonGrid based on the respective maze
	 */
	public Pd2RonnieMohapatraMazeGUI(int maze)
	{
		//assign grid to respective maze matrix
		if(maze == 0)
			grid = maze1;
		else if(maze == 1)
			grid = maze2;
		else if(maze == 2)
			grid = maze3;
		else if(maze == 3)
			grid = maze4;
		else
			grid = maze5;
		
		
		setLayout(new BorderLayout());
		mazePanel = new JPanel();
		
		mazePanel.setLayout(new GridLayout(grid.length, grid[0].length));
		
		add(mazePanel, BorderLayout.CENTER);
		
		buttonGrid = new JButton[grid.length][grid[0].length];
		
		for(int r = 0; r < buttonGrid.length; r++)
		{
			for(int c = 0; c < buttonGrid[0].length; c++)
			{
				buttonGrid[r][c] = new JButton();
				buttonGrid[r][c].setOpaque(true);
				buttonGrid[r][c].setBorderPainted(false);
				buttonGrid[r][c].setBackground(grid[r][c] == 0 ? Color.black : Color.white); //color button black or white depending on the matrix
				buttonGrid[r][c].addActionListener(new ButtonListener(r, c));
				mazePanel.add(buttonGrid[r][c]);
			}
		}
		
		southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(1, 2));
		add(southPanel, BorderLayout.SOUTH);
		
		stop = new JButton("STOP");
		stop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				t.stop(); //used to stop the solving of the maze
			}
		});
		
		southPanel.add(stop);
		
		reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				t.stop();
				reset();
			}
		});
		
		southPanel.add(reset);
		
		eastPanel.setLayout(new GridLayout(2, 1));
		add(eastPanel, BorderLayout.EAST);
		stackButton = new JButton("Stack Mode");
		stackButton.addActionListener(new ModeChanger());
		queueButton = new JButton("Queue Mode");
		queueButton.addActionListener(new ModeChanger());
		eastPanel.add(stackButton);
		eastPanel.add(queueButton);
		
		newMazeButton = new JButton("New Maze"); //takes user back to the main menu (Selection Screen)
		newMazeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(true)
				{
					try {
						UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
					} catch(Exception ex) {
						ex.getStackTrace();
					}
				}
				JFrame f = new JFrame("Selection");
				f.setLocation(455, 281);
				f.setSize(500, 200);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setContentPane(new Pd2RonnieMohapatraSelectionScreen());
				f.setVisible(true);
			}
		});
		add(newMazeButton, BorderLayout.WEST);		
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Maze");
		frame.setLocation(0, 0);
		frame.setSize(1380, 730);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new Pd2RonnieMohapatraMazeGUI(0));
		frame.setVisible(true);
	}
	/*
	 * pre-condition: grid and buttonGrid are not empty
	 * post-condition: resets matrix to original state and buttonGrid to black and white
	 */
	public void reset()
	{
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid[0].length; j++)
			{
				if(grid[i][j] != 0)
					grid[i][j] = 1; //make the matrix cell 1 if it doesn't equal 0
				
				buttonGrid[i][j].setBackground(grid[i][j] == 0 ? Color.black : Color.white); //color button according to the maze matrix
			}
		}
	}

	private class ButtonListener implements ActionListener
	{
		private int r, c;
		public ButtonListener(int r, int c)
		{
			this.r = r;
			this.c = c;
		}
		
		public void actionPerformed(ActionEvent e)
		{
			reset();
			//used a Swing Timer to replicate the step by step visual
			//executes a piece of code every 100 milliseconds
			t = new Timer(75, new ActionListener() {
				int beginningX = r; //get coordinates from Listener constructor
				int beginningY = c;
				int count = 0;
				Stack<Point> stack = new Stack<Point>();
				Queue<Point> q = new LinkedList<Point>();
				@Override
				public void actionPerformed(ActionEvent e) {
					if(stackMode)
					{
						if(count == 0) //count variable for keeping track of first turn
						{
							grid[beginningX][beginningY] = 3;
							buttonGrid[beginningX][beginningY].setBackground(Color.yellow); //marks the beginning point
							stack.push(new Point(beginningX,beginningY)); //add beginning point to stack
							count++;
						} else {
							if(stack.isEmpty())
							{
								Timer t = (Timer)e.getSource();
								t.stop(); //stop the timer and exit the method if stack is empty (no points to check)
								return;
							}
							
							Point p = stack.peek(); //get most current point
							
							if(p.getX() == buttonGrid.length - 1 && p.getY() == buttonGrid[0].length - 1)
							{
								grid[p.getX()][p.getY()] = 3; //add to solution path
								buttonGrid[p.getX()][p.getY()].setBackground(new Color(255, 0, 255)); //make final point purple
								Timer t = (Timer)e.getSource();
								t.stop(); //stop timer and exit method
								return;
							}
							
							if(!(p.getX() == beginningX && p.getY() == beginningY)) //make sure current point does not equal beginning point
							{
								grid[p.getX()][p.getY()] = 3; //add to solution path
								buttonGrid[p.getX()][p.getY()].setBackground(Color.GREEN); //color green
							}
							
							int east = p.getY() + 1, south = p.getX() + 1, north = p.getX() - 1, west = p.getY() - 1;
							
							//add any valid points to stack
							if(isValidPoint(p.getX(), east))
								stack.push(new Point(p.getX(), east));							
							else if(isValidPoint(south, p.getY()))
								stack.push(new Point(south, p.getY()));
							else if(isValidPoint(north, p.getY()))
								stack.push(new Point(north, p.getY()));
							else if(isValidPoint(p.getX(), west))
								stack.push(new Point(p.getX(), west));
							else
							{
								stack.pop(); //if point is blocked, remove point from path
								grid[p.getX()][p.getY()] = 7; //remove point from solution path and keep it as marked
								buttonGrid[p.getX()][p.getY()].setBackground(Color.white); //color the point white
							}
						}
					} else {
						if(count == 0)
						{
							q.add(new Point(beginningX,beginningY)); //add beginning point to queue
							buttonGrid[beginningX][beginningY].setBackground(Color.yellow); //color beginning point yellow
							count++; //used to differentiate between the first turn and other turns
						} else {
							if(q.isEmpty())
							{
								Timer c = (Timer)e.getSource();
								c.stop(); //stop timer and exit method if queue is empty (no points to check)
								return;
							}
							
							Point p = q.peek(); //get current point from queue
							
							if(p.getX() == grid.length - 1 && p.getY() == grid[0].length - 1)
							{
								buttonGrid[p.getX()][p.getY()].setBackground(new Color(255, 0, 255)); //color final point purple
								Timer c = (Timer)e.getSource();
								c.stop(); //stop timer and exit method if solution found
								return;
							}
							
							if(!(p.getX() == beginningX && p.getY() == beginningY))
								buttonGrid[p.getX()][p.getY()].setBackground(Color.green); //color green if current point does not equal beginning point
							
							int east = p.getY() + 1, south = p.getX() + 1, north = p.getX() - 1, west = p.getY() - 1;
							
							//add any valid point around the current to the queue
							if(isValidPoint(p.getX(), east) && !existsInQueue(q, new Point(p.getX(), east)))	
								q.add(new Point(p.getX(), east));
							if(isValidPoint(south, p.getY()) && !existsInQueue(q, new Point(south, p.getY())))
								q.add(new Point(south, p.getY()));
							if(isValidPoint(p.getX(), west) && !existsInQueue(q, new Point(p.getX(), west)))
								q.add(new Point(p.getX(), west));
							if(isValidPoint(north, p.getY()) && !existsInQueue(q, new Point(north, p.getY())))
								q.add(new Point(north, p.getY()));
							
							if(!isValidPoint(p.getX(), east) && !isValidPoint(p.getX(), west) && !isValidPoint(north, p.getY()) && !isValidPoint(south, p.getY()))
								buttonGrid[p.getX()][p.getY()].setBackground(Color.red); //color point red if blocked
							
							
							q.remove(); //remove point from queue if blocked
							System.out.println(q);
						}				
					}
				}
			});
			
			t.start(); //start going through the maze with the timer
		}
		
		/*
		 * pre-condition: none
		 * post-condition: returns true if the point is available to visit
		 */
		public boolean isValidPoint(int x, int y)
		{
			//point is valid if it is in bounds and grid[x][y] == 1
			if(stackMode)
				return x >= 0 && x <= buttonGrid.length - 1 && y >= 0 && y <= buttonGrid[0].length - 1 && grid[x][y] == 1;
			else
				return  x >= 0 && x <= buttonGrid.length - 1 && y >= 0 && y <= buttonGrid[0].length - 1 && buttonGrid[x][y].getBackground().equals(Color.WHITE);	
		}
		
		/*
		 * pre-condition: q is not empty and p is not null
		 * post-condition: returns true if the Point p is in the Queue q
		 */
		public boolean existsInQueue(Queue<Point> q, Point p)
		{
			Iterator<Point> iterator = q.iterator();
			while(iterator.hasNext())
			{
				if(iterator.next().equals(p))
					return true;
			}
			
			return false;
		}
	}
	
	//listener used for changing between stack and queue mode
	private class ModeChanger implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == stackButton)
			{
				stackMode = true; //change boolean
				//switch colors of buttons
				stackButton.setBackground(Color.BLUE);
				queueButton.setBackground(Color.LIGHT_GRAY);
			} else {
				stackMode = false;
				queueButton.setBackground(Color.BLUE);
				stackButton.setBackground(Color.LIGHT_GRAY);
			}
			
			System.out.println(stackMode);
		}
	}
	
	//Point class for defining locations on the grid
	private class Point
	{
		private int x, y;
		public Point(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		
		public int getX()
		{
			return this.x;
		}
		
		public int getY()
		{
			return this.y;
		}
		
		public boolean equals(Point other)
		{
			return this.x == other.getX() && this.y == other.getY();
		}
		public String toString()
		{
			return "(" + x + ", " + y + ")";
		}
	}
}