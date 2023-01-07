package com.myproject.javase.coreJavaTest;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lkxl
 */
public class streamTest {
    private final Logger logger = Logger.getLogger("StreamTest");
    @Test
    public void test(){
        List<String> arraylist=new ArrayList<>();
        arraylist.add("test1");
        arraylist.add("test2");
        arraylist.add("te");
        long count=arraylist.parallelStream().filter(w->w.length()>3).count();
        logger.info(String.valueOf(count));
        Stream<String> stream=arraylist.stream();
        stream=Stream.of("a","b","c","d");
        stream=Stream.empty();
        stream=Stream.generate(()->"test");
        Stream<BigInteger> stream2=Stream.iterate(BigInteger.ZERO,n->n.compareTo(BigInteger.ZERO)>0, n->n.add(BigInteger.ONE));
        stream=Stream.ofNullable("");
        stream=Stream.of("a","b","c","d");
        stream=stream.map(String::toUpperCase);
        stream=stream.limit(3);
        stream=stream.skip(1);
        stream=stream.takeWhile(n->n.length()==1);
        stream=stream.dropWhile(n->n.charAt(0)=='C');
        stream.forEach(n->logger.info("test1"+n));
        stream=Stream.of("a","b","c","d");
        Stream<Stream<String>> stream3=Stream.of(Stream.of("a","b","c","d"),Stream.of("a","b","c","d"),Stream.of("a","b","c","d"));
        stream=stream3.flatMap(n->n.map(String::toUpperCase));
        stream=stream.distinct();
        stream.forEach(n->logger.info("test2"+n));
        stream=Stream.of("d","c","b","c");

        stream=stream.sorted(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.charAt(0)-o2.charAt(0);
            }
        });
        stream=stream.peek(n->logger.info("peek"+n));
    }
}
