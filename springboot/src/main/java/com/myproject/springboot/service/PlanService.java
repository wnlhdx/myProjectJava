package com.myproject.springboot.service;

import com.myproject.springboot.entity.PlanEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author lkxl
 */

public interface PlanService {
    Flux<PlanEntity> getAll();
    Mono<PlanEntity> getPlanByName(String planName);
    Mono<PlanEntity> getPlansBasedOnTime();
    Mono<Void> addPlan(PlanEntity plan);
    Mono<Void> updatePlan(PlanEntity plan);
    Mono<Void> deletePlan(String planName);

}
