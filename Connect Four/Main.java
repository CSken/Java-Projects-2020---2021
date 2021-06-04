import java.util.*;
class Main {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    System.out.println("Connect 4\nPress Enter to Continue");
    scan.nextLine();

    String[][] board = new String[6][7];//connect 4 board 
    for(int r=0; r<board.length; r++)
    {
      for(int c=0; c<board[0].length; c++)
      {
        board[r][c] = "| ";
        board[r][6]="| |";
      }
    }


    int player = 1;//starting player's move
    boolean win=false;
    int again=1;//for asking player if they want to play again

    do{
      for(int r=0; r<board.length; r++)//prints board
      {
        for(int c=0; c<board[0].length; c++)
        {
          System.out.print(board[r][c]);
        }
        System.out.println();
      }
      System.out.println(" 1 2 3 4 5 6 7");
      System.out.println("\nPlayer 1 - X\nPlayer 2 - O\n");



      if(player==1)//player 1's move
      {
        int coloumn;
        int row;
        boolean full=false;
        do{//checks if coloumn is full and adds piece if not 

          full=false;//sets full back to false after finding a full coloumn
  
          do{//gets user's coloumn and checks if exists
            System.out.println("Player 1\nWhich coloumn would you like to drop your piece?");
            coloumn = scan.nextInt()-1;
            scan.nextLine();
        
            if(coloumn>6 || coloumn<0)//if coloumn doesn't exist
              {
                System.out.println("That coloumn doesn't exist. Press enter to try again.\n");
                scan.nextLine();
              }
            }while(coloumn>6 || coloumn<0);

          row=5;
          while((String.valueOf(board[row][coloumn].charAt(1)).equals("X") || String.valueOf(board[row][coloumn].charAt(1)).equals("O")) && full!=true)//finds lowest row not yet taken
          {
            if(row>0)
              row--;
            
            if(row==0 && (String.valueOf(board[row][coloumn].charAt(1)).equals("X") || String.valueOf(board[row][coloumn].charAt(1)).equals("O")))//checks if the coloumn is full
              {
                full=true;
              }
          }

          if(full==true)//response to full coloumns
            {
              System.out.println("That coloumn is full. Press enter to try again.\n");
              scan.nextLine();
            }
          }while(full==true);//asks for coloumn again if coloumn full


        board[row][coloumn] = "|X";//updates board
        if(coloumn==6)
        board[row][coloumn]="|X|";

        player++;//changes to player 2
      }


      else //player 2's move
      {
        int coloumn;
        int row;
        boolean full=false;
        do{//checks if coloumn is full and adds piece if not
    
          full=false;

          do{//gets user's coloumn and checks if exists
            System.out.println("Player 2\nWhich coloumn would you like to drop your piece?");
            coloumn = scan.nextInt()-1;
            scan.nextLine();
        
            if(coloumn>6 || coloumn<0)//if coloumn doesn't exist
              {
                System.out.println("That coloumn doesn't exist. Press enter to try again.\n");
                scan.nextLine();
              }
            }while(coloumn>6 || coloumn<0);

          row=5;
          while((String.valueOf(board[row][coloumn].charAt(1)).equals("X") || String.valueOf(board[row][coloumn].charAt(1)).equals("O")) && full!=true)//finds lowest row not yet taken
          {
            if(row>0)
              row--;
            
            if(row==0 && (String.valueOf(board[row][coloumn].charAt(1)).equals("X") || String.valueOf(board[row][coloumn].charAt(1)).equals("O")))//checks if the coloumn is full
              {
                full=true;
              }
          }

          if(full==true)//response to full coloumn
            {
              System.out.println("That coloumn is full. Press enter to try again.\n");
              scan.nextLine();
            }
          }while(full==true);//asks for coloumn again if coloumn full


        board[row][coloumn] = "|O";//updates board
        if(coloumn==6)
        board[row][coloumn]="|O|";

        player--;//changes to player 1
      }


      int vertCounter;//counter for vertical connect 4
      for(int r=0; r<3; r++)//checks for vertical connect 4 from row 1 to row 3 in intervals of 4
      {
        for(int c=0; c<board[0].length; c++)
        {
          vertCounter=1;//sets back to 1 after each interval
          for(int potRow=1; potRow<4; potRow++)//counts how many pieces equal in a 4 piece interval with potential rows
          {           
            if((board[r][c].charAt(1)==board[r+potRow][c].charAt(1)) && board[r][c].charAt(1)!=' ')
            {
              vertCounter++;
              if(vertCounter>=4)//if connect 4 found
              {
                win=true;
                vertCounter=1;
              }
            }
          }
        }
      }


      int horCounter;//counter for horizontal connect 4
      for(int c=0; c<4; c++)//checks for horizontal connect 4 from col 1 to row 3 in intervals of 4 
      {
        for(int r=0; r<board.length; r++)
        {
          horCounter=1;//sets back to 1 after each interval
          for(int potCol=1; potCol<4; potCol++)//counts how many pieces equal in a 4 piece interval with potential coloumns
          {           
            if((board[r][c].charAt(1)==board[r][c+potCol].charAt(1)) && board[r][c].charAt(1)!=' ')
            {
              horCounter++;
              if(horCounter>=4)//if connect 4 found
              {
                win=true;
                horCounter=1;
              }
            }
          }
        }
      }



      for(int botC=0; botC<board[0].length; botC++)//finds a connect 4 for every positive diagonal starting from the bottom row
      {
        int diagRow=5;
        int diagCounter;
        for(int c=botC; c<board[0].length; c++)//potential diagonals from the bottom
        {

          diagCounter=1;
          int potRow=1;
          for(int potCol=1; potCol<4 && c+potCol<7 && diagRow-potRow>-1; potCol++)//diagonals separated into intervals of 4
          {
            if(board[diagRow][c].charAt(1)==board[diagRow-potRow][c+potCol].charAt(1) && board[diagRow][c].charAt(1)!=' ')
            {
              diagCounter++;

              if(diagCounter>=4)
              {
                win=true;
                diagCounter=1;
              }
            }
            potRow++;
          }
          diagRow--;
        }
      }


      for(int topC=6; topC>-1; topC--)//finds a connect 4 for every positive diagonal starting from the top row
      {
        int diagRow=0;
        int diagCounter;
        for(int c=topC; c>-1; c--)//potential diagonals from the top
        {

          diagCounter=1;
          int potRow=1;
          for(int potCol=1; potCol<4 && c-potCol>-1 && diagRow+potRow<board.length; potCol++)//diagonals separated into intervals of 4
          {
            if(board[diagRow][c].charAt(1)==board[diagRow+potRow][c-potCol].charAt(1) && board[diagRow][c].charAt(1)!=' ')
            {
              diagCounter++;

              if(diagCounter>=4)
              {
                win=true;
                diagCounter=1;
              }
            }
            potRow++;
          }
          diagRow++;
        }
      }


      for(int topC=6; topC>-1; topC--)//finds a connect 4 for every negative diagonal starting from the bottom row
      {
        int diagRow=5;
        int diagCounter;
        for(int c=topC; c>-1; c--)//potential diagonals from the bottom
        {

          diagCounter=1;
          int potRow=1;
          for(int potCol=1; potCol<4 && c-potCol>-1 && diagRow-potRow>-1; potCol++)//diagonals separated into intervals of 4
          {
            if(board[diagRow][c].charAt(1)==board[diagRow-potRow][c-potCol].charAt(1) && board[diagRow][c].charAt(1)!=' ')
            {
              diagCounter++;

              if(diagCounter>=4)
              {
                win=true;
                diagCounter=1;
              }
            }
            potRow++;
          }
          diagRow--;
        }
      }


      for(int botC=0; botC<7; botC++)//finds a connect 4 for every negative diagonal starting from the top row
      {
        int diagRow=0;
        int diagCounter;
        for(int c=botC; c<7; c++)//potential diagonals from the top
        {

          diagCounter=1;
          int potRow=1;
          for(int potCol=1; potCol<4 && c+potCol<7 && diagRow+potRow<6; potCol++)//diagonals separated into intervals of 4
          {
            if(board[diagRow][c].charAt(1)==board[diagRow+potRow][c+potCol].charAt(1) && board[diagRow][c].charAt(1)!=' ')
            {
              diagCounter++;

              if(diagCounter>=4)
              {
                win=true;
                diagCounter=1;
              }
            }
            potRow++;
          }
          diagRow++;
        }
      }



//printing if someone wins      
      if(win==true && player==1)//player 2 wins
      {
        for(int r=0; r<board.length; r++)//prints board
        {
          for(int c=0; c<board[0].length; c++)
          {
            System.out.print(board[r][c]);
          }
          System.out.println();
        }
      System.out.println(" 1 2 3 4 5 6 7");
      System.out.println("\nPlayer 1 - X\nPlayer 2 - O\n");
        System.out.println("\nCongratulations. Player 2 wins!");
      }

      if(win==true && player==2)//player 1 wins
      {
        for(int r=0; r<board.length; r++)//prints board
        {
          for(int c=0; c<board[0].length; c++)
          {
            System.out.print(board[r][c]);
          }
          System.out.println();
        }
      System.out.println(" 1 2 3 4 5 6 7");
      System.out.println("\nPlayer 1 - X\nPlayer 2 - O\n");
        System.out.println("\nCongratulations. Player 1 wins!");
      }

      if(win==true)//end game
      {
        System.out.println("\nWould you like to play again?\n1. Yes\n2. No");
        again=scan.nextInt();
        scan.nextLine();

        if(again==1)//if again, reset board, win=false
        {
          win=false;
          for(int r=0; r<board.length; r++)
          {
            for(int c=0; c<board[0].length; c++)
            {
              board[r][c] = "| ";
              board[r][6]="| |";
            }
          }
        }
      }
    }while(again==1);
  }
}
