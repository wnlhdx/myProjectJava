package com.myproject.springboot.service.impl;

import com.myproject.springboot.entity.FileManageEntity;
import com.myproject.springboot.mapper.DataDownloadRepository;
import com.myproject.springboot.mapper.FileManageRepository;
import com.myproject.springboot.service.FileManageService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@Service
public class FileManageServiceImpl implements FileManageService {

    private final FileManageRepository fileManageloadMap;

    public FileManageServiceImpl(FileManageRepository fileManageloadMap) {
        this.fileManageloadMap = fileManageloadMap;
    }

    @Override
    public Flux<FileManageEntity> findAll() {
        return null;
    }

    @Override
    public Mono<FileManageEntity> findByFileName(String fileName) {
        return null;
    }

    @Override
    public Mono<Void> deleteByFileName(String fileName) {
            return null;
    }
}
