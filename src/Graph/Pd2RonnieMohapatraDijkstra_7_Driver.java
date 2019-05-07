package Graph;

//name:    date:

//driver for Graph 7 using AdjListWeighted  
//read from the given data file  
//             prints cities and paths   
import java.util.*;
import java.io.*;

public class Pd2RonnieMohapatraDijkstra_7_Driver {
	public static void main(String[] args) throws FileNotFoundException {
		Pd2RonnieMohapatraAdjListWeighted g = new Pd2RonnieMohapatraAdjListWeighted();
		g = g.graphFromEdgeListData(new File("cities.txt"), new File("cityEdgeListWeighted.txt"));
		Scanner key = new Scanner(System.in);
		System.out.print("Enter start: ");
		String source = key.next();
		g.minimumWeightPath(source); // runs Dijkstra's Algorithm
		for (wVertex v : g.getVertices()) {
			System.out.println("Distance to " + v + ": " + v.getMinDistance());
			List<wVertex> path = g.getShortestPathTo(v);
			System.out.println("                   Path: " + path);
		}
	}
}
/**************************************************
 * Enter start: Peoria Distance to Pendleton: 8.0 Path: [Peoria, Pueblo, Pierre,
 * Pendleton] Distance to Pensacola: 9.0 Path: [Peoria, Pittsburgh, Pensacola]
 * Distance to Peoria: 0.0 Path: [Peoria] Distance to Phoenix: 14.0 Path:
 * [Peoria, Pittsburgh, Pensacola, Phoenix] Distance to Pierre: 6.0 Path:
 * [Peoria, Pueblo, Pierre] Distance to Pittsburgh: 5.0 Path: [Peoria,
 * Pittsburgh] Distance to Princeton: Infinity Path: [Princeton] Distance to
 * Pueblo: 3.0 Path: [Peoria, Pueblo]
 * 
 ********************************************/
