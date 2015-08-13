package com.endovectors.uta;

import com.endovectors.uta.centralcontroller.MasterController;
import com.endovectors.uta.processing.vision.CaptureImage;

/**
 * Created by asham_000 on 7/5/2015.
 */
public class Main {

    private static MasterController masterController;

    public static void main(String args[]) {
        setup();
    }

    private static void setup(){
        System.out.println(System.getProperty("java.library.path"));
        System.setProperty("java.library.path", System.getProperty("user.dir"));
        masterController = new MasterController();
        //CaptureImage image = new CaptureImage();
        //image.capture();
    }
}
