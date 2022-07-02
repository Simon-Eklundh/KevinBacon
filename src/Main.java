public class Main {
	/*
	Kevin Bacon game.
	A program which asks for the name of an actor, and then calculates and prints said actor's bacon number.
	*/

	public static void main(String[] args) {
		programLoop();
	}

	/**
	 * Program loop. Runs until the user quits.
	 */
	public static void programLoop() {
		InputManager input = new InputManager();
		KevinBacon kb = new KevinBacon();

		boolean isRunning = true;
		while (isRunning) {
			System.out.println("Options: [K]evin Bacon, [E]xit.");
			char userInput = input.getChar("Command");

			switch (userInput) {
				case 'K', 'k' -> {
					String actor = input.getString("Actor");
					kb.printBaconNumber(actor);
				}
				case 'E', 'e' -> isRunning = false;
				default -> System.out.println("Error: Unknown command!");
			}
		}
	}
}
