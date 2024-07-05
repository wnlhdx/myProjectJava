package com.myproject.springboot.tools;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Index {

    public static pdfscanner sacnner;
    public static Analyzer writeanalyzer;
    public static String indexPath="./src/main/resources/index";
    public static IndexWriter writer;

    public Index() throws IOException {
        sacnner= new pdfscanner();
        writeanalyzer=new StandardAnalyzer();
        Directory index = FSDirectory.open(Paths.get(indexPath));
        IndexWriterConfig config = new IndexWriterConfig(writeanalyzer);
        writer = new IndexWriter(index,config);


    }


    public void indexPDF(String path) throws Exception {
        // 创建索引
        Document doc = new Document();
        doc.add(new TextField("content",sacnner.scan(path), Field.Store.YES));
        doc.add(new TextField("fullPath",path, Field.Store.YES));
        writer.addDocument(doc);
    }

    public void indexTxt(String path) throws Exception {
        // 创建索引
        Document doc = new Document();
        doc.add(new TextField("content", Files.readString(Paths.get(path)),Field.Store.YES));
        doc.add(new TextField("fullPath",path, Field.Store.YES));
        writer.addDocument(doc);
    }

    public void closeWrite() throws IOException {
        writer.close();
    }
}
