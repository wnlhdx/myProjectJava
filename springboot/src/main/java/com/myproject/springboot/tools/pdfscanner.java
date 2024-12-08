package com.myproject.springboot.tools;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.io.RandomAccessReadBufferedFile;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class pdfscanner {

    // 按页数提取文本
    public List<String> scanPages(String filename) throws IOException {
        PDDocument document = Loader.loadPDF(new RandomAccessReadBufferedFile(filename));
        PDFTextStripper stripper = new PDFTextStripper();

        List<String> pageTexts = new ArrayList<>();
        int totalPages = document.getNumberOfPages();

        // 逐页提取文本
        for (int pageNum = 1; pageNum <= totalPages; pageNum++) {
            stripper.setStartPage(pageNum);
            stripper.setEndPage(pageNum);
            String pageText = stripper.getText(document);
            pageTexts.add(pageText);
        }

        document.close();
        return pageTexts;
    }

    // 按页数范围提取文本
    public List<String> scanPagesByRange(String filename, int startPage, int endPage) throws IOException {
        PDDocument document = Loader.loadPDF(new RandomAccessReadBufferedFile(filename));
        PDFTextStripper stripper = new PDFTextStripper();

        List<String> pageTexts = new ArrayList<>();
        int totalPages = document.getNumberOfPages();

        // 确保页数范围有效
        if (startPage < 1 || endPage > totalPages || startPage > endPage) {
            throw new IllegalArgumentException("Invalid page range");
        }

        // 按页数范围提取文本
        for (int pageNum = startPage; pageNum <= endPage; pageNum++) {
            stripper.setStartPage(pageNum);
            stripper.setEndPage(pageNum);
            String pageText = stripper.getText(document);
            pageTexts.add(pageText);
        }

        document.close();
        return pageTexts;
    }

    // 扫描整个PDF的文本（不按页）
    public String scan(String filename) throws IOException {
        PDDocument document = Loader.loadPDF(Paths.get(filename).toFile());
        PDFTextStripper stripper = new PDFTextStripper();
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
