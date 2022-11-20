package com.myproject.javase.thread;

import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 * @author 31446
 */
public class CallableTest implements Callable<String> {
    private final Logger logger= Logger.getLogger("test.Test");
  @Override
  public String call(){
      logger.log(Level.INFO,"hello callable");
      return ("hello return");
  }
}
