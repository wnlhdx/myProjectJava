package com.myproject.javase.jdk;


import org.junit.jupiter.api.Test;

/**
 * @author lkxl
 */
public class SimpleJvmTest {
    @Test
    public void testStart(){
            SimpleJvm simpleJvm=new SimpleJvm();
            simpleJvm.start();
    }
}
