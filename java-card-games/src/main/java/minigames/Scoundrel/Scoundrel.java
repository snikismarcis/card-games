package minigames;

import java.util.ArrayList;
import java.util.Scanner;

public class Scoundrel {

   private static ArrayList<Card> newScoundrelDeck() {
      ArrayList<Card> deck = Card.createShuffledDeck();
      ArrayList<Card> deckScoundrel = new ArrayList<>();
      for (int i=0; i<deck.size(); i++) {
         if ((deck.get(i).getSuit().toString() == "DIAMONDS" || deck.get(i).getSuit().toString() == "HEARTS") && deck.get(i).getRank().getValue() > 10) {
         }
         else {
            deckScoundrel.add(deck.get(i));
         }
      }
      
      return deckScoundrel;
   }
   public static void main(String[] args) {
      ArrayList<Card> deck = newScoundrelDeck();
      Scanner sc = new Scanner(System.in);
    
      System.out.println("Test: ");
        for (int i = 0; i < deck.size(); i++) {
            System.out.println(deck.get(i));
        }

   }  
}
