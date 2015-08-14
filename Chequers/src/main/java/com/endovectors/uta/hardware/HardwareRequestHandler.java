package com.endovectors.uta.hardware;

import com.endovectors.uta.centralcontroller.MasterController;
import com.endovectors.uta.presentation.button.ButtonStatesEnum;
import com.endovectors.uta.processing.MoveInterface;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by asham_000 on 7/20/2015.
 */
public class HardwareRequestHandler extends Observable implements Runnable, HardwareRequestHandlerInterface {

    UsbComm usbComm;
    MasterController masterController;
    ArrayList<MoveInterface> moves;

    public HardwareRequestHandler (MasterController masterController){
        usbComm = new UsbComm();
        this.masterController = masterController;
        usbComm.connect();
    }

    public void run(){
        try {
            moves = masterController.getMoves();
            //do hardwaresy stuff
            setChanged();
            notifyObservers(ButtonStatesEnum.PLAY_STATE); //this is how you notify central controller when move is complete or other data
        }
        catch(Exception e){
            System.out.println(e);
        }
    }


    public void makeMove(){
    }
}
