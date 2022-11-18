package com.myproject.thread;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author lkxl
 */
public class ThreadCommunicate implements Runnable{
    private int number=1;
    private final Logger logger = Logger.getLogger("test.Test");
    private ReentrantLock reentrantLock = new ReentrantLock(true);
    private Condition condition=reentrantLock.newCondition();

    @Override
    public void run() {
        //public sychronized void run() {
        while (true){
            reentrantLock.lock();
            try{
                //notify();
                condition.signal();
                if(number<=100){
                    logger.log(Level.INFO, () -> Thread.currentThread().getName() + "window,number:" + number);
                    number++;
                    //wait();
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    break;
                }
            } finally {
                reentrantLock.unlock();
            }

        }
    }
}
