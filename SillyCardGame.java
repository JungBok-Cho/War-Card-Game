/*
 * JungBok Cho
 * War Card Game in Java
 */
import java.util.Scanner;

/**
 * This is a program to play the Silly Card Game.
 * 
 * @author  JungBok Cho
 * @version 1.0
 */
public class SillyCardGame {
	
	/**
	 * Play the game, using GameModel, RenderQueue, Stack classes.
	 * 
	 * @param args A string array containing the command line arguments.
	 */
	public static void main(String[] args) {
		System.out.println("\nWelcome to the Silly Card Game!\n");
		playAgain();
		System.out.println("Thank you for playing this game!\n");
	}
	
	
	/**
	 * Check whether user wants to play this program again or not
	 */
	private static void playAgain() {
		final String YES = "y";	   // Constant to repeat the program
		String again = "y";	   // Variable to repeat the game
		int numOfPlayers = 0;	   // The number of players
		
		// Create a Scanner object and an array of player names
		Scanner keyboard = new Scanner(System.in);
		String[] playerNames = null;
		
		// Process these if user wants to play again
		do {
			// Call getNumOfPlayers
			numOfPlayers = getNumOfPlayers(keyboard, numOfPlayers);
			
			playerNames = new String[numOfPlayers];
			
			// Call getPlayerNames method to get players' names
			getPlayerNames(keyboard, playerNames);
			
			System.out.println("\nLet's start the game.\n");
			
			// Call startGame method
			System.out.println(startGame(keyboard, playerNames));
			
			// Ask if user wants to try again
			System.out.print("\nWould you like to try again? (y/n) ");
			again = keyboard.nextLine();
			System.out.println();
		} while(again.equalsIgnoreCase(YES));
		
		// Close keyboard
		keyboard.close();
	}
	
	
	/**
	 * Ask user input the number of players
	 * 
	 * @return numOfPlayers  The number of players
	 */
	private static int getNumOfPlayers(Scanner keyboard, int numOfPlayers) {
		do {
			System.out.print("Enter the number of players: ");
			numOfPlayers = keyboard.nextInt();
			if(numOfPlayers < 2 || numOfPlayers > 6) {
				System.out.println("This game allows only 2 to 6 players,"
						   + " try again...\n");
			}
		} while(numOfPlayers < 2 || numOfPlayers > 6);
		keyboard.nextLine();
		return numOfPlayers;
	}
	
	
	/**
	 * Ask user input the players' names
	 * 
	 * @param keyboard	Object of Scanner
	 * @param playerNames	The array of the player names
	 */
	private static void getPlayerNames(Scanner keyboard, String[] playerNames) {
		for(int i = 0; i < playerNames.length; i++) {
			System.out.print("Enter the name of player " + (i + 1) + ": ");
			playerNames[i] = keyboard.nextLine();
		}
	}
	
	
	/**
	 * Start the game
	 * 
	 * @param keyboard	Object of Scanner
	 * @param playerNames	The array of the player names
	 * @return   Return a message if there is a winner
	 */
	private static String startGame(Scanner keyboard, String[] playerNames) {
		
		final int CARDMAX = 52;			// The total number of the cards
		final int NOWINNER = -1;		// If there is no winner, give -1
		int winner = -1,			// Check who is the winner
		    discardCard,			// To hold a value of discard card
		    currentCard;			// To hold a value of current card
		int numOfPlayers = playerNames.length;	// Number of Players	
		
		// Create GameModel object
		GameModel gameModel = new GameModel(CARDMAX, numOfPlayers);
		
		// Call giveFirstDeck method
		gameModel.giveFirstDeck(numOfPlayers);
		
		// Process these until the program finds a winner
		do {
			// Process these for each player
			for(int i = 0; i < playerNames.length; i++) {
				// Show the player's deck
				System.out.println(playerNames[i] + "'s turn, cards: ");
				System.out.println(gameModel.showCardList(i));
				
				// Show the discard card
				discardCard = gameModel.showDiscardCard();
				System.out.println("Discard pile card: " + discardCard);
				
				// Show the current card
				currentCard = gameModel.showCurrentCard(i);
				System.out.println("Your current card: " + currentCard);
				System.out.print("Press RETURN to take a turn. "); 
				keyboard.nextLine();
				
				// Change the discard card
				gameModel.changeDiscardCard(i);
				
				// Check if the current card is higher than the discard card
				winner = checkDiscardCurrentCard(discardCard, currentCard, 
								 gameModel, i, winner, playerNames);			
				if(winner != NOWINNER) {
					return "\nThe game has finished.";
				}		
			}
		} while(winner == NOWINNER);
		
		return "\nThe game has finished.";
	}
	
	
	/**
	 * Check if the current card is higher than the discard card,
	 * then print out a message
	 * 
	 * @param discardCard	To hold a value of discard card
	 * @param currentCard	To hold a value of current card
	 * @param gameModel	GameModel object
	 * @param index		The index of the player in the array
	 * @param winner	The winner's index
	 * @param playerNames	The array of the player names
	 * @return   Return the index of player names 
	 * 	     if there is a winner, otherwise return NOWINNER
	 */
	private static int checkDiscardCurrentCard(int discardCard, int currentCard, 
						   GameModel gameModel, int index, int winner,
						   String[] playerNames) {
		
		final int NOWINNER = -1;   // If there is no winner
		
		// After checking which one is higher, call afterCheck method
		if(discardCard > currentCard) {
			System.out.println("Your card is LOWER, pick up 2 cards.\n");
			gameModel.afterCheck(-2, index);
			return NOWINNER;
		} else if(discardCard == currentCard) {
			System.out.println("It is even, pick up 1 card.\n");
			gameModel.afterCheck(-1, index);
			return NOWINNER;
		} else {
			// Check if there is a winner
			winner = gameModel.checkWinner();
			if (winner != NOWINNER) {
				System.out.println(playerNames[winner] + " have won the game!");
				return winner;
			}
			System.out.println("Your card is HIGHER, turn is over.\n");
			return NOWINNER;
		}
	}
	
}
