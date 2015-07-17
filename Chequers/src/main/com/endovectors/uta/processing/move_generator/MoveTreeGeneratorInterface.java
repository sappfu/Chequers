package com.endovectors.uta.processing.move_generator;

import com.endovectors.uta.processing.BadMoveException;
import com.endovectors.uta.processing.CheckersBoard;
import com.endovectors.uta.processing.List;

/**
 * Created by asham_000 on 7/16/2015.
 */
public interface MoveTreeGeneratorInterface {

    public List getNextMoves(CheckersBoard board) throws BadMoveException;
}
