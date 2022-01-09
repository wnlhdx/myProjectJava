package com.myproject.javastudy;

import com.myproject.javastudy.thread.CallableTest;
import com.myproject.javastudy.thread.RunnableTest;
import com.myproject.javastudy.thread.Threadtest;

import java.util.concurrent.FutureTask;
import java.util.logging.Level;
import java.util.logging.Logger;


import com.myproject.javastudy.thread.WindowUnsafe;
import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
    private final Logger logger = Logger.getLogger("test.Test");

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
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
        logger.log(Level.INFO, () -> "test" + 3);
        Thread window1 = new Thread(new WindowUnsafe());
        Thread window2 = new Thread(new WindowUnsafe());
        Thread window3 = new Thread(new WindowUnsafe());
        window1.setName("window1");
        window2.setName("window2");
        window3.setName("window3");
        window1.start();
        window2.start();
        window3.start();
        assertTrue(true);
    }
}
