public class Card {
  private String suit;
  private String name;
  private int number;


 //card constructor 
  public Card(String s, int num)
  {
    suit = s;
    number = num;

    switch(number) {
      case 1:
        name = "Ace";
        break;

      case 2:
        name = "Two";
        break;

      case 3:
        name = "Three";
        break;

      case 4:
        name = "Four";
        break;

      case 5:
        name = "Five";
        break;

      case 6:
        name = "Six";
        break;

      case 7:
        name = "Seven";
        break;

      case 8:
        name = "Eight";
        break;

      case 9:
        name = "Nine";
        break;

      case 10:
        name = "Ten";
        break;

      case 11:
        name = "Jack";
        break;

      case 12:
        name = "Queen";
        break;

      case 13:
        name = "King";
        break;
    }

  }

  
  //number accessor
  public int getNum()
  {
    return number;
  }

  //suit accessor
  public String getSuit()
  {
    return suit;
  }


  //for switching the suit of a special eight card
  public void switchSuit(String newSuit)
  {
    suit = newSuit;
  }


  //method to check if 2 cards are equal when number/suit is equal
  public boolean cardEquals(Card other)
  {
    return (this.name.equals(other.name) || this.suit.equals(other.suit));
  }

  
  //toString() method
  public String toString()
  {
    return name + " of " + suit;
  }


}