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
        for (int i=0;i<boardData.length;i++){
            System.out.println("In convertBoard: " + board);
        }
        CheckersBoard board = this.convertBoard(boardData);
        return board;
    }

    private CheckersBoard convertBoard(byte[] boardData){
        CheckersBoard board = new CheckersBoard();
        board.setPieces(boardData);
        return board;
    }
}
