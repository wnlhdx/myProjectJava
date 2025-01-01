package com.myproject.springboot.service;

import com.myproject.springboot.entity.DataDownloadEntity;
import reactor.core.publisher.Mono;

public interface DataDownloadService {
    Mono<DataDownloadEntity> findByDataName(String dataName);
    void deleteByDataName(String dataName);
    void DownloadData(String url,String downloadPath);
    void SaveDatatoDataBase(String url);
}
