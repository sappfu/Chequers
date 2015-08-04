package com.endovectors.uta;

import com.endovectors.uta.centralcontroller.MasterController;

/**
 * Created by asham_000 on 7/5/2015.
 */
public class Main {

    private static MasterController masterController;

    public static void main(String args[]) {
        setup();
    }

    private static void setup(){
        System.setProperty("java.library.path", System.getProperty("user.dir"));
        masterController = new MasterController();
    }
}
