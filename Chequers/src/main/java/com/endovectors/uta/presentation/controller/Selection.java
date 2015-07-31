package com.endovectors.uta.presentation.controller;

import com.endovectors.uta.centralcontroller.MasterController;
import com.endovectors.uta.presentation.controller.MessageFormatter;
import com.endovectors.uta.presentation.display.GUI;
import com.endovectors.uta.presentation.voice.VoiceSelector;
import com.endovectors.uta.processing.CheckersBoard;

/**
 * Created by asham_000 on 7/5/2015.
 */
public class Selection implements MoveType
{
	private int type = 0;
	private GUI gui;
	
	public Selection(MessageFormatter m)
	{
		if (m.getResult() == null)
			type = NO_MOVE;
		else
			type = m.getResult().getMove();
		
		gui = PresentationRequestHandler.getGui();
	}
	
	public void send(CheckersBoard b)
	{
		// call on method from destination(s)
		// presentation request handler in master controller
		//String to[] = m.getInstruction().getDestinations();
		//for (int i = 0; i < to.length; i++)
		//{
			/*if (to[i].equals("Master Controller"))
			{
				// determine what to send to master controller
				MasterController controller = new MasterController();
				controller.startProcessing();
			}*/ // this will never be needed
			
			//if (to[i].equals("Voice"))
			//{
				VoiceSelector select = new VoiceSelector(type);
				select.play();
			//}
			//else if (to[i].equals("Display"))
			//{
				// change state of gui
				this.gui.changeDisplay(b);
			//}
		//}
	}
}
