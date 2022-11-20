package com.myproject.javase.coreJavaTest;

import com.myproject.javase.reflect.MyReflection;
import com.myproject.javase.reflect.TestReflectionCon;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.util.logging.*;

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

    @Test
    void testLog(){
        Logger logger=Logger.getLogger("test.Test","test");
        System.out.println(Charset.defaultCharset().name());
        logger.log(Level.INFO,"Test",new String[]{"test","test"});
        ConsoleHandler consoleHandler=new ConsoleHandler();
        logger.setFilter(record -> record.getLevel().intValue()==Level.INFO.intValue());
        SimpleFormatter simpleFormatter=new SimpleFormatter();
        consoleHandler.setFormatter(simpleFormatter);
        logger.addHandler(consoleHandler);

    }
}
