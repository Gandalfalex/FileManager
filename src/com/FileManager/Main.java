package com.FileManager;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Main {

    static String path = "C:\\Users\\Ferag\\Downloads";
    private static ArrayList<String> myPaths = new ArrayList<String>();


    public static void main(String[] args) {
        myPaths.add(path);

        for (String s : myPaths){
            FileHandler f = new FileHandler(s);
            f.pringOut(6);
        }

    }






}
