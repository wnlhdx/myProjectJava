package com.myproject.springboot.mapper;


import com.myproject.springboot.entity.SoftUpdateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface SoftUpdateRepository extends ReactiveCrudRepository<SoftUpdateEntity, Long> {
    Mono<SoftUpdateRepository> findBySoftName(String SoftName);
    Mono<Void> deleteBySoftName(String SoftName);
}
