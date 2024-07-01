package MVP;

import MVP.GameFunctions.Game;
import MVP.Player.Player;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuHandler {

    private final Scanner scanner=new Scanner(System.in);
    private final Game game=new Game();


    public MenuHandler() {
    }

    /**
     * Starts the menu loop, displaying options to the user and handling their input.
     * This method runs indefinitely until the user chooses to exit.
     *
     * @author Lorenzo
     * STILL DEVELOPING
     */
    public void start() {
        while (true) {
            System.out.println("\n=== MVP RACING GAME ===");
            System.out.println("1. Play game(4 players required)");
            System.out.println("2. Exit\n");

            int input = getUserInput(1, 2);
            scanner.nextLine();

            switch (input) {
                case 1:
                    System.out.println("\n\n Welcome to the game :D\n");
                    do {
                        game.gameStart();
                        handlePlayerTurns();
                        System.out.println(game.getReadyPlayerQueue().toString());
                    } while (!game.gameEnd());
                    break;
                case 2:
                    System.exit(0);
                    break;
            }
        }
    }
    /**
     * Handles the turns of the players during the game.
     * This method manages the main game loop, allowing each player to take their turn,
     * handling player input for rolling dice, abandoning the game, and checking game status.
     * <br>The game loop continues until the game ends, which happens when there are no players left.
     * Each iteration of the loop processes the current player's action. The current player
     * can choose to roll the dice, abandon the game, or check the game status. The player
     * queue is cycled so that each player gets a turn.
     *
     * @author Deyber
     */
    private void handlePlayerTurns() {
        while (!game.gameEnd()) {
            Player currentPlayer = game.getReadyPlayerQueue().getHead().getData();
            System.out.println("\nCurrent player: " + currentPlayer.getName() +
                    "\nCurrent position: " + currentPlayer.getPosition());
            System.out.println("1. Roll the dice\n2. Abandon game\n3. Game Status");

            int option = getUserInput(1, 3);
            scanner.nextLine(); // Clear the input buffer

            switch (option) {
                case 1:
                    game.handleTurn(currentPlayer);
                    game.loopCyclePlayers();
                    break;
                case 2:
                    game.removePlayerFromQueue(currentPlayer.getName());
                    break;
                case 3:
                    game.listCurrentPlayers();
                    break;
                default:
                    System.out.println("Invalid input. Please enter a valid integer between 1 and 3.");
                    break;
            }
            System.out.println("There are no players left. Game is ending...");
        }
    }


    /**
     * Prompts the user to enter an integer input for menu selection, ensuring the input is valid.
     * The valid input is an integer between min and max.
     *
     * @param min the minimum valid integer input
     * @param max the maximum valid integer input
     * @return the valid integer input from the user
     */
    private int getUserInput(int min, int max) {
        int input = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Enter an integer selecting the option: ");
                input = scanner.nextInt();
                if (input >= min && input <= max) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input. Please enter a valid integer between " + min + " and " + max + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer between " + min + " and " + max + ".");
                scanner.nextLine();
            }
        }

        return input;
    }
}
