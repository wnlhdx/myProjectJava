package com.myproject.springboot.controller;

import com.myproject.springboot.service.BookService;
import com.myproject.springboot.service.SoftUpdateService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/softupdate")
public class SoftUpdateController {
    private final SoftUpdateService softUpdateService;

    public SoftUpdateController(SoftUpdateService softUpdateService) {
        this.softUpdateService = softUpdateService;
    }
}
