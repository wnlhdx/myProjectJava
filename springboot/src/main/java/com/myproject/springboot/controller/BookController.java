package com.myproject.springboot.controller;

import com.myproject.springboot.entity.BookEntity;
import com.myproject.springboot.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;


    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/translate")
    // @Operation(summary = "获取书籍某页翻译", description = "获取书籍某页翻译")
    public ResponseEntity<String> getBookTranslate() throws IOException {
        return ResponseEntity.ok( bookService.getBooReadNowTranslate(bookService.getBookNow()));
    }

    @GetMapping("/book")
    // @Operation(summary = "获取书籍某页", description = "获取书籍某页")
    public ResponseEntity<String> getBookNow() throws IOException {
        return ResponseEntity.ok( bookService.getBookRead(bookService.getBookNow()));
    }


    @GetMapping("/page")
    //  @Operation(summary = "获取当前页数", description = "获取当前页数")
    public ResponseEntity<Integer> getBookPage() {
        return ResponseEntity.ok( bookService.getBookPage(bookService.getBookNow()));
    }

    @GetMapping("/bookname")
    //  @Operation(summary = "获取当前书名", description = "获取当前书名")
    public ResponseEntity<String> getBookName() {
        return ResponseEntity.ok(bookService.getBookNow());
    }



    @GetMapping("/pic")
    //  @Operation(summary = "获取当前页图片", description = "获取当前页图片")
    public ResponseEntity<byte[]> getBookPic() throws IOException {
        return ResponseEntity.ok( bookService.getBooPdfPicture(bookService.getBookNow()));
    }

    @PostMapping("/add")
    //  @Operation(summary = "增加新书", description = "增加新书")
    public ResponseEntity<String> addBook(@RequestBody BookEntity book) {
        bookService.addBook(book);
        return ResponseEntity.ok("Book added successfully");
    }

    @PutMapping("/update")
    //   @Operation(summary = "修改书籍", description = "修改书籍")
    public ResponseEntity<String> updateBook(@RequestBody BookEntity book) {
        bookService.updateBook(book);
        return ResponseEntity.ok("Book updated successfully");
    }


    @PutMapping("/addpage")
    //   @Operation(summary = "下一页", description = "下一页")
    public ResponseEntity<String> addPage() {
        bookService.addBookPageNow();
        return ResponseEntity.ok("BookPage add successfully");
    }

    @PutMapping("/subpage")
    //   @Operation(summary = "上一页", description = "上一页")
    public ResponseEntity<String> subPage() {
        bookService.subBookPageNow();
        return ResponseEntity.ok("BookPage sub successfully");
    }

    @PutMapping("/booknow")
    //   @Operation(summary = "当前书籍字段查询", description = "当前书籍字段查询")
    public ResponseEntity<BookEntity> updatePlan() {

        return ResponseEntity.ok(bookService.getBOokEntityNow());
    }


    @DeleteMapping("/del/{bookName}")
//    @Operation(summary = "删除计划", description = "删除计划")
    public ResponseEntity<String> deletBook(@RequestBody String bookName) {
        bookService.deleteBook(bookName);
        return ResponseEntity.ok("Book deleted successfully");
    }
}
