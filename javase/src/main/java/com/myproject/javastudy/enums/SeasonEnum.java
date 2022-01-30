package com.myproject.javastudy.enums;

import java.util.logging.Logger;

/**
 * @author lkxl
 */

interface TestExtend{
    /**
     * just a log
     **/
   static  final String LOG ="test.Test";

    /**
     * just a test
     **/
    void show();
}
/**
 * @author 31446
 */

public enum SeasonEnum implements TestExtend{
    /*
    春
     */

    SPRING("春", "春天"){
        @Override
        public void show() {
            Logger logger = Logger.getLogger(TestExtend.LOG);
            logger.info("春");
        }
    },
    /*
    夏
    */
    SUMMER("夏", "夏天"){
        @Override
        public void show() {
            Logger logger = Logger.getLogger(TestExtend.LOG);
            logger.info("夏");
        }
    },
    /*
    秋
     */
    AUTUMN("秋", "秋天"){
        @Override
        public void show() {
            Logger logger = Logger.getLogger(TestExtend.LOG);
            logger.info("秋");
        }
    },
    /*
     冬
     */
    WINTER("冬", "冬天"){
        @Override
        public void show() {
            Logger logger = Logger.getLogger(TestExtend.LOG);
            logger.info("冬");
        }
    };
    private final String seasonName;
    private final String seasonDesc;

     SeasonEnum(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    @Override
    public String toString() {
        return "SeasonEnum{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}

