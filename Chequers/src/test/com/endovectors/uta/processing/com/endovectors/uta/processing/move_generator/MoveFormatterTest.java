package com.endovectors.uta.processing.com.endovectors.uta.processing.move_generator;

import com.endovectors.uta.processing.CheckersBoard;
import com.endovectors.uta.processing.List;
import com.endovectors.uta.processing.Move;
import com.endovectors.uta.processing.move_generator.MoveFormatter;
import com.endovectors.uta.processing.move_generator.MoveFormatterInterface;
import com.endovectors.uta.processing.move_generator.MoveTreeGenerator;
import com.endovectors.uta.processing.move_generator.MoveTreeGeneratorInterface;
import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Enumeration;

/**
 * Created by asham_000 on 7/16/2015.
 */
public class MoveFormatterTest extends TestCase{

    CheckersBoard board;
    MoveTreeGeneratorInterface moveTreeGenerator;
    List moves;
    MoveFormatterInterface moveFormatter;

    public void setUp(){
        board = new CheckersBoard();
        moveTreeGenerator = new MoveTreeGenerator(board);
        try {
            moves = moveTreeGenerator.getNextMoves(board);
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public void testGetMove(){
        try {
            moveFormatter = new MoveFormatter();
            ArrayList<Move> formattedMoves = moveFormatter.getMove(board);
            Move move;
            Enumeration enu = moves.elements();
            int i=0;
            while (enu.hasMoreElements()) {
                move = (Move) enu.nextElement();
                Assert.assertTrue(move.compare(formattedMoves.get(i++)));
            }
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
