import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class KevinBacon {
	private static final String KEVIN_BACON = "kevin bacon (i)";
	private final UndirectedGraph<String> graph = new UndirectedGraph<>();

	public KevinBacon() {
		getData();
	}

	/**
	 * Gets the data from the file and creates the graph.
	 */
	private void getData() {
		System.out.println("Loading data...");
		try (BufferedReader br = Files.newBufferedReader(Path.of("movieDataSmall.txt"))) { // 1
			String lastActor = null;
			String line;
			HashMap<String, ArrayList<String>> MovieActors = new HashMap<>();
			while ((line = br.readLine()) != null) {
				if (line.charAt(1) == 'a') {
					lastActor = formatLine(line);
					graph.addNode(lastActor);
				} else if (line.charAt(1) == 't') {
					String movie = formatLine(line);
					if (MovieActors.containsKey(movie)) {
						MovieActors.get(movie).add(lastActor);
					} else {
						ArrayList<String> actors = new ArrayList<>();
						actors.add(lastActor);
						MovieActors.put(movie, actors);
					}
				}
			}
			System.out.println("connecting graph");
			for (String movie : MovieActors.keySet()) {
				for (String actor : MovieActors.get(movie)) {
					for (String otherActor : MovieActors.get(movie)) {
						if (!actor.equals(otherActor)) {
							graph.connect(actor, otherActor, movie);
						}
					}
				}
			}


		} catch (IOException e) {
			System.out.println("Error: Couldn't find the data file!");
			e.printStackTrace();
		}
		System.out.println("Data loaded!");
	}


	/**
	 * Formats the line so the actor's first name is before the last name.
	 * as well as remove the <a> or <t> tags and trailing whitespaces.
	 *
	 * @param line the line to format.
	 * @return the formatted line.
	 */
	private String formatLine(String line) {
		boolean actor = line.charAt(1) == 'a';
		line = line.substring(3).toLowerCase();
		if (actor) {
			// if line contains (i, it implies there are multiple actors with the same name, so we move the (i) to the end.
			if (line.contains("(i") && line.contains(",")) {
				line = line.split(",")[1].split(" ")[1] + ' ' + line.split(",")[0] + ' ' + line.split(",")[1].split(" ")[2];
			} else {
				if (line.charAt(line.length() - 1) == ',') {
					line = line.substring(0, line.length() - 1);
				}
				if (line.contains(",")) {
					line = line.split(",")[1] + ' ' + line.split(",")[0];
				}
			}
		}
		return line.trim().toLowerCase();
	}

	/**
	 * Prints the bacon number of the given actor.
	 *
	 * @param actor the actor to find the bacon number of.
	 */
	public void printBaconNumber(String actor) {

		if (!graph.contains(actor)) {
			System.out.println("Error: Actor doesn't exist!");
			return;
		}

		Pair<String, Integer> path = graph.breadthFirstSearch(actor, KEVIN_BACON);
		if (path == null || path.getValue() == null || path.getKey() == null) {
			System.out.println("Error: Kevin Bacon number doesn't exist!");
			return;
		}

		System.out.println(actor + " is " + path.getValue() + " steps away from Kevin Bacon.");
		System.out.println("Path: " + path.getKey());
	}

	/*
	Example of output for the movieDataSmall.txt file:
	? Garbo, Greta
	"Garbo, Greta" is 2 steps away from Kevin B. The path is <a>Bacon, Kevin
	(I)<a><t>FrostNixon (2008)<t><a>Ford, Gerald (I)<a><t>Brother Can You Spare a Dime
	(1975)<t><a>Garbo, Greta<a>
	 */
}
