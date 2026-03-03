package Chess.pieces;

import Chess.Piece;     // Imports Abstractclass
import Chess.Color;     // Imports color
import Chess.Position;  // Imports position
import Chess.PieceType;
import Chess.Board;

/**
 * represents a king on the board.
 */
public class King extends Piece {
    /**
     * initializes and creates a new piece(king).
     */ 
    public King(Color color, Position position) {
        super(color, PieceType.KING, position);
    }

    @Override
    public boolean isValidMove(Position pos, Board board){
        if (!board.OnBoardCheck(pos)) return false;
        Piece target = board.getPiece(pos);
        if (target != null && target.color == this.color) return false;
        int rowDiff = Math.abs(pos.row() - position.row());
        int colDiff = Math.abs(pos.col() - position.col());
        return rowDiff <= 1 && colDiff <= 1 && (rowDiff != 0 || colDiff != 0);
    }
}