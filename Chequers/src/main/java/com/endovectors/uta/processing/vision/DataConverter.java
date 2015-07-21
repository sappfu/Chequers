package com.endovectors.uta.processing.vision;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.opencv_core.*;
import org.bytedeco.javacpp.helper.*;
import org.bytedeco.javacpp.opencv_highgui.*;
import org.bytedeco.javacpp.presets.*;

import java.awt.image.BufferedImage;
import java.util.*;

/**
 * Created by asham_000 on 7/5/2015.
 */
public class DataConverter implements DataConverterInterface {

    int count =0;

    /*
    [0 1 2 3
     4 5 6 7
     8 9 10 11
     12 13 14 15
     16 17 18 19
     20 21 22 23
     24 25 26 27
     28 29 30 31]
     */
    public byte[] getBoard(){

        byte[] result = new byte[32];
        for (int i=0;i<32;i++){
            if (i < 12)
                result[i] = 4;
            if (i > 19)
                result[i] = 2;
        }
        switch(count++){
            case 0:
                break;
            case 1:
                result[10] = 0;
                result[15] = 4;
                result[20] = 0;
                result[17] = 2;
                break;
            case 2:
                result[15] = 0;
                result[19] = 4;
                result[17] = 0;
                result[12] = 2;
                break;
        }
        return result;
    }
}