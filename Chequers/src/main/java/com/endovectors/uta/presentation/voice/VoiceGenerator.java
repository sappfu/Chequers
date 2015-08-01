package com.endovectors.uta.presentation.voice;


import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;


/**
 * Created by asham_000 on 7/5/2015.
 */
public class VoiceGenerator {

    public void speak(String input){
        try {
            VoiceManager vm = VoiceManager.getInstance();
            Voice voice = vm.getVoice("kevin16");
            voice.allocate();
            voice.speak(input);
        }
        catch(Exception e){
            System.out.println("In speak exception: " + e);
        }
    }
}
