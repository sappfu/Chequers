package com.endovectors.uta.processing.controller;

import com.endovectors.uta.processing.move_generator.MoveGeneratorRequestHandler;
import com.endovectors.uta.processing.validator.ValidatorRequestHandler;
import com.endovectors.uta.processing.vision.VisionRequestHandler;

/**
 * Created by asham_000 on 7/5/2015.
 */
public class ProcessingDecision {

    private VisionRequestHandler visionRequestHandler;
    private ValidatorRequestHandler validatorRequestHandler;
    private MoveGeneratorRequestHandler moveGeneratorRequestHandler;

    public ProcessingDecision(){
        visionRequestHandler = new VisionRequestHandler();
        validatorRequestHandler = new ValidatorRequestHandler();
        moveGeneratorRequestHandler = new MoveGeneratorRequestHandler();
    }

    public Board decide(){
        return visionRequestHandler.getBoard();
    }
    public Move decide(CheckersBoard board){
        Boolean result = false;
        try {
            if (board != null) {
                result = validatorRequestHandler.validate(board);
                if (result == true) {
                    return moveGeneratorRequestHandler.getMove(board);
                }
                else {
                    return null;
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
