package com.myproject.javase.stringanddate;

import java.util.Comparator;
import java.util.Optional;

/**
 * @author lkxl
 */
public class ComparableThings  implements Comparable<ComparableThings> {
    private   Integer compareNumber =0;
    private static final MyCompare comparetor = new MyCompare();

     public static MyCompare getComparetor(){
        return  comparetor;
    }
    public Integer getCompareNumber(){
        return  compareNumber;
    }
    public ComparableThings(int i){
        this.compareNumber=i;
    }

    @Override
    public String toString(){
        return "compareName"+this.compareNumber;
    }

    @Override
    public int compareTo(ComparableThings o) {
        return compareNumber.compareTo(o.compareNumber);
    }


    @Override
    public boolean equals(Object o) {
        o = Optional.ofNullable(o).orElse(new ComparableThings(0));
        if( o instanceof ComparableThings comparableThings){
            return compareNumber.equals(comparableThings.getCompareNumber());
        }else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        return compareNumber;
    }


    static class MyCompare implements Comparator<ComparableThings>{

        @Override
        public int compare(ComparableThings o1, ComparableThings o2) {
            return o1.compareNumber.compareTo(o2.compareNumber);
        }
    }
}


