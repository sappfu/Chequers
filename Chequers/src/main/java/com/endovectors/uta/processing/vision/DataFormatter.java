package com.endovectors.uta.processing.vision;

import com.endovectors.uta.processing.BoardData;
import com.endovectors.uta.processing.CheckersBoard;
import com.endovectors.uta.processing.vision.DataConverter;

/**
 * Created by asham_000 on 7/5/2015.
 */
public class DataFormatter implements DataFormatterInterface{

    DataConverterInterface dataConverter;

    public DataFormatter(){
        dataConverter = new DataConverter();
    }

    public CheckersBoard getBoard(){
        byte[] boardData = dataConverter.getBoard();
        CheckersBoard board = this.convertBoard(boardData);
        return board;
    }

    private CheckersBoard convertBoard(byte[] boardData){
        //TODO: convert BoardData to Board
        CheckersBoard board = new CheckersBoard();
        board.setPieces(boardData);
        return board;
    }
}