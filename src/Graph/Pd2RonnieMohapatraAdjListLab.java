package Graph;

//name:    date:
//resource classes and interfaces
//for use with Graphs3: EdgeList
//           Graphs4: DFS-BFS
//           Graphs5: EdgeListCities

import java.io.*;
import java.util.*;

/********************* Graphs 3: EdgeList *******************************/
interface VertexInterface {
	public String toString(); // just return the name

	public String getName();

	public ArrayList<Vertex> getAdjacencies(); // returns all neighbors of the vertex

	public void addEdge(Vertex targetVertex); // the edge is: (this, targetVertex)
}

/***********************************************************/
class Vertex implements VertexInterface {
	private final String name;
	private ArrayList<Vertex> adjacencies = new ArrayList<Vertex>();

	public Vertex(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public ArrayList<Vertex> getAdjacencies() {
		return adjacencies;
	}

	public void addEdge(Vertex targetVertex) {
		adjacencies.add(targetVertex);
	}

	public boolean equals(Object other) {
		Vertex otherVertex = (Vertex)other;
		return this.name.equals(otherVertex.getName());
	}
	
	public String toString() {
		ArrayList<String> adjacenciesNameList = new ArrayList<String>();
		for (Vertex v : adjacencies)
			adjacenciesNameList.add(v.getName());

		return this.name + " " + adjacenciesNameList.toString();
	}
}

interface AdjListInterface {
	public List<Vertex> getVertices();

	public Vertex getVertex(int i);

	public Vertex getVertex(String vertexName);

	// name to integer map
	public Map<String, Integer> getVertexMap();

	// we use the next two methods to create the Adjacency List structure
	public void addVertex(String v);

	// Note: there is an addEdge method in the Vertex class
	// Logic: if "source" is not in the list, add it to the vertex list
	// if "target" is not in the list, add it to the vertex list
	// Get the adjacency list of "source"
	public void addEdge(String source, String target);

	// We use the toString method to display the adjacency list structure
	// See the sample run of AdjList_3_Driver.java
	public String toString();
}

/********************* Graphs 4: DFS and BFS ****************************/
interface DFS_BFS {
	// the user program calls the method below--iterative version of dfs that
	// uses a stack
	public List<String> depthFirstSearch(String name);

	// the method below will be called by the method above
	public List<String> depthFirstSearch(Vertex v);

	public List<String> breadthFirstSearch(String name);

	public List<String> breadthFirstSearch(Vertex v);

	// If you like, you can also implement the recursive version of dfs
	// public List<Vertex> depthFirstRecur(String name);
	// public List<Vertex> depthFirstRecur(Vertex v);
	// the parameter "reachable" stores all vertices that are reachable from "v"
	// public void depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable);
}

/**************** Graphs 5: EdgeList with Cities *********/
interface EdgeListWithCities {
	public void graphFromEdgeListData(String fileName) throws FileNotFoundException;

	public int edgeCount();

	// use the result of dfs (source) to see if "target" is reachable from
	// "source" or not.
	public boolean isReachable(String source, String target);

	// return true if every vertex is reachable from every
	// other vertex, otherwise false. Hint: Use a nested for loops + call
	// dfs for every vertex and call method isReachable.
	public boolean isFullyReachable();
}

/*******************************************************/
public class Pd2RonnieMohapatraAdjListLab implements AdjListInterface, DFS_BFS, EdgeListWithCities {
	private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	private Map<String, Integer> nameToIndex = new TreeMap<String, Integer>();

	public List<Vertex> getVertices() {
		return vertices;
	}

	public Vertex getVertex(String vertexName) {
		for (Vertex v : vertices) {
			if (v.equals(new Vertex(vertexName)))
				return v;
		}
		return null;
	}

	public Vertex getVertex(int i) {
		String vertexName = "";
		for (String key : nameToIndex.keySet()) {
			if (nameToIndex.get(key) == i)
				vertexName = key;
		}

		return getVertex(vertexName);
	}


	public Map<String, Integer> getVertexMap() {
		return nameToIndex;
	}

	public void addVertex(String v) {
		for (Vertex vertex : vertices) {
			if (vertex.getName().equals(v))
				return;
		}
		vertices.add(new Vertex(v));
		nameToIndex.put(v, vertices.size() - 1);
	}

	public void addEdge(String source, String target) {
		if (getVertex(source) == null)
			addVertex(source);

		if (getVertex(target) == null)
			addVertex(target);

		getVertex(source).addEdge(getVertex(target));
	}

	public List<String> depthFirstSearch(Vertex v) {
		List<String> dft = new ArrayList<String>();
		Stack<Vertex> s = new Stack<Vertex>();
		s.push(v);
		while (!s.isEmpty()) {
			Vertex current = s.pop();
			if (!dft.contains(current.getName()))
				dft.add(current.getName());

			for (Vertex adj : current.getAdjacencies()) {
				if (!dft.contains(adj.getName()))
					s.push(adj);
			}
		}

		return dft;
	}

	public List<String> depthFirstSearch(String name) {
		return depthFirstSearch(getVertex(name));
	}

	public List<String> breadthFirstSearch(Vertex v) {
		List<String> bfs = new ArrayList<String>();
		Queue<Vertex> q = new LinkedList<Vertex>();
		q.add(v);

		while (!q.isEmpty()) {
			Vertex current = q.remove();
			if (!bfs.contains(current.getName()))
				bfs.add(current.getName());
			for (Vertex adj : current.getAdjacencies()) {
				if (!bfs.contains(adj.getName()))
					q.add(adj);
			}
		}

		return bfs;
	}

	public List<String> breadthFirstSearch(String name) {
		return breadthFirstSearch(getVertex(name));
	}

	public void graphFromEdgeListData(String fileName) throws FileNotFoundException {
		FileReader fr = new FileReader(new File(fileName));
		BufferedReader br = new BufferedReader(fr);
		try {
			while (br.ready()) {
				String line = br.readLine();
				String source = line.substring(0, line.indexOf(' '));
				String target = line.substring(line.indexOf(' ') + 1);
				addEdge(source, target);
			}
		} catch (IOException e) {
			e.getStackTrace();
		}
	}

	public int edgeCount() {
		int count = 0;
		for(Vertex v : vertices) {
			count += v.getAdjacencies().size();
		}
		
		return count;
	}
	
	public boolean isReachable(String source, String target) {
		return depthFirstSearch(source).contains(target);
	}

	public boolean isFullyReachable() {
		for (Vertex source : vertices) {
			for (Vertex target : vertices) {
				if (!isReachable(source.getName(), target.getName()))
					return false;
			}
		}
		return true;
	}

	public String toString() {
		String result = "";
		for (Vertex v : vertices)
			result += v.toString() + "\n";

		return result;
	}
}
