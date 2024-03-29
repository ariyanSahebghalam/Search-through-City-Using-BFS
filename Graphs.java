import java.util.HashSet;
import java.util.Set;

// Added imports
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.Comparator;


/**
 * Class used for BFS search of an entire city depending on the limit of the numberOfEdges, and returning the
 * result in one of the methods as an ordered set.
 *
 * @author Ariyan Sahebghalam, Eyosyas Andarge, Sadiq Azmi
 */
public class Graphs {
	/**
	 * Description
	 *
	 * @param graph         input graph
	 * @param origin        starting vertex
	 * @param numberOfEdges the number of edges with respect to the origin allowed to search for vertices
	 * @return Set of vertices within the specified numberOfEdges
	 *
	 * Inspired by the Breadth First Implementation of this Youtube channel https://www.youtube.com/watch?v=9Oev6UgLhiI
	 * Its use of queue more specifically
	 */
	public static Set<String> nearby(Graph graph, String origin, int numberOfEdges) {
		//DUMMY CODE (STUB) TODO

		Comparator<String> comparator = new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				int number1 = Integer.parseInt(s1.charAt(s1.length() - 1) + "");
				int number2 = Integer.parseInt(s2.charAt(s2.length() - 1) + "");

				// Comparing the last digit of the strings
				int result = Integer.compare(number1, number2);

				// Intended to avoid duplicate number removal,
				// if the numbers are equal, compare the strings lexicographically
				if (result == 0) {
					return s1.compareTo(s2);
				}

				return result;
			}
		};

		Set<String> result = new TreeSet<>(comparator);


		// Hashmap to determine if the city vertices were searched before or not
		HashMap<String, Boolean> isMarked = new HashMap<>();

		for (String vertex : graph.vertices()) {
			isMarked.put(vertex, false);
		}

		// Queue for BFS
		Queue<String> queue = new LinkedList<>();

		// Queue for storing the level or the depth of the vertex relative to the origin vertex
		Queue<Integer> levelQueue = new LinkedList<>();

		queue.add(origin);
		levelQueue.add(0);

		String str = "";

		// Continues as long as there are adjacent vertices in the queue and the number of hops less than the numberOfEdges variable
		while (!queue.isEmpty()) {
			String current = queue.poll();
			int level = levelQueue.poll();

			// In case the level exceeds the numberOfEdges allowed, exit the loop
			if (level > numberOfEdges) {
				break;
			}

			// If the map has the city in it, and it is not visited
			if (isMarked.containsKey(current) && !isMarked.get(current)) {
				// Mark it as visited
				isMarked.put(current, true);
				// Create the string and add it onto the set
				str = current + ", " + level;
				result.add(str);
			}

			// All the neighbours of the current vertex
			List<Edge> edgeList = graph.adj.get(current);

			for (Edge edge : edgeList) {
				// Adding all the neighbours of the current vertex that have not been visited
				if (!isMarked.get(edge.other(edge.either()).name)) {
					queue.add(edge.other(edge.either()).name);
					levelQueue.add(level + 1);
				}
			}


		}

		// Removing the origin vertex that was added
		result.remove(origin + ", 0");

		return result;
	}
}


// private methods go here if needed
