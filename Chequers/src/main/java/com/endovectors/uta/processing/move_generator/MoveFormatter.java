package com.endovectors.uta.processing.move_generator;

import com.endovectors.uta.processing.BadMoveException;
import com.endovectors.uta.processing.CheckersBoard;
import com.endovectors.uta.processing.Move;
import com.endovectors.uta.processing.List;

import java.util.ArrayList;
import java.util.Enumeration;

/**
 * Created by asham_000 on 7/5/2015.
 */
public class MoveFormatter implements MoveFormatterInterface{

    MoveTreeGeneratorInterface moveTreeGenerator;

    public MoveFormatter(){}

    public MoveFormatter(MoveTreeGeneratorInterface moveTreeGenerator){
        this.moveTreeGenerator = moveTreeGenerator;
    }

    public ArrayList<Move> getMove(CheckersBoard board){
        if (board == null){
            System.out.println("board is null");
        }
        List moves;
        ArrayList<Move> list = new ArrayList<Move>();
        if (moveTreeGenerator == null){
            moveTreeGenerator = new MoveTreeGenerator(board);
        }
        try {
            CheckersBoard testBoard = new CheckersBoard();
            moves = moveTreeGenerator.getNextMoves(board);
            Move move;
            Enumeration enu = moves.elements();
            while (enu.hasMoreElements()) {
                move = (Move) enu.nextElement();
                list.add(move);
            }
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
        return list;
    }
}
