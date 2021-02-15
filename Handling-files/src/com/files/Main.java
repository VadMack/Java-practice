package com.files;

import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {
        FileHandler fh = new FileHandler();
        fh.choosePath();
        System.out.println(fh.getPath());
        fh.readFile();
        System.out.println(fh.getBuf());
        fh.calculateLetters();
        fh.printLetters();
        fh.writeToFile();
    }
}
