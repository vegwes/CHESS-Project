package Chess.pieces;

import Chess.Piece; // Imports Abstractclass
import Chess.Color; // Imports color
import Chess.Position; // Imports position
import Chess.PieceType;

import java.util.ArrayList;
import java.util.List;

import Chess.Board;

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
            // 1. Kan ikke stå stille
            if (this.position.equals(pos)) return false;
        
            // 2. Kan ikke ta egne brikker
            Piece target = board.getPiece(pos);
            if (target != null && target.color == this.color) return false;
        
            // 3. MÅ være diagonalt
            int rowDiff = Math.abs(pos.row() - position.row());
            int colDiff = Math.abs(pos.col() - position.col());
            
            if (rowDiff != colDiff) return false;
        
            // 4. Veien må være klar
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
