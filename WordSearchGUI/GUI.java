import javax.swing.*;

public class GUI extends JFrame {
    
    public static Board board;
    public static WordBank bank;
    public static GameOver gameOver;

    GUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setResizable(false);
        this.setTitle("Word Search");
        this.setLayout(null);//allows for resizing label, borderlayout to null

        ImageIcon image = new ImageIcon("logo.png");
        this.setIconImage(image.getImage());

        board = new Board();
        bank = new WordBank();
        gameOver = new GameOver();

        this.setSize(600, 775);
        this.add(board); 
        this.add(bank);
        this.add(gameOver);


        this.setVisible(true);
    }

}
