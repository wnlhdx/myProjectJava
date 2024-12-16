package com.myproject.springboot.service.impl;

import com.myproject.springboot.entity.PlanEntity;
import com.myproject.springboot.mapper.PlanRepository;
import com.myproject.springboot.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

    @Override
    public PlanEntity getPlansBasedOnTime() {
        // 获取当前时间和星期几
        LocalTime currentTime = LocalTime.now();
        int dayOfWeek = LocalDate.now().getDayOfWeek().getValue();  // 获取当前星期几

        // 白天时间段：06:00 到 22:30
        LocalTime morningStart = LocalTime.of(6, 0);
        LocalTime eveningEnd = LocalTime.of(22, 30);

        // 判断当前时间是否在白天时间段
        boolean isDayTime = currentTime.isAfter(morningStart) && currentTime.isBefore(eveningEnd);

        // 格式化当前时间为字符串，符合数据库中的时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedCurrentTime = currentTime.format(formatter);

        // 根据时间段和当前星期几查询计划
        if (isDayTime) {
            return planMapper.findPlansByTimeAndDayOfWeek(formattedCurrentTime, dayOfWeek);
        } else {
            return planMapper.findPlansByTimeAndDayOfWeek(formattedCurrentTime, dayOfWeek);
        }
    }
}
