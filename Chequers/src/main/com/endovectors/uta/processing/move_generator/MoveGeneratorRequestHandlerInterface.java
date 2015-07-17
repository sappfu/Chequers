package com.endovectors.uta.processing.move_generator;

import com.endovectors.uta.processing.BadMoveException;
import com.endovectors.uta.processing.CheckersBoard;
import com.endovectors.uta.processing.Move;

import java.util.ArrayList;

/**
 * Created by asham_000 on 7/16/2015.
 */
public interface MoveGeneratorRequestHandlerInterface {
    public ArrayList<Move> getMove(CheckersBoard board) throws BadMoveException;
}
