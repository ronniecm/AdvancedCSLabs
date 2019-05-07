package Graph;

//name:   date:

//driver for Graph 6, using AdjListWeighted   
import java.util.*;
import java.io.*;

public class Pd2RonnieMohapatraDijkstra_6_Driver {
	public static void main(String[] args) throws FileNotFoundException {
		/* Graphs 6 Dijkstra: hard-coded A-B-C-D */

		Pd2RonnieMohapatraAdjListWeighted graph = new Pd2RonnieMohapatraAdjListWeighted();
		graph.addVertex("A");
		graph.addVertex("B");
		graph.addVertex("C");
		graph.addVertex("D");
		System.out.println(graph.getVertices());
		graph.addEdge("A", "B", 9);
		graph.addEdge("A", "C", 3);
		graph.addEdge("C", "B", 5);
		graph.addEdge("C", "D", 2);
		graph.addEdge("D", "B", 1);
		Scanner key = new Scanner(System.in);
		System.out.print("Enter start: ");
		String source = key.next();
		graph.minimumWeightPath(source); // runs Dijkstra's Algorithm
		for (wVertex v : graph.getVertices()) {
			System.out.println("Distance to " + v + ": " + v.getMinDistance());
			System.out.println(graph.getShortestPathTo(v));
		}
	}
}

/* Output
[A, B, C, D]
Enter start: A
Distance to A: 0.0
Distance to B: 6.0
Distance to C: 3.0
Distance to D: 5.0
*/