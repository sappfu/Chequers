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
	//private int type = 0;
	private GUI gui;
	
	public Selection()
	{
		/*if (m.getResult() == null)
			type = NO_MOVE;
		else
			type = m.getResult().getMove();
		*/
		gui = PresentationRequestHandler.getGui();
	}
	
	public void send(CheckersBoard b)
	{

		//VoiceSelector select = new VoiceSelector(type);
		if (b == null)
		{
			//this.gui.changeToFace();
			//try
	    	//{
	    	    //Thread.sleep(3000); // let face "talk" for 3 seconds
	    	//}
	    	//catch(InterruptedException ex)
	    	//{
	    	  //  Thread.currentThread().interrupt();
	    	//}
			//this.gui.changeToBoth();
		}
		else
		{
			//this.gui.changeToFace();
			//select.play();
			//try
	    	//{
	    	  //  Thread.sleep(3000); // let face "talk" for 3 seconds
	    	//}
	    	//catch(InterruptedException ex)
	    	//{
	    	  //  Thread.currentThread().interrupt();
	    	//}
			this.gui.setBoard(b); // changed to this
			//this.gui.changeToBoth();
		}
	}
}
