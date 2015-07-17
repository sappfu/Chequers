package com.endovectors.uta.processing.vision;

import com.endovectors.uta.processing.CheckersBoard;

/**
 * Created by asham_000 on 7/5/2015.
 */
public class VisionRequestHandler implements VisionRequestHandlerInterface {

    public CheckersBoard getBoard(){
        DataFormatter dataFormatter = new DataFormatter();
        return dataFormatter.getBoard();
    }

}
