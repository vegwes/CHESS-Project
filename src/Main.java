import javax.swing.JFrame;

import Controller.*;
import Model.*;
import View.*;


public class Main {
    public static void main(String[] args) throws Exception {
        IGameModel game = new Game();
        ChessView view = new ChessView(game);
        ChessController controller = new ChessController(game, view);
        view.addMouseListener(controller);
        JFrame frame = new JFrame("Sjakk - Vegard Westermoen");
        frame.add(view);
        frame.setSize(640, 670);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        
    }
}