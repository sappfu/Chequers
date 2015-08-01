package com.endovectors.uta.persistence;

import junit.framework.TestCase;

/**
 * Created by asham_000 on 8/1/2015.
 */
public class FileStorageTest extends TestCase {

    public void testFileWriter(){
        FileStorage fileStorage = new FileStorage();
        fileStorage.writeToFile("This is a test.");
        fileStorage.closeFile();
    }
}
