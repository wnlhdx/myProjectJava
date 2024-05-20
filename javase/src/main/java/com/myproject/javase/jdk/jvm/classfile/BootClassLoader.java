package com.myproject.javase.jdk.jvm.classfile;

import com.myproject.javase.jdk.jvm.oop.InterKClass;

public class BootClassLoader {
    static InterKClass mainKClass;
    public static InterKClass loadMainClass(String mainClass) {
        if (null != mainKClass) {
            return mainKClass;
        }
        return loadClass(mainClass);
    }

    public static InterKClass loadClass(String Class){
        return  loadClass(Class,true);
    }

    public static InterKClass loadClass(String Class,boolean resolve){
        return  null;
    }
}
