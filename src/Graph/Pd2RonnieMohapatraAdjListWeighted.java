package Graph;

//name:   date: 
//resource classes for Graphs6: Dijkstra
//                   Graphs7: Dijkstra with Cities

import java.io.*;
import java.util.*;

class Edge {
	public final wVertex target;
	public final double weight;

	public Edge(wVertex argTarget, double argWeight) {
		target = argTarget;
		weight = argWeight;
	}
}

interface wVertexInterface {
	public String toString();

	public String getName();

	public double getMinDistance();

	public void setMinDistance(double m);

	// public wVertex getPrevious(); //for Dijkstra 7
	// public void setPrevious(wVertex v); //for Dijkstra 7
	public ArrayList<Edge> getAdjacencies();

	public void addEdge(wVertex v, double weight);

	public int compareTo(wVertex other);
}

class wVertex implements Comparable<wVertex>, wVertexInterface {
	private final String name;
	private ArrayList<Edge> adjacencies;
	// The reason why we have the two "unexpected" fields in the wVertex
	// class below is that when we run Dijkstra's algorithm, we want to
	// obtain two things:
	// 1. The minimum distance of a vertex from the source vertex.
	// 2. The path of the minimum distance from the source to the target vertex.

	private double minDistance = Double.POSITIVE_INFINITY;
	private wVertex previous; // for building the actual path in Dijkstra 7
	// uncomment this part when you do Graph 7

	public wVertex(String name) {
		this.name = name;
		adjacencies = new ArrayList<Edge>();
	}

	public String getName() {
		return this.name;
	}

	public double getMinDistance() {
		return minDistance;
	}

	public wVertex getPrevious() {
		return this.previous;
	}

	public void setMinDistance(double m) {
		this.minDistance = m;
	}

	public ArrayList<Edge> getAdjacencies() {
		return this.adjacencies;
	}

	public void addEdge(wVertex v, double weight) {
		adjacencies.add(new Edge(v, weight));
	}

	public int compareTo(wVertex other) {
		if (this.minDistance > other.getMinDistance())
			return 1;
		else if (this.minDistance < other.getMinDistance())
			return -1;
		else
			return 0;
	}

	public boolean equals(Object other) {
		wVertex otherVertex = (wVertex) other;
		return this.name.equals(otherVertex.getName()) && this.minDistance == otherVertex.getMinDistance();
	}

	public String toString() {
		return this.name;
	}

	public int hashCode() {
		return (int) minDistance;
	}
}

interface AdjListWeightedInterface {
	public List<wVertex> getVertices();

	public Map<String, Integer> getNameToIndex();

	public wVertex getVertex(int i);

	public wVertex getVertex(String vertexName);

	public void addVertex(String v);

	public void addEdge(String source, String target, double weight);

	// This method does 2 things:
	// 1. Set the minimum distance field of all other vertices from the
	// source vertex.
	// 2. Set the previous vertext field. This is for tracing the path
	// from the source to the target vertex. The method
	// getShortestPathTo (target) in line #73 needs to use this field in order
	// to return the PATH from source to target.
	// SetRead Dijkstra_6_Driver.java to see how this
	// method is called
	public void minimumWeightPath(String sourceVertexName); // Dijkstra's
}

/* Graphs 7 */
interface AdjListWeightedInterfaceWithCities {
	public List<wVertex> getShortestPathTo(wVertex v);

	public Pd2RonnieMohapatraAdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData)
			throws FileNotFoundException;
}

public class Pd2RonnieMohapatraAdjListWeighted implements AdjListWeightedInterface// ,
																					// AdjListWeightedInterfaceWithCities
{
	private List<wVertex> vertices = new ArrayList<wVertex>();
	private Map<String, Integer> nameToIndex = new HashMap<String, Integer>();

	public List<wVertex> getVertices() {
		return vertices;
	}

	public Map<String, Integer> getNameToIndex() {
		return nameToIndex;
	}

	public wVertex getVertex(String vertexName) {
		return getVertex(nameToIndex.get(vertexName));
	}

	public wVertex getVertex(int i) {
		String vertexName = "";
		for (String key : nameToIndex.keySet()) {
			if (nameToIndex.get(key) == i)
				vertexName = key;
		}

		for (wVertex v : vertices) {
			if (v.getName().equals(vertexName))
				return v;
		}

		return null;
	}

	public void addVertex(String v) {
		vertices.add(new wVertex(v));
		nameToIndex.put(v, vertices.size() - 1);
	}

	public void addEdge(String source, String target, double weight) {
		getVertex(source).addEdge(getVertex(target), weight);
	}

	public void minimumWeightPath(String sourceVertexName) {
		wVertex source = getVertex(sourceVertexName); // get source vertex
		source.setMinDistance(0.0); // set its minimum distance to 0
		ArrayList<wVertex> unsettled = new ArrayList<wVertex>(vertices); //using an ArrayList for the unchecked instead of PriorityQueue because the PriorityQueue did not work
		//PriorityQueue<wVertex> pq = new PriorityQueue<wVertex>(vertices);
		
		//while(!pq.isEmpty()) {
		while (!unsettled.isEmpty()) { //loop until all vertices have been checked
			//wVertex current = pq.remove();
			wVertex current = unsettled.remove(0); //unsettled used as a PriorityQueue where vertex with least minimum distance is at the head
			for (Edge e : current.getAdjacencies())
				//if(pq.contains(e.target))
				if (unsettled.contains(e.target))
					if (current.getMinDistance() + e.weight < e.target.getMinDistance())
						e.target.setMinDistance(current.getMinDistance() + e.weight); //loop through all neighbors of currents and update the minimum distance if the distance from current to neighbor is less than the neighbor's minimum distance from source
			putMinAtHead(unsettled); //put the vertex with the least minimum distance at the head of the unchecked list
		}
	}

	private void putMinAtHead(List<wVertex> vertexList) {
		if (!vertexList.isEmpty()) {
			int minInd = 0;
			for (int i = 0; i < vertexList.size(); i++) {
				if (vertexList.get(i).getMinDistance() < vertexList.get(minInd).getMinDistance())
					minInd = i;
			}

			wVertex temp = vertexList.get(0);
			vertexList.set(0, vertexList.get(minInd));
			vertexList.set(minInd, temp);
		}
	}
}