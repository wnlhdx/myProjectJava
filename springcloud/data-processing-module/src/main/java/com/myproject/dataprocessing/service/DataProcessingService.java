package com.myproject.dataprocessing.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DataProcessingService {
    Flux<String> processAllData();
    Mono<String> processData(Long id);
}
