package com.myproject.javase.enums;

/**
 * @author lkxl
 */
public record Season(String seasonName,String seasonDesc) {
    public  static final Season SPRING =new Season("春","春天");
    public  static final Season SUMMER =new Season("夏","夏天");
    public  static final Season AUTUMN =new Season("秋","秋天");
    public  static final Season WINTER =new Season("冬","冬天");

}