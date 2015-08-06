package com.endovectors.uta.persistence;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by asham_000 on 8/1/2015.
 */
public class FileStorage implements Observer {

    PrintWriter writer;

    public FileStorage() {
        try {
            Date date = new Date();
            writer = new PrintWriter("-logfile.txt", "UTF-8");
        } catch (FileNotFoundException fnfe) {
            throw new RuntimeException(fnfe);
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException(uee);
        }
    }

    public void update(Observable observed, Object arg) {
        writeToFile((String) arg);
    }

    public void writeToFile(String arg) {
        System.out.println(arg);
        writer.println(arg + "\n");
    }

    public void readFromFile() {

    }

    public void closeFile(){
        writer.close();
    }
}