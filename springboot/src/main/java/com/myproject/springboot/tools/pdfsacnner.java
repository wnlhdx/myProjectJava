package com.myproject.springboot.tools;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.io.RandomAccessReadBufferedFile;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class pdfsacnner{
        // 创建 PDFTextStripper 实例
    public String scan(String filename) throws IOException {
        PDDocument document =  Loader.loadPDF(new RandomAccessReadBufferedFile(filename);
        PDFTextStripper stripper = new PDFTextStripper();
        // 获取 PDF 文本内容
        return stripper.getText(document);
    }
}
