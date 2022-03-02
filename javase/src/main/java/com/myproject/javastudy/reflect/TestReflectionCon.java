package com.myproject.javastudy.reflect;

/**
 * @author lkxl
 */
public class TestReflectionCon {
    private String str="str";
    public String testReflectionCon(){
        return str;
    }
    public String setStr(String str){
        return this.str = str;
    }
    public TestReflectionCon(){
        super();

    }
}
