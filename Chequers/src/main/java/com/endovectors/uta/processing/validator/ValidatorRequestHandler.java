package com.endovectors.uta.processing.validator;

import com.endovectors.uta.processing.CheckersBoard;

/**
 * Created by asham_000 on 7/5/2015.
 */
public class ValidatorRequestHandler implements ValidatorRequestHandlerInterface{

    private CompareInterface compare;

    public ValidatorRequestHandler(){
        compare = new Compare();
    }

    public ValidatorRequestHandler(CompareInterface compare){
        this.compare = compare;
    }

    public boolean validate(CheckersBoard board){
        if (compare == null){
            compare = new Compare();
        }
        return compare.compare(board);
    }
    
    public CheckersBoard getBoard(CheckersBoard b)
    {
    	return compare.checkKings(b);
    }
}
