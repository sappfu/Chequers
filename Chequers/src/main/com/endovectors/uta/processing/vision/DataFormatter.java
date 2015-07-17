package com.endovectors.uta.processing.vision;

import com.endovectors.uta.processing.BoardData;

/**
 * Created by asham_000 on 7/5/2015.
 */
public class DataFormatter {

    public Board getBoard(){
        DataConverter dataConverter = new DataConverter();
        BoardData boardData = dataConverter.getBoard();
        Board board = this.convertBoard(boardData);
        return board;
    }

    public Board convertBoard(BoardData boardData){
        //TODO: convert BoardData to Board
        Board board = new Board();
        return board;
    }
}
