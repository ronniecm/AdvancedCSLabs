package Graph;

//uses AdjMat
import java.util.*;
import java.io.*;

public class WarshallDriver {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner kb = new Scanner(System.in);
		System.out.print("Warshall's Algorithm! Enter file of names: ");
		// cities
		String fileNames = kb.next() + ".txt";
		Scanner sc = new Scanner(new File(fileNames));
		int size = sc.nextInt();
		Pd2RonnieMohapatraAdjMatLab g = new Pd2RonnieMohapatraAdjMatLab(size);
		g.readNames(fileNames);
		System.out.print("Enter file of the matrix: "); // citymatrix
		String fileGrid = kb.next() + ".txt";
		g.readGrid(fileGrid);
		System.out.println("Adjacency Matrix");
		g.displayGrid();
		System.out.println("Number of Edges: " + g.edgeCount());
		System.out.println();

		g.allPairsReachability(); // runs Warshall's algorithm
		g.displayVertices();
		System.out.println();
		System.out.println("Reachability Matrix");
		g.displayGrid();
		System.out.println("Number of Edges: " + g.edgeCount());

		while (true) {
			System.out.print("\nIs it reachable?  Enter name of start city (-1 to exit): ");
			String from = kb.next().trim();
			if (from.equals("-1"))
				break;
			System.out.print("                Enter name of end city: ");
			String to = kb.next().trim();
			System.out.println(g.isEdge(from, to));
		}

		// Extension
		System.out.println("\n================== EXTENSION ==================");
		System.out.println("List of every city's reachable cities: ");
		for (String city : g.getVertices().keySet())
			System.out.println(city + "--> " + g.getReachables(city));

		while (true) {
			System.out.print("\nList the reachable cities from: ");
			String from = kb.next();
			if (from.equals("-1"))
				break;
			System.out.println(g.getReachables(from));
		}

	}
}