package minigames;

import java.util.ArrayList;
import java.util.Collections;

public class Card {

    public enum Suits {
        HEARTS, DIAMONDS, CLUBS, SPADES
    }

    public enum Ranks {
        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13), ACE(14);

        private final int value;

        Ranks (int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    }


    private Suits suit;
    private Ranks rank;

    public Card (Suits suit, Ranks rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suits getSuit() {
        return suit;
    }

    public Ranks getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    public static ArrayList<Card> createDeck() {
        ArrayList<Card> cardsDeck = new ArrayList<Card>();
        for (Suits cardSuit : Suits.values()) {
            for (Ranks cardRank : Ranks.values()) {
                Card card = new Card(cardSuit, cardRank);
                cardsDeck.add(card);
            }
        }
        return cardsDeck;
    }

    public static ArrayList<Card> createShuffledDeck() {
        ArrayList<Card> deck = createDeck();
        Collections.shuffle(deck);
        return deck;
    }

    public static void main(String[] args) {
        ArrayList<Card> deck = Card.createShuffledDeck();
        System.out.println("Test: ");
        for (int i = 0; i < deck.size(); i++) {
            System.out.println(deck.get(i));
        }
    }
}

