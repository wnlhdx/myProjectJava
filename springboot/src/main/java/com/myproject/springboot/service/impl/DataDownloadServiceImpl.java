package com.myproject.springboot.service.impl;

import com.myproject.springboot.entity.DataDownloadEntity;
import com.myproject.springboot.mapper.BookRepository;
import com.myproject.springboot.mapper.DataDownloadRepository;
import com.myproject.springboot.service.DataDownloadService;
import org.springframework.stereotype.Service;

@Service
public class DataDownloadServiceImpl implements DataDownloadService {

    private final DataDownloadRepository dataDownloadMap;

    public DataDownloadServiceImpl(DataDownloadRepository dataDownloadMap) {
        this.dataDownloadMap = dataDownloadMap;
    }

    @Override
    public DataDownloadEntity findByDataName(String dataName) {
        return null;
    }

    @Override
    public void deleteByDataName(String dataName) {

    }

    @Override
    public void DownloadData(String url, String downloadPath) {

    }

    @Override
    public void SaveDatatoDataBase(String url) {

    }
}
