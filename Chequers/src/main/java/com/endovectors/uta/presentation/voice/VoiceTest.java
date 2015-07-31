package com.endovectors.uta.presentation.voice;

import javax.sound.sampled.AudioInputStream;
import java.util.Locale;
import java.util.Set;


import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.util.data.audio.AudioPlayer;

/**
 * Created by asham_000 on 7/5/2015.
 */
public class VoiceTest{

    public void speak(String input){
        try {
            MaryInterface marytts = new LocalMaryInterface();
            System.out.println("I currently have " + marytts.getAvailableVoices() + " voices in "
                    + marytts.getAvailableLocales() + " languages available.");
            System.out.println("Out of these, " + marytts.getAvailableVoices(Locale.US) + " are for US English.");
            Set<String> voices = marytts.getAvailableVoices();
            marytts.setVoice(voices.iterator().next());
            AudioInputStream audio = marytts.generateAudio(input);
            AudioPlayer player = new AudioPlayer(audio);
            player.start();
            player.join();
        }
        catch(Exception e){
            System.out.println("In speak exception: " + e);
        }
    }
}
