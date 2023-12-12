package com.myproject.springboot.service;

import com.myproject.springboot.entity.PlanEntity;

/**
 * @author lkxl
 */
public interface PlanService {
    PlanEntity getPlanByName(String planName);
    void addPlan(PlanEntity plan);
    void updatePlan(PlanEntity plan);
    void deletePlan(String planName);

}
