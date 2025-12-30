package minigames;

import java.util.ArrayList;
import java.util.Scanner;

public class Scoundrel {
   private static int health = 20;
   private static int weapon = 0;
   private static int weaponMonster = 0;
   private static boolean weaponFlag = false;

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

   private static int weaponSlain(int v) {
      if (v > weaponMonster) return v;
      else return v-weapon;
   }

   private static void gameLogic(Card card, String suit, int value) {
      if (suit == "SPADES" || suit == "CLUBS") {
         if (weapon == 0) {
            health = health - value;
         }
         else {
            if (weaponFlag != true) {
               int damageCalc = value - weapon;

               health = (damageCalc > 0) ? health - damageCalc : health;

               weaponMonster = value;
               weaponFlag = true;
            }
            else {
               health -= weaponSlain(value);
            }
         }
      }

      if (suit == "HEARTS") {
         health = health + value;
         System.out.println(health);
      }
      if (suit == "DIAMONDS") {
         weapon = value;
         weaponFlag = false;
         System.out.println("New weapon: " + weapon + " of DIAMONDS");
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

      boolean skipFlag = false;

      while (!deck.isEmpty()) {

         System.out.println("-------------->Round: " + round + "<--------------");
         if (weapon != 0) System.out.println("Your weapon: " + weapon + " of DIAMONDS");

         Card face1 = deck.remove(0);
         Card face2 = deck.remove(0);
         Card face3 = deck.remove(0);
         Card face4 = deck.remove(0);

         System.out.println(room(face1, face2, face3, face4));

         String skipConfirm = "";

         if (!skipFlag){
            System.out.println("--> Do you wish to skip this room? Y/N");

            skipConfirm = sc.next();

            if (skipConfirm.equalsIgnoreCase("y")) {
               deck.add(face1);
               deck.add(face2);
               deck.add(face3);
               deck.add(face4);

               skipFlag = true;
               round++;
               continue;
            }
            
         }

         String cmd = "";

         for (int i=0; i<3; i++) {
            int move = 1 +i;

            System.out.println("YOUR HEALTH: " + health);
            System.out.println("Move " + move + " out of 3");

            cmd = sc.next();

            switch (cmd) {
               case "1":
                  gameLogic(face1, getCardSuit(face1), face1.getRank().getValue());
                  break;
               case "2":
                  gameLogic(face2, getCardSuit(face2), face2.getRank().getValue());
                  break;
               case "3":
                  gameLogic(face3, getCardSuit(face3), face3.getRank().getValue());
                  break;
               case "4":
                  gameLogic(face4, getCardSuit(face4), face4.getRank().getValue());
                  break;
            }
         }

         skipFlag = false;
         round++;
      }

      sc.close();
   }  
}
