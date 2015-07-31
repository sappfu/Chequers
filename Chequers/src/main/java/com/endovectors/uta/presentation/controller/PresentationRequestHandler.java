package com.endovectors.uta.presentation.controller;

import com.endovectors.uta.presentation.button.ButtonStatesEnum;
import com.endovectors.uta.presentation.button.button_one.ButtonOneMenuStateActionListener;
import com.endovectors.uta.presentation.button.button_three.ButtonThreeMenuStateActionListener;
import com.endovectors.uta.presentation.button.button_two.ButtonTwoMenuStateActionListener;
import com.endovectors.uta.presentation.display.GUI;
import com.endovectors.uta.processing.CheckersBoard;

import java.awt.event.ActionEvent;
import java.util.Observable;

/**
 * Created by asham_000 on 7/5/2015.
 */
public class PresentationRequestHandler extends Observable implements Runnable, MoveType{

    GUI gui; // I want to change this to static
    private static GUI g; // added
    CheckersBoard board;

    public PresentationRequestHandler(){

        gui = new GUI(this);
        setGui(gui); // added
    }
    
    public void setGui(GUI gu) // added
    {
    	g = gu;
    }
    
    public static GUI getGui() // added
    {
    	return g;
    }
    
    public void setBoard(CheckersBoard b)
    {
    	board = b;
    }

    @Override
    public void run()
    {
    	/*MessageResult r = new MessageResult(NO_MOVE); // needs to be based on result from processing
    	MessageFormatter m = new MessageFormatter(r);
    	Selection s = new Selection(m);
    	s.send(board);*/
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
