package com.endovectors.uta.presentation.controller;

import com.endovectors.uta.presentation.button.ButtonStatesEnum;
import com.endovectors.uta.presentation.display.GUI;
import com.endovectors.uta.presentation.voice.VoiceSelector;
import com.endovectors.uta.presentation.voice.speech_patterns.SpeechEnum;
import com.endovectors.uta.processing.CheckersBoard;
import com.endovectors.uta.centralcontroller.MasterController;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by asham_000 on 7/5/2015.
 */
public class PresentationRequestHandler extends Observable implements Runnable, MoveType{

    GUI gui; // I want to change this to static
    private static GUI g; // added
    CheckersBoard board;
    
    Thread thread = null;
    VoiceSelector voiceSelector;
    ArrayList<SpeechEnum> nextPhrases;

    public PresentationRequestHandler(){
        voiceSelector = new VoiceSelector(this);
        gui = new GUI(this);
        setGui(gui); // added
        
        voiceSelector = new VoiceSelector(this);
        nextPhrases = new ArrayList<SpeechEnum>();
        thread = new Thread(voiceSelector);
        thread.start();
        nextPhrases = new ArrayList<SpeechEnum>();
        thread = new Thread(voiceSelector);
        thread.start();
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
    	//MessageResult r = new MessageResult(NO_MOVE); // needs to be based on result from processing
    	//MessageFormatter m = new MessageFormatter(r);
    	Selection s = new Selection();
    	s.send(board);
    }

    public void startGame(){
        setChanged();
        notifyObservers(ButtonStatesEnum.PLAY_STATE);
        setButtonState(ButtonStatesEnum.PLAY_STATE);
    }

    public void continueGame()
    {
    	setButtonState(ButtonStatesEnum.PLAY_STATE);
    	MasterController.getTimer().schedule(new Alarm(this), 30000, 30000);
    }

    public void endGame(){
        setChanged();
        notifyObservers(ButtonStatesEnum.MENU_STATE);
        setButtonState(ButtonStatesEnum.MENU_STATE);
    }

    public void pauseGame() // can the game be paused during the system's turn?
    {
    	setButtonState(ButtonStatesEnum.MENU_STATE);
    	MasterController.getTimer().cancel();
    }

    public void endTurn() {
        gui.setButtonState(ButtonStatesEnum.WAIT_STATE);
        setChanged();
        notifyObservers(ButtonStatesEnum.WAIT_STATE);
        setButtonState(ButtonStatesEnum.WAIT_STATE);
    }

    public void setButtonState(ButtonStatesEnum state){
        gui.setButtonState(state);
    }

    public void speak(SpeechEnum speech){
        nextPhrases.add(speech);
    }

    public SpeechEnum getNextPhrase(){
        SpeechEnum enu = nextPhrases.get(0);
        nextPhrases.remove(0);
        return enu;
    }

    public int getNextPhrasesLength(){
        return nextPhrases.size();
    }

    public void notifyInvalidMove(){
        setButtonState(ButtonStatesEnum.PLAY_STATE);
    }

    public void handleGameOver(int winner){
        SpeechEnum enu;
        if (winner == 2){
            enu = SpeechEnum.gameOverLose;
        }
        else {
            enu = SpeechEnum.gameOverWin;
        }
        speak(enu);
        setButtonState(ButtonStatesEnum.MENU_STATE);
    }
}
