package Graph;

//name: Ronnie Mohapatra   date: April 25, 2019
//was absent the week this content was taught
//resource classes and interfaces
//for use with Graphs0: Intro
//           Graphs1: Warshall
//           Graphs2: Floyd
import java.util.*;
import java.io.*;

interface AdjacencyMatrix {
	public void addEdge(int source, int target);

	public void removeEdge(int source, int target);

	public boolean isEdge(int from, int to);

	public void displayGrid();

	public int edgeCount();

	public List<Integer> getNeighbors(int source);
}

interface Warshall // User-friendly functionality
{
	public boolean isEdge(String from, String to);

	public Map<String, Integer> getVertices();

	public void readNames(String fileName) throws FileNotFoundException;

	public void readGrid(String fileName) throws FileNotFoundException;

	public void displayVertices();

	public void allPairsReachability(); // Warshall's Algorithm
}

interface Floyd {
	public int getCost(int from, int to);

	public int getCost(String from, String to);

	public void allPairsWeighted();
}

public class Pd2RonnieMohapatraAdjMatLab implements AdjacencyMatrix, Warshall, Floyd {
	private int[][] grid = null; // adjacency matrix representation
	private Map<String, Integer> vertices = null; // name --> index. Used in
													// graph1

	public Pd2RonnieMohapatraAdjMatLab(int numVerticies) {
		grid = new int[numVerticies][numVerticies];
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[0].length; c++)
				grid[r][c] = 0;
		}

		vertices = new TreeMap<String, Integer>();
	}

	public void addEdge(int source, int target) {
		grid[source][target] = 1;
	}

	public void removeEdge(int source, int target) {
		grid[source][target] = 0;
	}

	public boolean isEdge(int from, int to) {
		return grid[from][to] == 1;
	}

	public void displayGrid() {
		for (int[] rows : grid) {
			for (int col : rows)
				System.out.print(col + " ");
			System.out.println();
		}
	}

	public int edgeCount() {
		int count = 0;
		for (int[] rows : grid) {
			for (int col : rows)
				if (col != 0 && col != 9999)
					count++;
		}
		return count;
	}

	public List<Integer> getNeighbors(int source) {
		List<Integer> neighbors = new ArrayList<Integer>();
		for (int i = 0; i < grid[source].length; i++) {
			if (grid[source][i] == 1)
				neighbors.add(i);
		}
		return neighbors;
	}

	public boolean isEdge(String to, String from) {
		return grid[vertices.get(from)][vertices.get(to)] == 1;
	}

	public Map<String, Integer> getVertices() {
		return vertices;
	}

	public void readNames(String filename) throws FileNotFoundException {
		FileReader fr = new FileReader(new File(filename));
		BufferedReader br = new BufferedReader(fr);
		try {
			int size = Integer.parseInt(br.readLine());
			for (int i = 0; i < size; i++)
				vertices.put(br.readLine(), i);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public void readGrid(String fileName) throws FileNotFoundException {
		FileReader fr = new FileReader(new File(fileName));
		BufferedReader br = new BufferedReader(fr);
		try {
			int size = Integer.parseInt(br.readLine());
			grid = new int[size][size];
			for (int r = 0; r < grid.length; r++) {
				String line = br.readLine();
				int c = 0;
				int savedInd = 0;
				for (int i = 0; i < line.length(); i++) {
					if (line.charAt(i) == ' ' || i == line.length() - 1) {
						if (i == line.length() - 1)
							grid[r][c++] = Integer.parseInt(line.substring(savedInd));
						else
							grid[r][c++] = Integer.parseInt(line.substring(savedInd, i));

						savedInd = i + 1;
					}
				}
				c = 0;
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public void displayVertices() {
		Set<String> keySet = vertices.keySet();
		for (String key : keySet)
			System.out.println(vertices.get(key) + "-" + key);
	}

	public void allPairsReachability() {
		for (int intermediate = 0; intermediate < grid.length; intermediate++)
			for (int r = 0; r < grid.length; r++)
				for (int c = 0; c < grid[0].length; c++)
					if (grid[r][intermediate] == 1 && grid[intermediate][c] == 1)
						grid[r][c] = 1;

	}

	public List<String> getReachables(String from) {
		List<String> reachables = new ArrayList<String>();
		int source = vertices.get(from);
		for (int c = 0; c < grid[0].length; c++) {
			if (grid[source][c] == 1) {
				for (String key : vertices.keySet())
					if (vertices.get(key) == c)
						reachables.add(key);
			}
		}
		return reachables;
	}

	public int getCost(int from, int to) {
		return grid[from][to];
	}

	public int getCost(String from, String to) {
		return grid[vertices.get(from)][vertices.get(to)];
	}

	public void allPairsWeighted() {
		for (int intermediate = 0; intermediate < grid.length; intermediate++) {
			for (int r = 0; r < grid.length; r++) {
				for (int c = 0; c < grid[0].length; c++) {
					if (r != c) {
						if (grid[r][intermediate] != 9999 && grid[intermediate][c] != 9999)
							grid[r][c] = grid[r][intermediate] + grid[intermediate][c] < grid[r][c] ? grid[r][intermediate] + grid[intermediate][c] : grid[r][c];
					}
				}
			}
		}
	}
}
