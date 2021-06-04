import java.util.*;
class Main {
  public static int player = 1;

  //boolean to check whether or not a user input an int
  public boolean isInt(String userInput)
  {
    if(userInput.length() == 0)
      return false;
    
    int intCounter = 0;
      for(int i = 0; i < userInput.length(); i++)
      {
        if(userInput.charAt(i) >= 48 && userInput.charAt(i) <= 57)
        {
          intCounter++;
        }
      }

      if(intCounter == userInput.length())
      {
        return true;
      }

      return false;
  }


  //boolean to check if game ends
  public boolean gameEnd(Player playerDeck, Opponent opponentDeck, Deck deck, DiscardPile pile)
  {
    return playerDeck.getSize() == 0 || opponentDeck.getSize() == 0 || (!(playerDeck.checkAll(pile)) && !(opponentDeck.checkAll(pile)) && deck.getSize() == 0);
  }


  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

   
   
    int againInt = 1;
    boolean exit = false;
    while(againInt == 1)
    {
      Main run = new Main();
      Deck deck = new Deck();
      player = 1;
      deck.shuffle();

      //adds 5 cards to each player deck
      Player player1 = new Player();
      Opponent player2 = new Opponent();
      player1.addCard(5, deck);
      player2.addCard(5, deck);


      DiscardPile pile = new DiscardPile();
      if(deck.getTopCard().getNum() == 8)//the first card in the discard pile can't be an eight
        deck.bottomCard();
      pile.addCard(deck.dealCard());

      
      exit = false;
      do {
      
        //introductory UI and ensures that the user's input is an integer
        int choice = 0;
        String userInput = "";
        while(!(run.isInt(userInput)) || !(choice <= 3 && choice >=1))
        {
          System.out.println("\nCrazy Eights Card Game\n1. Play\n2. Rules\n3. Exit");
          userInput = scan.nextLine();

          if(run.isInt(userInput))
          {
            choice = Integer.valueOf(userInput);
          }

          if(!(run.isInt(userInput)) || !(choice <= 3 && choice >=1))
            System.out.println("\nPlease enter either \"1\" \"2\" or \"3\".");
        }


      
        //user chooses to play
        if(choice == 1)
        {
          while(!exit && !(run.gameEnd(player1, player2, deck, pile)))
          {
            while(!exit && player != 2 && !(run.gameEnd(player1, player2, deck, pile)))
            {
              System.out.println("\n\n\n\n");
              System.out.println(player1);
              System.out.println("Top Card in Discard Pile: " + pile.getTopCard());
              System.out.println("\nOpponents Cards Remaining: " + player2.getSize());
              System.out.println("\nCards Remaining in Deck: " + deck.getSize());

              
              //takes user's move, ensuring that it's an int
              String userMove = "";
              int choiceMove = 0;
              while(!(run.isInt(userMove)) || !(choiceMove <=3 && choiceMove >= 1))
              {
                System.out.println("\nUser's Choices:\n1. Discard Card\t\t2. Draw Card\n3. Exit");
                userMove = scan.nextLine();

                if(run.isInt(userMove))
                {
                  choiceMove = Integer.valueOf(userMove);//if the user's input is an int change the int value
                }

                if(!(run.isInt(userMove)) || !(choiceMove <=3 && choiceMove >= 1))
                  System.out.println("\nPlease enter either \"1\" or \"2\" or \"3\".");

              }


              if(choiceMove == 1)//user chooses to discard a card
              {
                //takes user's index to discard, ensuring that it's an int
                String userIndex = "";
                int cardIndex = 0;
                while(!(run.isInt(userIndex)) || !(cardIndex <= player1.getSize() && cardIndex >= 1))
                {
                  System.out.println("\n\nWhich card would you like to discard (Please enter an integer from 1 to " + player1.getSize() + ")?");
                  userIndex = scan.nextLine();

                  if(run.isInt(userIndex))
                  {
                    cardIndex = Integer.valueOf(userIndex);//if the user's input is an int change   the int value
                  }

                  if(!(run.isInt(userIndex)) || !(cardIndex <= player1.getSize() && cardIndex >= 1))
                  {
                    System.out.println("\nPlease enter an integer from 1 to " + player1.getSize() + ".");
                  }
                }

                player1.discardCard((cardIndex - 1), pile);
              }

              else if(choiceMove == 2)//user draws a card
              {
                if(!(player1.checkAll(pile)) && !(player1.doesEight()))
                {
                  int numDraws = 0;//once it reaches 3 draws, it will pass the turn
                  while(numDraws < 3 && deck.getSize() != 0)
                  {
                    System.out.println("You drew a " + deck.getTopCard() + ".");
                    player1.addCard(1, deck);

                    if(!(player1.getCard(player1.getSize() - 1).cardEquals(pile.getTopCard())) && player1.getCard(player1.getSize() - 1).getNum() != 8)
                    {
                      System.out.println(player1.getCard(player1.getSize() - 1) + " can't match the top card of the deck " + pile.getTopCard() + ".\nDrawing again.\n");
                    }

                    else
                    {
                      System.out.println(player1.getCard(player1.getSize() - 1) + " can match with the the top card in the discard pile " + pile.getTopCard() + ".\nDiscarding card.\n");
                      player1.discardCard(player1.getSize() - 1, pile);
                      break;
                    }
                    System.out.println("Press enter to continue.\n");
                    scan.nextLine();
                    numDraws++;
                  }


                  if(deck.getSize() == 0 && !run.gameEnd(player1, player2, deck, pile))
                  {
                    System.out.println("There are no more cards remaining in the deck to draw from. Passing turn.\nPress enter to continue.");
                    scan.nextLine();
                    player = 2;
                  }

                  else if(numDraws >= 3)
                  {
                    System.out.println("You have drawn three cards. Passing turn.\nPress enter to continue.");
                    scan.nextLine();
                    player = 2;//switches player
                  }
                }

                else
                {
                  System.out.println("Your hand contains card(s) that match the top card of the discard pile. Please discard those cards fisrt.\nPress enter to continue.");
                  scan.nextLine();
                }
              }

              else if(choiceMove == 3)//user exits
                exit = true;          

            }

            if(run.gameEnd(player1, player2, deck, pile))//checks game over after user move
            {
              break;
            }


            if(exit)
            {
              exit = false; //sets back to false for the main menu
              break;
            }



            else if(player == 2)//when it's the computer's turn
            {
              if(player2.checkAll(pile) || player2.doesEight())//discards card
              {
                player2.discardCard(pile);
              }

              else//draws a card
              {
                int numDraws = 0;
                while(numDraws < 3 && deck.getSize() != 0)
                {
                  player2.addCard(1, deck);
                  System.out.println("Opponent drew a card.\nPress enter to continue");
                  scan.nextLine();

                  if(player2.checkAll(pile))
                  {
                    player2.discardCard(pile);
                    break;
                  }
                  numDraws++;
                }

                if(numDraws >= 3)
                {
                  System.out.println("Your opponent drew " + numDraws + " cards. Passing turn.\nPress enter to continue.");
                  scan.nextLine();
                  player = 1;
                }
              }

              if(deck.getSize() == 0 && !run.gameEnd(player1, player2, deck, pile))//if the original deck runs out of cards to draw from
              {
                System.out.println("Opponent could not draw a card, because the deck is empty. Passing turn.\nPress enter to continue.");
                scan.nextLine();
                player = 1;
              }


            }

          }

          if(run.gameEnd(player1, player2, deck, pile))
          {
            break;
          }


        }

        //user selects rules
        else if(choice == 2)
        {
          System.out.println("\nCrazy Eights Rules:\n1. The objective of the game is to get rid of all your cards.\n\n2. Each player starts out with 5 cards from the starting deck.\n\n3. One more card from the original deck will be placed in another pile face-up called the discard pile.\n\n4. A player may remove a card from their deck into the discard pile face-up when it is their turn. The card must have the same number or suit as the top card in the discard pile.\n\n5. If the player wants to remove an eight of any suit, the top card in the discard pile may be any number or suit. The player must notify which suit the eight will represent for the next player's move.\n\n6. If a player has no cards that match the top card in the discard pile, they must draw up to 3 cards from the original deck. If none of the drawn cards match, they must pass their turn.\n\n7. If neither player can make a move and all cards have been drawn from the deck, the person with the least value of cards, with Ace equaling one, wins.");

          System.out.println("\nPress enter to return to the main menu.");
          scan.nextLine();
        }

        else if(choice == 3)//user chooses to exit
          exit = true;

      }while(!exit);
      
      //if the game is over, preparing to ask the user if they want to play again
      if(run.gameEnd(player1, player2, deck, pile))
      {
        System.out.println("The game has ended.");

        if(player1.getSize() == 0 && player2.getSize() != 0)
        {
          System.out.println("Congratulations! You win.");
        }

        else if(player2.getSize() == 0 && player1.getSize() != 0)
        {
          System.out.println("Sorry! Your opponent won.");
        }

        else if(player1.getSize() != 0 && player2.getSize() != 0)
        {
          int pts1 = 0;
          for(int i = 0; i < player1.getSize(); i++)
          {
            pts1 += player1.getCard(i).getNum();
          }

          int pts2 = 0;
          for(int i = 0; i < player2.getSize(); i++)
          {
            pts2 += player2.getCard(i).getNum();
          }

          if(pts1 < pts2)
          {
            System.out.println("Congratulations! You won with a total of " + pts1 + " points, while your opponent scored " + pts2 + " points(Lower is better).");
          }

          else if(pts2 < pts1)
          {
            System.out.println("Sorry! You lost with a total of " + pts1 + " points, while your opponent scored " + pts2 + " points (Lower is better).");
          }

          else if(pts2 == pts1)
          {
            System.out.println("You tied with your opponent at " + pts1 + " points.");
          }
        }
      }
      
      if(exit)//user exits out of main menu
        break;

      String again = "";
      while(!(run.isInt(again)) || !(againInt >= 1 && againInt <= 2))//ensures again is an int and asks if the user wants to play agin
      {
        System.out.println("Would you like to play again?\n1. Yes\n2. No");
          again = scan.nextLine();

        if(run.isInt(again))
        {
          againInt = Integer.valueOf(again);

          if(!(againInt >= 1 && againInt <= 2))
          {
            System.out.println("\nPlease enter either \"1\" or \"2\".");
          }
        }  
        
        else if(!(run.isInt(again)))
        {
          System.out.println("\nPlease enter either \"1\" or \"2\".");
        }

      }

    }

  }
}