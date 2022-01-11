package com.myproject.javastudy.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author lkxl
 */
public class WindowUnsafe implements Runnable {
    private final Logger logger = Logger.getLogger("test.Test");
    private static int ticket = 100;
    private Lock reentrantLock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true) {
            //           synchronized (Integer.valueOf(ticket)){
            reentrantLock.lock();
            try{
                if (ticket > 0) {
                    try {
                        Thread.sleep(100);
                        logger.log(Level.INFO, () -> Thread.currentThread().getName() + "window,ticket:" + ticket);
                        ticket--;
                    } catch (Exception e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                } else {
                    break;
                }
            } finally {
                reentrantLock.unlock();
            }
            //       }
        }
    }
}
