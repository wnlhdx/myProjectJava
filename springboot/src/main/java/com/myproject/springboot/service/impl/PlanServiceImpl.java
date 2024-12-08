package com.myproject.springboot.service.impl;

import com.myproject.springboot.entity.PlanEntity;
import com.myproject.springboot.mapper.PlanRepository;
import com.myproject.springboot.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lkxl
 */
@Service
public class PlanServiceImpl implements PlanService {
    private final PlanRepository planMapper;

    @Autowired
    public PlanServiceImpl(PlanRepository planMapper) {
        this.planMapper = planMapper;
    }

    @Override
    public List<PlanEntity> getAll() {
        return planMapper.findAll();
    }

    @Override
    public PlanEntity getPlanByName(String planName) {
        return planMapper.findByPlanName(planName);
    }

    @Override
    public void addPlan(PlanEntity plan) {
        planMapper.save(plan);
    }

    @Override
    public void updatePlan(PlanEntity plan) {
        planMapper.save(plan);
    }

    @Override
    public void deletePlan(String planName) {
        planMapper.deleteByPlanName(planName);
    }
}
