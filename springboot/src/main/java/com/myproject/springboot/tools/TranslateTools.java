package com.myproject.springboot.tools;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TranslateTools {

    private final WebClient webClient;

    // 构造时注入 WebClient，基础 URL 建议配置化
    public TranslateTools(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://libretranslate.de").build();
    }

    /**
     * 按块翻译：核心 IO 逻辑
     * 修好点：直接通过 HTTP 请求替代失效的 SDK
     */
    public String translatePart(String text) {
        return webClient.post()
                .uri("/translate")
                .bodyValue(Map.of(
                        "q", text,
                        "source", "en",
                        "target", "zh",
                        "format", "text"
                ))
                .retrieve()
                .bodyToMono(Map.class)
                .map(res -> (String) res.get("translatedText"))
                .block(); // 暂时用 block 保持同步逻辑，后续可优化为非阻塞
    }

    /**
     * 将大文本分割成小块并翻译
     * 逻辑审计：保持 1000 字符分片，防止 API 拒收
     */
    public String translate(String text) {
        if (text == null || text.isBlank()) return "";

        final int maxTextLength = 1000;
        StringBuilder translatedText = new StringBuilder();

        for (int i = 0; i < text.length(); i += maxTextLength) {
            String part = text.substring(i, Math.min(i + maxTextLength, text.length()));
            try {
                String partTranslation = translatePart(part);
                translatedText.append(partTranslation).append("\n");
            } catch (Exception e) {
                // 增加容错：某一块失败不影响整体
                translatedText.append("[Translation Error: ").append(part).append("]\n");
            }
        }
        return translatedText.toString();
    }

    /**
     * 批量翻译多个页面的内容
     * 提点：Java 8+ Stream 风格重写，更具 Java 5 年开发者的技术美学
     */
    public List<String> translatePages(List<String> pagesText) {
        return pagesText.stream()
                .map(pageText -> {
                    int index = pagesText.indexOf(pageText) + 1;
                    return "Page " + index + ":\n" + translate(pageText);
                })
                .collect(Collectors.toList());
    }
}