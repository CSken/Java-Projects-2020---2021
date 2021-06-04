import javax.swing.*;
import java.awt.*;

public class GameOver extends JPanel {
    
    GameOver() {
        this.setVisible(false);
        this.setLayout(null);
        this.setBounds(150, 194, 300, 388);
        this.setBackground(Color.black);

        JLabel victory = new JLabel("Victory!");
        victory.setFont(new Font("Time", Font.BOLD, 38));
        victory.setForeground(Color.yellow);
        victory.setBackground(Color.black);
        victory.setBounds(78, 10, 150, 80);
        this.add(victory);
        victory.setOpaque(true);
        

        JLabel congrats = new JLabel("Congratulations! You win.");
        congrats.setFont(new Font("Time", Font.PLAIN, 18));
        congrats.setForeground(Color.white);
        congrats.setBackground(Color.black);
        congrats.setBounds(46, 190, 210, 20);
        this.add(congrats);
        congrats.setOpaque(true);

        victory.setVisible(true);
        congrats.setVisible(true);
    }

    public void endGame() {
        this.setVisible(true);
        this.setOpaque(true);
        GUI.board.setVisible(false);
        GUI.bank.setVisible(false);
        System.out.println("Ended");
    }
}
