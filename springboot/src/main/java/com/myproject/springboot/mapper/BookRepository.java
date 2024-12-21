package com.myproject.springboot.mapper;

import com.myproject.springboot.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    BookEntity findByBookName(String bookName);
    void deleteByBookName(String bookName);
}
