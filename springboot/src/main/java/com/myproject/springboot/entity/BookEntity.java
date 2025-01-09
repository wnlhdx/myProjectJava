package com.myproject.springboot.entity;

//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(name = "books")

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("books")
public class BookEntity {
    @Id
    @Column("book_name")
    private String bookName;

    @Column("book_src_termux")
    private String bookSrcTermux;

    @Column("book_src_windows")
    private String bookSrcWindows;

    @Column( "book_page")
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
