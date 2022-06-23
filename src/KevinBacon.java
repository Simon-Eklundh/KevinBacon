import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class KevinBacon {
	private static final String KEVIN_BACON = "bacon, kevin (i)";
	private final UndirectedGraph<String> graph = new UndirectedGraph<>();

	public KevinBacon() {
		getData();
	}

	private void getData() {
		try (BufferedReader br = Files.newBufferedReader(Path.of("movieDataSmall.txt"))) {
			String lastActor = null;
			String line;
			while ((line = br.readLine()) != null) {
				if (line.charAt(1) == 'a') {
					line = formatLine(line);
					graph.add(line);
					lastActor = line;
					System.out.println("Actor: " + line);
				} else if (line.charAt(1) == 't') {
					String movie = formatLine(line);
					graph.add(movie);
					graph.connect(lastActor, movie, 1);
					System.out.println("Movie: " + movie);
				}
			}
		} catch (IOException e) {
			System.out.println("Error: Couldn't find the data file!");
			e.printStackTrace();
		}
	}

	private String formatLine(String line) {
		line = line.substring(3);
		line = line.trim().toLowerCase();
		return line;
	}

	public void printBaconNumber(String actor) {
		System.out.println(graph);


		if (!graph.contains(actor)) {
			System.out.println("Error: Actor doesn't exist!");
			return;
		}

		List<String> path = graph.breadthFirstSearch(actor, KEVIN_BACON);

		System.out.println(actor + " is " + path.size() / 2 + " steps away from Kevin Bacon.");
		System.out.println("Path: " + path);
	}

	/*
	Example of output:
	? Garbo, Greta
	"Garbo, Greta" is 2 steps away from Kevin B. The path is <a>Bacon, Kevin
	(I)<a><t>FrostNixon (2008)<t><a>Ford, Gerald (I)<a><t>Brother Can You Spare a Dime
	(1975)<t><a>Garbo, Greta<a>
	 */
}
