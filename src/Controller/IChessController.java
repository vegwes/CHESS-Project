package Controller;

import java.awt.event.MouseEvent;

import Model.*;
import View.*;
public interface IChessController{

  void handleClicks(Position pos);

  void mousePressed(MouseEvent e);
}
