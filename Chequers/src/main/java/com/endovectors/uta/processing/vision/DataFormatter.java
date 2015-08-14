package com.endovectors.uta.processing.vision;

import com.endovectors.uta.processing.BoardData;
import com.endovectors.uta.processing.CheckersBoard;
import com.endovectors.uta.processing.vision.DataConverter;

/**
 * Created by asham_000 on 7/5/2015.
 */
public class DataFormatter implements DataFormatterInterface{

    CaptureImage captureImage;

    public DataFormatter(){
        captureImage = new CaptureImage();
    }

    public CheckersBoard getBoard(){
        captureImage.capture();
        byte[] boardData = captureImage.getBoard();
        CheckersBoard board = this.convertBoard(boardData);
        return board;
    }

    private CheckersBoard convertBoard(byte[] boardData){
        CheckersBoard board = new CheckersBoard();
        for (byte board : boardData){
            System.out.println("In convertBoard: " + board);
        }
        board.setPieces(boardData);
        return board;
    }
}
