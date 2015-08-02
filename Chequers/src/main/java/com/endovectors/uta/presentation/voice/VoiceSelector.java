package com.endovectors.uta.presentation.voice;

import com.endovectors.uta.presentation.controller.PresentationRequestHandler;
import com.endovectors.uta.presentation.voice.speech_patterns.SpeechEnum;
import com.endovectors.uta.presentation.voice.speech_patterns.SpeechFlyweightFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asham_000 on 8/1/2015.
 */
public class VoiceSelector implements Runnable {

    private VoiceGenerator voiceGenerator;
    private boolean speaking = false;
    private ArrayList<SpeechEnum> nextPhrase;
    private PresentationRequestHandler presentationRequestHandler;

    public VoiceSelector(){ //used by testcase
        nextPhrase = new ArrayList<SpeechEnum>();
        voiceGenerator = new VoiceGenerator();
    }

    public VoiceSelector(PresentationRequestHandler presentationRequestHandler) {
        this.presentationRequestHandler = presentationRequestHandler;
        nextPhrase = new ArrayList<SpeechEnum>();
        voiceGenerator = new VoiceGenerator();
    }

    public void setSpeech(SpeechEnum speech){ //used by testcase
        nextPhrase.add(speech);
        speak();
    }

    public void run() {
        while (true) {
            if (presentationRequestHandler.getNextPhrasesLength() > 0){
                nextPhrase.add(presentationRequestHandler.getNextPhrase());
            }
            if (!nextPhrase.isEmpty()) {
                speak();
            }
        }
    }


    private void speak() {
        switch (nextPhrase.get(0)) {
            case goodMove:
                voiceGenerator.speak(SpeechFlyweightFactory.getInstance().getGoodMove());
                break;
            case reallyGoodMove:
                voiceGenerator.speak(SpeechFlyweightFactory.getInstance().getReallyGoodMove());
                break;
            case badMove:
                voiceGenerator.speak(SpeechFlyweightFactory.getInstance().getBadMove());
                break;
            case reallyBadMove:
                voiceGenerator.speak(SpeechFlyweightFactory.getInstance().getReallyBadMove());
                break;
            case waitingOnPlayer:
                voiceGenerator.speak(SpeechFlyweightFactory.getInstance().getWaitingOnPlayer());
                break;
            case waitingOnArmMovement:
                voiceGenerator.speak(SpeechFlyweightFactory.getInstance().getWaitingOnArmMovement());
                break;
            case waitingOnProcessing:
                voiceGenerator.speak(SpeechFlyweightFactory.getInstance().getWaitingOnProcessing());
                break;
            case invalidMove:
                voiceGenerator.speak(SpeechFlyweightFactory.getInstance().getInvalidMove());
                break;
            default:
                voiceGenerator.speak("I can not think of anything to say.");
        }
        nextPhrase.remove(0);
    }
}
