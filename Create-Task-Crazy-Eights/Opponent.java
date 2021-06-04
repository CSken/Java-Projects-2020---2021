public class Opponent extends Player {

  public void discardCard(DiscardPile pile)//overrides the player discardCard() method
  {
    for(int i = 0; i < getSize(); i++)
    {
      if(getDeck().get(i).cardEquals(pile.getTopCard()) || getDeck().get(i).getNum() == 8)
      {
        if(getDeck().get(i).getNum() == 8)//switches the suit to random suit if an eight is selected
        {
          int suit = (int)(Math.random() * 4);

          switch(suit){
            case 0:
              getDeck().get(i).switchSuit("Hearts");
              break;

            case 1:
              getDeck().get(i).switchSuit("Clubs");
              break;

            case 2:
              getDeck().get(i).switchSuit("Spades");
              break;

            case 3:
              getDeck().get(i).switchSuit("Diamonds");
              break;
          }
        }

        pile.addCard(getDeck().get(i));

        System.out.println("Opponent removed " + getDeck().get(i) + " from its hand.\nPress enter to continue.");
        scan.nextLine();

        getDeck().remove(i);
        Main.player = 1;
        break;
      }
    }

  }


}