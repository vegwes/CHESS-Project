package Chess.pieces;

import Chess.Piece;
import Chess.Color;
import Chess.Position;
import Chess.PieceType;
import Chess.Board;
/**
 * Represents a queen on a board.
 */
public class Queen extends Piece {

    /**
     * initializes and creates a new piece(queen).
     */ 
    public Queen(Color color, Position position){
        super(color, PieceType.QUEEN, position);
    }

    @Override
    public boolean isValidMove(Position pos, Board board){
        if (!board.OnBoardCheck(pos)) return false;
        if(!board.pathCheck(position,pos)) return false;
        int rowDiff = Math.abs(pos.row() - position.row());
        int colDiff = Math.abs(pos.col() - position.col());
        return rowDiff == colDiff || rowDiff == 0 || colDiff == 0;
    }
}