package com.myproject.springboot.service;

import com.myproject.springboot.entity.FileManageEntity;

import java.util.List;

public interface FileManageService {
    List<FileManageEntity> findAll();
    FileManageEntity findByFileName(String fileName);
    void deleteByFileName(String fileName);
}
