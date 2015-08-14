package com.endovectors.uta.processing.vision;

import com.endovectors.uta.processing.CheckersBoard;

/**
 * Created by asham_000 on 7/5/2015.
 */
public class VisionRequestHandler implements VisionRequestHandlerInterface {

    DataFormatterInterface dataFormatter;

    public VisionRequestHandler(){
        dataFormatter = new DataFormatter();
    }

    public CheckersBoard getBoard(){
        return dataFormatter.getBoard();
    }
}
