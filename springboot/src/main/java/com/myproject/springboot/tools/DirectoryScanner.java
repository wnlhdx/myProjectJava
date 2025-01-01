package com.myproject.springboot.tools;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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


    public Map<String, List<FileInfo>> groupFilesByExtension(List<FileInfo> fileList) {
        // Using Collectors.groupingBy to collect files into a Map grouped by extension
        return fileList.stream()
                .collect(Collectors.groupingBy(FileInfo::getExtension));
    }

    // Function to collect file information into a Map (grouped by file size)
    public Map<Long, List<FileInfo>> groupFilesBySize(List<FileInfo> fileList) {
        // Using Collectors.groupingBy to collect files into a Map grouped by file size
        return fileList.stream()
                .collect(Collectors.groupingBy(FileInfo::getSize));
    }

    // Function to count the number of files by extension
    public Map<String, Long> countFilesByExtension(List<FileInfo> fileList) {
        // Using Collectors.groupingBy and Collectors.counting to count files by extension
        return fileList.stream()
                .collect(Collectors.groupingBy(FileInfo::getExtension, Collectors.counting()));
    }

}