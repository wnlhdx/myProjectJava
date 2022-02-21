package com.myproject.javastudy;

import com.myproject.javastudy.collections.MyCollection;
import static  org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author lkxl
 */
class CollectionTest {
    private final Logger logger = Logger.getLogger("test.Test");
    @Test
    void testCollection(){
        MyCollection myCollection = new MyCollection();
        myCollection.setCollection(new ArrayList<>());
        Collection<Object> theCollection = myCollection.getCollection();
        theCollection.add("1");
        theCollection.add(2);
        Collection<Object> collection=new ArrayList<>();
        collection.add(3);
        theCollection.remove(2);
        theCollection.addAll(collection);
        logger.info("测试方法");
        assertTrue(theCollection.contains("1"));
        assertTrue(theCollection.containsAll(collection));
        theCollection.removeAll(collection);
        assertFalse(theCollection.isEmpty());
        theCollection.retainAll(collection);
        logger.info(Arrays.toString(theCollection.toArray()));
        List<Integer> test= List.of(1,2,3,4,5);
        assertEquals(1,test.size());
    }

}
