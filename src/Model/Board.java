package Model;

import Model.pieces.*;

 /**
  * Represents the board in a chess game.
  * @author Vegard Westermoen
  * @version 1.0
  */
public class Board {
    private Piece[][] grid;
    
    /**
     * Creates a new board and initializes it with pieces in their starting positions.
     */
    public Board() {
        this.grid = new Piece[8][8];
        makeBoard();
    }
    //initialize the board with pieces in their starting positions
    private void makeBoard(){
        makeBackRow(0,Color.WHITE);
        makeBackRow(7,Color.BLACK);

        for (int i = 0; i < grid.length; i++){
            grid[1][i] = new Pawn(Color.WHITE, new Position(1,i));
            grid[6][i] = new Pawn(Color.BLACK, new Position(6,i));
        }
    }

    /**
     * Initializes the back row of the board with the correct pieces.
     * @param row
     * @param color
     */
    private void makeBackRow(int row, Color color){
        grid[row][0] = new Rook(color, new Position(row,0));
        grid[row][1] = new Knight(color, new Position(row,1));
        grid[row][2] = new Bishop(color, new Position(row,2));
        grid[row][3] = new Queen(color, new Position(row,3));
        grid[row][4] = new King(color, new Position(row,4));
        grid[row][5] = new Bishop(color, new Position(row,5));
        grid[row][6] = new Knight(color, new Position(row,6));
        grid[row][7] = new Rook(color, new Position(row,7));
    }

    /**
     * Gets the piece at the given position.
     * @param pos
     * @return the piece at the given position.
     */
    public Piece getPiece(Position pos){
        if (!OnBoardCheck(pos)) return null;
        return grid[pos.row()][pos.col()];
    }

    

    /**
     * Checks if the given position is on the board.
     * @param pos
     * @return whether the position is on the board.
     */
    public boolean OnBoardCheck(Position pos) {
    return pos.row() >= 0 && pos.row() < 8 && 
           pos.col() >= 0 && pos.col() < 8;
    }

    /**
     * Moves a piece from the start position to the end position.
     * @param start the position of the piece to move.
     * @param end the position to move the piece to.
     * @return whether the move was successful or not.
     */
    public boolean movePiece(Position start, Position end){
        Piece currentPiece = getPiece(start);
        if(currentPiece == null){
            return false;
        }
        if (currentPiece.isValidMove(end, this)){
            grid[end.row()][end.col()] = currentPiece;
            grid[start.row()][start.col()] = null;

            currentPiece.setPosition(end);

            return true;
        }
        return false;
    }

    /**
     * Checks if the path between the start and end position is clear.
     * @param start the position of the piece to move.
     * @param end the position to move the piece to.
     * @return whether the path is clear.
     */
    public boolean pathCheck(Position start, Position end) {
        int rowStep = Integer.compare(end.row(), start.row());
        int colStep = Integer.compare(end.col(), start.col());
    
        int currRow = start.row() + rowStep;
        int currCol = start.col() + colStep;
    
  
        while (currRow != end.row() || currCol != end.col()) {
            if (currRow < 0 || currRow > 7 || currCol < 0 || currCol > 7) return false;
            if (grid[currRow][currCol] != null) {
                return false;
            }
            currRow += rowStep;
            currCol += colStep;
        }
        return true;
    }

    public void setPiece(Position pos, Piece piece){
        grid[pos.row()][pos.col()] = piece;
    }

    public boolean isInCheck(Color kingColor) {
        Position kingPos = findKing(kingColor);
        if (kingPos == null) return false;
    
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = grid[r][c];
                if (p != null && p.color != kingColor) {
                    if (p.isValidMove(kingPos, this)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private Position findKing(Color color){
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                Piece piece = grid[i][j];
                if (piece instanceof King && piece.color == color)
                    return new Position(i,j);
            }
        }
        return null;
    }

}


