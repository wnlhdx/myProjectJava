package com.myproject.javastudy.Thread;

import java.util.concurrent.Callable;

/**
 * Hello world!
 *
 */
public class CallableTest implements Callable {
  @Override
  public Object call() throws Exception {
      System.out.println("hello callable");
      return ("hello return");
  }
}
