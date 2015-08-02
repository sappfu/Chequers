package com.endovectors.uta.presentation.voice;

import com.endovectors.uta.presentation.voice.speech_patterns.SpeechEnum;
import junit.framework.TestCase;

/**
 * Created by asham_000 on 7/20/2015.
 */
public class VoiceTester extends TestCase {



    public void testSpeak(){
        VoiceSelector voiceSelector = new VoiceSelector();
        voiceSelector.setSpeech(SpeechEnum.waitingOnProcessing);
    }
}
