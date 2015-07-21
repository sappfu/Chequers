package com.endovectors.uta.hardware;

import com.endovectors.uta.presentation.button.ButtonStatesEnum;

import java.util.Observable;

/**
 * Created by asham_000 on 7/20/2015.
 */
public class HardwareRequestHandler extends Observable implements Runnable, HardwareRequestHandlerInterface {



    public void run(){
        try {
            Thread.sleep(500);
            setChanged();
            notifyObservers(ButtonStatesEnum.PLAY_STATE);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }


    public void makeMove(){
        HardwareDummy hardwareDummy = new HardwareDummy();

    }
}
