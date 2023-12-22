package com.myproject.springboot.service.impl;

import com.myproject.springboot.entity.PlanEntity;
import com.myproject.springboot.mapper.PlanMapper;
import com.myproject.springboot.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lkxl
 */
@Service
public class PlanServiceImpl implements PlanService {
    private final PlanMapper planMapper;

    @Autowired
    public PlanServiceImpl(PlanMapper planMapper) {
        this.planMapper = planMapper;
    }

    @Override
    public List<PlanEntity> getAll() {
        return planMapper.getAll();
    }

    @Override
    public PlanEntity getPlanByName(String planName) {
        return planMapper.getPlanByName(planName);
    }

    @Override
    public void addPlan(PlanEntity plan) {
        planMapper.insertPlan(plan);
    }

    @Override
    public void updatePlan(PlanEntity plan) {
        planMapper.updatePlan(plan);
    }

    @Override
    public void deletePlan(String planName) {
        planMapper.deletePlan(planName);
    }
}
