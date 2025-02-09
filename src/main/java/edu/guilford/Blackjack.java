package edu.guilford;

public class Blackjack {
    private Hand playerHand;
    private Hand dealerHand;
    private Deck deck;
    /**
     * * Constructor for Blackjack which resets the game and shuffles the deck
     */
    public Blackjack() {
        reset(true);
    }
    

    public Hand getPlayerHand() {
        return playerHand;
    }


    public Hand getDealerHand() {
        return dealerHand;
    }


    public Deck getDeck() {
        return deck;
    }

    /**
     * Resets the game by creating a new standard deck and shuffling it
     * @param newDeck
     */
    public void reset(boolean newDeck) {
        if (newDeck) {
            deck = new Deck();
            deck.shuffle();
        }
    }
    /**
     * Deals two cards to the player and dealer
     */
    public void deal() {
        playerHand = new Hand();
        dealerHand = new Hand();
        playerHand.addCard(deck.deal());
        dealerHand.addCard(deck.deal());
        playerHand.addCard(deck.deal());
        dealerHand.addCard(deck.deal());
    }
    /**
     * * Player's turn to draw until they reach 16 or higher
     * @return true if the player is still in the game, false if they bust (value of cards is over 21)
     */
    public boolean playerTurn() {
        while (playerHand.getTotalValue() < 16) {
            playerHand.addCard(deck.deal());
        }
        return playerHand.getTotalValue() <= 21;

    }
    /**
     * * Dealer's turn to draw until they reach 17 or higher
     * @return true if the dealer is still in the game, false if they bust (value of cards is over 21)
     */
    public boolean dealerTurn() {
        while (dealerHand.getTotalValue() < 17) {
            dealerHand.addCard(deck.deal());
        }
        return dealerHand.getTotalValue() <= 21;
    }

    // Override toString
    /**
     * * Returns a string representation of the player's and dealer's hands
     * @return String representation of the player's and dealer's hands
     */
    @Override
    public String toString() {
        String result = "Player's Hand:\n";
        for (int i = 0; i < playerHand.size(); i++) {
            result += playerHand.getCard(i) + "\n";
        }
        result += "Player's Total: " + playerHand.getTotalValue() + "\n\n";
        result += "Dealer's Hand:\n";
        for (int i = 0; i < dealerHand.size(); i++) {
            result += dealerHand.getCard(i) + "\n";
        }
        result += "Dealer's Total: " + dealerHand.getTotalValue() + "\n\n";
        return result;
    }

}
