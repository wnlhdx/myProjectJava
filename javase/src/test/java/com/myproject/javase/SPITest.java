package com.myproject.javase;

import com.myproject.javase.serivcespi.SPIInterface;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author lkxl
 */
public class SPITest {
    @Test
    public void testSPI(){
        ServiceLoader<SPIInterface> serviceLoader=ServiceLoader.load(SPIInterface.class);
        for (SPIInterface spiInterface : serviceLoader) {
            spiInterface.spi();
        }
    }
}
