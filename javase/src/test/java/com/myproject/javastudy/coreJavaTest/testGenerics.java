package com.myproject.javastudy.coreJavaTest;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import javax.swing.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Supplier;
import java.util.logging.Logger;

/**
 * @author lkxl
 */
public class testGenerics {
    private final Logger logger = Logger.getLogger("test.Test");
    @Test
    void testGenerics(){
        Supplier<String> s = () -> "hello";
        HashMap<String, String> map = new HashMap<>();
        map.merge("key", "value", (oldValue, newValue) -> oldValue + newValue);
    }

    @Test
    void testCollections(){
        ArrayList<String> list = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        set.toArray(String[]::new);
        set.toArray(new String[0]);
    }

    @Test
    void testSwing(){
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setLocationByPlatform(true);
    }
}
