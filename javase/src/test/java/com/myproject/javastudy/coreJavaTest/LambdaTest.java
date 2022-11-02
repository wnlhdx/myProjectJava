package com.myproject.javastudy.coreJavaTest;

import org.junit.jupiter.api.Test;

import java.awt.event.ActionListener;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

/**
 * @author lkxl
 */
public class LambdaTest {

    @Test
    void testLambda(){
        lambdaInterface lambdaInterface=(i,j)->{
            System.out.println(i+j);
        };
        lambdaInterface.test(1,2);
    }

    @Test
    void testMethodReference(){
        ArrayList<Integer> testarray=new ArrayList<>();
        for (int i=0;i<10;i++){
            testarray.add(i);
        }
        Stream<test> stream=testarray.stream().map(test::new);
        List<test> list=stream.collect(Collectors.toList());
        Object[] objects=stream.toArray();
        test[] tests=stream.toArray(test[]::new);
        Arrays.sort(tests,Comparator.comparing(test::getTestnum).thenComparing(test::getTestnum,(s,t)->{return Integer.compare(s.length(),t.length());}));
    }

}
class test{
    String testnum;
    public  test(){
        testnum="0";
    }
    public  test(Integer i){
        testnum=Integer.toString(i);
    }

    public String getTestnum() {
        return testnum;
    }
}
interface lambdaInterface{
    public void test(int a,int b);
}
