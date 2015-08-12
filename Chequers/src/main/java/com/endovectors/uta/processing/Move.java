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

public class Move implements MoveInterface {


    private int from;
    private int to;

    public Move() {
    }

    public Move(int moveFrom, int moveTo) {
        from = moveFrom;
        to = moveTo;
    }


    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public String toString() {
        return "(" + from + "," + to + ")";
    }

    public boolean compare(Move move) {
        return move.from == this.from && move.to == this.to;
    }

    public int getWinner() {
        return 0;
    }

    public int getCapturePiece() {
        if (Math.abs(to - from) > 6) {
            return calculateCapturePiece();
        } else {
            return -1;
        }
    };

    private void calculateCapturePiece() {
        switch (from) {
            case 0:
                switch (to) {
                    case 9:
                        return 4;
                }
                break;
            case 1:
                switch (to) {
                    case 8:
                        return 4;
                    case 10:
                        return 5;
                }
                break;
            case 2:
                switch (to) {
                    case 9:
                        return 5;
                    case 11:
                        return 6;
                }
                break;
            case 3:
                switch (to) {
                    case 10:
                        return 6;
                }
                break;
            case 4:
                switch (to) {
                    case 13:
                        return 9;
                }
                break;
            case 5:
                switch (to) {
                    case 14:
                        return 10;
                    case 12:
                        return 9;
                }
                break;
            case 6:
                switch (to) {
                    case 15:
                        return 11;
                    case 13:
                        return 10;
                }
                break;
            case 7:
                switch (to) {
                    case 14:
                        return 11;
                }
                break;
            case 8:
                switch (to) {
                    case 17:
                        return 12;
                }
                break;
            case 9:
                switch (to) {
                    case 0:
                        return 4;
                    case 2:
                        return 5;
                    case 18:
                        return 13;
                    case 16:
                        return 12;
                }
                break;
            case 10:
                switch (to) {
                    case 1:
                        return 5;
                    case 3:
                        return 6;
                    case 17:
                        return 13;
                    case 19:
                        return 14;
                }
                break;
            case 11:
                switch (to) {
                    case 2:
                        return 6;
                    case 18:
                        return 14;
                }
                break;
            case 12:
                switch (to) {
                    case 9:
                        return 5;
                    case 21:
                        return 17;
                }
                break;
            case 13:
                switch (to) {
                    case 4:
                        return 9;
                    case 6:
                        return 10;
                    case 20:
                        return 17;
                    case 22:
                        return 18;
                }
                break;
            case 14:
                switch (to) {
                    case 5:
                        return 10;
                    case 7:
                        return 11;
                    case 23:
                        return 19;
                    case 21:
                        return 18;
                }
                break;
            case 15:
                switch (to) {
                    case 6:
                        return 11;
                    case 22:
                        return 19;
                }
                break;
            case 16:
                switch (to) {
                    case 9:
                        return 12;
                    case 25:
                        return 20;
                }
                break;
            case 17:
                switch (to) {
                    case 8:
                        return 12;
                    case 10:
                        return 13;
                    case 26:
                        return 21;
                    case 24:
                        return 20;
                }
                break;
            case 18:
                switch (to) {
                    case 9:
                        return 13;
                    case 11:
                        return 14;
                    case 27:
                        return 22;
                    case 25:
                        return 21;
                }
                break;
            case 19:
                switch (to) {
                    case 10:
                        return 14;
                    case 26:
                        return 22;
                }
                break;
            case 20:
                switch (to) {
                    case 13:
                        return 17;
                    case 29:
                        return 25;
                }
                break;
            case 21:
                switch (to) {
                    case 12:
                        return 17;
                    case 14:
                        return 18;
                    case 28:
                        return 25;
                    case 30:
                        return 26;
                }
                break;
            case 22:
                switch (to) {
                    case 13:
                        return 18;
                    case 15:
                        return 19;
                    case 29:
                        return 26;
                    case 31:
                        return 27;
                }
                break;
            case 23:
                switch (to) {
                    case 14:
                        return 19;
                    case 30:
                        return 27;
                }
                break;
            case 24:
                switch (to) {
                    case 17:
                        return 20;
                }
                break;
            case 25:
                switch (to) {
                    case 16:
                        return 20;
                    case 18:
                        return 21;
                }
                break;
            case 26:
                switch (to) {
                    case 17:
                        return 21;
                    case 19:
                        return 22;
                }
                break;
            case 27:
                switch (to) {
                    case 18:
                        return 22;
                }
                break;
            case 28:
                switch (to) {
                    case 21:
                        return 25;
                }
                break;
            case 29:
                switch (to) {
                    case 20:
                        return 25;
                    case 22:
                        return 26;
                }
                break;
            case 30:
                switch (to) {
                    case 21:
                        return 26;
                    case 23:
                        return 27;
                }
                break;
            case 31:
                switch (to) {
                    case 22:
                        return 27;
                }
                break;
        }
    }
}

