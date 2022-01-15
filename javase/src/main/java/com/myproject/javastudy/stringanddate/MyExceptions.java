package com.myproject.javastudy.stringanddate;

import java.util.logging.Logger;

/**
 * @author lkxl
 */
public class MyExceptions extends Exception{
    private final Logger logger = Logger.getLogger("test.Test");

    public MyExceptions(String message) {
        logger.warning(message);
    }

}
