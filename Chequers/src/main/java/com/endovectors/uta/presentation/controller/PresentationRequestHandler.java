package com.endovectors.uta.presentation.controller;

import com.endovectors.uta.presentation.button.ButtonStatesEnum;
import com.endovectors.uta.presentation.display.GUI;
import com.endovectors.uta.presentation.voice.VoiceGenerator;
import com.endovectors.uta.presentation.voice.VoiceSelector;
import com.endovectors.uta.presentation.voice.speech_patterns.SpeechEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class PresentationRequestHandler extends Observable implements Runnable{

    GUI gui;
    Thread thread = null;
    VoiceSelector voiceSelector;
    ArrayList<SpeechEnum> nextPhrases;

    public PresentationRequestHandler(){
        voiceSelector = new VoiceSelector(this);
        gui = new GUI(this);
        nextPhrases = new ArrayList<SpeechEnum>();
        thread = new Thread(voiceSelector);
        thread.start();
    }

    @Override
    public void run() {

    }

    public void startGame(){
        setChanged();
        notifyObservers(ButtonStatesEnum.PLAY_STATE);
        setButtonState(ButtonStatesEnum.PLAY_STATE);
    }

    public void continueGame(){

    }

    public void endGame(){
        setChanged();
        notifyObservers(ButtonStatesEnum.MENU_STATE);
        setButtonState(ButtonStatesEnum.MENU_STATE);
    }

    public void pauseGame(){

    }

    public void endTurn() {
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
        //TODO process invalid move
    }
}
