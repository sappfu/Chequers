package com.endovectors.uta.processing;

/**
 * Created by asham_000 on 8/6/2015.
 */
public class GameOver implements MoveInterface{

    private int winner;

    public GameOver(int winner){
        this.winner = winner;
    }

    public String toString(){
        return "The game is over. Winner is : " + this.winner;
    }

    public int getWinner(){
        return this.winner;
    }
}
