package com.myproject.javastudy.thread;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author lkxl
 */
public class WindowUnsafe implements Runnable {
    private final Logger logger= Logger.getLogger("test.Test");
    private int ticket=100;


    @Override
    public void run() {
        while (true){
 //           synchronized (Integer.valueOf(ticket)){
                if(ticket>0){
                    try{
                        Thread.sleep(100);
                        logger.log(Level.INFO,()->Thread.currentThread().getName()+"号窗口,票号:"+ticket);
                        ticket--;
                    }catch (Exception e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }
                else {
                    break;
                }
            }
 //       }
    }
}
