package com.myproject.javase.coreJavaTest;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

/**
 * @author lkxl
 */
public class streamTest {
    @Test
    public void test(){
        Stream.generate(()->"test");
    }
}
