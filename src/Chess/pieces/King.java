package Chess.pieces;

import Chess.Piece; // Imports Abstractclass
import Chess.Color; // Imports color
import Chess.Position; // Imports position
import Chess.PieceType;

import java.util.ArrayList;
import java.util.List;

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
    public boolean isValidMove(Position pos, Board board) {
        Piece targetPiece = board.getPiece(pos);
        if (targetPiece != null && targetPiece.getColor() == this.color) return false;
        if (!board.OnBoardCheck(pos))
            return false;
        Piece target = board.getPiece(pos);
        if (target != null && target.color == this.color)
            return false;
        int rowDiff = Math.abs(pos.row() - position.row());
        int colDiff = Math.abs(pos.col() - position.col());
        return rowDiff <= 1 && colDiff <= 1 && (rowDiff != 0 || colDiff != 0);
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