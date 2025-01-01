package com.myproject.springboot.controller;

import com.myproject.springboot.entity.BookEntity;
import com.myproject.springboot.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;


@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/translate")
    public Mono<ResponseEntity<String>> getBookTranslate() {
        return bookService.getBookNow() // 假设这个方法返回 Mono<String>
                .flatMap(bookName -> {
                    try {
                        return bookService.getBookReadNowTranslate(bookName);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })  // 使用 bookName 获取翻译结果
                .map(ResponseEntity::ok);  // 包装翻译结果并返回 ResponseEntity
    }

    @GetMapping("/book")
    public Mono<ResponseEntity<String>> getBookNow() {
        return bookService.getBookNow() // 假设这个方法返回 Mono<String>
                .flatMap(bookName -> {
                    try {
                        return bookService.getBookRead(bookName);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })  // 使用 bookName 获取翻译结果
                .map(ResponseEntity::ok);
    }

    @GetMapping("/page")
    public Mono<ResponseEntity<Integer>> getBookPage() {
        return bookService.getBookNow() // 假设这个方法返回 Mono<String>
                .flatMap(bookService::getBookPage)  // 使用 bookName 获取翻译结果
                .map(ResponseEntity::ok);
    }

    @GetMapping("/bookname")
    public Mono<ResponseEntity<String>> getBookName() {
        return bookService.getBookNow().map(ResponseEntity::ok);
    }

    @GetMapping("/pic")
    public Mono<ResponseEntity<byte[]>> getBookPic() {
        return bookService.getBookNow() // 假设这个方法返回 Mono<String>
                .flatMap(bookName -> {
                    try {
                        return bookService.getBookPdfPicture(bookName);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })  // 使用 bookName 获取翻译结果
                .map(ResponseEntity::ok);
    }

    @PostMapping("/add")
    public Mono<ResponseEntity<String>> addBook(@RequestBody BookEntity book) {
        return Mono.fromRunnable(() -> bookService.addBook(book))
                .thenReturn(ResponseEntity.ok("Book added successfully"));
    }

    @PutMapping("/update")
    public Mono<ResponseEntity<String>> updateBook(@RequestBody BookEntity book) {
        return Mono.fromRunnable(() -> bookService.updateBook(book))
                .thenReturn(ResponseEntity.ok("Book updated successfully"));
    }

    @PutMapping("/addpage")
    public Mono<ResponseEntity<String>> addPage() {
        return Mono.fromRunnable(bookService::addBookPageNow)
                .thenReturn(ResponseEntity.ok("BookPage add successfully"));
    }

    @PutMapping("/subpage")
    public Mono<ResponseEntity<String>> subPage() {
        return Mono.fromRunnable(bookService::subBookPageNow)
                .thenReturn(ResponseEntity.ok("BookPage sub successfully"));
    }

    @PutMapping("/booknow")
    public Mono<ResponseEntity<BookEntity>> updatePlan() {
        return bookService.getBookEntityNow()
                .map(ResponseEntity::ok);  // 将返回值包装成 ResponseEntity<BookEntity>
    }

    @DeleteMapping("/del/{bookName}")
    public Mono<ResponseEntity<String>> deletBook(@PathVariable String bookName) {
        return Mono.fromRunnable(() -> bookService.deleteBook(bookName))
                .thenReturn(ResponseEntity.ok("Book deleted successfully"));
    }
}
