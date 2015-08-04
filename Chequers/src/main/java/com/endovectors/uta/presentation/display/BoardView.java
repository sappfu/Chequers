/* BoardView.java : Where the board is drawn
 * Copyright (C) 1998-2002  Paulo Pinto
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA 02111-1307, USA.
 */
package com.endovectors.uta.presentation.display;

import com.endovectors.uta.processing.*;
import com.endovectors.uta.processing.List;
import com.endovectors.uta.processing.move_generator.MoveTreeGenerator;
import com.endovectors.uta.processing.move_generator.MoveTreeGeneratorInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ResourceBundle;
import java.util.Stack;



public class BoardView extends JPanel {

  private CheckersBoard board;

   int startX;
   int startY;

  int cellWidth;
  
  Graphics graph; // added

  List selected;


  MoveTreeGeneratorInterface computer;
  
  private static final int SIZE = 0;
  

  private JFrame parent;

  private MouseHandler handler;
  

  // need to get rid of mouse handler and listener
  public BoardView (JFrame parentComponent, CheckersBoard b) {
    selected = new List ();
    board = b;
    parent = parentComponent;
    computer = new MoveTreeGenerator(b);
    handler = new MouseHandler (this, parent);
    addMouseListener (handler);
  }

  public CheckersBoard getBoard () {
    return board;
  }

  public void newGame () throws BadMoveException {
    board.clearBoard ();
    selected.clear ();
    repaint ();
    handler.reset ();
    computer.getNextMoves(board);
    ChangeTitle ();
  }


   public void ChangeTitle () {
    if (board.getCurrentPlayer () == CheckersBoard.WHITE)
     parent.setTitle ("whiteTitleLabel");
    else
      parent.setTitle ("blackTitleLabel");
  }

  public void saveBoard (String fileName) {
    try {
      FileOutputStream ostream = new FileOutputStream (fileName);
      ObjectOutputStream p = new ObjectOutputStream(ostream);
      p.writeObject(board);
      p.flush();
      ostream.close();
    }
    catch (IOException e) {
      e.printStackTrace ();
      System.exit (1);
    }
  }

  public void loadBoard (String fileName) {
    try {
      FileInputStream istream = new FileInputStream(fileName);
      ObjectInputStream p = new ObjectInputStream(istream);
      board = (CheckersBoard) p.readObject();
      istream.close();
      repaint ();
      ChangeTitle ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      System.exit (1);
    }    
  }
  
  public void setBoard(CheckersBoard b)
  {
	  this.board = b;
	  repaint();
  }
  
  public void paintComponent (Graphics g) {
    Dimension d = getSize ();
    int marginX;
    int marginY;
    int incValue;
    
    
    g.setColor (Color.lightGray);
    g.fillRect (0, 0, d.width, d.height);
    g.setColor (Color.black);
    
    if (d.width < d.height) {
      marginX = 0;
      marginY = (d.height - d.width) / 2;
      
      incValue = d.width / 8;
    }
    else  {
      marginX = (d.width - d.height) / 2;
      marginY = 0;
      
      incValue = d.height / 8;
    }

    startX = marginX;
    startY = marginY;
    cellWidth = incValue;
    
    //graph = g; // added

    drawBoard (g, marginX, marginY, incValue);
    drawPieces (g, marginX, marginY, incValue, board);
    // might need to change to drawPieces with board in parameters
    // would need to have a setBoard method
  }


  private void drawBoard (Graphics g, int marginX, int marginY, int incValue) {
    int pos;

    for (int y = 0; y < 8; y++)
      for (int x = 0; x < 8; x++) {
        if ((x + y) % 2 == 0)
          g.setColor (Color.white);
        else {
          pos = y * 4 + (x + ((y % 2 == 0) ? - 1 : 0)) / 2;

          if (selected.has (new Integer (pos)))
            g.setColor (Color.green);
          else
            g.setColor (Color.black);
        }


        g.fillRect (marginX + x * incValue, marginY + y * incValue, incValue - 1, incValue - 1);
      }
  }


  private static final int KING_SIZE = 3;
  
  private void drawPieces (Graphics g, int marginX, int marginY, int incValue) {
    int x, y;
    for (int i = 0; i < 32; i++)
      try {
        if (board.getPiece (i) != CheckersBoard.EMPTY) {
          if (board.getPiece (i) == CheckersBoard.BLACK ||
              board.getPiece (i) == CheckersBoard.BLACK_KING)
            g.setColor (Color.ORANGE);
          else
            g.setColor (Color.blue);

          y = i / 4;
          x = (i % 4) * 2 + (y % 2 == 0 ? 1 : 0);
          g.fillOval (SIZE + marginX + x * incValue, SIZE + marginY + y * incValue,
                      incValue - 1 - 2 * SIZE, incValue - 1 - 2 * SIZE);

          if (board.getPiece (i) == CheckersBoard.WHITE_KING) {
            g.setColor (Color.black);
            g.drawOval (KING_SIZE + marginX + x * incValue, KING_SIZE + marginY + y * incValue,
                        incValue - 1 - 2 * KING_SIZE, incValue - 1 - 2 * KING_SIZE);
          }
          else if (board.getPiece (i) == CheckersBoard.BLACK_KING) {
            g.setColor (Color.white);
            g.drawOval (KING_SIZE + marginX + x * incValue, KING_SIZE + marginY + y * incValue,
                        incValue - 1 - 2 * KING_SIZE, incValue - 1 - 2 * KING_SIZE);
          }



        }
      }
      catch (BadCoordException bad) {
        bad.printStackTrace ();
        System.exit (1);
      }
  }


  
  // made it public from private
  private void drawPieces (Graphics g, int marginX, int marginY, int incValue, CheckersBoard board) {
    int x, y;
    for (int i = 0; i < 32; i++)
      try {
        if (board.getPiece (i) != CheckersBoard.EMPTY) {
          if (board.getPiece (i) == CheckersBoard.BLACK ||
                  board.getPiece (i) == CheckersBoard.BLACK_KING)
            g.setColor (Color.ORANGE);
          else
            g.setColor (Color.blue);

          y = i / 4;
          x = (i % 4) * 2 + (y % 2 == 0 ? 1 : 0);
          g.fillOval (SIZE + marginX + x * incValue, SIZE + marginY + y * incValue,
                  incValue - 1 - 2 * SIZE, incValue - 1 - 2 * SIZE);

          if (board.getPiece (i) == CheckersBoard.WHITE_KING) {
            g.setColor (Color.black);
            g.drawOval (KING_SIZE + marginX + x * incValue, KING_SIZE + marginY + y * incValue,
                    incValue - 1 - 2 * KING_SIZE, incValue - 1 - 2 * KING_SIZE);
          }
          else if (board.getPiece (i) == CheckersBoard.BLACK_KING) {
            g.setColor (Color.white);
            g.drawOval (KING_SIZE + marginX + x * incValue, KING_SIZE + marginY + y * incValue,
                    incValue - 1 - 2 * KING_SIZE, incValue - 1 - 2 * KING_SIZE);
          }



        }
      }
      catch (BadCoordException bad) {
        bad.printStackTrace ();
        System.exit (1);
      }
  }
 
}
    
  
class MouseHandler extends MouseAdapter {
  private BoardView view;

  private JFrame parent;
  


  Stack boards;
  
  public MouseHandler (BoardView boardView, JFrame parentComponent) {
    view = boardView;
    parent = parentComponent;
    boards = new Stack ();
  }

  public void mouseClicked (MouseEvent e) {
    int pos;
   


    pos = getPiecePos (e.getX (), e.getY ());
    if (pos != -1)
      try {
        CheckersBoard board = view.getBoard ();

        int piece = board.getPiece (pos);
        
        if (piece != CheckersBoard.EMPTY &&
            (((piece == CheckersBoard.WHITE || piece == CheckersBoard.WHITE_KING) &&
              board.getCurrentPlayer () == CheckersBoard.WHITE) ||
              ((piece == CheckersBoard.BLACK || piece == CheckersBoard.BLACK_KING) &&
              board.getCurrentPlayer () == CheckersBoard.BLACK))) {
          if (view.selected.isEmpty ()) 
            view.selected.push_back (new Integer (pos));
          else {
            int temp = ((Integer) view.selected.peek_tail ()).intValue ();

            if (temp == pos)
              view.selected.pop_back ();
            else
	      JOptionPane.showMessageDialog (parent,
                                             "notSelectedLabel",
                                             "errorLabel",
                                             JOptionPane.ERROR_MESSAGE);
          }
          
          
          view.repaint ();
          return;
        }
        else {
          boolean good = false;
          CheckersBoard tempBoard;
                    
          if (!view.selected.isEmpty ()) {
            
            if (boards.empty ()) {
              tempBoard = (CheckersBoard) board.clone ();
              boards.push (tempBoard);
            }
            else
              tempBoard = (CheckersBoard) boards.peek ();
            

            int from = ((Integer) view.selected.peek_tail ()).intValue ();
            if (tempBoard.isValidMove (from, pos)) {
              tempBoard = (CheckersBoard) tempBoard.clone ();

              boolean isAttacking = tempBoard.mustAttack ();
              
              tempBoard.move (from, pos);
              
              if (isAttacking && tempBoard.mayAttack (pos)) {
                view.selected.push_back (new Integer (pos));
                boards.push (tempBoard);
                view.repaint ();
              }
              else {
                view.selected.push_back (new Integer (pos));
                makeMoves (view.selected, board);
                boards = new Stack ();
              }
              
              good = true;
            }
            else if (from == pos) {
              view.selected.pop_back ();
              boards.pop ();
              view.repaint ();
              good = true;
            }
          }
          
          if (!good)
            JOptionPane.showMessageDialog (parent,
                                           "invalidMoveLabel",
                                           "errorLabel",
                                           JOptionPane.ERROR_MESSAGE);
        }
      }
      catch (BadCoordException bad) {
        bad.printStackTrace ();
        System.exit (1);        
      }
      catch (BadMoveException bad) {
        bad.printStackTrace ();
        System.exit (1);        
      }
    }


  public void reset () {
    boards = new Stack ();
  }
  


  private void makeMoves (List moves, CheckersBoard board) throws BadMoveException {
    List moveList = new List ();
    int from, to = 0;

    from = ((Integer) moves.pop_front ()).intValue ();
    while (!moves.isEmpty ()) {
      to = ((Integer) moves.pop_front ()).intValue ();
      moveList.push_back (new Move (from, to));
      from = to;
    }

    board.move (moveList);
    view.repaint (1);
    view.selected.clear ();
    reset ();
        

    if (!gameEnded ()) {
      view.ChangeTitle ();
      view.computer.getNextMoves(board);
      view.repaint ();

      if (!gameEnded ())
        view.ChangeTitle ();
    }
  }
    
  private int getPiecePos (int currentX, int currentY) {
    for (int i = 0; i < 32; i++) {
      int x, y;

      y = i / 4;
      x = (i % 4) * 2 + (y % 2 == 0 ? 1 : 0);
      if (view.startX + x * view.cellWidth < currentX &&
          currentX < view.startX + (x + 1) * view.cellWidth &&
          view.startY + y * view.cellWidth < currentY &&
          currentY < view.startY + (y + 1) * view.cellWidth)
        return i;
    }

    return -1;
  }

  private boolean gameEnded () {
    CheckersBoard board = view.getBoard ();
    boolean result;

    int white = board.getWhitePieces ();
    int black = board.getBlackPieces ();
    if (board.hasEnded ()) {
      if (board.winner () == CheckersBoard.BLACK)
        JOptionPane.showMessageDialog (parent, "blackWinLabel",
                                       "endGameLabel",
                                       JOptionPane.INFORMATION_MESSAGE);
      else
        JOptionPane.showMessageDialog (parent, "whiteWinLabel",
                                       "endGameLabel",
                                       JOptionPane.INFORMATION_MESSAGE);
      result = true;
    }
    else
      result = false;

    return result;
  }
  
}