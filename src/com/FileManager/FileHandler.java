package com.FileManager;

import java.io.File;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.HashMap;

public class FileHandler {

    private String pathToDirectory;

    /**
    * every Element represents a file format, each of these is sorted by their prefix and contains all files belonging to that
    * prefix
    * */
    private ArrayList<HashMap<String, ArrayList<CustomFile>>> sortedFiles = new ArrayList<>();

    public FileHandler(String path){
        try{
            checkForDirectory(path);
            this.pathToDirectory = path;
            HashMap<String, ArrayList<CustomFile>> filesSorted = getFileTypesOfDirectory();
            for (String s : filesSorted.keySet()){
                sortedFiles.add(getSortedFilesByPrefix(filesSorted.get(s)));
            }
        }
        catch (NotDirectoryException e){
            e.printStackTrace();
        }
    }




    private void checkForDirectory(String path) throws NotDirectoryException{
        File f = new File(path);
        if (f.isDirectory()){
            this.pathToDirectory = path;
        }
        else {
            throw new NotDirectoryException(path);
        }
    }


    public HashMap<String, ArrayList<CustomFile>> getFileTypesOfDirectory(){
        File[] folder = new File(this.pathToDirectory).listFiles();
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
     * this functions takes a filepath and returns all files in that directory as Hashmap
     * where the key represents the file extension and the list all files belonging to that file extension
     **/
    public HashMap<String, ArrayList<CustomFile>> getSortedFilesByPrefix (ArrayList<CustomFile> customFiles){
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
        return sortedFiles;
    }


    /**
     * @param fileName
     * @return file extension
     */
    public String getExtension(String fileName){
        String[] elements = fileName.split("\\.");
        return elements[elements.length-1];
    }


    public void pringOut(int index){
        for (HashMap<String, ArrayList<CustomFile>> temp : sortedFiles){
            System.out.println(temp.keySet());
        }
    }
}
