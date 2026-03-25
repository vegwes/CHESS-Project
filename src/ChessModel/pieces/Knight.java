package ChessModel.pieces;

import java.util.ArrayList;
import java.util.List;

import ChessModel.Board;
import ChessModel.Color;
import ChessModel.Piece;
import ChessModel.PieceType;
import ChessModel.Position;
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
        Piece targetPiece = board.getPiece(pos);
        if (targetPiece != null && targetPiece.getColor() == this.color) return false;
        if(!board.OnBoardCheck(pos)) return false;
        Piece target = board.getPiece(pos);
        int rowDiff = Math.abs(pos.row() - position.row());
        int colDiff = Math.abs(pos.col() - position.col());
        if (target != null && target.color == this.color) return false;
        return (rowDiff ==2 && colDiff ==1) || (rowDiff ==1 && colDiff ==2);
    }

    public List<Position> getValidMoves(Board board){
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
