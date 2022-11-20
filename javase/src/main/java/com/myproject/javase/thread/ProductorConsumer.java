package com.myproject.javase.thread;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author lkxl
 */
public class ProductorConsumer {
    private int product = 0;
    private final ReentrantLock reentrantLock = new ReentrantLock(true);
    private final Condition condition = reentrantLock.newCondition();
    private final Logger logger = Logger.getLogger("test.Test");
    private int times =0;
    private static final int TIMES_LIMIT =1000;

    public class Productor implements Runnable {
        @Override
        public void run() {
            while (times<=TIMES_LIMIT) {
                reentrantLock.lock();
                try {
                    if (product < 20) {
                        times+=1;
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
                            Thread.currentThread().interrupt();
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
            while (times<=TIMES_LIMIT) {
                reentrantLock.lock();
                try {
                if (product > 0) {
                        times+=1;
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
                        Thread.currentThread().interrupt();
                    }
                }
                } finally {
                    reentrantLock.unlock();
                }
            }
        }
    }

    public void start() {
/*        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 1, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        threadPoolExecutor.execute(new Producter());
        threadPoolExecutor.execute(new Consumer());*/
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new Productor());
        executorService.execute(new Consumer());
        executorService.shutdown();
    }
}

