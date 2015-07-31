package com.endovectors.uta;

import com.endovectors.uta.centralcontroller.MasterController;
import com.endovectors.uta.presentation.voice.VoiceTest;

/**
 * Created by asham_000 on 7/5/2015.
 */
public class Main {

    private static MasterController masterController;

    public static void main(String args[]) {
        setup();
    }

    private static void setup(){
        System.setProperty("java.library.path", System.getProperty("user.dir"));
        VoiceTest voiceTest = new VoiceTest();
        voiceTest.speak("I wonder if this will work.");
        masterController = new MasterController();
    }
}
