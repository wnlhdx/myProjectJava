package com.myproject.springboot.service;

import com.myproject.springboot.mapper.SoftUpdateRepository;
import reactor.core.publisher.Mono;

public interface SoftUpdateService {
    Mono<SoftUpdateRepository> findBySoftName(String SoftName);
    Mono<Void> deleteBySoftName(String SoftName);
}
