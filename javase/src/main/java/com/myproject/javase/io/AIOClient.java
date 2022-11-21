package com.myproject.javase.io;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.Date;

/**
 * @author lkxl
 */
public class AIOClient implements Runnable {
    @Override
    public void run() {
        try (AsynchronousSocketChannel client = AsynchronousSocketChannel.open();) {
            client.connect(new InetSocketAddress("localhost", 9999));
            while (true) {
                client.write(ByteBuffer.wrap((new Date() + ": hello world").getBytes())).get();
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
