package com.FileManager;

public class CustomFile implements Comparable<CustomFile> {

    private String fileName;
    private String filePath;

    public CustomFile(String name, String path){
        this.fileName = name;
        this.filePath = path;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getRelevantPart(){
        String relevantPart = fileName.split("\\.")[0];
        if (fileName.contains(" ")){
            relevantPart = fileName.split(" ")[0];
        }
        if (fileName.contains("_")){
            relevantPart = fileName.split("_")[0];
           // System.out.println(fileName);
        }
        if (fileName.contains("-")) {
            relevantPart = fileName.split("-")[0];
        }
        if (fileName.contains("+")) {
            relevantPart = fileName.split("\\+")[0];
        }
        return relevantPart != " " ? relevantPart : "SomeWeirdStuff" ;
    }

    @Override
    public int compareTo(CustomFile customFile) {
        return getRelevantPart().toLowerCase().compareTo(customFile.getRelevantPart().toLowerCase());
    }


    @Override
    public String toString(){
        return fileName;
    }
}

