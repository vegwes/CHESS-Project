package ChessModel.pieces;

import java.util.ArrayList;
import java.util.List;

import ChessModel.Board;
import ChessModel.Color;
import ChessModel.Piece;
import ChessModel.PieceType;
import ChessModel.Position;

/**
 * represents a bishop on the board.
 */
public class Bishop extends Piece {

    /**
     * initializes and creates a new piece(bishop).
     * 
     * @param color
     * @param position
     */
    public Bishop(Color color, Position position) {
        super(color, PieceType.BISHOP, position);
    }

    @Override
        public boolean isValidMove(Position pos, Board board) {

            if (this.position.equals(pos)) return false;

            Piece target = board.getPiece(pos);
            if (target != null && target.color == this.color) return false;
        
            int rowDiff = Math.abs(pos.row() - position.row());
            int colDiff = Math.abs(pos.col() - position.col());
            
            if (rowDiff != colDiff) return false;
        
            return board.pathCheck(this.position, pos);
        }

    public List<Position> getValidMoves(Board board) {
        List<Position> validMoves = new ArrayList<>();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Position cellPos = new Position(r, c);

                if (this.isValidMove(cellPos, board)) {
                    validMoves.add(cellPos);
                }
            }
        }
        return validMoves;
    }

}
