package com.myproject.javastudy;

import com.myproject.javastudy.thread.ProducterConsumer;

import java.util.concurrent.FutureTask;
import java.util.logging.Level;
import java.util.logging.Logger;


import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;

import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;


/**
 * Unit test for simple App.
 */
public class AppTest  {
    private final Logger logger = Logger.getLogger("test.Test");
    @Test
/*    @Execution(CONCURRENT)
    @RepeatedTest(3)*/
    public void testApp() throws InterruptedException {
   /*     try {
            new Thread(new RunnableTest()).start();
            new Threadtest().start();
            FutureTask<String> task = new FutureTask<String>(new CallableTest());
            new Thread(task).start();
            logger.info(task.get());
        } catch (Exception e) {
            e.printStackTrace();
            //TODO: handle exception
        }

        ThreadCommunicate testWindow=new ThreadCommunicate();
        Thread window1 = new Thread(testWindow);
        Thread window2 = new Thread(testWindow);
        window1.start();
        window2.start();
        Thread.sleep(5000);
      WindowUnsafe  windows=new WindowUnsafe();
        Thread window1 = new Thread(windows);
        Thread window2 = new Thread(windows);
        Thread window3 = new Thread(windows);
        window1.setName("window1");
        window2.setName("window2");
        window3.setName("window3");
        window1.start();
        window2.start();
        window3.start();
        assertTrue(true);*/
        new ProducterConsumer().start();
        Thread.sleep(10000);
    }
}
