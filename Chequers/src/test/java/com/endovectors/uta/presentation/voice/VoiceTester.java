package com.endovectors.uta.presentation.voice;

import junit.framework.TestCase;

/**
 * Created by asham_000 on 7/20/2015.
 */
public class VoiceTester extends TestCase {

    VoiceTest voiceTest;
    public void testSpeak(){
        voiceTest = new VoiceTest();
        voiceTest.speak("Hello world");
    }
}
