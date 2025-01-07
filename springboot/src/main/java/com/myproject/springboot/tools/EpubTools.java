package com.myproject.springboot.tools;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubReader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class EpubTools {

    public static void convertEpubToPdf(String epubFilepath, String pdfFilepath) throws IOException {
        File epubFile = new File(epubFilepath);
        File pdfFile = new File(pdfFilepath);
        Book book = new EpubReader().readEpub(Files.newInputStream(epubFile.toPath()));
        PDDocument document = new PDDocument();
        PDPageContentStream contentStream;

        for (Resource resource : book.getResources().getAll()) {
                if (resource != null && resource.getData() != null) {
                    try (InputStream resourceStream = new ByteArrayInputStream(resource.getData())) {
                        Document doc = Jsoup.parse(resourceStream, "UTF-8", "", org.jsoup.parser.Parser.xmlParser());
                        Elements elements = doc.body().select("*");

                        PDPage page = new PDPage(PDRectangle.A4);
                        document.addPage(page);
                        contentStream = new PDPageContentStream(document, page);

                        float yPosition = page.getMediaBox().getHeight() - 50; // Start 50 units from the bottom
                        float fontSize = 12;
                        PDType0Font font = PDType0Font.load(document, EpubTools.class.getResourceAsStream("/org/apache/pdfbox/resources/ttf/LiberationSans-Regular.ttf"));


                        for (Element element : elements) {
                            if (element.isBlock()) {
                                yPosition -= fontSize; // New block, move down
                            }

                            for (TextNode textNode : element.textNodes()) {
                                String text = textNode.text().trim();
                                if (!text.isEmpty()) {
                                    if (yPosition < fontSize * 2) { // Check if we need a new page
                                        contentStream.close();
                                        page = new PDPage(PDRectangle.A4);
                                        document.addPage(page);
                                        contentStream = new PDPageContentStream(document, page);
                                        yPosition = page.getMediaBox().getHeight() - 50;
                                    }

                                    contentStream.beginText();
                                    contentStream.setFont(font, fontSize);
                                    contentStream.newLineAtOffset(40, yPosition);
                                    contentStream.showText(text);
                                    contentStream.endText();

                                    yPosition -= (float) (fontSize * 1.5); // Adjust line spacing
                                }
                            }

                            // Handle images
                            if (element.tagName().equals("img")) {
                                String src = element.attr("src");
                                byte[] imageBytes = book.getResources().getByHref(src).getData();
                                PDPageContentStream imageContentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);
                                imageContentStream.drawImage(PDImageXObject.createFromByteArray(document, imageBytes, null), 40, yPosition - 100, 100, 100);
                                imageContentStream.close();
                            }
                        }

                        contentStream.close();
                    }
                }
            }


        document.save(pdfFile);
        document.close();
    }

}
