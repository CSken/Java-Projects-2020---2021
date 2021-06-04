import java.io.*;
import java.util.*;
class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("words.txt"));
    Scanner scan = new Scanner(System.in);

    ArrayList<String> words = new ArrayList<String>();//arraylist of words from txt
    while(s.hasNext())
    {
      words.add(s.nextLine());
    }


    int repeat;//choice for user to repeat or not
    int i=0;//index for list of words
    do {
      int guesses = 6;//user's guesses
      int length = words.get(i).length();//word length
      int lettersRemaining = length;//value for letters remaining

      String lettersGuessed ="Letters Guessed: \n";//string for letters guessed
      

      ArrayList<String> letters = new ArrayList<String>();//arraylist for letters
        for(int n=0; n<length; n++)
        {
          letters.add("_");
        }

      do {
        for(int n=0; n<length; n++)//prints user's remaining letters
        {
          System.out.print(letters.get(n)+" ");
        }
        
        System.out.println();//layout
        System.out.println("Guesses Remaining: "+guesses+"\n\n"+lettersGuessed);
        

        System.out.println("\nYour next letter?");//asks user's letter
        char userLetter =scan.next().charAt(0);
        System.out.println("\n\n");

        lettersGuessed+=userLetter+", ";//updates letters guessed
        
        lettersRemaining=0;
        boolean exists=false;//boolean to check if letter guessed correctly
        for(int n=0; n<length; n++)//checks if guessed right or wrong and how many left
        {
          if(userLetter==words.get(i).charAt(n))//if guessed correctly
          {
            letters.set(n, String.valueOf(words.get(i).charAt(n)));
            exists=true;
          }     

          if(letters.get(n).equals("_"))//counts letters remaining
            lettersRemaining++;
        }

        if(exists==false)//guessed incorrectly
          guesses--;
      
      }while(guesses!=0 && lettersRemaining!=0);

      //final results
      if(guesses==0)
        System.out.println("Sorry! You've used all your guesses.");
      if(lettersRemaining==0)
        System.out.println(words.get(i)+"\nCongratulations! You got the word.");

      System.out.println("Would you like a new word?\n1. Yes\n2. No");
      repeat = scan.nextInt();//asks if user would like to repeat

      i++;//goes to next word
      System.out.println("\n\n\n\n");
    }while(repeat!=2);
  }
}