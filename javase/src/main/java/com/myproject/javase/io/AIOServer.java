package com.myproject.javase.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author lkxl
 */
public class AIOServer  implements Runnable {
    static int PORT=9999;
    @Override
    public void run() {
        AsynchronousServerSocketChannel server = null;
        try {
            server = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(PORT));
            // 注册事件和事件完成后的处理器
            AsynchronousServerSocketChannel finalServer = server;
            server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
                final ByteBuffer buffer = ByteBuffer.allocate(1024);
                public void completed(AsynchronousSocketChannel result, Object attachment) {
                    System.out.println(Thread.currentThread().getName());
                    System.out.println("start");
                    try {
                        buffer.clear();
                        result.read(buffer).get(100, TimeUnit.SECONDS);
                        buffer.flip();
                        System.out.println("received message: " + new String(buffer.array()));
                    } catch (InterruptedException | ExecutionException e) {
                        System.out.println(e.toString());
                    } catch (TimeoutException e) {
                        e.printStackTrace();
                    } finally {

                        try {
                            result.close();
                            finalServer.accept(null, this);
                        } catch (Exception e) {
                            System.out.println(e.toString());
                        }
                    }
                    System.out.println("end");
                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                    System.out.println("failed: " + exc);
                }
            });
            // 主线程继续自己的行为
            while (true) {
                System.out.println("server main thread");
                Thread.sleep(1000);
            }
        } catch (Exception e) {
           e.printStackTrace();
        }


    }
}
