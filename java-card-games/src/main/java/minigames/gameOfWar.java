package minigames;

import java.util.ArrayList;
import java.util.Collections;

public class gameOfWar {

    private static int war(ArrayList<Card> p1, ArrayList<Card> p2, ArrayList<Card> pot) {
        if (p1.size() < 4) return 2;
        if (p2.size() < 4) return 1;

        for (int i = 0; i < 3; i++) {
            pot.add(p1.remove(0));
            pot.add(p2.remove(0));
        }

        Card face1 = p1.remove(0);
        Card face2 = p2.remove(0);

        pot.add(face1);
        pot.add(face2);

        int value1 = face1.getRank().getValue();
        int value2 = face2.getRank().getValue();

        System.out.println("--------WAR!-------" + "\n" + face1 + " vs " + face2);

        if (value1 > value2) return 1;
        if (value1 < value2) return 2;

        return war(p1, p2, pot);
    }

    public static void main(String[] args){

        ArrayList<Card> deck = Card.createShuffledDeck();
        ArrayList<Card> playerDeck1 = new ArrayList<Card>();
        ArrayList<Card> playerDeck2 = new ArrayList<Card>();

        for (int i = 0; i < deck.size(); i++) {
            if (i < 26) playerDeck1.add(deck.get(i));
            else playerDeck2.add(deck.get(i));
        }

        int round = 1;

        while (!playerDeck1.isEmpty() && !playerDeck2.isEmpty()) {

            ArrayList<Card> pot = new ArrayList<>();

            Collections.shuffle(playerDeck1);
            Collections.shuffle(playerDeck1);

            Card cardP1 = playerDeck1.remove(0);
            Card cardP2 = playerDeck2.remove(0);

            pot.add(cardP1);
            pot.add(cardP2);

            int p1 = cardP1.getRank().getValue();
            int p2 = cardP2.getRank().getValue();  

            System.out.println("Round " + round + ": " + cardP1 + " vs " + cardP2);

            int winner;

            if (p1 > p2) {
                winner = 1;
            }
            else if (p1 < p2) {
                winner = 2;
            }
            else {
                winner = war(playerDeck1, playerDeck2, pot); 
            }

            if (winner == 1) {
                playerDeck1.addAll(pot);
                System.out.println("Deck ONE takes " + pot.size() + " cards");
            }
            else {
                playerDeck2.addAll(pot);
                System.out.println("Deck TWO takes " + pot.size() + " cards");
            }

            round++;
        }

        if (playerDeck1.isEmpty()) {
            System.out.println("FINAL: deck two wins");
        }
        else {
            System.out.println("FINAL: deck one wins");
        }
    }
}