package com.myproject.javastudy.coreJavaTest;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author lkxl
 */
 class DataTypeTest {
    private final Logger logger = Logger.getLogger("test.Test");
    private final String path="./src/test/java/com/myproject/javastudy/coreJavaTest/test.txt";
    @Test
    void TestInputFile() throws IOException {
        Scanner in=new Scanner(Paths.get(path), StandardCharsets.UTF_8);
        PrintWriter out=new PrintWriter(path,StandardCharsets.UTF_8);
        out.println("Test");
        out.close();
        logger.info(Path.of(".").toAbsolutePath().toString());
        in.close();
        assertTrue(in.nextLine().contains("Test"));


    }



}
