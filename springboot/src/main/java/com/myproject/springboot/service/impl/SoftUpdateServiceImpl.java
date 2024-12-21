package com.myproject.springboot.service.impl;

import com.myproject.springboot.mapper.FileManageRepository;
import com.myproject.springboot.mapper.SoftUpdateRepository;
import com.myproject.springboot.service.SoftUpdateService;
import org.springframework.stereotype.Service;

@Service
public class SoftUpdateServiceImpl implements SoftUpdateService {

    private final SoftUpdateRepository softUpdateMap;


    public SoftUpdateServiceImpl(SoftUpdateRepository softUpdateMap) {
        this.softUpdateMap = softUpdateMap;
    }


    @Override
    public SoftUpdateRepository findBySoftName(String SoftName) {
        return null;
    }

    @Override
    public void deleteBySoftName(String SoftName) {

    }
}
