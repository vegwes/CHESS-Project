package View;

import javax.imageio.ImageIO;
import javax.swing.*;

import Model.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.Graphics2D;

/**
 * Represents the GUI of the chessgame.
 * @author Vegard Westermoen
 */
public class ChessView extends JPanel {
    private IGameModel game;
    private final int TILE_SIZE = 80;
    private Position selectedPosition = null;
    private Map<String, Image> pieceImages = new HashMap<>();

    /**Contructor that initializes the images of the chesspieces */
    public ChessView(IGameModel game) {
        this.game = game;
        loadImages();
    }

    /**
     * Sets the selected position.
     * @param pos
     */
    public void setSelectedPosition(Position pos) {
        this.selectedPosition = pos;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        drawPieces(g);
        drawGameOverMessage(g);
    }

    private void drawPieces(Graphics g) {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = game.getPiece(new Position(r, c));
                if (p != null) {
                    String key = p.getColor().toString().toLowerCase() + "_"
                            + p.getPieceType().toString().toLowerCase();
                    Image img = pieceImages.get(key);
                    if (img != null) {
                        g.drawImage(img, c * TILE_SIZE, (7 - r) * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
                    }
                }
            }
        }
    }

    private void drawBoard(Graphics g) {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if ((r + c) % 2 == 0) {
                    g.setColor(new Color(235, 235, 210)); // Creamwhite
                } else {
                    g.setColor(new Color(75, 50, 35)); // Brown
                }

                g.fillRect(c * TILE_SIZE, r * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
        if (selectedPosition != null) {
            Graphics2D g2 = (Graphics2D) g;
            g.setColor(new Color(100, 200, 0, 128));
            g2.setStroke(new BasicStroke(5));
            g.drawRect(selectedPosition.col() * TILE_SIZE, (7 - selectedPosition.row()) * TILE_SIZE, TILE_SIZE,
                    TILE_SIZE);
            g2.setStroke(new BasicStroke(1));

            Piece selectedPiece = game.getPiece(selectedPosition);

            if (selectedPiece != null && selectedPiece.getColor() == game.getCurrentTurn()) {

                List<Position> legalMoves = game.getLegalMovesForPiece(selectedPosition);
                g.setColor(new java.awt.Color(100, 200, 0, 128));

                for (Position move : legalMoves) {
                    int centerX = move.col() * TILE_SIZE + TILE_SIZE / 2;
                    int centerY = (7 - move.row()) * TILE_SIZE + TILE_SIZE / 2;
                    g.fillOval(centerX - 10, centerY - 10, 20, 20);
                }
            }
        }
    }

    private void drawGameOverMessage(Graphics g) {
        Game.GameStatus status = game.getStatus();
        if (status == Game.GameStatus.IN_PROGRESS || status == Game.GameStatus.CHECK) {
            return;
        }

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 40));

        String message = "";
        if (status == Game.GameStatus.CHECKMATE) {
            String winner = (game.getCurrentTurn() == Model.Color.WHITE) ? "BLACK" : "WHITE";
            message = "CHECKMATE " + winner + " WON!";
        } else if (status == Game.GameStatus.STALEMATE) {
            message = "STALEMATE, it's a draw!";
        } else if (status == Game.GameStatus.DRAW) {
            message = "REMIS, it's a draw!";
        }

        FontMetrics metrics = g2.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(message)) / 2;
        int y = getHeight() / 2;

        g2.drawString(message, x, y);
    }

    private void loadImages() {
        String[] colors = { "white", "black" };
        String[] types = { "pawn", "rook", "knight", "bishop", "queen", "king" };

        for (String color : colors) {
            for (String type : types) {
                String key = color + "_" + type;
                String path = "resources/" + key + ".png";
                try {
                    Image img = ImageIO.read(new File(path));
                    pieceImages.put(key, img);
                } catch (Exception e) {
                    System.out.println("No path such as: " + path);
                }
            }
        }
    }
}
