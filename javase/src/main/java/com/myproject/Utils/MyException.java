package com.myproject.Utils;

import java.util.logging.Logger;

/**
 * @author lkxl
 */
public class MyException extends Exception{

    public MyException(String message) {
        Logger logger = Logger.getLogger("test.Test");
        logger.warning(message);
    }

}
