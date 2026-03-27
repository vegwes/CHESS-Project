package Model;
import java.util.List;

public interface IGameModel {
    /**
     * tries to make a move on the board, if it is not in progress or check.
     * 
     * @param from the position of the piece to move.
     * @param to   the position to move the piece to.
     * @return whether the move was successful or not.
     */
    boolean makeMove(Position from, Position to);

    /**
     * gets the current color that can make a move. 
     * @return white or black.
     */
    Color getCurrentTurn();
    
    /**
     * gets the current status of the game.
     * @return one of the alternatives.
     */
    Game.GameStatus getStatus();
    
    // En ny metode i Game som bruker isSafeMove + Piece sin getValidMoves
    List<Position> getLegalMovesForPiece(Position pos);

    // Fra Board.java (via Game)
    Piece getPiece(Position pos);
}