package com.myproject.javastudy;

import com.myproject.javastudy.thread.CallableTest;
import com.myproject.javastudy.thread.RunnableTest;
import com.myproject.javastudy.thread.Threadtest;

import java.util.concurrent.FutureTask;
import java.util.logging.Level;
import java.util.logging.Logger;


import com.myproject.javastudy.thread.WindowUnsafe;
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
    @Execution(CONCURRENT)
    @RepeatedTest(3)
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
*/
        WindowUnsafe testWindow1=new WindowUnsafe();
        Thread window1 = new Thread(testWindow1);
        window1.start();
/*      Thread window1 = new Thread(new WindowUnsafe());
        Thread window2 = new Thread(new WindowUnsafe());
        Thread window3 = new Thread(new WindowUnsafe());
        window1.setName("window1");
        window2.setName("window2");
        window3.setName("window3");
        window1.start();
        window2.start();
        window3.start();
        assertTrue(true);*/
    }
}
