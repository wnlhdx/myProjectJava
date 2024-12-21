package com.myproject.springboot.controller;

import com.myproject.springboot.service.DataDownloadService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/datadownload")
public class DataDownloadController {
    private final DataDownloadService dataDownloadService;

    public DataDownloadController(DataDownloadService dataDownloadService) {
        this.dataDownloadService = dataDownloadService;
    }
}
