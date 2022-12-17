package com.myproject.javase.io;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Date;

/**
 * @author lkxl
 */
public class NIOClient implements Runnable {
    @Override
    public void run() {
        try (Selector clientSelector = Selector.open(); SocketChannel clientChannel=SocketChannel.open();) {
            clientChannel.connect(new InetSocketAddress("localhost",9999));
            while (true) {
                clientChannel.write(ByteBuffer.wrap((new Date() + ": hello world").getBytes()));
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
