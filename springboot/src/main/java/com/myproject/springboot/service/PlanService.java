package com.myproject.springboot.service;

import com.myproject.springboot.entity.PlanEntity;
import java.util.List;

/**
 * @author lkxl
 */

public interface PlanService {
    List<PlanEntity> getAll();
    PlanEntity getPlanByName(String planName);
    void addPlan(PlanEntity plan);
    void updatePlan(PlanEntity plan);
    void deletePlan(String planName);

}
