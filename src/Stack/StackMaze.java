package Stack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StackMaze {
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
	 
	 private Stack<Point> stack = new Stack<Point>();
	 private Queue<Point> q = new LinkedList<Point>();
	 
	 public void solve(int x, int y)
	 {
		 q.add(new Point(x, y));
		 
		 grid[x][y] = 3;
		 
		 Point p = q.peek();
		 while(!q.isEmpty() && !(q.peek().getX() == grid.length - 1 && q.peek().getY() == grid[0].length - 1))
		 {
			p = q.remove();
			grid[p.getX()][p.getY()] = 3;
			int east = p.getY() + 1, south = p.getX() + 1, north = p.getX() - 1, west = p.getY() - 1;
			
			if(isValidPoint(p.getX(), east))
				q.add(new Point(p.getX(), east));
			if(isValidPoint(south, p.getY()))
				q.add(new Point(south, p.getY()));
			if(isValidPoint(p.getX(), west))
				q.add(new Point(p.getX(), west));
			if(isValidPoint(north, p.getY()))
				q.add(new Point(north, p.getY()));
			
			if(!isValidPoint(p.getX(), east) && !isValidPoint(p.getX(), west) && !isValidPoint(north, p.getY()) && !isValidPoint(south, p.getY())) {
				
				grid[p.getX()][p.getY()] = 7;
				//q.remove();
			}
			
			System.out.println(q);
			
		 }
		 
		// System.out.println("Solution: " + q);
	 }
	 
	 public String toString()
	 {
		 String result = "";
		for(int r = 0; r < grid.length; r++)
		 {
			 for(int c = 0; c < grid[0].length; c++)
				result += grid[r][c] + " ";
			result += "\n";
		 }
		 
		 return result;
	 }
	 
	 public boolean isValidPoint(int x, int y)
	 {
		 return x >= 0 && x <= grid.length - 1 && y >= 0 && y <= grid[0].length - 1 && grid[x][y] == 1;
	 }
	 
	 public static void main(String[] args)
	 {
		StackMaze m = new StackMaze();
		System.out.println(m);
		m.solve(0, 0);
		System.out.println();
		System.out.println(m);
	 }
	 
	 
	 
	
	 private class Point {
		 private int x, y;
		 public Point(int x, int y)
		 {
			 this.x = x;
			 this.y = y;
		 }
		 
		 public int getX()
		 {
			 return x;
		 }
		 
		 public int getY()
		 {
			 return y;
		 }
		 
		 public boolean equals(Point other)
		 {
			 return other.getX() == this.x && other.getY() == this.y;
		 }
		 public String toString()
		 {
			 return "(" + x + ", " + y + ")";
		 }
	 }
	 
	 
}
