package com.endovectors.uta.presentation.controller;

import com.endovectors.uta.presentation.display.DisplayActionListener;
import com.endovectors.uta.presentation.display.GUI;

import java.awt.event.ActionEvent;
import java.util.Observable;

/**
 * Created by asham_000 on 7/5/2015.
 */
public class PresentationRequestHandler extends Observable implements Runnable{

    GUI gui;
    DisplayActionListener displayActionListener;

    public PresentationRequestHandler(){
        displayActionListener = new DisplayActionListener(this);
        gui = new GUI(displayActionListener);
    }

    public void buttonClicked(ActionEvent e){
        setChanged();
        notifyObservers(e);
    }

    @Override
    public void run() {

    }
}
