package com.myproject.javastudy.coreJavaTest;

import com.myproject.javastudy.reflect.MyReflection;
import com.myproject.javastudy.reflect.TestReflectionCon;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lkxl
 */
 class TestReflection {
    private final Logger logger = Logger.getLogger("test.Test");
    @Test
    void test() throws Exception {
        TestReflectionCon testReflectionCon=new TestReflectionCon();
        Class<? extends TestReflectionCon> aClass=testReflectionCon.getClass();
        aClass.getDeclaredConstructor().newInstance();
        logger.info(aClass.getName());
        Class<? extends MyReflection> bClass=MyReflection.class;
        assertThrowsExactly(NoSuchMethodException.class, bClass::getDeclaredConstructor);
    }
}
