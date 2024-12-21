package com.myproject.springboot.service;

import com.myproject.springboot.mapper.SoftUpdateRepository;

public interface SoftUpdateService {
    SoftUpdateRepository findBySoftName(String SoftName);
    void deleteBySoftName(String SoftName);
}
