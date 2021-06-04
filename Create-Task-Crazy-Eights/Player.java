/*
This will represent a player's deck

*/

import java.util.*;
public class Player {
  Scanner scan = new Scanner(System.in);
  private ArrayList<Card> playerDeck;

  public Player()//default constructor
  {
    playerDeck = new ArrayList<Card>();
  }


  //adding certain amount of cards to a player deck
  public void addCard(int amt, Deck deck)
  {
    for(int i = 0; i < amt; i++)
    {
      playerDeck.add(deck.dealCard());
    }
  }



  //discards player's card into the discard pile
  public void discardCard(int i, DiscardPile pile)
  {
    if(pile.getSize() == 0 || playerDeck.get(i).cardEquals(pile.getTopCard()) || playerDeck.get(i).getNum() == 8)
    {
        pile.addCard(playerDeck.get(i));

        if(playerDeck.get(i).getNum() == 8)//if the user chooses an eight card
        {
          
          String newSuit;//for asking what the user would like the eight's suit to represent

          do {//loop used to ensure the user enters one of the four options

            System.out.println("What suit would you like this eight to represent? Ex: \"Hearts\" \"Clubs\" \"Spades\" \"Diamonds\"");
              newSuit = scan.nextLine();

            if(!newSuit.equals("Hearts") && !newSuit.equals("Clubs") && !newSuit.equals("Spades") && !newSuit.equals("Diamonds"))
              System.out.println("\nPlease try again.\n");

          }while(!newSuit.equals("Hearts") && !newSuit.equals("Clubs") && !newSuit.equals("Spades") && !newSuit.equals("Diamonds"));


          playerDeck.get(i).switchSuit(newSuit);//switches the eight's suit to the new suit
        }

        playerDeck.remove(i);
        System.out.println(pile.getTopCard() + " Removed.\nPress enter to continue.");
        scan.nextLine();
        Main.player = 2;
    }

    else
    {
      System.out.println(playerDeck.get(i) + " can't match with the top card in the discard pile, " + pile.getTopCard() + ".");//the card doesn't match the top pile card and is not equal to 8
      System.out.println("Press enter to continue.");
      scan.nextLine();
    }
  }


  //boolean checking if none of the cards match with the top pile deck card; false means none equal
  public boolean checkAll(DiscardPile pile)
  {
    int counter = 0;
    for(int i = 0; i < playerDeck.size(); i++)
    {
      if(playerDeck.get(i).cardEquals(pile.getTopCard()))
        counter++;
    }

    return counter != 0;
  }


  //toString() method for player's deck
  public String toString()
  {
    String cardList = "";
    for(int i = 0; i < playerDeck.size(); i++)
    {
      cardList += (i + 1) + ". " + playerDeck.get(i) + "\n";
    }
    return "Your Cards: " + "\n" + cardList;
  }

  //returns playerdeck's size
  public int getSize()
  {
    return playerDeck.size();
  }

  //card accessor
  public Card getCard(int i)
  {
    return playerDeck.get(i);
  }

  //deck accessor
  public ArrayList<Card> getDeck()
  {
    return playerDeck;
  }

  //checks if an eight exists in the player deck
  public boolean doesEight()
  {
    for(int i = 0; i < playerDeck.size(); i++)
    {
      if(playerDeck.get(i).getNum() == 8)
      {
        return true;
      }
    }
    return false;
  }


}