package com.myproject.javastudy;

import com.myproject.javastudy.stringanddate.MyExceptions;
import com.myproject.javastudy.stringanddate.StringAndDateTest;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

/**
 * @author lkxl
 */
class SDTest {
    private final Logger logger = Logger.getLogger("test.Test");
    private final  StringAndDateTest test=new StringAndDateTest();

    @Test
    void makeSDTest() throws MyExceptions {
       //test.testDate();
       test.testString();
    }
}
