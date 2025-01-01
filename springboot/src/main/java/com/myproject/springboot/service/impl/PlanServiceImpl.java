package com.myproject.springboot.service.impl;

import com.myproject.springboot.entity.PlanEntity;
import com.myproject.springboot.mapper.PlanRepository;
import com.myproject.springboot.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {
    private final PlanRepository planRepository;

    @Autowired
    public PlanServiceImpl(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Override
    public Flux<PlanEntity> getAll() {
        return planRepository.findAll();  // 返回 Flux，这样可以异步获取所有计划
    }

    @Override
    public Mono<PlanEntity> getPlanByName(String planName) {
        return planRepository.findByPlanName(planName);  // 返回 Mono，因为只返回一个 PlanEntity
    }

    @Override
    public Mono<Void> addPlan(PlanEntity plan) {
        return planRepository.save(plan).then();  // 保存后不返回数据，只返回 Void
    }

    @Override
    public Mono<Void> updatePlan(PlanEntity plan) {
        return planRepository.save(plan).then();  // 保存后不返回数据，只返回 Void
    }

    @Override
    public Mono<Void> deletePlan(String planName) {
        return planRepository.deleteByPlanName(planName);  // 删除计划
    }

    @Override
    public Mono<PlanEntity> getPlansBasedOnTime() {
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
            return planRepository.findPlansByTimeAndDayOfWeek(formattedCurrentTime, dayOfWeek);  // 返回Mono<PlanEntity>
        } else {
            return planRepository.findPlansByTimeAndDayOfWeek(formattedCurrentTime, dayOfWeek);  // 返回Mono<PlanEntity>
        }
    }
}
