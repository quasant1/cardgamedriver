package edu.guilford;

import java.util.Random;

/**
 * Card class representing a card in a standard deck of cards with suit and rank
 */
public class Card implements Comparable<Card> {
    // enum for the suits
    /**
     * Enum for the suits in standard deck of cards
     */
    public enum Suit {
        CLUBS, DIAMONDS, HEARTS, SPADES
    }

    // enum for the ranks
    /**
     * Enum for the ranks in standard deck of cards
     */
    public enum Rank {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN,
        KING
    }

    // instance variables
    private Suit suit;
    private Rank rank;

    // constructor
    /**
     * Constructor for the Card class given a suit and rank
     * 
     * @param suit
     * @param rank
     */
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Constructor for the Card class that creates a random card
     */
    public Card() {
        // random Card
        Random rand = new Random();
        int suit = rand.nextInt(Suit.values().length);
        int rank = rand.nextInt(Rank.values().length);
        this.suit = Suit.values()[suit];
        this.rank = Rank.values()[rank];
    }

    // getters
    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    // toString

    public String toString() {
        return rank + " of " + suit;
    }

    @Override
    /**
     * Compares this card to another card, first by rank, then by suit
     * 
     * @param otherCard the card to compare to
     * @return 1 if this card is greater than the other card, -1 if this card is
     *         less than the other card, 0 if they are equal
     */
    public int compareTo(Card otherCard) {
        // TODO Auto-generated method stub
        if (this.rank.ordinal() > otherCard.rank.ordinal()) {
            return 1;
        } else if (this.rank.ordinal() < otherCard.rank.ordinal()) {
            return -1;
        } else {
            if (this.suit.ordinal() > otherCard.suit.ordinal()) {
                return 1;
            } else if (this.suit.ordinal() < otherCard.suit.ordinal()) {
                return -1;
            }
        }

        return 0;
    }

}