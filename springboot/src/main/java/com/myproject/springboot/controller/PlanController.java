package com.myproject.springboot.controller;

import com.myproject.springboot.entity.PlanEntity;
import com.myproject.springboot.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

//@Tag(name = "JsonController tags")
@RestController
@RequestMapping("/plans")
public class PlanController {

    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @GetMapping("/all")
  //  @Operation(summary = "获取全部计划", description = "获取全部计划")
    public ResponseEntity<List<PlanEntity>> getAllPlans() {
        return ResponseEntity.ok(planService.getAll());
    }

    @GetMapping("/{planName}")
   // @Operation(summary = "获取计划", description = "获取计划")
    public ResponseEntity<PlanEntity> getPlanByName(@PathVariable String planName) {
        PlanEntity plan = planService.getPlanByName(planName);
        if (plan == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(plan);
    }

    @GetMapping("/getPlanNow")
    public ResponseEntity<PlanEntity> getPlanNow() {
        PlanEntity plan = planService.getPlansBasedOnTime();
        if (plan == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(plan);
    }


    @PostMapping("/add")
  //  @Operation(summary = "添加计划", description = "添加计划")
    public ResponseEntity<String> addPlan(@RequestBody PlanEntity plan) {
        planService.addPlan(plan);
        return ResponseEntity.ok("Plan added successfully");
    }

    @PutMapping("/update")
 //   @Operation(summary = "修改计划", description = "修改计划")
    public ResponseEntity<String> updatePlan(@RequestBody PlanEntity plan) {
        planService.updatePlan(plan);
        return ResponseEntity.ok("Plan updated successfully");
    }

    @DeleteMapping("/del/{planName}")
//    @Operation(summary = "删除计划", description = "删除计划")
    public ResponseEntity<String> deletePlan(@PathVariable String planName) {
        planService.deletePlan(planName);
        return ResponseEntity.ok("Plan deleted successfully");
    }
}