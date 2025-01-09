package com.myproject.springboot.mapper;

import com.myproject.springboot.entity.ExecEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface ExecRepository extends ReactiveCrudRepository<ExecEntity, Long> {
    Flux<ExecEntity> findAll();
    Mono<ExecEntity> findByExecName(String execName);
    Mono<Void> deleteByExecName(String execName);
}
