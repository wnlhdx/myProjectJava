package com.myproject.javastudy.thread;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author lkxl
 */
public class ProducterConsumer {
    private static int product = 0;
    private final ReentrantLock reentrantLock = new ReentrantLock();
    private final Condition condition = reentrantLock.newCondition();
    private final Logger logger = Logger.getLogger("test.Test");

    public class Producter implements Runnable {
        @Override
        public void run() {
            while (true) {
                reentrantLock.lock();
                try {
                    if (product < 20) {

                        product++;
                        condition.signalAll();
                        logger.log(Level.INFO, () -> Thread.currentThread().getName() + "thread,product" + product);
                        //notify();

                    } else {
                        try {
                            condition.await();
                            //wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } finally {
                    reentrantLock.unlock();
                }
            }
        }
    }

    public class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                reentrantLock.lock();
                try {
                if (product > 0) {

                        product--;
                        logger.log(Level.INFO, () -> Thread.currentThread().getName() + "thread,product" + product);
                        condition.signalAll();
                        //notify();

                } else {
                    try {
                        condition.await();
                        //wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                } finally {
                    reentrantLock.unlock();
                }
            }
        }
    }

    public void start() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 1, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(2), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        threadPoolExecutor.execute(new Producter());
        threadPoolExecutor.execute(new Consumer());
    }
}

