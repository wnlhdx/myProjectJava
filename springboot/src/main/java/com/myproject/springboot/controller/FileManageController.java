package com.myproject.springboot.controller;

import com.myproject.springboot.service.BookService;
import com.myproject.springboot.service.FileManageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filemanager")
public class FileManageController {
    private final FileManageService fileManageService;

    public FileManageController(FileManageService fileManageService) {
        this.fileManageService = fileManageService;
    }
}
