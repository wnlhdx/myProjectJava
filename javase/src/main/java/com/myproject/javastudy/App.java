package com.myproject.javastudy;

import com.myproject.javastudy.thread.WindowUnsafe;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Thread window1 = new Thread(new WindowUnsafe());
        Thread window2 = new Thread(new WindowUnsafe());
        Thread window3 = new Thread(new WindowUnsafe());
        window1.setName("window1");
        window2.setName("window2");
        window3.setName("window3");
        window1.start();
        window2.start();
        window3.start();
    }
}
