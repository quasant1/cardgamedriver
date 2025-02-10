package edu.guilford;

import java.util.ArrayList;

/**
 * Hand class representing a hand of cards
 * 
 * @see Card
 */
public class Hand {
    /**
     * List of cards in the hand
     */
    private ArrayList<Card> hand;

    public Hand() {
        hand = new ArrayList<Card>();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public void removeCard(Card card) {
        hand.remove(card);
    }

    public void reset() {
        hand.clear();
    }

    public int size() {
        return hand.size();
    }

    public Card getCard(int index) {
        return hand.get(index);
    }

    // Calculate the value of the hand
    /**
     * Calculates the value of the hand according to Blackjack rules
     * 
     * @return the value of the hand
     */
    public int getTotalValue() {
        int value = 0;
        int aces = 0;
        for (Card card : hand) {
            switch (card.getRank()) {
                case TWO:
                    value += 2;
                    break;
                case THREE:
                    value += 3;
                    break;
                case FOUR:
                    value += 4;
                    break;
                case FIVE:
                    value += 5;
                    break;
                case SIX:
                    value += 6;
                    break;
                case SEVEN:
                    value += 7;
                    break;
                case EIGHT:
                    value += 8;
                    break;
                case NINE:
                    value += 9;
                    break;
                case TEN:
                case JACK:
                case QUEEN:
                case KING:
                    value += 10;
                    break;
                case ACE:
                    aces++;
                    break;
            }
        }

        for (int i = 0; i < aces; i++) {
            if (value + 11 <= 21) {
                value += 11;
            } else {
                value += 1;
            }
        }
        return value;
    }

    // Override toString method
    /**
     * A string representation of the hand
     * 
     * @return a string representation of the hand
     */
    @Override
    public String toString() {
        String handString = "";
        for (Card card : hand) {
            handString += card.toString() + "\n";
        }
        return handString;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

}
