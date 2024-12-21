package com.myproject.springboot.service.impl;

import com.myproject.springboot.entity.BookEntity;
import com.myproject.springboot.entity.PlanEntity;
import com.myproject.springboot.mapper.BookRepository;
import com.myproject.springboot.service.BookService;
import com.myproject.springboot.service.PlanService;
import com.myproject.springboot.tools.OS;
import com.myproject.springboot.tools.TranslateTools;
import com.myproject.springboot.tools.pdfscanner;
import org.springframework.stereotype.Service;


import java.io.IOException;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookMapper;
    private final OS os=new OS();
    private final pdfscanner pdfscanner= new pdfscanner();
    private final PlanService planService;
    private final TranslateTools translateTools=new TranslateTools();

    public BookServiceImpl(BookRepository bookMapper,PlanService planService) {
        this.bookMapper = bookMapper;
        this.planService = planService;
    }

    @Override
    public String getBookNow(){
        return planService.getPlansBasedOnTime().getBookName();
    }


    @Override
    public String getBookRead(String bookName) throws IOException {
        BookEntity book=bookMapper.findByBookName(bookName);
        int page = Integer.parseInt(book.getBookPage());
        String bookSrc=os.osname.equals("termux")?book.getBookSrcTermux():book.getBookSrcWindows();
        String content=pdfscanner.scanPagesByRange(bookSrc,page,page).get(0);
        return  content;
    }

    @Override
    public int getBookPage(String bookName) {
        BookEntity book=bookMapper.findByBookName(bookName);
        return  Integer.parseInt(book.getBookPage());
    }

    @Override
    public byte[] getBooPdfPicture(String bookName) throws IOException {
        BookEntity book=bookMapper.findByBookName(bookName);
        int page = Integer.parseInt(book.getBookPage());
        String bookSrc=os.osname.equals("termux")?book.getBookSrcTermux():book.getBookSrcWindows();
        byte[] image=pdfscanner.scanImageByPage(bookSrc,page);
        return  image;
    }

    @Override
    public String getBooReadNowTranslate(String bookName) throws IOException {
        BookEntity book=bookMapper.findByBookName(bookName);
        int page = Integer.parseInt(book.getBookPage());
        String bookSrc=os.osname.equals("termux")?book.getBookSrcTermux():book.getBookSrcWindows();
        String content=pdfscanner.scanPagesByRange(bookSrc,page,page).get(0);
        String translate= translateTools.translate(content);
        return  translate;
    }

    @Override
    public void addBook(BookEntity book) {
        bookMapper.save(book);
    }

    @Override
    public void updateBook(BookEntity book) {
        bookMapper.save(book);
    }

    @Override
    public void deleteBook(String bookName) {
        bookMapper.deleteByBookName(bookName);
    }

    @Override
    public void addBookPageNow() {
        BookEntity book=bookMapper.findByBookName(getBookNow());
        int page = Integer.parseInt(book.getBookPage())+1;
        book.setBookPage(String.valueOf(page));
        bookMapper.save(book);
    }

    @Override
    public void subBookPageNow() {
        BookEntity book=bookMapper.findByBookName(getBookNow());
        int page = Integer.parseInt(book.getBookPage())-1;
        book.setBookPage(String.valueOf(page));
        bookMapper.save(book);
    }

    @Override
    public BookEntity getBOokEntityNow() {
        BookEntity book=bookMapper.findByBookName(getBookNow());
        return  book;
    }
}
