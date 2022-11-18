package com.myproject;

import com.myproject.enums.MyAnnotation;
import com.myproject.enums.Season;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.myproject.enums.SeasonEnum;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * @author lkxl
 */
class EnumTest {
    private final Logger logger = Logger.getLogger("test.Test");
    @Test
    @SuppressWarnings("unused")
    @MyAnnotation(value = "test4")
    @MyAnnotation(value="test1")
    //@MyAnnotations(value={@MyAnnotation(value="test2"),@MyAnnotation(value="test3")})
    void SeasonTest(){
         assertEquals("春天",Season.SPRING.seasonDesc());
         logger.info(Arrays.toString(SeasonEnum.values()));
         Class<EnumTest> enumTestClass= EnumTest.class;
         Annotation[] annotations= enumTestClass.getAnnotations();
         for(Annotation annotation:annotations){
             logger.info(annotation.toString());
         }
    }
}
