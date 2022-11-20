package com.myproject.javase.collections;

/**
 * @author lkxl
 */
public class TestCompare implements Comparable<TestCompare>{
    private String testString;
    public TestCompare(String testString){
        this.testString = testString;
    }

    public String getTestString() {
        return testString;
    }

    public void setTestString(String testString) {
        this.testString = testString;
    }

    @Override
    public int compareTo(TestCompare o) {
        return testString.compareTo(o.testString);
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof TestCompare testObject){
            return testString.equals(testObject.testString);
        }else{
            return false;
        }
    }
    @Override
    public int hashCode(){
        return testString.hashCode();
    }
}
