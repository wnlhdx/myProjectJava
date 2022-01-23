package com.myproject.javastudy.stringanddate;

import java.util.Properties;
import java.util.logging.Logger;

/**
 * @author lkxl
 */
public class TestSystem {
    private final Logger logger = Logger.getLogger("test.Test");
    public void testSystem(){
        Properties properties=System.getProperties();
        String property=properties.toString();
        String[] pros= property.split(",");
        for(int i=0;i<pros.length;i++){
            logger.info(pros[i]);
        }
        System.exit(0);
    }
}
