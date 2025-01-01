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
import reactor.core.publisher.Mono;

import java.io.IOException;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final OS os = new OS();
    private final pdfscanner pdfscanner = new pdfscanner();
    private final PlanService planService;
    private final TranslateTools translateTools = new TranslateTools();

    public BookServiceImpl(BookRepository bookRepository, PlanService planService) {
        this.bookRepository = bookRepository;
        this.planService = planService;
    }

    @Override
    public Mono<String> getBookNow() {
        return planService.getPlansBasedOnTime().map(PlanEntity::getBookName);
    }

    @Override
    public Mono<String> getBookRead(String bookName) {
        return bookRepository.findByBookName(bookName)
                .map(book -> {
                    int page = Integer.parseInt(book.getBookPage());
                    String bookSrc = os.osname.equals("termux") ? book.getBookSrcTermux() : book.getBookSrcWindows();
                    try {
                        return pdfscanner.scanPagesByRange(bookSrc, page, page).getFirst();
                    } catch (IOException e) {
                        return null;
                    }
                });
    }

    @Override
    public Mono<Integer> getBookPage(String bookName) {
        return bookRepository.findByBookName(bookName)
                .map(book -> Integer.parseInt(book.getBookPage()));
    }

    @Override
    public Mono<byte[]> getBookPdfPicture(String bookName) {
        return bookRepository.findByBookName(bookName)
                .flatMap(book -> {
                    int page = Integer.parseInt(book.getBookPage());
                    String bookSrc = os.osname.equals("termux") ? book.getBookSrcTermux() : book.getBookSrcWindows();
                    try {
                        return Mono.just(pdfscanner.scanImageByPage(bookSrc, page));
                    } catch (IOException e) {
                        return Mono.empty();
                    }
                });
    }

    @Override
    public Mono<String> getBookReadNowTranslate(String bookName) {
        return bookRepository.findByBookName(bookName)
                .map(book -> {
                    int page = Integer.parseInt(book.getBookPage());
                    String bookSrc = os.osname.equals("termux") ? book.getBookSrcTermux() : book.getBookSrcWindows();
                    try {
                        String content = pdfscanner.scanPagesByRange(bookSrc, page, page).get(0);
                        return translateTools.translate(content);
                    } catch (IOException e) {
                        return null;
                    }
                });
    }

    @Override
    public Mono<Void> addBook(BookEntity book) {
        return bookRepository.save(book).then();
    }

    @Override
    public Mono<Void> updateBook(BookEntity book) {
        return bookRepository.save(book).then();
    }

    @Override
    public Mono<Void> deleteBook(String bookName) {
        return bookRepository.findByBookName(bookName)
                .flatMap(bookRepository::delete).then();
    }

    @Override
    public Mono<Void> addBookPageNow() {
        return bookRepository.findByBookName(getBookNow().block())
                .flatMap(book -> {
                    int page = Integer.parseInt(book.getBookPage()) + 1;
                    book.setBookPage(String.valueOf(page));
                    return bookRepository.save(book).then();
                });
    }

    @Override
    public Mono<Void> subBookPageNow() {
        return bookRepository.findByBookName(getBookNow().block())
                .flatMap(book -> {
                    int page = Integer.parseInt(book.getBookPage()) - 1;
                    book.setBookPage(String.valueOf(page));
                    return bookRepository.save(book).then();
                });
    }

    @Override
    public Mono<BookEntity> getBookEntityNow() {
        return bookRepository.findByBookName(getBookNow().block());
    }
}
