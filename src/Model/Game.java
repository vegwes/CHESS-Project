package Model;

import java.util.List;

/**
 * Represents a chess-game and handles the state of the game. Checks if it can
 * perform an attempted move and if it is a potentiall state-change and if the
 * game can go on.
 * 
 * @author Vegard Westermoen
 */
public class Game implements IGameModel {

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

    /**
     * {@inheritdoc}
     */
    @Override
    public GameStatus getStatus() {
        return status;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean makeMove(Position from, Position to) {
        if (status == GameStatus.CHECKMATE || status == GameStatus.STALEMATE || status == GameStatus.DRAW)
            return false;

        Piece piece = board.getPiece(from);
        if (piece == null || piece.color != currentPlayer)
            return false;

        if (!piece.isValidMove(to, board))
            return false;

        if (!isSafeMove(from, to, currentPlayer))
            return false;

        boolean moved = board.movePiece(from, to);
        if (moved) {
            switchPlayer();

            if (isCheckMate(currentPlayer)) {
                this.status = GameStatus.CHECKMATE;
            } else if (isStalemate(currentPlayer)) {
                this.status = GameStatus.STALEMATE;
            } else if (board.isInCheck(currentPlayer)) {
                this.status = GameStatus.CHECK;
            } else {
                this.status = GameStatus.IN_PROGRESS;
            }
            return true;
        }
        return false;

    }


    @Override
    public List<Position> getLegalMovesForPiece(Position pos) {
        Piece piece = board.getPiece(pos);
        if (piece == null)
            return new java.util.ArrayList<>();

        // Her henter vi de "mekaniske" trekkene fra brikken (fra Piece-filene)
        List<Position> potential = piece.getValidMoves(board);
        List<Position> legal = new java.util.ArrayList<>();

        for (Position to : potential) {
            // Her bruker vi den isSafeMove-metoden vi skrev tidligere!
            if (isSafeMove(pos, to, piece.color)) {
                legal.add(to);
            }
        }
        return legal;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public Color getCurrentTurn() {
        return this.currentPlayer;

    }

    @Override
    public Piece getPiece(Position pos) {
        return board.getPiece(pos);
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
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
        if (board.isInCheck(color)) {
            return false;
        }
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Position pos = new Position(r, c);
                Piece piece = board.getPiece(pos);
                if (piece != null && piece.color == color) {
                    List<Position> legal = getLegalMovesForPiece(pos);
                    if (legal.size() > 0)
                        return false;
                }
            }
        }
        return true;

    }
}