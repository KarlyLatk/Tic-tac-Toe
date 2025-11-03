package org.example;

import java.io.FileOutputStream;

public class GameWriter {
    private String fileName;
    public GameWriter(String fileName) {
        this.fileName = fileName;
    }

    // Modeled off of cheeseWriter
    public void write_data(String string){
        try {
            FileOutputStream fw = new FileOutputStream(fileName);
            byte[] stringBytes = string.getBytes();
            fw.write(stringBytes);
            fw.close();
        }catch (Exception e){
            System.out.println("Error while writing to "+fileName +": "+e.getMessage());
        }
    }
}
