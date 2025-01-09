package com.myproject.springboot.mapper;

import com.myproject.springboot.entity.DataDownloadEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface DataDownloadRepository extends ReactiveCrudRepository<DataDownloadEntity, Long> {
    Mono<DataDownloadEntity> findByDataName(String dataName);
    Mono<Void> deleteByDataName(String dataName);
}
