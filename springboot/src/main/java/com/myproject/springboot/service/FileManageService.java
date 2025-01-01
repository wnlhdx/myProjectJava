package com.myproject.springboot.service;

import com.myproject.springboot.entity.FileManageEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface FileManageService {
    Flux<FileManageEntity> findAll();
    Mono<FileManageEntity> findByFileName(String fileName);
    Mono<Void> deleteByFileName(String fileName);
}
