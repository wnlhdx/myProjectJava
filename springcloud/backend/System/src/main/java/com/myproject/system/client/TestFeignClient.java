package com.myproject.system.client;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author lkxl
 */

@FeignClient
public interface TestFeignClient {
    public String test(String str);
}
