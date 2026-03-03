package Chess.pieces;

import Chess.Piece;     // Imports Abstractclass
import Chess.Color;     // Imports color
import Chess.Position;  // Imports position
import Chess.PieceType;
import Chess.Board;
    /**
     * represents a bishop on the board.
     */
public class Bishop extends Piece {

    /**
     * initializes and creates a new piece(bishop).
     * @param color
     * @param position
     */
    public Bishop(Color color, Position position) {
        super(color, PieceType.BISHOP, position);
    }

    @Override
    public boolean isValidMove(Position pos, Board board){
        if (!board.OnBoardCheck(pos)) return false;
        if(!board.pathCheck(position,pos)) return false;
        int rowDiff = Math.abs(pos.row() - position.row());
        int colDiff = Math.abs(pos.col() - position.col());
        return (rowDiff == colDiff) && rowDiff>0;
    }

}
