package Chess.pieces;

import Chess.Piece;     // Imports Abstractclass
import Chess.Color;     // Imports color
import Chess.Position;  // Imports position
import Chess.PieceType;
import Chess.Board;
/**
 * Represents a knight on the board.
 */
public class Knight extends Piece {

    /**
     * initializes and creates a new piece(knight).
     */ 
    public Knight(Color color, Position position) {
        super(color, PieceType.KNIGHT, position);
    }

    @Override
    public boolean isValidMove(Position pos, Board board){
        if(!board.OnBoardCheck(pos)) return false;
        Piece target = board.getPiece(pos);
        int rowDiff = Math.abs(pos.row() - position.row());
        int colDiff = Math.abs(pos.col() - position.col());
        if (target != null && target.color == this.color) return false;
        return (rowDiff ==2 && colDiff ==1) || (rowDiff ==1 && colDiff ==2);
    }

}
