package Chess.pieces;

import Chess.Piece;
import Chess.Color;
import Chess.Position;
import Chess.PieceType;
import Chess.Board;
/**
 * Repreresents a rook on the board. 
 */
public class Rook extends Piece{

    /**
     * initializes and creates a new piece(rook).
     */ 
    public Rook(Color color, Position position){
        super(color, PieceType.ROOK, position);
    }

    @Override
    public boolean isValidMove(Position pos, Board board){
        if (!board.OnBoardCheck(pos)) return false;
        if(!board.pathCheck(position,pos)) return false;
        int rowDiff = Math.abs(pos.row() - position.row());
        int colDiff = Math.abs(pos.col() - position.col());
        return (colDiff >0 && rowDiff == 0 || rowDiff>0 && colDiff == 0);
    }
}
