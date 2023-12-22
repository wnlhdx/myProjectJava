package com.myproject.springboot.service;

import com.myproject.springboot.entity.PlanEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lkxl
 */
@Service
public interface PlanService {
    List<PlanEntity> getAll();
    PlanEntity getPlanByName(String planName);
    void addPlan(PlanEntity plan);
    void updatePlan(PlanEntity plan);
    void deletePlan(String planName);

}
