package com.myproject.javase.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @author lkxl
 */
public class NIOServer implements Runnable {
    @Override
    public void run() {
                // 1. serverSelector负责轮询是否有新的连接，服务端监测到新的连接之后，不再创建一个新的线程，
                // 而是直接将新连接绑定到clientSelector上，这样就不用 IO 模型中 1w 个 while 循环在死等
        try {
            Selector serverSelector = Selector.open();
            Selector clientSelector = Selector.open();
                    try( ServerSocketChannel listenerChannel = ServerSocketChannel.open()) {
                        // 对应IO编程中服务端启动
                        listenerChannel.socket().bind(new InetSocketAddress(9999));
                        listenerChannel.configureBlocking(false);
                        listenerChannel.register(serverSelector, SelectionKey.OP_ACCEPT);
                            // 监测是否有新的连接，这里的1指的是阻塞的时间为 1ms
                            if (serverSelector.select(1000) > 0) {
                                System.out.println("nio reg");
                                Set<SelectionKey> set = serverSelector.selectedKeys();
                                Iterator<SelectionKey> keyIterator = set.iterator();
                                while (keyIterator.hasNext()) {
                                    SelectionKey key = keyIterator.next();
                                    if (key.isAcceptable()) {
                                        try {
                                            // (1)
                                            // 每来一个新连接，不需要创建一个线程，而是直接注册到clientSelector
                                            SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
                                            clientChannel.configureBlocking(false);
                                            clientChannel.register(clientSelector, SelectionKey.OP_READ);
                                        } finally {
                                            keyIterator.remove();
                                        }
                                    }
                                }
                            }
                        while (true) {
                            // (2) 批量轮询是否有哪些连接有数据可读，这里的1指的是阻塞的时间为 1ms
                            System.out.println("nio query");
                            if (clientSelector.select() > 0) {
                                Set<SelectionKey> set = clientSelector.selectedKeys();
                                Iterator<SelectionKey> keyIterator = set.iterator();
                                while (keyIterator.hasNext()) {
                                    SelectionKey key = keyIterator.next();
                                    if (key.isReadable()&&key.channel()instanceof SocketChannel) {
                                        try {
                                            SocketChannel clientChannel = (SocketChannel) key.channel();
                                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                                            // (3) 面向 Buffer
                                            clientChannel.read(byteBuffer);
                                            byteBuffer.flip();
                                            String res=Charset.defaultCharset().newDecoder().decode(byteBuffer).toString();
                                            System.out.println("received message: "+res);
                                        } finally {
                                            keyIterator.remove();
                                            key.interestOps(SelectionKey.OP_READ);
                                        }
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
