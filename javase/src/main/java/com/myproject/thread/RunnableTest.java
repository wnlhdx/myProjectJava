package com.myproject.thread;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 * @author 31446
 */
public class RunnableTest implements Runnable{
    private final Logger logger= Logger.getLogger("test.Test");
  @Override
  public void run() {
      logger.log(Level.INFO,
              Thread.currentThread().getName()+"hello runnable");
  }
}
