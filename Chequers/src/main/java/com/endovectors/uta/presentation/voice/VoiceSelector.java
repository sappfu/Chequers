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


    public VoiceSelector(PresentationRequestHandler presentationRequestHandler) {
        this.presentationRequestHandler = presentationRequestHandler;
        nextPhrase = new ArrayList<SpeechEnum>();
        voiceGenerator = new VoiceGenerator();
    }

    public void run() {
        while (true) {
            if (presentationRequestHandler.getNextPhrasesLength() > 0){
                nextPhrase.add(presentationRequestHandler.getNextPhrase());
            }
            if (!nextPhrase.isEmpty()) {
                System.out.println("in while" + nextPhrase.get(0));
                switch (nextPhrase.get(0)) {
                    case goodMove:
                        speak(SpeechFlyweightFactory.getInstance().getGoodMove());
                        break;
                    case reallyGoodMove:
                        speak(SpeechFlyweightFactory.getInstance().getReallyGoodMove());
                        break;
                    case badMove:
                        speak(SpeechFlyweightFactory.getInstance().getBadMove());
                        break;
                    case reallyBadMove:
                        speak(SpeechFlyweightFactory.getInstance().getReallyBadMove());
                        break;
                    case waitingOnPlayer:
                        speak(SpeechFlyweightFactory.getInstance().getWaitingOnPlayer());
                        break;
                    case waitingOnArmMovement:
                        speak(SpeechFlyweightFactory.getInstance().getWaitingOnArmMovement());
                        break;
                    case waitingOnProcessing:
                        speak(SpeechFlyweightFactory.getInstance().getWaitingOnProcessing());
                        break;
                    case invalidMove:
                        speak(SpeechFlyweightFactory.getInstance().getInvalidMove());
                        break;
                    default:
                        speak("I can not think of anything to say.");
                }
                nextPhrase.remove(0);
            }
        }
    }


    private void speak(String speech) {
        speaking = true;
        voiceGenerator.speak(speech);
        speaking = false;
    }
}
