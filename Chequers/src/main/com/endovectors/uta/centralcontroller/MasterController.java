package com.endovectors.uta.centralcontroller;

import com.endovectors.uta.presentation.controller.PresentationRequestHandler;
import com.endovectors.uta.processing.controller.ProcessingRequestHandler;

import java.util.Observable;
import java.util.Observer;

public class MasterController implements Observer {
	
	Thread[] threads = new Thread[4];

	private State state;
	private ProcessingRequestHandler processingRequestHandler;
	private PresentationRequestHandler presentationRequestHandler;

	public MasterController(){
		state = new State();
		processingRequestHandler = new ProcessingRequestHandler();
		presentationRequestHandler = new PresentationRequestHandler();
		processingRequestHandler.addObserver(this);
		presentationRequestHandler.addObserver(this);
	}

	
	public void start(){
		threads[0] = new Thread(processingRequestHandler);
		threads[0].start();
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
	}

	public void processProcessingResponse(Object arg){
		//TODO: process response
		System.out.println("ProcessingResponse: " + arg);
	}

	public void processPresentationResponse(Object arg){
		//TODO: process response
		System.out.println("PresentationResponse: " + arg);
		start();
	}
}
