package com.myproject.batch.jobs;

import org.springframework.stereotype.Component;

@Component
public class BatchJob {
    public void execute() {
        // 批处理任务逻辑
        System.out.println("Executing batch job...");
        // 数据清洗、聚合等逻辑实现
    }
}
