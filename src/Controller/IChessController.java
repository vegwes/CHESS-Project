package Controller;

import java.awt.event.MouseEvent;

import Model.*;
import View.*;
public interface IChessController{

  /**
   * Handles the clicks on the board.
   * It checks if it is the color you try to move's turn.
   * If so, then try to make the move, if not do not do anything.
   * @param pos the position you clicked on.
   */
  void handleClicks(Position pos);

/**
 * Registers when you press the mouse, and where on the board you pressed.
 * @param e mouse-event.
 */
  void mousePressed(MouseEvent e);
}
