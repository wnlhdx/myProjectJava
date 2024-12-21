package com.myproject.springboot.APITest;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class ConnectDataBaseTest {
    @Test
    public void saveImage() {
        String imageUrl = "http://localhost:8080/book/pic";
        String destinationFile = "downloadedImage.png"; // 保存在当前目录下的文件名

        try {
            // 创建URL对象
            URL url = new URI(imageUrl).toURL();
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设置请求方法为GET
            connection.setRequestMethod("GET");
            // 连接到资源
            connection.connect();

            // 检查HTTP响应码
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 获取输入流
                InputStream inputStream = connection.getInputStream();
                // 使用BufferedInputStream包装以提供缓冲功能
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

                // 读取输入流到BufferedImage
                BufferedImage image = ImageIO.read(bufferedInputStream);

                // 关闭输入流
                bufferedInputStream.close();
                inputStream.close();

                // 将BufferedImage写入文件
                File outputFile = new File(destinationFile);
                ImageIO.write(image, "png", outputFile); // 根据实际图片格式修改后缀名

                System.out.println("Image downloaded successfully: " + destinationFile);
            } else {
                System.out.println("No image found at " + imageUrl);
            }

            // 断开连接
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
