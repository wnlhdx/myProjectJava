package com.myproject.springboot.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author lkxl
 */
@Data
@Component
//@Schema(name = "Plan", description = "计划实体类")

public class PlanEntity {
    private String planName;
    private String startTime;
    private String endTime;
    private String dayOfWeek;
    private String startTimeNight;
    private String endTimeNight;
    private String functionFor;
    private String book;
    private String pages;
    private String rest;
}
