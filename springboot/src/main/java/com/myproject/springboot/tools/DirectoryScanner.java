package com.myproject.springboot.tools;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class DirectoryScanner {

    // Main method to demonstrate the functionality
    public List<FileInfo> Scanner(String directoryPath) {
        List<FileInfo> fileList= new ArrayList<>();
        try {
            Files.walkFileTree(Paths.get(directoryPath), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    String fileName = file.getFileName().toString();
                    String extension = "";
                    int i = fileName.lastIndexOf('.');
                    if (i > 0) {
                        extension = fileName.substring(i + 1);
                    }

                    FileInfo fileInfo = new FileInfo(
                            fileName,
                            extension,
                            attrs.creationTime().toString(),
                            attrs.lastModifiedTime().toString(),
                            attrs.size()
                    );

                    fileList.add(fileInfo);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileList;
    }
}