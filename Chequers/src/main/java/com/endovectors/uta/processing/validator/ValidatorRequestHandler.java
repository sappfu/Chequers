package com.endovectors.uta.processing.validator;

import com.endovectors.uta.processing.CheckersBoard;

/**
 * Created by asham_000 on 7/5/2015.
 */
public class ValidatorRequestHandler implements ValidatorRequestHandlerInterface{

    private CompareInterface compare;

    public ValidatorRequestHandler(){}

    public ValidatorRequestHandler(CompareInterface compare){
        this.compare = compare;
    }

    public boolean validate(CheckersBoard board){
        if (compare == null){
            compare = new Compare();
        }
        return false;//compare.compare(board);
    }
}