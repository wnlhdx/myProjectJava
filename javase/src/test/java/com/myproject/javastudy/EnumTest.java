package com.myproject.javastudy;

import com.myproject.javastudy.enums.Season;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.myproject.javastudy.enums.SeasonEnum;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.logging.Logger;

/**
 * @author lkxl
 */
class EnumTest {
    private final Logger logger = Logger.getLogger("test.Test");
    @Test
    void SeasonTest(){
         assertEquals("春天",Season.SPRING.seasonDesc());
         logger.info(Arrays.toString(SeasonEnum.values()));
    }
}
