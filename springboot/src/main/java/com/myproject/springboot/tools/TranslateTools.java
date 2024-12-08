package com.myproject.springboot.tools;

import space.dynomake.libretranslate.Language;
import space.dynomake.libretranslate.Translator;

import java.util.ArrayList;
import java.util.List;

public class TranslateTools {

    // 按页翻译
    public String translatePart(String text) {
        return Translator.translate(Language.ENGLISH, Language.CHINESE, text);  // 假设翻译为中文
    }

    // 将大文本分割成小块并翻译
    public String translate(String text) {
        final int maxTextLength = 1000;
        StringBuilder translatedText = new StringBuilder();

        for (int i = 0; i < text.length(); i += maxTextLength) {
            String part = text.substring(i, Math.min(i + maxTextLength, text.length()));
            String partTranslation = translatePart(part);
            translatedText.append(partTranslation).append("\n");
        }

        return translatedText.toString();
    }

    // 批量翻译多个页面的内容
    public List<String> translatePages(List<String> pagesText) {
        List<String> translatedPages = new ArrayList<>();
        for (int i = 0; i < pagesText.size(); i++) {
            String pageText = pagesText.get(i);
            String translatedPage = translate(pageText);
            // 在翻译结果中标注页码
            translatedPages.add("Page " + (i + 1) + ":\n" + translatedPage);
        }
        return translatedPages;
    }
}
