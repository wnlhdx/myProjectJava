package com.myproject.springboot.mapper;

import com.myproject.springboot.entity.BookEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface BookRepository extends ReactiveCrudRepository<BookEntity, Long> {
    Mono<BookEntity> findByBookName(String bookName);
    Mono<Void> deleteByBookName(String bookName);
}
