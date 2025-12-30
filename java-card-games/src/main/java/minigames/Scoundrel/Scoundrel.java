package minigames;

import java.util.ArrayList;
import java.util.Scanner;

public class Scoundrel {
   private static int health = 20;
   private static int weapon = 0;
   private static int weaponMonster = 0;
   private static boolean weaponFlag = false;

   private static ArrayList<Card> roomCards = new ArrayList<>(4);

   private static ArrayList<Card> newScoundrelDeck() {
      ArrayList<Card> deck = Card.createShuffledDeck();
      ArrayList<Card> deckScoundrel = new ArrayList<>();

      for (Card c : deck) {
         String suit = c.getSuit().toString();
         int value = c.getRank().getValue();

         if ((suit.equals("DIAMONDS") || suit.equals("HEARTS")) && value > 10) {
            continue;
         }
         deckScoundrel.add(c);
      }
      
      return deckScoundrel;
   }

   private static int weaponSlain(int v) {
      return (v > weaponMonster) ? v : (v-weapon);
   }

   private static void gameLogic(Card card) {
      String suit = card.getSuit().toString();
      int value = card.getRank().getValue();

      if (suit.equals("SPADES") || suit.equals("CLUBS")) {
         if (weapon == 0) {
            health -= value;
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

      if (suit.equals("HEARTS")) {
         health += value;
         System.out.println(health);
      }
      if (suit.equals("DIAMONDS")) {
         weapon = value;
         weaponFlag = false;
         System.out.println("New weapon: " + weapon + " of DIAMONDS");
      }
   }

   private static void gameRoom(ArrayList<Card> deck) {
      ArrayList<Card> survivors = new ArrayList<>();

      for (Card c : roomCards) {
         if (c != null) survivors.add(c);
      }

      roomCards.clear();
      roomCards.addAll(survivors);

      while (roomCards.size() < 4 && !deck.isEmpty()) {
         roomCards.add(deck.remove(0));
      }

   }

   private static void printRoom() {
      for (int i=0; i<4; i++) {
         System.out.println((i + 1) + ": ");
         if (i < roomCards.size() && roomCards.get(i) != null) {
            System.out.println(roomCards.get(i));
         } else {
            System.out.println("[USED]");
         }
      }
   }

   public static void main(String[] args) {
      ArrayList<Card> deck = newScoundrelDeck();
      Scanner sc = new Scanner(System.in);

      short round = 1;

      boolean skipFlag = false;

      while (health > 0 && !deck.isEmpty()) {

         System.out.println("-------------->Round: " + round + "<--------------");
         if (weapon != 0) System.out.println("Your weapon: " + weapon + " of DIAMONDS");

         gameRoom(deck);
         printRoom();

         if (!skipFlag) {
            System.out.print("Skip this room? (Y/N): ");

            if (sc.next().equalsIgnoreCase("Y")) {

               for (Card c : roomCards) {
                  if (c != null) deck.add(c);
               }

               roomCards.clear();
               skipFlag = true;
               round++;
               continue;
            }
         }

         for (int i=1; i<=3; i++) {
            System.out.println("YOUR HEALTH: " + health);
            System.out.println("Move " + i + " out of 3");

            int choice = Integer.parseInt(sc.next()) - 1;

            if (choice < 0 || choice >= 4 || roomCards.get(choice) == null) {
               System.out.println("##  TRY AGAIN  ##");
               i--;
               continue;
            }

            Card chosen = roomCards.get(choice);
            roomCards.set(choice, null);
            gameLogic(chosen);

            if (health <= 0) break;
         }

         skipFlag = false;
         round++;
      }

      sc.close();
   }  
}
