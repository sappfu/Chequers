package com.endovectors.uta.processing.controller;

import com.endovectors.uta.processing.CheckersBoard;

import java.util.Observable;

/**
 * Created by asham_000 on 7/5/2015.
 */
public class ProcessingRequestHandler extends Observable implements Runnable {

    private ProcessingDecision processingDecision;
    private boolean request;

    public ProcessingRequestHandler() {
        processingDecision = new ProcessingDecision();
        request = false;
    }

    public void getNextMove() {
        System.out.println("getNextMove() " + this);
        request = true;
    }

    @Override
    public void run() {
        CheckersBoard board = processingDecision.decide();
        setChanged();
        notifyObservers(board);
        setChanged();
        notifyObservers(processingDecision.decide(board));
    }
}
