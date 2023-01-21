package com.myproject.javase.coreJavaTest;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lkxl
 */
public class streamTest {
    private final Logger logger = Logger.getLogger("StreamTest");
    @Test
    public void testStream(){
        List<String> arraylist=new ArrayList<>();
        arraylist.add("test1");
        arraylist.add("test2");
        arraylist.add("te");
        long count=arraylist.parallelStream().filter(w->w.length()>3).count();
        logger.info("tests"+String.valueOf(count));
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
        stream=stream.takeWhile(n->n.charAt(0)>='B');
        stream=stream.dropWhile(n->n.charAt(0)<'C');
        stream.forEach(n->logger.info("test1"+n));
        stream=Stream.of("a","b","c","d");
        Stream<Stream<String>> stream3=Stream.of(Stream.of("a","b","c","d"),Stream.of("a","b","c","d"),Stream.of("a","b","c","d"));
        stream=stream3.flatMap(n->n.map(String::toUpperCase));
        stream.forEach(n->logger.info("test2"+n));
        stream=Stream.of("d","c","b","c");
        stream=stream.distinct();
        stream=stream.sorted();
        stream=stream.peek(n->logger.info("peek"+n));
        logger.info("count"+stream.count());
        logger.info("max"+stream.max(String::compareTo));
        logger.info("min"+stream.min(String::compareTo));
        logger.info("first"+stream.findFirst());
        logger.info("any"+stream.parallel().findAny());
        logger.info("anyM"+stream.anyMatch(n->n.charAt(0)=='C'));
        logger.info("allM"+stream.allMatch(n->n.length()==1));
        logger.info("noneM"+stream.noneMatch(n->n.length()>1));
        Optional<String> opt=Optional.of("Str");
        Optional<String> nu=Optional.empty();
        String notnull=nu.orElse("empty");
        String notnull2=nu.orElseGet(()->"isEmpty");
        String nullstr=nu.orElseThrow(RuntimeException::new);
        logger.info(String.valueOf(opt.isPresent()));
//        Executors.newVirtualThreadPerTaskExecutor();
//        Thread.ofVirtual().start();
//        Thread.startVirtualThread();
//        Executors.newThreadPerTaskExecutor(Thread.ofVirtual().factory().newThread());
    }


}
