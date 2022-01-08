package com.myproject.javastudy;

import com.myproject.javastudy.Thread.CallableTest;
import com.myproject.javastudy.Thread.RunnableTest;
import com.myproject.javastudy.Thread.Threadtest;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

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
    try {
      new Thread(new RunnableTest()).start();
      new Threadtest().start();
      FutureTask task = new FutureTask(new CallableTest());
      new Thread(task).start();
      System.out.println((String) task.get());
    } catch (Exception e) {
      e.printStackTrace();
      //TODO: handle exception
    }
  }
}
