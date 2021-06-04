import java.util.*;
import java.io.*;
class Main {

  public void printBoard(String[][] board)//print board method
  {
    for(int r = 0; r < board.length; r++)
    {
      for(int c = 0; c < board[0].length; c++)
      {
        System.out.print(board[r][c] + " ");
      }
      System.out.println();
    }
    System.out.println();
  }


  public void addHorizWord(String word, String[][] board, int indexRow, int indexColoumn)//adds a horizontal word to board with each index representing a letter
  {
    for(int i = 0; i < word.length(); i++)
    {
      board[indexRow][indexColoumn + i] = word.substring(i, i + 1);
    }
  }


  public void addVertWord(String word, String[][] board, int indexRow, int indexColoumn)//adds a vertical word to board with each index representing a letter
  {
    for(int i = 0; i < word.length(); i++)
    {
      board[indexRow + i][indexColoumn] = word.substring(i, i + 1);
    }
  }


  public int[] selectPosition(String word, String[][] board)//selects position for word on board
  {
    int[] posInfo = new int[3];//index 0 represents row index, index 1 represents coloumn index, index 2 represents if the word is going vertical or horizontal
    posInfo[2] = (int)(Math.random()*2);//0 represents horizontal word, 1 represents vertical word
    boolean boardChecked = false;//boolean for checking whether or not the whole board has been checked

    int counterFits = 0;//int counting the amount of characters in a word that can fit onto the board
    while(counterFits != word.length())
    {
      if(posInfo[2] == 0)//horizontal word
      {
        posInfo[0] = (int)(Math.random() * board.length);//represents row for the word to be on
        posInfo[1] = (int)(Math.random() * ((board[0].length - word.length()) + 1));//represents starting coloumn, and ensures that coloumn is not too far right so word can't fit

        boolean rowChecked = false;//boolean for checking whether or not a row has been completely checked
        
        while(counterFits != word.length())
        {
          counterFits = 0; //sets counter back to 0 if position not found

          for(int i = 0; i < word.length(); i++)//loop adds to counter if a character can fit into the matrix indices
          {
            if(board[posInfo[0]][posInfo[1] + i] == null || board[posInfo[0]][posInfo[1] + i].equals(word.substring(i, i + 1)))
              counterFits++;
          }

          if(counterFits == word.length())//returns the position info if position found
            return posInfo;

          else if(posInfo[1] != board[0].length - word.length())//if not at the end of the row, add 1 to the coloumn
            posInfo[1]++;

          else if(posInfo[1] == board[0].length - word.length() && rowChecked == false)//if at the end of the row, set it back to coloumn 0, and the row will be completely rechecked if it comes back to this if statement
          {
              posInfo[1] = 0;
              rowChecked = true;
          }

          else if(rowChecked)//this means the word can't fit in the current row and must find a new row
          {  
            rowChecked = false;//sets back to false for new row

            if(posInfo[0] == board.length - 1 && !boardChecked)//if on the last row, moves back to first, prepare switching orientation of word if not found when rechecking all rows
            {  
              posInfo[0] = 0;
              boardChecked = true; //will result in switching to vertical word instead of horizontal
            }

            else if(posInfo[0] == board.length-1 && boardChecked)//if the board has already been checked, switch oreintation to vertical
            { 
              posInfo[2] = 1;
              boardChecked = false;
              break;//exits the loop to enter the vertical word algorithm
            }

            else if(posInfo[0] != board.length - 1)//if not at the end of board
            {
              posInfo[0]++;
            }
          }

        }
      }


      else if(posInfo[2] == 1)//vertical word
      {
        posInfo[1] = (int)(Math.random() * board.length);//represents coloumn for the word to be on
        posInfo[0] = (int)(Math.random() * ((board[0].length - word.length()) + 1));//represents starting row, and ensures that coloumn is not too far down so word can't fit

        boolean colChecked = false;//boolean for checking whether or not a row has been completely checked
        while(counterFits != word.length())
        {
          counterFits = 0; //sets counter back to 0 if position not found

          for(int i = 0; i < word.length(); i++)//loop adds to counter if a character can fit into the matrix indices
          {
            if(board[posInfo[0] + i][posInfo[1]] == null || board[posInfo[0] + i][posInfo[1]].equals(word.substring(i, i + 1)))
              counterFits++;
          }

          if(counterFits == word.length())//returns the position info if position found
            return posInfo;

          else if(posInfo[0] != board.length - word.length())//if not at the end of the coloumn, add 1 to the row
            posInfo[0]++;

          else if(posInfo[0] == board.length - word.length() && colChecked == false)//if at the end of the coloumn, set it back to row 0, and the coloumn will be completely rechecked if it comes back to this if statement
          {
              posInfo[0] = 0;
              colChecked = true;
          }

          else if(colChecked)//this means the word can't fit in the current coloumn and must find a new coloumn
          {  
            colChecked = false;//sets back to false for the new coloumn

            if(posInfo[1] == board[0].length - 1 && !boardChecked)//if on the last coloumn, moves back to first, prepare to switch oreintation of word if not found when rechecking all coloumns
            {
              posInfo[1] = 0;
              boardChecked = true;
            }

            else if(posInfo[1] == board[0].length - 1 && boardChecked) //if the board has already been checked, switch orientation to horizontal
            {
              posInfo[2] = 0;
              boardChecked = false;
              break;//exits loop to enter horizontal word algorithm
            }

            else if(posInfo[1] != board[0].length - 1)//if not at end of board
            {
              posInfo[1]++;
            }
          }

        }
      }
    }

    return posInfo;
  }


  public void fillBoard(String[][] board)//fills remaining spaces with random letters
  {
    for(int r = 0; r < board.length; r++)
    {
      for(int c = 0; c < board[0].length; c++)
      {
        if(board[r][c] == null)
        {
          char randABC = (char)((int)((Math.random() * 26) + 97));//ramdom char from a-z
          board[r][c] = String.valueOf(randABC);
        }
      }
    }
  }


  public void insertionSort(ArrayList<String> words)//sortiong algorithm so words can be added to board more easily
  {
    for(int i = 1; i < words.size(); i++)
    {
      String currentWord = words.get(i);
      int j = i - 1;
      while(j >= 0 && currentWord.length() > words.get(j).length())
      {
        words.set(j+1, words.get(j));
        j--;
      }

      words.set(j + 1, currentWord);
    }
  }


//MAIN METHOD
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("words.txt"));
    Scanner scan = new Scanner(System.in);
    Main run = new Main();


//Creating board
    ArrayList<String> words = new ArrayList<String>();//arraylist of words from txt
    while(s.hasNext())
    {
      words.add(s.nextLine());
    }

    run.insertionSort(words);//sorts the words from longest to shortest to make adding words to board easier, also allows for finding the longest length in the list for the matrix size

    int matrixSize;//int for the matrix size
    if(words.get(0).length() > words.size())
      matrixSize = words.get(0).length();
    else matrixSize = words.size();

    String[][] board = new String[matrixSize][matrixSize];//new board with size depending on words in the list

    
    
//Adding words to list
    for(int i = 0; i < words.size(); i++)//adds each character of each word to matrix at random indices with random vertical/horizontal orientation
    {

      int[] posInfo = run.selectPosition(words.get(i), board);

      if(posInfo[2] == 0)
        run.addHorizWord(words.get(i), board, posInfo[0], posInfo[1]);

      else if(posInfo[2] == 1)
        run.addVertWord(words.get(i), board, posInfo[0], posInfo[1]);

    }

    run.fillBoard(board);//fills remaining spaces with random letters

    ArrayList<String> wordsRemaining = new ArrayList<String>(); //arraylist representing the words remaining
    for(int i = 0; i < words.size(); i++)
    {
      wordsRemaining.add(words.get(i));
    }
    do {
      System.out.println("\n");
      run.printBoard(board);//prints board
      System.out.println("Word Bank:\n" + wordsRemaining + "\n");

      System.out.println("What word do you see?");
        String userWord = scan.nextLine();

      System.out.println("\nWhat row does it start at (The top row is row 1 and the bottom row is row " + matrixSize + ")?");
        int userRow = scan.nextInt();
        scan.nextLine();

      System.out.println("\nWhat coloumn does it start at(The leftmost coloumn is coloumn 1 and the rightmost coloumn is coloumn " + matrixSize + ")?");
        int userColoumn = scan.nextInt();
        scan.nextLine();

      System.out.println("\nIs it horizontal (0) or vertical (1)?");
        int orientation = scan.nextInt();
        scan.nextLine();

      
      int lettersMatched = 0;//counter representing the amount of letters user got right for 
      for(int i = 0; i < userWord.length(); i++)
      {
        if(orientation == 0 && board[userRow - 1][(userColoumn - 1) + i].equals(userWord.substring(i, i+1)))//horizontal word
        {
          lettersMatched++;
        }

        else if(board[(userRow - 1) + i][userColoumn - 1].equals(userWord.substring(i, i+1)) && orientation == 1)//vertical word
        {
          lettersMatched++;
        }
      }



      if(lettersMatched == userWord.length())
      {
        System.out.println("Correct! \"" + userWord + "\" removed.");


        int removedIndex = 0;//index of the removed word
        for(int i = 0; i < wordsRemaining.size(); i++)
        {
          if(userWord.equals(wordsRemaining.get(i)))
            removedIndex = i;
        }

        wordsRemaining.remove(removedIndex);//removed word from words remaining
      }
      
      else if(lettersMatched != userWord.length())
      {
        System.out.println("Incorrect. Try again.");
      }


      if(wordsRemaining.size() == 0)
        System.out.println("Congratulations! You win!");


    }while(wordsRemaining.size() > 0);
  }
}