package com.myproject;

import com.myproject.stringanddate.StringAndDateTest;
import com.myproject.stringanddate.TestSystem;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

/**
 * @author lkxl
 */
class SDTest {
    private final Logger logger = Logger.getLogger("test.Test");
    private final StringAndDateTest test=new StringAndDateTest();

   /* @Test
    void makeSDTest() throws MyExceptions, ParseException {
       //test.testDate();
       //test.testString();
        test.testDateFormate();
    }*/

//    @Test
//    void testCompare(){
//        ComparableThings things1=new ComparableThings(2);
//        ComparableThings things2=new ComparableThings(3);
//        ComparableThings things3 = new ComparableThings(1);
//        ComparableThings things4 = new ComparableThings(0);
//        ComparableThings[] arrays={things1,things2,things3,things4};
//        ArrayList<ComparableThings> arrayList = new ArrayList<>();
//        arrayList.add(things1);
//        arrayList.add(things2);
//        arrayList.add(things3);
//        arrayList.add(things4);
//        Arrays.sort(arrays);
//        logger.info(Arrays.toString(arrays));
//        arrayList.sort(ComparableThings.getComparetor());
//        logger.info(arrayList.toString());
//    }

    @Test
    void testSystem(){
        new TestSystem().testSystem();
        assert true;
    }
}
