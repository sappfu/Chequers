package com.endovectors.uta.presentation.voice;

import com.endovectors.uta.presentation.controller.MoveType;
import java.io.*;
import sun.audio.*;

public class VoiceSelector implements MoveType
{
	private String input; // sound bite
	
	public VoiceSelector(int type)
	{
		// TODO: Random selection of inputs; multiple inputs for each type
		
		if (type == GOOD_MOVE) // good move by system
		{
			// choose a good sound bite
			// assumption that sound bite will be generated
			input = "Beat that.";
		}
		else if (type == PLAYER_GOOD_MOVE)
		{
			// choose a bad sound bite
			input = "Hmm. Not bad.";
		}
		else if (type == PLAYER_BAD_MOVE)
		{
			input = "Haha. I've got this in the bag.";
		}
		else if (type == NO_MOVE)// speak when no move is made
		{
			input = "Let's hurry up. I haven't got all day.";
		}
		// Is this covered?
		else // Invalid move made
		{
			input = "Try something else.";
		}
	}
	
	public void play()
	{
		// play input
		
		
		// this is an example of playing audio from a file
		/*
		 * String gongFile = "/Users/al/DevDaily/Projects/MeditationApp/resources/gong.au";
    InputStream in = new FileInputStream(gongFile);
 
    // create an audiostream from the inputstream
    AudioStream audioStream = new AudioStream(in);
 
    // play the audio clip with the audioplayer class
    AudioPlayer.player.start(audioStream);
		 */
	}
}
