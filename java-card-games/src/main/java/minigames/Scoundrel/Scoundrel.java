package minigames;

import java.util.ArrayList;
import java.util.Scanner;

public class Scoundrel {
   private static int health = 20;
   private static Card weapon;

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

   private static String room(Card card1, Card card2, Card card3, Card card4) {
      String cardFace1 = card1.toString();
      String cardFace2 = card2.toString();
      String cardFace3 = card3.toString();
      String cardFace4 = card4.toString();

      String room = "Card1: " + cardFace1 + " Card2: " + cardFace2 + " Card3: " + cardFace3 + " Card4: " + cardFace4;
      return room;
   }

   private static String getCardSuit(Card card) {
      return card.getSuit().toString();
   }

   private static void gameLogic(Card card, String suit, int value) {
      if (suit == "SPADES" || suit == "CLUBS") {
         health = health - value;
         System.out.println(health);
      }
      if (suit == "HEARTS") {
         health = health + value;
         System.out.println(health);
      }
      if (suit == "DIAMONDS") {
         weapon = card;
         System.out.println(weapon.getSuit().toString());
      }
   }

   public static void main(String[] args) {
      ArrayList<Card> deck = newScoundrelDeck();
      Scanner sc = new Scanner(System.in);
    
      // System.out.println("Test: ");
      // for (int i = 0; i < deck.size(); i++) {
      //    System.out.println(deck.get(i));
      // }
      short round = 1;

      while (!deck.isEmpty()) {
         System.out.println("Round: " + round);

         Card face1 = deck.remove(0);
         Card face2 = deck.remove(0);
         Card face3 = deck.remove(0);
         Card face4 = deck.remove(0);

         System.out.println(room(face1, face2, face3, face4));

         String cmd = "";
         cmd = sc.next();

         switch (cmd) {
            case "1":
               gameLogic(face1, getCardSuit(face1), face1.getRank().getValue());
               break;
            case "2":
               // card2
               break;
            case "3":
               // card3
               break;
            case "4":
               // card4
               break;
         }

         round++;
      }

      sc.close();
   }  
}
