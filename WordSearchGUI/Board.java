import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Board extends JPanel implements ActionListener {
    
    private JButton[][] buttonBoard;//representing each letter in the gui board
    private ArrayList<JButton> buttonsPressed = new ArrayList<JButton>();//buttons pressed
    private int[][] locationsPressed = new int[2][2];//representing the 2 locations of the user's inputs, row 0 represents first position, row 1 2nd position
    private int buttonSize = 600/Main.board.length;

    //creates number of buttons depending on matrix input
    Board() {

        locationsPressed[0][0] = -1;//default value, -1 represents it being empty
        this.setLayout(null);
        this.setBounds(0, 0, 600, 600);
        this.setOpaque(true);
        this.setBackground(Color.decode("#242424"));
        this.setVisible(true);

        
        buttonBoard = new JButton[Main.board.length][Main.board[0].length];//Matrix of JButton to represent the board

        int y = 0;//sets value of JButton equal to board and adds it to GUI according to the size of the board
        for(int r = 0; r < Main.board.length; r++)
        {
            int x = 0;
            for(int c = 0; c < Main.board[0].length; c++)
            {
                buttonBoard[r][c] = new JButton();

                buttonBoard[r][c].addActionListener(this);
                buttonBoard[r][c].setText(Main.board[r][c]);
                buttonBoard[r][c].setFocusable(false);//gets rid of dotted box when clicking button
                buttonBoard[r][c].setFont(new Font("Time", Font.BOLD, buttonSize/2));
                buttonBoard[r][c].setForeground(Color.decode("#5B9C20"));//text color
                buttonBoard[r][c].setBackground(Color.decode("#242424"));
                buttonBoard[r][c].setBorder(null);

                buttonBoard[r][c].setBounds(x, y, buttonSize, buttonSize);

                this.add(buttonBoard[r][c]);

                x += buttonSize;
            }
            y += buttonSize;
        }
    }


    public int[] buttonLocate(ActionEvent e) {//method for locating the row and column of a button pressed
        
        int[] location = new int[2];

        for(int r = 0; r < buttonBoard.length; r++)
        {
            for(int c = 0; c < buttonBoard[0].length; c++)
            {
                if(buttonBoard[r][c] == e.getSource())
                {
                    location[0] = r;//0,0 represents row, 0,1 column
                    location[1] = c;
                    return location;
                }
            }
        }
        return location;
    }


    public boolean wordFound()//to check if a word is found using 2 button presses
    {
        if(locationsPressed[0][0] != locationsPressed[1][0] && locationsPressed[0][1] != locationsPressed[1][1])//to check if they can even form a vertical/horizontal line
        {
            return false;//the line formed is diagonal
        }

        else
        {
            for(int i = 0; i < Main.words.size(); i ++)//checks every word in word list
            {
                if(this.wordInButtons().equals(Main.words.get(i)))
                {
                    return true;
                }
            }          
        }
        return false;//no word found in words list
    }

    public boolean isDuplicate(String wordChecked) {
        for(int i = 0; i < Main.bankWords.size(); i++)
        {
            if(wordChecked.equals(Main.bankWords.get(i)))
            {
                return true;
            }
        }
        return false;
    }


    public String wordInButtons()//called in wordFound() only if line isn't diagonal
    {
        String word = "";
        int orientation;//word is either horizontal or vertical, hor 0, ver 1

        if(locationsPressed[0][0] == locationsPressed[1][0])//horizontal word
        {
            orientation = 0;
        }

        else orientation = 1;//vertical word

        if(orientation == 0)
        {
            if(locationsPressed[0][1] < locationsPressed[1][1])//first button is to the left
            {
                for(int i = 0; i < locationsPressed[1][1] - locationsPressed[0][1] + 1; i++)
                {
                    word += Main.board[locationsPressed[0][0]][locationsPressed[0][1] + i];
                }
            }

            else//first button is to the right
            {
                for(int i = 0; i < locationsPressed[0][1] - locationsPressed[1][1] + 1; i++)
                {
                    word += Main.board[locationsPressed[0][0]][locationsPressed[1][1] + i];
                }
            }
        }

        else if(orientation == 1)
        {
            if(locationsPressed[0][0] < locationsPressed[1][0])//first button on top
            {
                for(int i = 0; i < locationsPressed[1][0] - locationsPressed[0][0] + 1; i++)
                {
                    word += Main.board[locationsPressed[0][0] + i][locationsPressed[0][1]];
                }
            }

            else//first button is on bottom
            {
                for(int i = 0; i < locationsPressed[0][0] - locationsPressed[1][0] + 1; i++)
                {
                    word += Main.board[locationsPressed[1][0] + i][locationsPressed[0][1]];
                }
            }
        }       

        return word;
    }

    public void colorButtons(int[][] locations) {
        int orientation;//word is either horizontal or vertical, hor 0, ver 1

        if(locationsPressed[0][0] == locationsPressed[1][0])//horizontal word
        {
            orientation = 0;
        }

        else orientation = 1;//vertical word

        if(orientation == 0)
        {
            if(locationsPressed[0][1] < locationsPressed[1][1])//first button is to the left
            {
                for(int i = 0; i < locationsPressed[1][1] - locationsPressed[0][1] + 1; i++)
                {
                    buttonBoard[locationsPressed[0][0]][locationsPressed[0][1] + i].setForeground(Color.decode("#BDA73F"));
                }
            }

            else//first button is to the right
            {
                for(int i = 0; i < locationsPressed[0][1] - locationsPressed[1][1] + 1; i++)
                {
                    buttonBoard[locationsPressed[0][0]][locationsPressed[1][1] + i].setForeground(Color.decode("#BDA73F"));
                }
            }
        }

        else if(orientation == 1)
        {
            if(locationsPressed[0][0] < locationsPressed[1][0])//first button on top
            {
                for(int i = 0; i < locationsPressed[1][0] - locationsPressed[0][0] + 1; i++)
                {
                    buttonBoard[locationsPressed[0][0] + i][locationsPressed[0][1]].setForeground(Color.decode("#BDA73F"));
                }
            }

            else//first button is on bottom
            {
                for(int i = 0; i < locationsPressed[0][0] - locationsPressed[1][0] + 1; i++)
                {
                    buttonBoard[locationsPressed[1][0] + i][locationsPressed[0][1]].setForeground(Color.decode("#BDA73F"));
                }
            }
        }  
    }


    public void actionPerformed(ActionEvent e) {
        
        int[] location = this.buttonLocate(e);

        if(locationsPressed[0][0] == -1)//first row empty, -1 represents empty
        {
            locationsPressed[0][0] = location[0];
            locationsPressed[0][1] = location[1];
        }

        else
        {
            locationsPressed[1][0] = location[0];
            locationsPressed[1][1] = location[1];
        }
        
            
        buttonsPressed.add(buttonBoard[location[0]][location[1]]);
        buttonBoard[location[0]][location[1]].setEnabled(false);

        if(buttonsPressed.size() == 2)//if number of buttons user pressed reaches 2
        {
            if(wordFound() && !isDuplicate(wordInButtons()) )
            {
                this.colorButtons(locationsPressed);
                Main.bankWords.add(wordInButtons());//adds word to word bank
                WordBank.updateLabel();//updates wordBank label

                if(Main.bankWords.size() == Main.words.size())
                {
                    GUI.gameOver.endGame();
                }
                
                for(int i = buttonsPressed.size() - 1; i >= 0; i--)//removes buttonsPressed and re-enables
                {
                    buttonsPressed.get(i).setEnabled(true);
                    buttonsPressed.remove(i);
                }
            }

            locationsPressed[0][0] = -1;//sets locations pressed back to empty
            for(int i = buttonsPressed.size() - 1; i >= 0; i--)//removes buttonsPressed and re-enables
            {
                buttonsPressed.get(i).setEnabled(true);
                buttonsPressed.remove(i);
            }
        }
    }

    
}
