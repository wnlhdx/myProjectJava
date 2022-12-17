package com.myproject.javase.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author lkxl
 */
public class AIOServer  implements Runnable {
    @Override
    public void run() {
        AsynchronousServerSocketChannel server = null;
        try {
            server = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(9999));
            // 注册事件和事件完成后的处理器
            AsynchronousServerSocketChannel finalServer = server;
            server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
                final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                public void completed(AsynchronousSocketChannel result, Object attachment) {
                    System.out.println("thread:"+Thread.currentThread().getName());
                    try {
                        byteBuffer.clear();
                        result.read(byteBuffer).get(100, TimeUnit.SECONDS);
                        byteBuffer.flip();
                        String res= Charset.defaultCharset().newDecoder().decode(byteBuffer).toString();
                        System.out.println("received message: " + res);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            finalServer.accept(null, this);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                    System.out.println("failed: " + exc);
                }
            });
            // 主线程继续自己的行为
            while (true) {
                System.out.println("thread:"+Thread.currentThread().getName());
                Thread.sleep(2000);
            }
        } catch (Exception e) {
           e.printStackTrace();
        }


    }
}
