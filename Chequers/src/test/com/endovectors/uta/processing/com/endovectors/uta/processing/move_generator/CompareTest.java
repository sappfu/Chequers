package com.endovectors.uta.processing.com.endovectors.uta.processing.move_generator;

import com.endovectors.uta.processing.CheckersBoard;
import com.endovectors.uta.processing.validator.Compare;
import com.endovectors.uta.processing.validator.CompareInterface;
import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by asham_000 on 7/17/2015.
 */
public class CompareTest extends TestCase {

    CompareInterface compare;
    CheckersBoard board;
    public void setUp(){
        board = new CheckersBoard();
        board.getPieces()[20] = 0;
        board.getPieces()[19] = 2;
    }

    public void testCompare(){
        compare = new Compare();
        Assert.assertTrue(compare.compare(board));
    }
}
