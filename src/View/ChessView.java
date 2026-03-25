package View;

import javax.imageio.ImageIO;
import javax.swing.*;

import ChessModel.Game;
import ChessModel.Piece;
import ChessModel.Position;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.Graphics2D;


public class ChessView extends JPanel {
    private Game game;
    private final int TILE_SIZE = 80;
    private Position selectedPosition = null;
    private Map<String, Image> pieceImages = new HashMap<>();

    public ChessView(Game game) {
        this.game = game;
        loadImages();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleClicks(e.getX() / TILE_SIZE, e.getY() / TILE_SIZE);
            }
        });
    }

    private void handleClicks(int col, int row) {
        int logicalRow = 7 - row;
        Position clickedPos = new Position(logicalRow, col);

        if (selectedPosition == null) {
            if (game.getBoard().getPiece(clickedPos) != null) {
                selectedPosition = clickedPos;
            }
        } else {
            game.makeMove(selectedPosition, clickedPos);
            selectedPosition = null;
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        drawPieces(g);
    }

    private void drawPieces(Graphics g) {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = game.getBoard().getPiece(new Position(r, c));
                if (p != null) {
                    String key = p.getColor().toString().toLowerCase() + "_" +
                            p.getPieceType().toString().toLowerCase();
                    Image img = pieceImages.get(key);
                    if (img != null) {
                        g.drawImage(img, c * TILE_SIZE, (7-r) * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
                    }
                }
            }
        }
    }

    private void drawBoard(Graphics g) {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if ((r + c) % 2 == 0) {
                    g.setColor(new Color(235, 235, 210)); // Kremhvit
                } else {
                    g.setColor(new Color(75, 50, 35)); // brun
                }

                g.fillRect(c * TILE_SIZE, r * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
        if (selectedPosition != null){
            Graphics2D g2 = (Graphics2D) g;
            g.setColor(new Color(100,200,0,128));
            g2.setStroke(new BasicStroke(5));
            g.drawRect(selectedPosition.col() * TILE_SIZE, (7-selectedPosition.row()) * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            g2.setStroke(new BasicStroke(1));

            Piece selectedPiece = game.getBoard().getPiece(selectedPosition);

            if (selectedPiece != null && selectedPiece.getColor() == game.getCurrentTurn()){

                List<Position> validMoves = selectedPiece.getValidMoves(game.getBoard());
                g.setColor(new java.awt.Color(100, 200, 0, 128));
                
                for (Position move : validMoves){
                    int centerX = move.col() * TILE_SIZE + TILE_SIZE / 2;
                    int centerY = (7 - move.row()) * TILE_SIZE + TILE_SIZE / 2;
                    g.fillOval(centerX - 10, centerY - 10, 20, 20);
                }
            }
        }
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
