package com.myproject.enums;

import java.util.ArrayList;

/**
 * @author lkxl
 */
public class TestClass <@MyAnnotation  T>{
    public void show() {
        ArrayList<@MyAnnotation String> list = new ArrayList<>();
    }
}
