package com.endovectors.uta.processing.vision;

import com.endovectors.uta.processing.BoardData;
import com.endovectors.uta.processing.CheckersBoard;
import com.endovectors.uta.processing.vision.DataConverter;

/**
 * Created by asham_000 on 7/5/2015.
 */
public class DataFormatter {

    public CheckersBoard getBoard(){
        DataConverter dataConverter = new DataConverter();
        BoardData boardData = dataConverter.getBoard();
        CheckersBoard board = this.convertBoard(boardData);
        return board;
    }

    public CheckersBoard convertBoard(BoardData boardData){
        //TODO: convert BoardData to Board
        CheckersBoard board = new CheckersBoard();
        return board;
    }
}
