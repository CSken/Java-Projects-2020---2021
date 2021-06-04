/*
This will represent the discard DiscardPile

*/
import java.util.*;
public class DiscardPile {
  private ArrayList<Card> discardDeck;

  public DiscardPile()
  {
    discardDeck = new ArrayList<Card>();
  }

  //accesses the top card
  public Card getTopCard()
  {
    return discardDeck.get(discardDeck.size() - 1);
  }

  //adds a card
  public void addCard(Card card)
  {
    discardDeck.add(card);
  }

  //gets the size of the deck
  public int getSize()
  {
    return discardDeck.size();
  }

}