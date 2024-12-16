package com.myproject.javase.coreJavaTest;

import org.junit.jupiter.api.Assertions;
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
    @Test
    public void testFilterAndCount() {
        List<String> arraylist = List.of("test1", "test2", "te");
        long count = arraylist.parallelStream().filter(w -> w.length() > 3).count();
        Assertions.assertEquals(2, count);
    }

    @Test
    public void testMapAndForEach() {
        List<String> arraylist = List.of("a", "b", "c", "d");
        List<String> upperCaseList = arraylist.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        Assertions.assertEquals(List.of("A", "B", "C", "D"), upperCaseList);
    }

    @Test
    public void testLimitAndSkip() {
        List<String> arraylist = List.of("a", "b", "c", "d");
        List<String> limitedSkippedList = arraylist.stream()
                .skip(1)
                .limit(2)
                .collect(Collectors.toList());
        Assertions.assertEquals(List.of("b", "c"), limitedSkippedList);
    }

    @Test
    public void testTakeWhileAndDropWhile() {
        List<String> arraylist = List.of("a", "b", "c", "d");
        List<String> takenList = arraylist.stream()
                .takeWhile(n -> n.charAt(0) < 'c')
                .collect(Collectors.toList());
        List<String> droppedList = arraylist.stream()
                .dropWhile(n -> n.charAt(0) < 'c')
                .collect(Collectors.toList());
        Assertions.assertEquals(List.of("a", "b"), takenList);
        Assertions.assertEquals(List.of("c", "d"), droppedList);
    }

    @Test
    public void testDistinctAndSorted() {
        List<String> arraylist = List.of("d", "c", "b", "c");
        List<String> distinctSortedList = arraylist.stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        Assertions.assertEquals(List.of("b", "c", "d"), distinctSortedList);
    }

    @Test
    public void testPeek() {
        List<String> arraylist = List.of("a", "b", "c", "d");
        // 这里只是演示peek的使用，实际上不应该在测试中打印日志
        arraylist.stream().peek(System.out::println).collect(Collectors.toList());
    }

    @Test
    public void testMinMaxAndFind() {
        List<String> arraylist = List.of("d", "c", "b", "a");
        Optional<String> max = arraylist.stream().max(String::compareTo);
        Optional<String> min = arraylist.stream().min(String::compareTo);
        Optional<String> first = arraylist.stream().findFirst();
        Optional<String> any = arraylist.parallelStream().findAny();
        Assertions.assertEquals("d", max.get());
        Assertions.assertEquals("a", min.get());
        Assertions.assertEquals("d", first.get());
        Assertions.assertTrue(arraylist.contains(any.get()));
    }

    @Test
    public void testMatch() {
        List<String> arraylist = List.of("a", "b", "c", "d");
        boolean anyMatch = arraylist.stream().anyMatch(n -> n.charAt(0) == 'c');
        boolean allMatch = arraylist.stream().allMatch(n -> n.length() == 1);
        boolean noneMatch = arraylist.stream().noneMatch(n -> n.length() > 1);
        Assertions.assertTrue(anyMatch);
        Assertions.assertTrue(allMatch);
        Assertions.assertTrue(noneMatch);
    }

    @Test
    public void testOptional() {
        Optional<String> opt = Optional.of("Str");
        Optional<String> nu = Optional.empty();
        String notNull = nu.orElse("empty");
        String notNull2 = nu.orElseGet(() -> "isEmpty");
        Assertions.assertThrows(RuntimeException.class, () -> nu.orElseThrow(RuntimeException::new));
        Assertions.assertTrue(opt.isPresent());
        Assertions.assertFalse(nu.isPresent());
        Assertions.assertEquals("STR", opt.map(String::toUpperCase).get());
        Assertions.assertEquals("empty", notNull);
        Assertions.assertEquals("isEmpty", notNull2);
    }



}
