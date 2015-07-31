package com.endovectors.uta.centralcontroller;

import com.endovectors.uta.hardware.HardwareRequestHandler;
import com.endovectors.uta.hardware.HardwareRequestHandlerInterface;
import com.endovectors.uta.presentation.button.ButtonStatesEnum;
import com.endovectors.uta.presentation.controller.PresentationRequestHandler;
import com.endovectors.uta.processing.CheckersBoard;
import com.endovectors.uta.processing.List;
import com.endovectors.uta.processing.controller.ProcessingRequestHandler;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MasterController implements Observer {
	
	Thread[] threads = new Thread[4];

	private State state;
	private ProcessingRequestHandler processingRequestHandler;
	private PresentationRequestHandler presentationRequestHandler;
	private HardwareRequestHandler hardwareRequestHandler;

	public MasterController(){
		state = new State();
		processingRequestHandler = new ProcessingRequestHandler();
		presentationRequestHandler = new PresentationRequestHandler();
		hardwareRequestHandler = new HardwareRequestHandler();
		processingRequestHandler.addObserver(this);
		presentationRequestHandler.addObserver(this);
		hardwareRequestHandler.addObserver(this);
	}

	// why are they all threads[0]
	public void startProcessing() {
		threads[0] = new Thread(processingRequestHandler);
		threads[0].start();
	}

	public void startHardware(){
		threads[1] = new Thread(hardwareRequestHandler);
		threads[1].start();
	}
	
	public void startPresentation(){
		threads[2] = new Thread(presentationRequestHandler);
		threads[2].start();
	}

	public State getState(){
		return this.state;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o.getClass() == ProcessingRequestHandler.class){
			this.processProcessingResponse(arg);
		}
		if (o.getClass() == PresentationRequestHandler.class){
			this.processPresentationResponse(arg);
		}
		if (o.getClass() == HardwareRequestHandler.class){
			this.processHardwareResponse(arg);
		}
	}

	public void processProcessingResponse(Object arg){
		//TODO: process response
		System.out.println("ProcessingResponse: " + arg);
		if (arg.getClass() == ArrayList.class){
			startHardware();
		}
		else if (arg.getClass() == CheckersBoard.class)
		{
			presentationRequestHandler.setBoard((CheckersBoard)arg);
		}

	}

	public void processPresentationResponse(Object arg){
		//TODO: process response
		System.out.println("PresentationResponse: " + arg);
		startProcessing();
	}

	public void processHardwareResponse(Object arg){
		presentationRequestHandler.setButtonState((ButtonStatesEnum) arg);
	}
}
