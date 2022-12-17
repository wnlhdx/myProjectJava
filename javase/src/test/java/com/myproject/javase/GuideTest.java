package com.myproject.javase;

import com.myproject.javase.io.*;
import com.myproject.javase.serivcespi.SPIInterface;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.channels.Selector;
import java.util.ServiceLoader;
import java.util.logging.Logger;

/**
 * @author lkxl
 */
public class  GuideTest {
    private final Logger logger = Logger.getLogger("GuideTest");
    public static class Serializ implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;
        transient  int testt=1;
        int tests=2;
    }

    @Test
    public void testSPI(){
        ServiceLoader<SPIInterface> serviceLoader=ServiceLoader.load(SPIInterface.class);
        for (SPIInterface spiInterface : serviceLoader) {
            spiInterface.spi();
        }
    }

    @Test
    public void ser() throws Exception {
        Serializ serializ= new Serializ();
        FileOutputStream fileOut =
                new FileOutputStream("./test.txt");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(serializ);
        out.close();
        fileOut.close();
        FileInputStream fileIn = new FileInputStream("./test.txt");
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(fileIn));
        ObjectInputStream in = new ObjectInputStream(fileIn);
        Serializ aserializ = (Serializ) in.readObject();
        in.close();
        fileIn.close();
        logger.info(String.valueOf(aserializ.tests));
        logger.info(String.valueOf(aserializ.testt));
    }

    @Test
    public void testBIO() throws InterruptedException {
          //BIO阻塞读取完了才能读取下一个
       new Thread(new Server()).start();
       new Thread(new Client()).start();
       Thread.sleep(999999);

         //NIO不阻塞
    }

    @Test
    public void testNIO() throws IOException, InterruptedException {
        new Thread(new NIOServer()).start();
        new Thread(new NIOClient()).start();
        Thread.sleep(999999);
    }

    @Test
    public void testAIO() throws InterruptedException {
        new Thread(new AIOServer()).start();
        new Thread(new AIOClient()).start();
        Thread.sleep(999999);

    }

}
