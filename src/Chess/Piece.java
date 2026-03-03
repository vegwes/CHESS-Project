package Chess;
/**
 * Super class, defines must haves for all the different type of pieces in chess.
 * @author Vegard Westermoen
 * @verison 1.0
 */
public abstract class Piece {
    public Color color;
    public PieceType type;
    public Position position;
    
    /**
     * Creates a new piece.
     * @param color desides which color it is.
     * @param type what type of piece it is.
     * @param position wher the piece is on the board.
     */
    public Piece(Color color, PieceType type, Position position) {
        this.color = color;
        this.type = type;
        this.position = position;
    }
    /**
     * finds what type of piece it is from the enum Piecetype.
     * @return
     */
    public PieceType getPieceType(){
        return type;
    }
    /**
    * Checks whether the move a piece is trying to make is legal.
    * It terms of it's general moving ability and if it is legal for it circumstances.
    * @param target The position we are trying to move to.
    * @param board The board we are trying to move on.
    * @return whether the move is valid or not.
    */
    public abstract boolean isValidMove(Position target, Board board);

    /**
     * This method updates the position your current piece is in.
     * @param position the position on the board.
     */
    public void setPosition(Position position) {
        this.position = position;
    }
}
