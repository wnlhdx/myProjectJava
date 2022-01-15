package com.myproject.javastudy;

import com.myproject.javastudy.thread.*;

import java.util.concurrent.FutureTask;
import java.util.logging.Logger;


import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;

import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

public class ThreadTest {
    private final Logger logger = Logger.getLogger("test.Test");
/* //测试三种线程方法
    @Test
    public void testThread() throws InterruptedException {
     try {
            new Thread(new RunnableTest()).start();
            new Threadtest().start();
            FutureTask<String> task = new FutureTask<String>(new CallableTest());
            new Thread(task).start();
            logger.info(task.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

       /* //测试线程安全问题
    @Test
    public void testThreadUnsafe() throws InterruptedException {
        WindowUnsafe windows = new WindowUnsafe();
        Thread window1 = new Thread(windows);
        Thread window2 = new Thread(windows);
        Thread window3 = new Thread(windows);
        window1.setName("window1");
        window2.setName("window2");
        window3.setName("window3");
        window1.start();
        window2.start();
        window3.start();
        Thread.sleep(5000);
    }*/

 /*  //线程通信
    @Test
    public void testThreadCommunicate() throws InterruptedException {
        ThreadCommunicate testWindow = new ThreadCommunicate();
        Thread window1 = new Thread(testWindow);
        Thread window2 = new Thread(testWindow);
        window1.start();
        window2.start();
        Thread.sleep(5000);
    } */

/*    //测试生产者消费者问题
    @Test
    @Execution(CONCURRENT)  //多线程并发执行测试方法
    @RepeatedTest(3)  //线程重复执行三次
    public void testProducterConsumer() throws InterruptedException {
        new ProducterConsumer().start();
        Thread.sleep(10000);
    }*/
}
