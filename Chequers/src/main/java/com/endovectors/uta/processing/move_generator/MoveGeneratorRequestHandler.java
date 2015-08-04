package com.endovectors.uta.processing.move_generator;

import com.endovectors.uta.processing.BadMoveException;
import com.endovectors.uta.processing.CheckersBoard;
import com.endovectors.uta.processing.Move;
import com.endovectors.uta.processing.MoveInterface;


import java.util.ArrayList;

/**
 * Created by asham_000 on 7/5/2015.
 */
public class MoveGeneratorRequestHandler implements MoveGeneratorRequestHandlerInterface{

    MoveFormatterInterface moveFormatter;

    public MoveGeneratorRequestHandler(){
        moveFormatter = new MoveFormatter();
    }

    public MoveGeneratorRequestHandler(MoveFormatterInterface moveFormatter){
        this.moveFormatter = moveFormatter;
    }

    public ArrayList<MoveInterface> getMove(CheckersBoard board) throws BadMoveException {
        if (moveFormatter == null){
            moveFormatter = new MoveFormatter();
        }
        return moveFormatter.getMove(board);
    }
}