package com.myproject.javase;

import com.myproject.javase.collections.MyCollection;
import com.myproject.javase.collections.TestCompare;

import static  org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import sun.misc.Unsafe;

import javax.swing.text.Segment;
import javax.swing.tree.TreeNode;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

/**
 * @author lkxl
 */
class CollectionTest {
    private final Logger logger = Logger.getLogger("test.Test");
    @Test
    void testList(){
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
        Iterator<Object> iterator=collection.iterator();
        while(iterator.hasNext()){
            iterator.next();
        }
        //线程安全的Object数组
        Vector<String> vector = new Vector<>();
        //线程不安全的Object数组
        ArrayList<String> arrayList = new ArrayList<>();
        //线程安全的双向链表.频繁的插入和删除
        LinkedList<String> linkedList = new LinkedList<>();
        arrayList.add("1");
        List<String> synchronizedarray=Collections.synchronizedList(arrayList);
        linkedList.offer("1");
        //多读少写线程安全
        CopyOnWriteArrayList<String> copyOnWriteArrayList=new CopyOnWriteArrayList<>();
        ArrayDeque<String> arrayDeque=new ArrayDeque<>();
    }

    @Test
    void TestSet(){
        Set<String> set=new LinkedHashSet<>();
        set.add("1");
        TreeSet<TestCompare> treeSet = new TreeSet<>();
        treeSet.add(new TestCompare("abc"));
        treeSet.add(new TestCompare("aaa"));
        treeSet.add(new TestCompare("caa"));
        assertEquals("aaa", treeSet.first().getTestString());
        Comparator<TestCompare> comparator=new Comparator<TestCompare>() {
            @Override
            public int compare(TestCompare o1, TestCompare o2) {
                return o1.getTestString().compareTo(o2.getTestString());
            }
        };
        TreeSet<TestCompare> treeSet1 = new TreeSet<>(comparator);
        treeSet1.add(new TestCompare("abc"));
        treeSet1.add(new TestCompare("aaa"));
        treeSet1.add(new TestCompare("caa"));
        assertEquals("aaa", treeSet1.first().getTestString());
    }

    @Test
    void TestMap() throws IOException {
        HashMap hashMap=new HashMap();
        LinkedHashMap linkedHashMap=new LinkedHashMap();
        TreeMap treeMap=new TreeMap();
        Hashtable hashtable=new Hashtable();
        Properties properties=new Properties();
        System.out.println(new File(".").getAbsolutePath());
        FileInputStream fileInputStream=new FileInputStream("src/test/resources/junit-platform.yaml");
        properties.load(fileInputStream);
        properties.list(System.out);
    }

    @Test
    void TestCollections() {
        Collections.synchronizedCollection(new ArrayList<>());
        Enum enum1=Enum.valueOf(Enum.class,"A");
        Segment segment=new Segment();
        ConcurrentHashMap concurrentHashMap=new ConcurrentHashMap();
        Unsafe unsafe;
    }

}
