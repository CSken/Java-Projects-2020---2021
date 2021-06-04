/*

This will represent the original deck from which cards are dealt

*/

import java.util.*;
public class Deck {
  private ArrayList<Card> deck;

  //card deck constructor
  public Deck()
  {
    deck = new ArrayList<Card>();

    for(int i = 1; i <= 13; i++)
    {
      deck.add(new Card("Hearts", i));
      deck.add(new Card("Clubs", i));
      deck.add(new Card("Spades", i));
      deck.add(new Card("Diamonds", i));
    }
  }



  //method to shuffle deck
  public void shuffle()
  {
    ArrayList<Card> shuffledDeck = new ArrayList<Card>();
    int origDeckSize = deck.size();

    for(int i = 0; i < origDeckSize; i++ )
    {
      int randIndex = (int)(Math.random() * deck.size());
      shuffledDeck.add(deck.get(randIndex));
      deck.remove(randIndex);

    }

    deck = shuffledDeck;
  }


  //method for dealing cards into different player decks
  public Card dealCard()
  {
    return deck.remove(deck.size() - 1);
  }

  //switches the top card to the bottom of the deck
  public void bottomCard()
  {
    deck.add(0, dealCard());
  }


  //deck size accessor
  public int getSize()
  {
    return deck.size();
  }


  //accesses the topcard
  public Card getTopCard()
  {
    return deck.get(deck.size() - 1);
  }

  public String toString()
  {
    String deckString = "";
    for(int i = 0; i < deck.size(); i++)
    {
      deckString += deck.get(i) + "\n";
    }
    return "Deck:\n" + deckString;
  }
}