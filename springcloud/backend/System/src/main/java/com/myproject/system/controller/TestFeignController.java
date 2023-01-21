package com.myproject.system.controller;

import com.myproject.system.client.TestFeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

/**
 * @author lkxl
 */

@RestController
@RequestMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
public class TestFeignController {
      @Resource
      private TestFeignClient testFeign;


      @GetMapping(value = "/test")
      String test(@RequestParam(value = "q") String q) {
          return testFeign.test(q);
      }
}
