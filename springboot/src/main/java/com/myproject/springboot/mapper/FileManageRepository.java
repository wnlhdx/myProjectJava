package com.myproject.springboot.mapper;

import com.myproject.springboot.entity.FileManageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
@Repository
public interface FileManageRepository extends ReactiveCrudRepository<FileManageEntity, Long> {
    Flux<FileManageEntity> findAll();
    Mono<FileManageEntity> findByFileName(String fileName);
    Mono<Void> deleteByFileName(String fileName);
}
