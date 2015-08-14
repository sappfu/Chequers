package com.endovectors.uta.processing.vision;

import com.endovectors.uta.processing.CheckersBoard;

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
        board.setPieces(boardData);
        for (int i=0;i<32;i++){
            System.out.println(board.getPieces()[i]);
        }
        return board;
    }
}
