package com.myproject.springboot.tools;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.io.RandomAccessReadBufferedFile;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.nio.file.*;

import java.io.IOException;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class pdfscanner {
        // 创建 PDFTextStripper 实例
    public String scan(String filename) throws IOException {
        PDDocument document =  Loader.loadPDF(new RandomAccessReadBufferedFile(filename));
        PDFTextStripper stripper = new PDFTextStripper();
        // 获取 PDF 文本内容
        return stripper.getText(document);
    }

    public List<String> getFullPath(String basicPath,String FIleType) throws IOException {
        Path startPath = Paths.get(basicPath);
        List<String> fullpath= new ArrayList<>();
        if (!Files.exists(startPath)) {
            System.out.println("Directory does not exist: " + basicPath);
            return null;
        }
        EnumSet<FileVisitOption> opts = EnumSet.noneOf(FileVisitOption.class);
        Files.walkFileTree(startPath, opts, Integer.MAX_VALUE, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                if (file.toString().endsWith(FIleType)) {
                    fullpath.add(file.toAbsolutePath().toString());
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException e) {
                return FileVisitResult.SKIP_SUBTREE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                return FileVisitResult.CONTINUE;
            }
        });
        System.out.println("全路径:"+fullpath);
        return fullpath;
    }
}
