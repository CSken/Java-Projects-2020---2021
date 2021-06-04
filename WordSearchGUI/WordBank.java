import javax.swing.*;
import java.awt.*;

public class WordBank extends JPanel {
    private static JLabel words;
    
    WordBank() {
        this.setLayout(null);
        this.setBackground(Color.black);
        this.setBounds(0, 600, 600, 175);
        this.setVisible(true);

        
        JLabel title = new JLabel();
        title.setBounds(5, 0, 250, 50);
        title.setText("Word Bank");
        title.setFont(new Font("Time", Font.BOLD,30));
        title.setForeground(Color.white);
        
        
        
        String wordsString = "";
        int characters = 0;
        for(int i = 0; i < Main.words.size(); i++)
        {
            wordsString += Main.words.get(i) + "   ";
            characters += Main.words.get(i).length();

            if(characters > 575/6)//new line if it reaches a certain amount of characters
            {
                wordsString += "\n";
                characters = 0;
            }
        }
        words = new JLabel("<html>" + wordsString.replaceAll("\n", "<br/>") + "</html>");
        words.setBounds(5, 35, 595, 50);
        words.setFont(new Font("Time", Font.PLAIN, 14));
        words.setForeground(Color.gray);



        this.add(title);
        this.add(words);
    }

    public static void updateLabel() {//updates label when a correct word found

        String wordsString = "";
        int characters = 0;
        for(int i = 0; i < Main.words.size(); i++)
        {
            wordsString += Main.words.get(i) + "   ";
            characters += Main.words.get(i).length();

            if(characters > 575/6)//new line if it reaches a certain amount of characters
            {
                wordsString += "\n";
                characters = 0;
            }
           
        }

        for(int x = 0; x < Main.bankWords.size(); x++)//changes color of found words
        {
           wordsString = wordsString.replaceAll(Main.bankWords.get(x), "<font color = 'green'>" + Main.bankWords.get(x) + "</font>");
        }

        words.setText("<html>" + wordsString.replaceAll("\n", "<br/>") + "</html>" );
        
    }

    
    
}
