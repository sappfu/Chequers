package com.endovectors.uta.processing.vision;

/**
 * Created by asham_000 on 7/5/2015.
 */
public class VisionRequestHandler {

    public Board getBoard(){
        DataFormatter dataFormatter = new DataFormatter();
        return dataFormatter.getBoard();
    }

}
