package com.myproject.springboot.service.impl;

import com.myproject.springboot.entity.FileManageEntity;
import com.myproject.springboot.mapper.DataDownloadRepository;
import com.myproject.springboot.mapper.FileManageRepository;
import com.myproject.springboot.service.FileManageService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FileManageServiceImpl implements FileManageService {

    private final FileManageRepository fileManageloadMap;

    public FileManageServiceImpl(FileManageRepository fileManageloadMap) {
        this.fileManageloadMap = fileManageloadMap;
    }

    @Override
    public List<FileManageEntity> findAll() {
        return List.of();
    }

    @Override
    public FileManageEntity findByFileName(String fileName) {
        return null;
    }

    @Override
    public void deleteByFileName(String fileName) {

    }
}
