package com.endovectors.uta.processing.validator;

import com.endovectors.uta.processing.CheckersBoard;

/**
 * Created by asham_000 on 7/16/2015.
 */
public interface CompareInterface {

    public boolean compare(CheckersBoard board);

	public CheckersBoard checkKings(CheckersBoard b);

}
