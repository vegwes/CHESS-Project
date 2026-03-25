package Model;
import java.util.List;

public interface IGameModel {
    // Fra Game.java
    boolean makeMove(Position from, Position to);

    Color getCurrentTurn();
    
    Game.GameStatus getStatus();
    
    // En ny metode i Game som bruker isSafeMove + Piece sin getValidMoves
    List<Position> getLegalMovesForPiece(Position pos);

    // Fra Board.java (via Game)
    Piece getPiece(Position pos);
}