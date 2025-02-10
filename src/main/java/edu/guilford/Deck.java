package edu.guilford;

import java.util.ArrayList;
import java.util.Random;

/**
 * Deck class representing a deck of standard cards
 * 
 * @see Card
 */
public class Deck {
    /**
     * List of cards form a deck
     */
    private ArrayList<Card> deck = new ArrayList<Card>();
    private Random rand = new Random();

    /**
     * Constructor for the Deck class that builds a standard deck of 52 cards
     */
    public Deck() {
        build();
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void clear() {
        deck.clear();
    }

    /**
     * Builds a standard deck of 52 cards
     */
    public void build() {
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                deck.add(new Card(suit, rank));
            }
        }
    }

    /**
     * Shuffles the deck
     */
    public void shuffle() {
        ArrayList<Card> tempDeck = new ArrayList<Card>();
        while (deck.size() > 0) {
            int loc = rand.nextInt(deck.size());
            tempDeck.add(deck.get(loc));
            deck.remove(loc);
        }
        deck = tempDeck;
    }

    /**
     * Picks a card and removes it from the deck
     * 
     * @param i
     * @return the card at index i
     */
    public Card pick(int i) {
        Card picked = deck.remove(i);
        return picked;
    }

    /**
     * Deals a card from the top of the deck by removing the card at index 0
     * 
     * @return the card at index 0
     */
    public Card deal() {
        return deck.remove(0);
    }

    public int size() {
        return deck.size();
    }

    /**
     * toString method for the Deck class
     * 
     * @return a string representation of the deck
     */
    @Override
    public String toString() {
        String deckString = "";
        for (Card card : deck) {
            deckString += card.toString() + "\n";
        }
        return deckString;
    }
}
