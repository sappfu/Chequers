package com.endovectors.uta.hardware;

import com.endovectors.uta.centralcontroller.MasterController;
import com.endovectors.uta.presentation.button.ButtonStatesEnum;
import com.endovectors.uta.processing.MoveInterface;
import com.endovectors.uta.processing.Move;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by asham_000 on 7/20/2015.
 */
public class HardwareRequestHandler extends Observable implements Runnable, HardwareRequestHandlerInterface {

    UsbComm usbComm;
    MasterController masterController;
    ArrayList<MoveInterface> moves;
    ArrayList<moves> Move;

    public HardwareRequestHandler (MasterController masterController){
        usbComm = new UsbComm();
        this.masterController = masterController;
        //Move move = new Move();
    }

    public void run(){
        try {
            usbComm.connect();
            moves = masterController.getMoves();
            
            //do hardwaresy stuff
            //char coor[] = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
            char coor[] = Move.Move();
            StringBuilder position = new StringBuilder();
            position.append(coor[0]);
            position.append(/);
            position.append("1");
            position.append(/);
            position.append(coor[1]);
            position.append(/);
            position.append("0");
            position.append(/);
            String coordinates = position.toString();
            
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
