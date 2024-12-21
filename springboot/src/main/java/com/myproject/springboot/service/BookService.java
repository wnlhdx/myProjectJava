package com.myproject.springboot.service;

import com.myproject.springboot.entity.BookEntity;
import com.myproject.springboot.entity.PlanEntity;

import java.io.IOException;
import java.util.List;

public interface BookService {
    String getBookNow();
    String getBookRead(String bookName) throws IOException;
    int getBookPage(String bookName);
    byte[] getBooPdfPicture(String bookName) throws IOException;
    String getBooReadNowTranslate(String bookName) throws IOException;
    void addBook(BookEntity book);
    void updateBook(BookEntity book);
    void deleteBook(String bookName);
    void addBookPageNow();
    void subBookPageNow();
    BookEntity getBOokEntityNow();
}
