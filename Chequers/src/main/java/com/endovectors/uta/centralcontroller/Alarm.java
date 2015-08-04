package com.endovectors.uta.centralcontroller;

import java.util.Timer;
import java.util.TimerTask;

import com.endovectors.uta.presentation.controller.PresentationRequestHandler;
import com.endovectors.uta.presentation.voice.speech_patterns.SpeechEnum;

public class Alarm extends TimerTask
{	
	private PresentationRequestHandler handler;
	
	public Alarm(PresentationRequestHandler h)
	{
		handler = h;
	}
	
	public void run()
	{
		handler.speak(SpeechEnum.waitingOnPlayer);
		// make handler show text to make turn?
	}
}
