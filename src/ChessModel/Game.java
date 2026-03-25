package ChessModel;

import java.util.List;

import ChessModel.pieces.*;

/**
 * Represents a chess-game and handles the state of the game. Checks if it can
 * perform an attempted move and if it is a potentiall state-change and if the
 * game can go on.
 * 
 * @author Vegard Westermoen
 */
public class Game {

    /**
     * Represents the different states a chess game can be in.
     */
    public enum GameStatus {
        IN_PROGRESS, CHECK, CHECKMATE, STALEMATE, DRAW
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
     * 
     * @param from the position of the piece to move.
     * @param to   the position to move the piece to.
     * @return whether the move was successful or not.
     */
    public boolean makeMove(Position from, Position to) {
        if (status == GameStatus.CHECKMATE)
            return false;

        Piece piece = board.getPiece(from);
        if (piece == null || piece.color != currentPlayer)
            return false;

        if (!isSafeMove(from, to, currentPlayer))
            return false;

        boolean moved = board.movePiece(from, to);
        if (moved) {
            switchPlayer();

            if (isCheckMate(currentPlayer)) {
                this.status = GameStatus.CHECKMATE;
            } else if (board.isInCheck(currentPlayer)) {
                this.status = GameStatus.CHECK;
            } else {
                this.status = GameStatus.IN_PROGRESS;
            }
            return true;
        }
        return false;

    }

    public Color getCurrentTurn() {
        return this.currentPlayer;

    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private boolean hasValidMoves(Color color) {
        // implement method to check if the king has valid moves
        return false;
    }

    private boolean isCheckMate(Color color) {
        if (!board.isInCheck(color)) {
            return false;
        }

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Position from = new Position(r, c);
                Piece piece = board.getPiece(from);

                if (piece != null && piece.color == color) {
                    List<Position> potentialMoves = piece.getValidMoves(board);
                    for (Position to : potentialMoves) {
                        if (isSafeMove(from, to, color)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true; // sjakkmatt
    }

    private boolean isSafeMove(Position from, Position to, Color color) {
        // Implenet mtehod to check if the move we are checking takes the king out of
        // check.
        // visst posisjonen vi er på inneholder en brikke som er av den midlertidige
        // fargen,
        // gjennomfør movet og sjekk om det er sjakk.
        // visst det finnes et gyldig trekk, returner false
        Piece fromPiece = board.getPiece(from);
        Piece toPiece = board.getPiece(to);
        board.setPiece(to, fromPiece);
        board.setPiece(from, null);
        fromPiece.setPosition(to);

        boolean isStillCheck = board.isInCheck(color);

        board.setPiece(from, fromPiece);
        board.setPiece(to, toPiece);
        fromPiece.setPosition(from);

        if (toPiece != null) {
            toPiece.setPosition(to);
        }

        return !isStillCheck;
    }

    private boolean isEnPassant() {
        return true;
    }

    private boolean isStalemate(Color color) {
        // implement method to check if the king is in stalemate
        return false;

    }
}