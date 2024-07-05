package com.myproject.springboot.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

public class Spliter {
    public static  String savePath="./src/main/resources/testSplit/";
    public void splite(String filePath){
        String chapterRegex = "第\\S+章\\s+.+"; // 章节正则表达式，根据实际情况可能需要调整
        Pattern pattern = Pattern.compile(chapterRegex, Pattern.MULTILINE);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder chapterContent = new StringBuilder();
            int chapterCount = 0;
            boolean newChapter = false;

            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    // 当找到新的章节标题时，先保存前一个章节
                    System.out.println("有");
                    if (newChapter && !chapterContent.isEmpty()) {
                        saveChapter(chapterCount, chapterContent.toString());
                        chapterContent.setLength(0); // 清空 StringBuilder
                        chapterCount++;
                    }
                    chapterContent.append(matcher.group()).append("\n"); // 添加章节标题
                    newChapter = true;
                } else if (newChapter) {
                    chapterContent.append(line).append("\n"); // 添加章节内容
                }
            }

                    // 保存最后一个章节
            if (!chapterContent.isEmpty()) {
                saveChapter(chapterCount, chapterContent.toString());
            }

            System.out.println("拆分完成！");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveChapter(int chapterNumber, String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(savePath+"chapter_" + chapterNumber + ".txt"))) {
            bw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
