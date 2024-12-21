package com.myproject.springboot.tools;

public class FileInfo {
    private String fileName;
    private String extension;
    private String creationTime;
    private String lastModifiedTime;
    private long size;

    // Constructor
    public FileInfo(String fileName, String extension, String creationTime, String lastModifiedTime,long size) {
        this.fileName = fileName;
        this.extension = extension;
        this.creationTime = creationTime;
        this.lastModifiedTime = lastModifiedTime;
        this.size=size;
    }

    // Getters and Setters
    public String getFileName() {
        return fileName;
    }

    public String getExtension() {
        return extension;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public String getLastModifiedTime() {
        return lastModifiedTime;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}

