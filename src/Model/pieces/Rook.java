package Model.pieces;

import java.util.ArrayList;
import java.util.List;

import Model.Board;
import Model.Color;
import Model.Piece;
import Model.PieceType;
import Model.Position;

/**
 * Repreresents a rook on the board.
 */
public class Rook extends Piece {

    /**
     * initializes and creates a new piece(rook).
     */
    public Rook(Color color, Position position) {
        super(color, PieceType.ROOK, position);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean isValidMove(Position pos, Board board) {
        Piece targetPiece = board.getPiece(pos);
        if (targetPiece != null && targetPiece.getColor() == this.color) return false;
        if (!board.OnBoardCheck(pos))
            return false;
        if (!board.pathCheck(position, pos))
            return false;
        int rowDiff = Math.abs(pos.row() - position.row());
        int colDiff = Math.abs(pos.col() - position.col());
        return (colDiff > 0 && rowDiff == 0 || rowDiff > 0 && colDiff == 0);
    }
    
    /**
     * {@inheritdoc}
     */
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
