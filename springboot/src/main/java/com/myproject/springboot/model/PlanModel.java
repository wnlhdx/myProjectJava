package com.myproject.springboot.model;

import lombok.Data;

/**
 * @author lkxl
 */
@Data
public class PlanModel {
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
