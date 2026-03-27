package Model.pieces;

import java.util.ArrayList;
import java.util.List;

import Model.Board;
import Model.Color;
import Model.Piece;
import Model.PieceType;
import Model.Position;
/**
 * represents a pawn on the board.
 */
public class Pawn extends Piece {

    /**
     * initializes and creates a new piece(pawn).
     */ 
    public Pawn(Color color, Position position) {
        super(color, PieceType.PAWN, position);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean isValidMove(Position pos, Board board){
        Piece targetPiece = board.getPiece(pos);
        if (targetPiece != null && targetPiece.getColor() == this.color) return false;
        if (!board.OnBoardCheck(pos)) return false;
        int rowDiff = pos.row() - position.row();
        int colDiff = Math.abs(pos.col() - position.col());
        int forward = (color == Color.WHITE) ? 1 : -1;

        Piece target = board.getPiece(pos);
        Position inbetween = new Position(position.row() + forward, position.col());
        Piece inBetweenPiece = board.getPiece(inbetween);

        // Usual move, one route forward. 
        if (rowDiff == forward && colDiff == 0 && target == null) return true;

        // First move, two routes forward. 
        if ((position.row() == 1 || position.row() == 6) && rowDiff == 2 * forward && colDiff == 0
            && target == null && inBetweenPiece == null) return true;

        // Move diagonaly when attacking. 
        if (rowDiff == forward && colDiff == 1 && target != null && target.color != color) return true;
        return false;
    }

    /**
     * {@inheritdoc}
     */
    public List<Position> getValidMoves(Board board) {
        List<Position> validMoves = new ArrayList<>();
        for (int r = 0; r < 8; r++){
            for (int c = 0; c < 8; c++){
                Position cellPos = new Position(r,c);

                if(this.isValidMove(cellPos, board)){
                    validMoves.add(cellPos);
                }
            }
        }
        return validMoves;
    }
}
