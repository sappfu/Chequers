package com.endovectors.uta.processing.com.endovectors.uta.processing.move_generator;

import com.endovectors.uta.processing.CheckersBoard;
import com.endovectors.uta.processing.List;
import com.endovectors.uta.processing.move_generator.MoveTreeGenerator;
import com.endovectors.uta.processing.move_generator.MoveTreeGeneratorInterface;
import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by asham_000 on 7/16/2015.
 */
public class MoveTreeGeneratorTest extends TestCase {

    CheckersBoard board;
    MoveTreeGeneratorInterface moveTreeGenerator;

    protected void setUp(){
        board = new CheckersBoard();
    }

    public void testGetNextMoves(){
        moveTreeGenerator = new MoveTreeGenerator(board);
        try {
            List moves = moveTreeGenerator.getNextMoves(board);
            Assert.assertTrue(moves.length() > 0);
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
