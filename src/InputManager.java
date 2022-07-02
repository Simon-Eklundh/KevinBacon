/*
 * @author Markus Larsson mala3165
 */

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The InputManager class is used to read input from users.
 * it was taken from Markus Larsson's code. I did not write this class.
 *
 * @author Markus Larsson ( <a href="https://github.com/MarkusLarsson421/KevinBacon">...</a> )
 */
public class InputManager {

	private static final ArrayList<InputStream> inputStreams = new ArrayList<>();
	private final Scanner scanner;

	public InputManager() {
		this(System.in);
	}

	public InputManager(InputStream inputStream) {
		if (inputStreams.contains(inputStream)) {
			throw new IllegalStateException("Error: Illegal state exception!");
		}
		inputStreams.add(inputStream);
		scanner = new Scanner(inputStream);
	}

	public int getInt(String question) {
		askUser(question);
		int output = scanner.nextInt();
		scanner.nextLine();
		return output;
	}

	public double getDouble(String question) {
		askUser(question);
		double output = scanner.nextDouble();
		scanner.nextLine();
		return output;
	}

	public char getChar(String question) {
		String output;

		do {
			output = getString(question);
			if (output.length() > 1) {
				System.out.println("Error: Input is not one character!");
			}
		} while (output.length() > 1);

		return output.charAt(0);
	}

	public String getString(String question) {
		String output;

		do {
			askUser(question);
			output = scanner.nextLine().trim();
			if (output.isEmpty()) {
				System.out.println("Error: Input is empty!");
			}
		} while (output.isEmpty());

		return output;
	}

	//---HELPER METHODS---

	private void askUser(String input){
		System.out.print(input + "?> ");
	}
}
