/* Move.java : The game movements
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
 * 
 * 
 * 
 * 
 * Some parts of this software has been heavily modified by John Sapp of team Endovectors for use on the Chequers system for the 
 * University of Texas at Arlington.
 */

package com.endovectors.uta.processing;

public class Move implements MoveInterface{
 

  private int from;
  private int to;

  public Move(){}

  public Move(int moveFrom, int moveTo) {
    from = moveFrom;
    to = moveTo;
  }


  public int getFrom () {
    return from;
  }

  public void setFrom(int from) {this.from = from;}

  public int getTo () {
    return to;
  }

  public void setTo(int to) {this.to = to;}

  public String toString () {
    return "(" + from + "," + to + ")";
  }

  public boolean compare(MoveInterface m){
    Move move = (Move) m;
    return move.from == this.from && move.to == this.to;
  }
}
