package com.myproject.springboot.entity;

/**
 * @author lkxl
 */
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

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getStartTimeNight() {
        return startTimeNight;
    }

    public void setStartTimeNight(String startTimeNight) {
        this.startTimeNight = startTimeNight;
    }

    public String getEndTimeNight() {
        return endTimeNight;
    }

    public void setEndTimeNight(String endTimeNight) {
        this.endTimeNight = endTimeNight;
    }

    public String getFunctionFor() {
        return functionFor;
    }

    public void setFunctionFor(String functionFor) {
        this.functionFor = functionFor;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getRest() {
        return rest;
    }

    public void setRest(String rest) {
        this.rest = rest;
    }
}
