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
    }

    public void run(){
        try {
            usbComm.connect();
            moves = masterController.getMoves();
            String string = ""; 
            //do hardwaresy stuff
            for (int i = 0; i < moves.size(); i++)
            {
            	String s[] = moves.get(i).toString().split("(,)");
            	string = string + s[0] + "/" + 0 + "/" + s[1] +  "/" + 1 + "/";
            }
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
