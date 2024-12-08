package com.myproject.springboot.APITest;

import com.myproject.springboot.tools.TranslateTools;
import com.myproject.springboot.tools.pdfscanner;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class TranslateToolsTest {

    private pdfscanner pdfscanner = new pdfscanner();
    private TranslateTools translateTools = new TranslateTools();

    @Test
    public void testTranslate() throws IOException {
        // 测试：逐页提取PDF文本
        List<String> pagesText = pdfscanner.scanPages("D:\\book\\(NEW)Code Complete  _Steve McConnell_1170911_zhel.pdf");

        // 翻译每一页的内容并输出
        List<String> translatedPages = translateTools.translatePages(pagesText);

        // 输出翻译后的文本
        for (String translatedPage : translatedPages) {
            System.out.println(translatedPage);
        }
    }

    @Test
    public void testTranslateByRange() throws IOException {
        // 测试：按页数范围提取PDF文本
        List<String> pagesText = pdfscanner.scanPagesByRange("D:\\book\\(NEW)Code Complete  _Steve McConnell_1170911_zhel.pdf", 1, 3);

        // 翻译每一页的内容并输出
        List<String> translatedPages = translateTools.translatePages(pagesText);

        // 输出翻译后的文本
        for (String translatedPage : translatedPages) {
            System.out.println(translatedPage);
        }
    }
}
