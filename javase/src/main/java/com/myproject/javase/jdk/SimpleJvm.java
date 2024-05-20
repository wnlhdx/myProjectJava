package com.myproject.javase.jdk;


import com.myproject.javase.jdk.jvm.classfile.BootClassLoader;

/**
 * @author lkxl
 */
public class SimpleJvm {

  private void parser() {
    BootClassLoader.loadMainClass("");
  }

  public void start() {
  }
}
