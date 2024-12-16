package com.myproject.springboot.service.impl;

import com.myproject.springboot.entity.PlanEntity;
import com.myproject.springboot.mapper.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public class DataSyncService {
    @Autowired
    private PlanRepository writePlanRepository;  // PostgreSQL repository (write)

    @Autowired
    private PlanRepository readPlanRepository;   // SQLite repository (read)

    @Scheduled(fixedRate = 3600000)  // 每小时同步一次
    public void syncData() {
        List<PlanEntity> allPlan = writePlanRepository.findAll();  // 从 PostgreSQL 获取所有数据

        // 将数据同步到 SQLite
        for (PlanEntity plan : allPlan) {
            readPlanRepository.save(plan);  // 将数据保存到 SQLite
        }
    }
}


