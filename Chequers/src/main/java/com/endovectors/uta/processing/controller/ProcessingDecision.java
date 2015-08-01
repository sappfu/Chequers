package com.endovectors.uta.processing.controller;

import com.endovectors.uta.processing.CheckersBoard;
import com.endovectors.uta.processing.InvalidMove;
import com.endovectors.uta.processing.Move;
import com.endovectors.uta.processing.MoveInterface;
import com.endovectors.uta.processing.move_generator.MoveGeneratorRequestHandler;
import com.endovectors.uta.processing.move_generator.MoveGeneratorRequestHandlerInterface;
import com.endovectors.uta.processing.validator.ValidatorRequestHandler;
import com.endovectors.uta.processing.validator.ValidatorRequestHandlerInterface;
import com.endovectors.uta.processing.vision.VisionRequestHandler;
import com.endovectors.uta.processing.vision.VisionRequestHandlerInterface;

import java.util.ArrayList;

/**
 * Created by asham_000 on 7/5/2015.
 */
public class ProcessingDecision {

    private VisionRequestHandlerInterface visionRequestHandler;
    private ValidatorRequestHandlerInterface validatorRequestHandler;
    private MoveGeneratorRequestHandlerInterface moveGeneratorRequestHandler;

    public ProcessingDecision(){
        visionRequestHandler = new VisionRequestHandler();
        validatorRequestHandler = new ValidatorRequestHandler();
        moveGeneratorRequestHandler = new MoveGeneratorRequestHandler();
    }

    public CheckersBoard decide(){
        return visionRequestHandler.getBoard();
    }

    public ArrayList<MoveInterface> decide(CheckersBoard board){
        Boolean result = false;
        try {
            if (board != null) {
                result = validatorRequestHandler.validate(board);
                if (result == true) {
                    return moveGeneratorRequestHandler.getMove(board);
                }
                else {
                    ArrayList<MoveInterface> invalidResult = new ArrayList<MoveInterface>();
                    invalidResult.add(new InvalidMove());
                    return invalidResult;
                }
            }
            else {
                throw new RuntimeException("Error getting board from Vision subsystem.");
            }
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
