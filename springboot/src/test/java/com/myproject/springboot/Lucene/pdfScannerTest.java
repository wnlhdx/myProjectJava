package com.myproject.springboot.Lucene;

import com.myproject.springboot.tools.Index;
import com.myproject.springboot.tools.Spliter;
import com.myproject.springboot.tools.pdfscanner;
import com.myproject.springboot.tools.Query;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class pdfScannerTest {

    public static  pdfscanner scanner=new pdfscanner();
    public static  Index index;
    public static  Query query;
    public static  Spliter spliter=new Spliter();

    static {
        try {
            index = new Index();
            query = new Query();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }








    @Test
    public void testScan() throws IOException {

        String content=scanner.scan("D:\\book\\(NEW)Acoustic and Auditory Phonetics.pdf");
        System.out.println(content);
    }

    @Test
    public void testGetAllBook() throws IOException {

        String allFile = scanner.getFullPath("D:\\book",".pdf").toString();
        System.out.println(allFile);
    }

    @Test
    public void testIndex() throws Exception {
        index.indexPDF("D:\\book\\Management.pdf");
    }

    @Test
    public void testIndexAllBook() throws Exception {
        List<String> fullPath=scanner.getFullPath("./src/main/resources/testSplit",".txt");
        for(String path:fullPath){
            index.indexTxt(path);
        }
        index.closeWrite();
    }


    @Test
    public void testSearch() throws Exception {
        List<String> res=query.query("卧槽");
        System.out.println(res);
        query.closeRead();
    }

    @Test
    public void testSpli() throws Exception {
        spliter.splite("D:\\book\\《诡秘之主》（精校版全本）作者：爱潜水的乌贼.txt");
    }




}
