/*
 * JungBok Cho
 * War Card Game in Java
 */
package choj8_p3X;
import java.util.ArrayList;
import java.util.Random;

/**
 * This is a program to create all of the game logic.
 * 
 * @author  JungBok Cho
 * @version 1.0
 */
public class GameModel {
	
	private ArrayList<Integer> cards;	// ArrayList of cards
	private Stack<Integer> dealer;		// Stack of deal deck
	private Stack<Integer> discardPile;	// Stack of discard pile deck
	private int sizeOfCard;			// The size of the card
	private int numOfPlayers;		// The number of players
	
	// Create ArrayList and RenderQueue objects
	private ArrayList<RenderQueue<Integer>> players;
	private RenderQueue<Integer> temp;
	
	
	/**
	 * Constructor of GameModel
	 * 
	 * @param sizeOfCard  	The Size of the card
	 * @param numOfPlayers  The number of players
	 */
	public GameModel(int sizeOfCard, int numOfPlayers) {
		this.sizeOfCard = sizeOfCard;
		this.numOfPlayers = numOfPlayers;
		
		// Initialize ArrayList, Stacks, and Queues
		cards = new ArrayList<Integer>(sizeOfCard);
		dealer = new Stack<Integer>(sizeOfCard);
		discardPile = new Stack<Integer>(sizeOfCard);
		
		// Initialize ArrayList and RenderQueue objects
		players = new ArrayList<RenderQueue<Integer>>(numOfPlayers);
		for(int i = 0; i < numOfPlayers; i++) {
			temp = new RenderQueue<Integer>();
			players.add(temp);
		}
		createDeck();	// Call createDeck method
	}
	
	
	/**
	 * Create the first deck
	 * 
	 * @param numOfPlayers  The number of players
	 */
	public void giveFirstDeck(int numOfPlayers) {
		for(int i = 0 ; i < numOfPlayers; i++) {
			temp = new RenderQueue<Integer>();
			for(int j = 0; j < 7; j++) {
				players.set(i, temp);
				temp.enqueue(dealer.pop());
			}
		}
		discardPile.push(dealer.pop());
	}
	
	
	/**
	 * Get a player's deck
	 * 
	 * @param index  The index of the player in the array
	 * @return  Return the player's deck
	 */
	public String showCardList(int index) {
		temp = players.get(index);
		return temp.toString();	
	}
	
	
	/**
	 * Show the discard card
	 * 
	 * @return  Return the first card in the discardPile stack
	 */
	public int showDiscardCard() {
		return discardPile.peek();
	}
	
	
	/**
	 * Show a current card of a player
	 * 
	 * @param index  The index of the player in the array
	 * @return   Return a current card of a player
	 */
	public int showCurrentCard(int index) {
		temp = players.get(index);
		return temp.peek();
	}
	
	
	/**
	 * Change the discard card
	 * 
	 * @param index  The index of the player in the array
	 */
	public void changeDiscardCard(int index) {
		temp = players.get(index);
		discardPile.push(temp.dequeue());
	}
	
	
	/**
	 * Let the players take cards
	 * 
	 * @param result  The number of cards that the player needs to take
	 * @param index   The index of the player in the array
	 */
	public void afterCheck(int result, int index) {
		temp = players.get(index);
		emptyDeck();
		if(result == -2) {
			temp.enqueue(dealer.pop());
			emptyDeck();
			temp.enqueue(dealer.pop());
		} else if(result == -1) {
			temp.enqueue(dealer.pop());
		} 
	}
	
	
	/**
	 * Check who is the winner
	 * 
	 * @return   Return the index of the player, otherwise return -1
	 */
	public int checkWinner() {
		for(int i = 0; i < numOfPlayers; i++) {
			temp = players.get(i);
			if(temp.empty()) {
				return i;
			}
		}
		return -1;
	}  
	
	
	/**
	 * Create a deck at the beginning
	 */
	private void createDeck() {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 13; j++) {
				cards.add(j + 1);
			}
		}
		// Shuffle the deck
		shuffleDeck(cards);
		
		for(int i = 0; i < sizeOfCard; i++) {
			dealer.push(cards.get(i));
		}	
	}
	
	
	/**
	 * Shuffles the cards using the
	 * <a href="https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle">
	 * Fisher-Yates algorithm</a>
	 * 
	 * @param cards  Deck to shuffle
	 */
	private void shuffleDeck(ArrayList<Integer> cards) {
	    Random rand = new Random();
	    for (int i = cards.size(); i > 1; i--) {
	        int j = rand.nextInt(i);
	        int temp = cards.get(i - 1);
	        cards.set(i - 1, cards.get(j));
	        cards.set(j, temp);
	    }
	}
	
	
	/**
	 * Check if the deal's deck is empty
	 */
	private void emptyDeck() {
		int turnOverCard;  // The card to keep in the discardPile stack
		
		if(dealer.empty()) {
			turnOverCard = discardPile.pop();
			for(int j= 0; j < discardPile.size(); j++) {
				dealer.push(discardPile.pop());
			}
			discardPile.push(turnOverCard);
		}
	}
	
}
