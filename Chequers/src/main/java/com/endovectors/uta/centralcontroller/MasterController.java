package com.endovectors.uta.centralcontroller;

import com.endovectors.uta.hardware.HardwareRequestHandler;
import com.endovectors.uta.hardware.HardwareRequestHandlerInterface;
import com.endovectors.uta.presentation.button.ButtonStatesEnum;
import com.endovectors.uta.presentation.controller.PresentationRequestHandler;
import com.endovectors.uta.presentation.voice.speech_patterns.SpeechEnum;
import com.endovectors.uta.processing.InvalidMove;
import com.endovectors.uta.processing.List;
import com.endovectors.uta.processing.Move;
import com.endovectors.uta.processing.MoveInterface;
import com.endovectors.uta.processing.controller.ProcessingRequestHandler;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MasterController implements Observer {
	
	Thread[] threads = new Thread[4];

	private State state = null;
	private ProcessingRequestHandler processingRequestHandler;
	private PresentationRequestHandler presentationRequestHandler;
	private HardwareRequestHandler hardwareRequestHandler;

	public MasterController(){
		processingRequestHandler = new ProcessingRequestHandler();
		presentationRequestHandler = new PresentationRequestHandler();
		hardwareRequestHandler = new HardwareRequestHandler();
		processingRequestHandler.addObserver(this);
		presentationRequestHandler.addObserver(this);
		hardwareRequestHandler.addObserver(this);
	}

	
	public void startProcessing() {
		threads[0] = new Thread(processingRequestHandler);
		threads[0].start();
	}

	public void startHardware(){
		threads[0] = new Thread(hardwareRequestHandler);
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
		if (o.getClass() == HardwareRequestHandler.class){
			this.processHardwareResponse(arg);
		}
	}

	public void processProcessingResponse(Object arg){
		//TODO: process response
		System.out.println("ProcessingResponse: " + arg);
		if (arg.getClass() == ArrayList.class){
			ArrayList<MoveInterface> result = (ArrayList<MoveInterface>)arg;
			state.setNextMoves(result);
			if(result.get(0).getClass() == Move.class){
				startHardware();
			}
			if(result.get(0).getClass() == InvalidMove.class){
				presentationRequestHandler.speak(SpeechEnum.invalidMove);
				presentationRequestHandler.notifyInvalidMove();
			}

		}

	}

	public void processPresentationResponse(Object arg){
		ButtonStatesEnum enu = (ButtonStatesEnum)arg;
		switch(enu){
			case WAIT_STATE:
				presentationRequestHandler.speak(SpeechEnum.waitingOnProcessing);
				startProcessing();
				break;
			case PLAY_STATE:
				if (state == null){
					state = new State();
				}
				presentationRequestHandler.speak(SpeechEnum.waitingOnProcessing);
				startProcessing();
				break;
			case MENU_STATE:
				state.stopWriter();
				state = null;
				break;
		}
	}

	public void processHardwareResponse(Object arg){
		presentationRequestHandler.setButtonState((ButtonStatesEnum) arg);
	}
}
