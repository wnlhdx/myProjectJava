package com.myproject.javase.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

/**
 * @author lkxl
 */
public class Client implements Runnable {
    @Override
    public void run() {
        try (Socket socket = new Socket("127.0.0.1", 9999);) {
            while (true) {
                socket.getOutputStream().write((new Date() + ": hello world").getBytes());
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

