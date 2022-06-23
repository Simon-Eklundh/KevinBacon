import java.util.*;

public class UndirectedGraph<T> {
	// Nodes and what they're connected to
	private final HashMap<T, ArrayList<T>> graph = new HashMap<>();


	public boolean add(T nodeValue) {
		if (graph.containsKey(nodeValue)) return false;
		graph.put(nodeValue, new ArrayList<>());
		return true;
	}

	public void connect(T nodeOne, T nodeTwo, int i) {
		graph.get(nodeOne).add(nodeTwo);
		graph.get(nodeTwo).add(nodeOne);
	}

	public List<T> breadthFirstSearch(T start, T end) {
		System.out.println(start);
		System.out.println(end);
		if (!graph.containsKey(start) || !graph.containsKey(end)) throw new IllegalStateException();

		if (start.equals(end)) return new LinkedList<>();
		// backwards
		HashMap<T, T> path = new HashMap<>();
		Queue<T> frontier = new LinkedList<>();

		frontier.add(start);
		T current = null;
		while (true) {
			T last = current;
			current = frontier.poll();
			path.put(current, last);
			ArrayList<T> neighbours = graph.get(current);
			for (T node :
					neighbours) {
				if (node.equals(end)) {
					path.put(node, current);
					return pathCreator(path, end);
				}
				if (path.containsKey(node)) continue;
				frontier.add(node);
			}

		}
	}

	private List<T> pathCreator(HashMap<T, T> path, T end) {
		LinkedList<T> finalPath = new LinkedList<>();
		T node = path.get(end);
		finalPath.add(end);
		while (node != null) {
			finalPath.add(0, node);
			node = path.get(node);
		}
		return finalPath;
	}

	public boolean contains(T actor) {
		return graph.containsKey(actor);
	}

	@Override
	public String toString() {
		return graph.toString();
	}
}
