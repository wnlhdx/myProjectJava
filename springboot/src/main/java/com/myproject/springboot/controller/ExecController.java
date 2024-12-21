package com.myproject.springboot.controller;


import com.myproject.springboot.service.ExecService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exec")
public class ExecController {
    private final ExecService execService;


    public ExecController(ExecService execService) {
        this.execService = execService;
    }
}
