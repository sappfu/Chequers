package com.endovectors.uta.presentation.controller;

import presentationController.MessageFormatter;

/**
 * Created by asham_000 on 7/5/2015.
 */
public class Selection
{
private int type = 0;
	
	public Selection(MessageFormatter m)
	{
		type = Integer.parseInt(m.getResult().getMove());
	}
	
	public void send(MessageFormatter m)
	{
		// call on method from destination(s)
		// presentation request handler in master controller
		String to[] = m.getInstruction().getDestinations();
		for (int i = 0; i < to.length; i++)
		{
			if (to[i].equals("Master Controller"))
			{
				// determine what to send to master controller
			}
			
			else if (to[i].equals("Voice"))
			{
				//VoiceSelector select = new VoiceSelector(type);
			}
			else if (to[i].equals("LED"))
			{
				
			}
			else if (to[i].equals("Display"))
			{
				
			}
		}
	}
}
