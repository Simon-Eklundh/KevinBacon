import javafx.util.Pair;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class UndirectedGraph<T> {
	// Nodes and what they're connected to; node -> name of edge -> node
	private final HashMap<T, HashMap<String, T>> graph = new HashMap<>();

	/**
	 * Adds a node to the graph
	 *
	 * @param nodeValue
	 */
	public void addNode(T nodeValue) {
		graph.putIfAbsent(nodeValue, new HashMap<>());
	}

	/**
	 * Adds an edge to the graph
	 *
	 * @param nodeOne
	 * @param nodeTwo
	 * @param label
	 */
	public void connect(T nodeOne, T nodeTwo, String label) {
		graph.get(nodeOne).putIfAbsent(label, nodeTwo);
		graph.get(nodeTwo).putIfAbsent(label, nodeOne);
	}

	/**
	 * finds a path between start and end and returns it as a pair of a string and the amount of nodes
	 *
	 * @param start the node to start from
	 * @param end   the node to end at
	 * @return a pair of a string and the amount of nodes
	 */
	public Pair<String, Integer> breadthFirstSearch(T start, T end) {

		if (!graph.containsKey(start) || !graph.containsKey(end))
			throw new IllegalArgumentException("Start or end node not in graph");

		HashMap<T, Pair<String, T>> path = new HashMap<>();
		if (start.equals(end)) return new Pair<>(start.toString(), 0);
		Queue<T> queue = new LinkedList<>();
		queue.add(start);
		path.put(start, new Pair<>("", null));
		T current;
		while (!queue.isEmpty()) {
			current = queue.remove();
			// path.put(current, new Pair<>(path.get(previous).getKey(), previous));
			if (current.equals(end)) break;
			for (Map.Entry<String, T> entry : graph.get(current).entrySet()) {
				if (!path.containsKey(entry.getValue())) {
					queue.add(entry.getValue());
					path.put(entry.getValue(), new Pair<>(entry.getKey(), current));

				}
			}

		}
		return pathCreator(path, end);
	}

	/**
	 * creates a string version of the path and returns it together with the amount of nodes in the path
	 *
	 * @param path the path to create a string of
	 * @param end  the end node of the path
	 * @return a pair of a string and the amount of nodes
	 */
	private Pair<String, Integer> pathCreator(HashMap<T, Pair<String, T>> path, T end) {
		StringBuilder finalPath = new StringBuilder();
		Integer count = 0;
		String movie;
		T node = end;

		finalPath.append(end);

		do {
			count++;
			movie = path.get(node).getKey();
			node = path.get(node).getValue();
			finalPath.insert(0, " -> ");
			finalPath.insert(0, movie);
			finalPath.insert(0, " -> ");
			finalPath.insert(0, node);


		} while (!path.get(node).getKey().equals("") && path.get(node).getValue() != null);

		return new Pair<>(finalPath.toString(), count);
	}

	public boolean contains(T actor) {
		return graph.containsKey(actor);
	}

	@Override
	public String toString() {
		return graph.toString();
	}
}
