package com.files;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Vadim Makarov
 */
public class FileHandler {
    private String path;
    private StringBuilder buf;
    private int[] letters;

    /**
     * Empty signature constructor
     */
    public FileHandler() {
        this.buf = new StringBuilder();
        this.letters = new int[26];
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public StringBuilder getBuf() {
        return buf;
    }

    public void setBuf(StringBuilder buf) {
        this.buf = buf;
    }

    public int[] getLetters() {
        return letters;
    }

    public void setLetters(int[] letters) {
        this.letters = letters;
    }

    /**
     *  Allows to enter a path from the keypad
     */
    public void choosePath() {
        System.out.println("Type the path to the input file: ");
        Scanner in = new Scanner(System.in);
        path = in.next();
    }

    /**
     * Reads the file in the specified path
     * @throws IOException if file is not found
     */
    public void readFile() throws IOException {
        try (FileInputStream fin = new FileInputStream(path)) {
            int i;
            while ((i = fin.read()) != -1) {
                buf.append((char) i);
            }
        } catch (IOException exception) {
            System.err.println("FileNotFoundException: " + exception.getMessage());
        }
    }

    /**
     * Counts the letters of the English alphabet
     */
    public void calculateLetters() {
        for (int i = 0; i < buf.length(); i++) {
            if (buf.charAt(i) >= 'A' && buf.charAt(i) <= 'Z') {
                letters[buf.charAt(i) - 'A']++;
            } else if (buf.charAt(i) >= 'a' && buf.charAt(i) <= 'z') {
                letters[buf.charAt(i) - 'a']++;
            }
        }
    }

    /**
     * Writes the calculated values to the file
     * @throws IOException if file is not found
     */
    public void writeToFile() throws IOException {
        try (FileWriter fout = new FileWriter("output.txt")) {
            for (int i = 0; i < letters.length; i++) {
                String strOut = (char)(i + 65) + " - " + letters[i] + "\n";
                fout.write(strOut);
            }
        } catch (IOException exception) {
            System.err.println("FileNotFoundException: " + exception.getMessage());
        }
    }

    /**
     * Prints the calculated values
     */
    public void printLetters() {
        for (int i = 0; i < letters.length; i++) {
            System.out.printf("%c - %d\n", i + 65, letters[i]);
        }
    }
}
