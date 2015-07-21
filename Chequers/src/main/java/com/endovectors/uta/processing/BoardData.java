package com.endovectors.uta.processing;

/**
 * Created by asham_000 on 7/5/2015.
 */
public class BoardData {

    byte[] pieces;

    public BoardData(){
        pieces = new byte[32];
    }
    
    public byte[] getPieces(){
        return pieces;
    }

    public void setPieces(byte[] pieces){
        this.pieces = pieces;
    }
}
