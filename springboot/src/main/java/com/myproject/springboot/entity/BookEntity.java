package com.myproject.springboot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    @Column(name = "book_name")
    private String bookName;

    @Column(name = "book_src_termux")
    private String bookSrcTermux;

    @Column(name = "book_src_windows")
    private String bookSrcWindows;

    @Column(name = "book_page")
    private String bookPage;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookSrcTermux() {
        return bookSrcTermux;
    }

    public void setBookSrcTermux(String bookSrcTermux) {
        this.bookSrcTermux = bookSrcTermux;
    }

    public String getBookSrcWindows() {
        return bookSrcWindows;
    }

    public void setBookSrcWindows(String bookSrcWindows) {
        this.bookSrcWindows = bookSrcWindows;
    }

    public String getBookPage() {
        return bookPage;
    }

    public void setBookPage(String bookPage) {
        this.bookPage = bookPage;
    }
}
