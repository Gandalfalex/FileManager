package com.FileManager;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        HashMap<String, ArrayList<CustomFile>> files = new HashMap<>(findFilesInDirectory("C:\\Users\\Ferag\\Downloads"));
	    for (String key : files.keySet()){
	        Collections.sort(files.get(key));
	        if (key.equals("pdf")) {
	           getStuff(files.get(key));
            }
        }
    }

    /**
     * this functions takes a filepath and returns all files in that directory as Hashmap
     * where the key represents the file extension and the list all files belonging to that file extension
     **/
    public static HashMap<String, ArrayList<CustomFile>> findFilesInDirectory(String path){
        File[] folder = new File(path).listFiles();
        HashMap<String, ArrayList<CustomFile>>  fileNames= new HashMap<>();

        for (File file : folder){
            if (file.isFile()) {
                String extension = getExtension(file.getName());
                CustomFile customFile = new CustomFile(file.getName(), file.getAbsolutePath());
                if (fileNames.containsKey(extension)){
                    fileNames.get(extension).add(customFile);
                }
                else {
                    ArrayList<CustomFile> temp = new ArrayList<>();
                    temp.add(customFile);
                    fileNames.put(extension, temp);
                }
            }
        }
        return fileNames;
    }

    /**
     *
     * @param fileName
     * @return file extension
     */
    public static String getExtension(String fileName){
        String[] elements = fileName.split("\\.");
        return elements[elements.length-1];
    }

    public static HashMap<String, ArrayList<CustomFile>> getStuff(ArrayList<CustomFile> customFiles){
        HashMap<String, ArrayList<CustomFile>> sortedFiles = new HashMap<>();
        for (CustomFile s : customFiles) {
            if (sortedFiles.containsKey(s.getRelevantPart())){
                sortedFiles.get(s.getRelevantPart()).add(s);
            }
            else{
                ArrayList<CustomFile> temp = new ArrayList<>();
                temp.add(s);
                sortedFiles.put(s.getRelevantPart(), temp);
            }
        }
        for(String temp : sortedFiles.keySet()){
            if (sortedFiles.get(temp).size() > 1){
                System.out.println(sortedFiles.get(temp));
            }
        }
        return sortedFiles;
    }

}
