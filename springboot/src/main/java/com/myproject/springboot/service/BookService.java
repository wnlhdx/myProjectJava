package com.myproject.springboot.service;

import com.myproject.springboot.entity.BookEntity;
import com.myproject.springboot.entity.PlanEntity;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

public interface BookService {
    Mono<String> getBookNow();
    Mono<String> getBookRead(String bookName) throws IOException;
    Mono<Integer> getBookPage(String bookName);
    Mono<byte[]> getBookPdfPicture(String bookName) throws IOException;
    Mono<String> getBookReadNowTranslate(String bookName) throws IOException;
    Mono<Void> addBook(BookEntity book);
    Mono<Void> updateBook(BookEntity book);
    Mono<Void> deleteBook(String bookName);
    Mono<Void> addBookPageNow();
    Mono<Void> subBookPageNow();
    Mono<BookEntity> getBookEntityNow();
}
