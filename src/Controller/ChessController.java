package Controller;

import View.*;

import java.awt.event.MouseEvent;
import Model.*;

import java.awt.event.MouseAdapter;

public class ChessController extends MouseAdapter implements IChessController{
  private final IGameModel model;
  private final ChessView view;
  private Position selectedPosition = null;
  private final int TILE_SIZE = 80;

  public ChessController(IGameModel model, ChessView view) {
    this.model = model;
    this.view = view;
  }

    @Override
    public void mousePressed(MouseEvent e) {
        int col = e.getX() / TILE_SIZE;
        int row = 7 - (e.getY() / TILE_SIZE);
        Position clickedPos = new Position(row, col);

        handleClicks(clickedPos);
    }

  @Override
  public void handleClicks(Position clickedPos) {
    if (selectedPosition == null) {

      if (model.getPiece(clickedPos) != null && model.getPiece(clickedPos).getColor() == model.getCurrentTurn()) {
        selectedPosition = clickedPos;
      }
    } else {
      model.makeMove(selectedPosition, clickedPos);
      selectedPosition = null;
    }

    view.setSelectedPosition(selectedPosition);
    view.repaint();
  }
}
