package edu.guilford;

import java.util.ArrayList;
import java.util.Random;

/**
 * * LamarckianPoker class representing a game of Lamarckian Poker (player 1
 * hand, player 2 hand, pool, discard, and deck)
 * 
 * @see Card
 * @see Hand
 * @see Deck
 */
public class LamarckianPoker {
    /**
     * Player 1's hand
     */
    private Hand player1Hand;
    /**
     * Player 2's hand
     */
    private Hand player2Hand;
    /**
     * Pool of cards
     */
    private Hand pool;
    /**
     * Discard pile
     */
    private Deck discard;
    /**
     * Deck they are playing with
     */
    private Deck deck;
    private Random rand = new Random();
    //private int iTurn;

    /**
     * * Constructor for LamarckianPoker which resets the game and shuffles the deck
     */
    public LamarckianPoker() {
        reset(true);
    }

    public Hand getPlayer1Hand() {
        return player1Hand;
    }

    public Hand getPlayer2Hand() {
        return player2Hand;
    }

    public Hand getPool() {
        return pool;
    }

    /**
     * Resets the game by creating a new standard deck and shuffling it and clearing
     * the discard pile
     * 
     * @param newDeck
     */
    public void reset(boolean newDeck) {
        if (newDeck) {
            deck = new Deck();
            discard = new Deck();
            discard.clear();
            deck.shuffle();
        }
        //iTurn = 0;
    }

    /**
     * Deals four cards to each player
     */
    public void deal() {
        player1Hand = new Hand();
        player2Hand = new Hand();
        for (int iCard = 0; iCard < 4; iCard++) {
            player1Hand.addCard(deck.deal());
            player2Hand.addCard(deck.deal());
        }
    }

    /**
     * * Deals four cards from the deck to form the pool
     */
    public void makePool() {
        pool = new Hand();
        for (int iCard = 0; iCard < 4; iCard++) {
            pool.addCard(deck.deal());
        }
        // System.out.println("Deck size: " + deck.size());
    }

    /**
     * * Plays a turn of the game. In short, players sacrifice a random card and
     * draw any cards with the same rank or suit from the pool. Unused cards go into
     * the discard pile.
     * 
     * @return true if the game is still going, false if the game is over
     */
    public boolean turn() {
        if (player1Hand.size() < 7 || player2Hand.size() < 7) {
            // CHANGE FROM ORIGINAL CODE -- if someone doesn't have any cards, the game is
            // over
            if (player1Hand.size() == 0 || player2Hand.size() == 0) {
                return false;
            }

            makePool();
            // System.out.println("Turn " + iTurn + "\n" + pool);
            Card player1Card = player1Hand.getCard(rand.nextInt(player1Hand.size()));
            Card player2Card = player2Hand.getCard(rand.nextInt(player2Hand.size()));
            Hand firstHand, secondHand;
            Card firstCard, secondCard;
            // UPDATED CODE
            if (player1Card.compareTo(player2Card) > 0) { // if player 1's card is greater
                firstHand = player1Hand;
                secondHand = player2Hand;
                firstCard = player1Card;
                secondCard = player2Card;
            } else {
                firstHand = player2Hand;
                secondHand = player1Hand;
                firstCard = player2Card;
                secondCard = player1Card;
            }
            /* 
            if (player1Card.getRank().ordinal() > player2Card.getRank().ordinal()) {
                firstHand = player1Hand;
                secondHand = player2Hand;
                firstCard = player1Card;
                secondCard = player2Card;
            } else if (player1Card.getRank().ordinal() < player2Card.getRank().ordinal()) {
                firstHand = player2Hand;
                secondHand = player1Hand;
                firstCard = player2Card;
                secondCard = player1Card;
            } else {
                if (player1Card.getSuit().ordinal() > player2Card.getSuit().ordinal()) {
                    firstHand = player1Hand;
                    secondHand = player2Hand;
                    firstCard = player1Card;
                    secondCard = player2Card;
                } else {
                    firstHand = player2Hand;
                    secondHand = player1Hand;
                    firstCard = player2Card;
                    secondCard = player1Card;
                }

            }
            */

            ArrayList<Card> poolRemove = new ArrayList<Card>();

            for (Card poolCard : pool.getHand()) {
                if (firstCard.getRank().ordinal() == poolCard.getRank().ordinal() ||
                        firstCard.getSuit().ordinal() == poolCard.getSuit().ordinal()) {
                    firstHand.addCard(poolCard);
                    poolRemove.add(poolCard);
                }
            }
            // Remove cards from pool
            for (Card poolCard : poolRemove) {
                pool.removeCard(poolCard);
            }
            poolRemove.clear();
            // CHANGED CODE
            discard.getDeck().add(firstCard);
            //pool.addCard(firstCard);
            firstHand.removeCard(firstCard);
            
            for (Card poolCard : pool.getHand()) {
                if (secondCard.getRank().ordinal() == poolCard.getRank().ordinal() ||
                        secondCard.getSuit().ordinal() == poolCard.getSuit().ordinal()) {
                    secondHand.addCard(poolCard);
                    poolRemove.add(poolCard);
                }
            }
            for (Card poolCard : poolRemove) {
                pool.removeCard(poolCard);
            }
            pool.addCard(secondCard);
            secondHand.removeCard(secondCard);

            
            for (Card poolCard : pool.getHand()) {
                discard.getDeck().add(poolCard);
            }
            pool.getHand().clear();
            // System.out.println("Discard\n" + discard.size());
            if (deck.size() < 4) {
                for (Card card : discard.getDeck()) {
                    deck.getDeck().add(card);
                }
                discard.clear();
                // System.out.println("Discard\n" + discard.size());
            }
            //iTurn++;

            return true;
        } else {
            return false;
        }

    }

    @Override
    public String toString() {
        return "\nPlayer 1: \n" + player1Hand + "\nPlayer 2: \n" + player2Hand + "\nPool: " + pool + "\n";
    }
}
