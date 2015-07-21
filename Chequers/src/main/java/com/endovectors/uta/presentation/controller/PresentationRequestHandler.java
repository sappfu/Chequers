package com.endovectors.uta.presentation.controller;

import com.endovectors.uta.presentation.button.ButtonStatesEnum;
import com.endovectors.uta.presentation.button.button_one.ButtonOneMenuStateActionListener;
import com.endovectors.uta.presentation.button.button_three.ButtonThreeMenuStateActionListener;
import com.endovectors.uta.presentation.button.button_two.ButtonTwoMenuStateActionListener;
import com.endovectors.uta.presentation.display.GUI;

import java.awt.event.ActionEvent;
import java.util.Observable;

/**
 * Created by asham_000 on 7/5/2015.
 */
public class PresentationRequestHandler extends Observable implements Runnable{

    GUI gui;

    public PresentationRequestHandler(){

        gui = new GUI(this);
    }

    @Override
    public void run() {

    }

    public void startGame(){
        gui.setButtonState(ButtonStatesEnum.PLAY_STATE);
    }

    public void continueGame(){

    }

    public void endGame(){
        gui.setButtonState(ButtonStatesEnum.MENU_STATE);
    }

    public void pauseGame(){

    }

    public void endTurn() {
        gui.setButtonState(ButtonStatesEnum.WAIT_STATE);
        setChanged();
        notifyObservers(ButtonStatesEnum.WAIT_STATE);
    }

    public void setButtonState(ButtonStatesEnum state){
        gui.setButtonState(state);
    }
}
