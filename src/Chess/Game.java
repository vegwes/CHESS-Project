package Chess;

 /**
  * Represents a chess-game and handles the state of the game. 
  * Checks if it can perform an attempted move and if it is a potentiall
  * state-change and if the game can go on.
  * 
  * @author Vegard Westermoen
  */
public class Game {

    /**
     * Represents the different states a chess game can be in.
     */
    public enum GameStatus {
        IN_PROGRESS,
        CHECK,
        CHECKMATE,
        STALEMATE,
        DRAW
    }

    private final Board board;
    private Color currentPlayer;
    private GameStatus status;
    /**
     * Creates a new game with a new board and sets the game-satus to "in progress".
     */
    public Game() {
        this.board = new Board();
        this.currentPlayer = Color.WHITE;
        this.status = GameStatus.IN_PROGRESS;
    }
    // gets the current board
    public Board getBoard() {
        return board;
    }
    // gets the current player
    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    // gets the current game status
    public GameStatus getStatus() {
        return status;
    }

    /**
     * tries to make a move on the board, if it is not in progress or check.
     * @param from the position of the piece to move.
     * @param to the position to move the piece to.
     * @return whether the move was successful or not.
     */
    public boolean makeMove(Position from, Position to) {
        if (status != GameStatus.IN_PROGRESS && status != GameStatus.CHECK) {
            return false;
        }

        Piece piece = board.getPiece(from);
        if (piece == null || piece.color != currentPlayer) {
            return false;
        }

        boolean moved = board.movePiece(from, to);
        if (moved) {
            switchPlayer();
            return true;
        }
        return false;
    }

        public Color getCurrentTurn(){
            return this.currentPlayer;
        
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }


    private boolean isInCheck(Color color) {
        //implement method to check if the king is in check
        return false;
    }

    private boolean hasValidMoves(Color color) {
        //implement method to check if the king has valid moves
        return false;
    }

    private boolean isCheckmate(Color color) {
        //implement method to check if the king is in checkmate
        return false;
    }

    private boolean isStalemate(Color color) {
        //implement method to check if the king is in stalemate
        return false;
    }
}
