package com.myproject.springboot.tools;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.URL;

public class DownloadFile {
    public static void Download(String fileURL,String saveDir) {
        try {
            // 创建URL对象
            URL url = new URI(fileURL).toURL();
            // 打开连接
            java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) url.openConnection();
            int responseCode = httpConn.getResponseCode();

            // 检查HTTP响应码
            if (responseCode == java.net.HttpURLConnection.HTTP_OK) {
                // 获取文件名
                String fileName = "";
                String disposition = httpConn.getHeaderField("Content-Disposition");
                String contentType = httpConn.getContentType();
                int contentLength = httpConn.getContentLength();

                if (disposition != null) {
                    // 从Content-Disposition头中提取文件名
                    int index = disposition.indexOf("filename=");
                    if (index > 0) {
                        fileName = disposition.substring(index + 10,
                                disposition.length() - 1);
                    }
                } else {
                    // 从URL中提取文件名
                    fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1);
                }

                // 打开输入流
                BufferedInputStream inputStream = new BufferedInputStream(url.openStream());

                // 打开输出流以保存下载的文件
                FileOutputStream outputStream = new FileOutputStream(saveDir + java.io.File.separator + fileName);

                int bytesRead;
                byte[] buffer = new byte[4096];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                // 关闭流资源
                outputStream.close();
                inputStream.close();

                System.out.println("文件下载完成: " + saveDir + java.io.File.separator + fileName);
            } else {
                System.out.println("无法下载文件，服务器响应错误: " + responseCode);
            }
            httpConn.disconnect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
