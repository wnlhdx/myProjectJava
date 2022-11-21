package com.myproject.javase.io;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lkxl
 */
public class Server implements Runnable {
    @Override
    public void run() {
        try {
                ServerSocket serverSocket = new ServerSocket(9999);
                    while (true) {
                            // 阻塞方法获取新的连接
                            Socket socket = serverSocket.accept();
                            // 每一个新的连接都创建一个线程，负责读取数据
                            new Thread(() -> {
                                try {
                                    int len;
                                    byte[] data = new byte[1024];
                                    InputStream inputStream = socket.getInputStream();
                                    // 按字节流方式读取数据
                                    while ((len = inputStream.read(data)) != -1) {
                                        System.out.println(new String(data, 0, len));
                                    }
                                }catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }).start();
                    }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
