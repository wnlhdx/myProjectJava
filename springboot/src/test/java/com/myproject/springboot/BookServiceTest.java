package com.myproject.springboot;

import com.myproject.springboot.entity.BookEntity;
import com.myproject.springboot.entity.PlanEntity;
import com.myproject.springboot.mapper.BookRepository;
import com.myproject.springboot.mapper.PlanRepository;
import com.myproject.springboot.service.BookService;
import com.myproject.springboot.service.PlanService;
import com.myproject.springboot.service.impl.BookServiceImpl;
import com.myproject.springboot.service.impl.PlanServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    private BookService bookService;
    private PlanService planService;
    private BookRepository bookRepository;
    private PlanRepository planRepository;

    @BeforeEach
    public void setUp() {
        // 初始化mock对象
        bookRepository = mock(BookRepository.class);
        planRepository = mock(PlanRepository.class);
        planService = new PlanServiceImpl(planRepository);
        bookService = new BookServiceImpl(bookRepository, planService);
    }

    @Test
    public void testAddBook() {
        // 创建一个BookEntity对象
        BookEntity book = new BookEntity();
        book.setBookName("Test Book");
        book.setBookPage("1");

        // 模拟repository的save方法
        when(bookRepository.save(book)).thenReturn(Mono.just(book));

        // 调用addBook方法
        Mono<Void> result = bookService.addBook(book);

        // 验证是否保存成功
        verify(bookRepository, times(1)).save(book);
        assertDoesNotThrow(() -> result.block());
    }

    @Test
    public void testGetBookNow() {
        // 创建一个PlanEntity对象
        PlanEntity plan = new PlanEntity();
        plan.setBookName("Test Book");

        // 模拟planService的getPlansBasedOnTime方法
        when(planService.getPlansBasedOnTime()).thenReturn(Mono.just(plan));

        // 调用getBookNow方法
        Mono<String> bookName = bookService.getBookNow();

        // 验证返回的书名
        assertEquals("Test Book", bookName.block());
    }

    @Test
    public void testDeleteBook() {
        // 创建一个BookEntity对象
        BookEntity book = new BookEntity();
        book.setBookName("Test Book");

        // 模拟repository的findByBookName方法
        when(bookRepository.findByBookName("Test Book")).thenReturn(Mono.just(book));
        when(bookRepository.delete(book)).thenReturn(Mono.empty());

        // 调用deleteBook方法
        Mono<Void> result = bookService.deleteBook("Test Book");

        // 验证是否调用删除方法
        verify(bookRepository, times(1)).delete(book);
        assertDoesNotThrow(() -> result.block());
    }

    @Test
    public void testGetPlansBasedOnTime() {
        // 创建一个PlanEntity对象
        PlanEntity plan = new PlanEntity();
        plan.setPlanName("Morning Plan");

        // 模拟repository的findPlansByTimeAndDayOfWeek方法
        when(planRepository.findPlansByTimeAndDayOfWeek(anyString(), anyInt())).thenReturn(Mono.just(plan));

        // 调用getPlansBasedOnTime方法
        Mono<PlanEntity> result = planService.getPlansBasedOnTime();

        // 验证返回的计划
        assertEquals("Morning Plan", Objects.requireNonNull(result.block()).getPlanName());
    }

    // --- 第二步：基于离散数学中的函数概念的扩展测试 ---

    @Test
    public void testGetBookReadReturnsCorrectPage() throws IOException {
        // 创建一个BookEntity对象
        BookEntity book = new BookEntity();
        book.setBookName("Test Book");
        book.setBookPage("5");

        // 模拟repository的findByBookName方法
        when(bookRepository.findByBookName("Test Book")).thenReturn(Mono.just(book));

        // 调用getBookRead方法
        Mono<String> bookContent = bookService.getBookRead("Test Book");

        // 验证返回的内容，检查页面是否正确
        bookContent.subscribe(content -> {
            assertNotNull(content);
            assertTrue(content.contains("Test Book"));
            assertTrue(content.contains("5"));
        });
    }

    @Test
    public void testAddBookPageIncreasesPageCorrectly() {
        // 创建一个BookEntity对象
        BookEntity book = new BookEntity();
        book.setBookName("Test Book");
        book.setBookPage("3");

        // 模拟repository的findByBookName方法
        when(bookRepository.findByBookName("Test Book")).thenReturn(Mono.just(book));
        when(bookRepository.save(book)).thenReturn(Mono.just(book));

        // 调用addBookPageNow方法
        Mono<Void> result = bookService.addBookPageNow();

        // 验证页面是否递增
        result.block(); // Synchronously wait for the result
        assertEquals("4", book.getBookPage());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void testSubBookPageDecreasesPageCorrectly() {
        // 创建一个BookEntity对象
        BookEntity book = new BookEntity();
        book.setBookName("Test Book");
        book.setBookPage("5");

        // 模拟repository的findByBookName方法
        when(bookRepository.findByBookName("Test Book")).thenReturn(Mono.just(book));
        when(bookRepository.save(book)).thenReturn(Mono.just(book));

        // 调用subBookPageNow方法
        Mono<Void> result = bookService.subBookPageNow();

        // 验证页面是否递减
        result.block(); // Synchronously wait for the result
        assertEquals("4", book.getBookPage());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void testBookNotFoundReturnsEmpty() throws IOException {
        // 模拟repository的findByBookName方法，当书籍不存在时返回空
        when(bookRepository.findByBookName("Nonexistent Book")).thenReturn(Mono.empty());

        // 调用getBookRead方法
        Mono<String> bookContent = bookService.getBookRead("Nonexistent Book");

        // 验证返回值是否为空
        bookContent.subscribe(Assertions::assertNull);
    }

    @Test
    public void testBookAndPlanRelationship() {
        // 创建BookEntity对象
        BookEntity book = new BookEntity();
        book.setBookName("Java Basics");
        book.setBookPage("10");

        // 创建PlanEntity对象，指定书籍名称
        PlanEntity plan = new PlanEntity();
        plan.setPlanName("Java Learning Plan");
        plan.setBookName("Java Basics");

        // 模拟planService的getPlanByName方法
        when(planService.getPlanByName("Java Learning Plan")).thenReturn(Mono.just(plan));
        when(bookRepository.findByBookName("Java Basics")).thenReturn(Mono.just(book));

        // 调用getBookNow方法
        Mono<String> bookName = bookService.getBookNow();

        // 验证是否返回与计划相关的书籍
        assertEquals("Java Basics", bookName.block());
    }

}
