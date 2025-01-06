package com.myproject.dataprocessing.service.impl;

import com.myproject.dataprocessing.repository.DataRepository;
import com.myproject.dataprocessing.service.DataProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DataProcessingServiceImpl implements DataProcessingService {

    private final DataRepository dataRepository;

    @Autowired
    public DataProcessingServiceImpl(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public Flux<String> processAllData() {
        return dataRepository.findAll().map(data -> "Processed: " + data.getValue());
    }

    @Override
    public Mono<String> processData(Long id) {
        return dataRepository.findById(id).map(data -> "Processed: " + data.getValue());
    }
}
