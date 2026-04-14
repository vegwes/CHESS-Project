
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controller.*;
import Model.*;
import View.*;


public class Main {
    public static void main(String[] args) throws Exception {
        IGameModel game = new Game();
        ChessView view = new ChessView(game);

        JLabel turnLabel = new JLabel();
        turnLabel.setText("IT'S " + game.getCurrentTurn() + "'S TURN!");
        turnLabel.setHorizontalAlignment(SwingConstants.CENTER);
        turnLabel.setFont(new Font("Arial", Font.BOLD, 22));
        turnLabel.setBorder(new EmptyBorder(8, 0, 14, 0));

        ChessController controller = new ChessController(game, view, turnLabel);
        view.addMouseListener(controller);
        JFrame frame = new JFrame("Sjakk - Vegard Westermoen");

        JPanel root = new JPanel(new BorderLayout());
        root.setBorder(new EmptyBorder(16, 16, 16, 16)); // margin around everything
        root.add(turnLabel, BorderLayout.NORTH);
        root.add(view, BorderLayout.CENTER);
        frame.setContentPane(root);
        frame.pack();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        
    }
}