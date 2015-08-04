package com.endovectors.uta.presentation.voice;


import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


/**
 * Created by asham_000 on 7/5/2015.
 */
public class VoiceGenerator {

    VoiceManager vm = VoiceManager.getInstance();
    Voice voice = vm.getVoice("kevin16");

    public void speak(String input){
        try {
            voice.allocate();
            voice.speak(input);
        }
        catch(Exception e){
            System.out.println("In speak exception: " + e);
        }
    }
}
