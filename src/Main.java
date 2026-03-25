import javax.swing.JFrame;

import ChessModel.*;
import View.ChessView;


public class Main {
    public static void main(String[] args) throws Exception {
        Game game = new Game();
        JFrame frame = new JFrame("Sjakk - Vegard Westermoen");
        ChessView view = new ChessView(game);
        
        frame.add(view);
        frame.setSize(640, 670);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        
    }
}