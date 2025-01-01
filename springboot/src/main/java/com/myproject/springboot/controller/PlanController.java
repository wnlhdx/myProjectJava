package com.myproject.springboot.controller;

import com.myproject.springboot.entity.PlanEntity;
import com.myproject.springboot.service.PlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/plans")
public class PlanController {

    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @GetMapping("/all")
    public Flux<PlanEntity> getAllPlans() {
        return planService.getAll();  // 返回 Flux 类型，表示多个元素的响应
    }

    @GetMapping("/{planName}")
    public Mono<ResponseEntity<PlanEntity>> getPlanByName(@PathVariable String planName) {
        return  planService.getPlanByName(planName).map(ResponseEntity::ok);
    }

    @GetMapping("/getPlanNow")
    public Mono<ResponseEntity<PlanEntity>> getPlanNow() {
        return planService.getPlansBasedOnTime().map(ResponseEntity::ok);
    }

    @PostMapping("/add")
    public Mono<ResponseEntity<String>> addPlan(@RequestBody PlanEntity plan) {
        return Mono.fromRunnable(() -> planService.addPlan(plan))  // 异步执行添加计划
                .thenReturn(ResponseEntity.ok("Plan added successfully"));
    }

    @PutMapping("/update")
    public Mono<ResponseEntity<String>> updatePlan(@RequestBody PlanEntity plan) {
        return Mono.fromRunnable(() -> planService.updatePlan(plan))  // 异步执行更新计划
                .thenReturn(ResponseEntity.ok("Plan updated successfully"));
    }

    @DeleteMapping("/del/{planName}")
    public Mono<ResponseEntity<String>> deletePlan(@PathVariable String planName) {
        return Mono.fromRunnable(() -> planService.deletePlan(planName))  // 异步执行删除计划
                .thenReturn(ResponseEntity.ok("Plan deleted successfully"));
    }
}
