package com.myproject.springboot.entity;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "plans")
public class PlanEntity {

    @Id
    @Column(name = "plan_name")
    private String planName;

    @Column(name = "time_start")
    private String timeStart;

    @Column(name = "time_end")
    private String timeEnd;

    @Column(name = "plan_details")
    private String planDetails;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "project_details")
    private String projectDetails;

    @Column(name = "night_time_start")
    private String nightTimeStart;

    @Column(name = "night_time_end")
    private String nightTimeEnd;

    @Column(name = "project_finish_percent")
    private String projectFinishPercent;

    @Column(name = "day_of_week")
    private Integer dayOfWeek;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "book_content")
    private String bookContent;

    @Column(name = "major_in")
    private String majorIn;

    @Column(name = "project_month")
    private String projectMonth;

    @Column(name = "project_year")
    private String projectYear;

    @Column(name = "relax_item")
    private String relaxItem;

    @Column(name = "relax_item_foregin")
    private String relaxItemForeign;

    @Column(name = "type_of_learn")
    private String typeOfLearn;

    @Column(name = "type_detail")
    private String typeDetail;

    @Column(name = "standard_learn")
    private String standardLearn;

    @Column(name = "update_time")
    private String updateTime;

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getPlanDetails() {
        return planDetails;
    }

    public void setPlanDetails(String planDetails) {
        this.planDetails = planDetails;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDetails() {
        return projectDetails;
    }

    public void setProjectDetails(String projectDetails) {
        this.projectDetails = projectDetails;
    }

    public String getNightTimeStart() {
        return nightTimeStart;
    }

    public void setNightTimeStart(String nightTimeStart) {
        this.nightTimeStart = nightTimeStart;
    }

    public String getNightTimeEnd() {
        return nightTimeEnd;
    }

    public void setNightTimeEnd(String nightTimeEnd) {
        this.nightTimeEnd = nightTimeEnd;
    }

    public String getProjectFinishPercent() {
        return projectFinishPercent;
    }

    public void setProjectFinishPercent(String projectFinishPercent) {
        this.projectFinishPercent = projectFinishPercent;
    }

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookContent() {
        return bookContent;
    }

    public void setBookContent(String bookContent) {
        this.bookContent = bookContent;
    }

    public String getMajorIn() {
        return majorIn;
    }

    public void setMajorIn(String majorIn) {
        this.majorIn = majorIn;
    }

    public String getProjectMonth() {
        return projectMonth;
    }

    public void setProjectMonth(String projectMonth) {
        this.projectMonth = projectMonth;
    }

    public String getProjectYear() {
        return projectYear;
    }

    public void setProjectYear(String projectYear) {
        this.projectYear = projectYear;
    }

    public String getRelaxItem() {
        return relaxItem;
    }

    public void setRelaxItem(String relaxItem) {
        this.relaxItem = relaxItem;
    }

    public String getRelaxItemForeign() {
        return relaxItemForeign;
    }

    public void setRelaxItemForeign(String relaxItemForeign) {
        this.relaxItemForeign = relaxItemForeign;
    }

    public String getTypeOfLearn() {
        return typeOfLearn;
    }

    public void setTypeOfLearn(String typeOfLearn) {
        this.typeOfLearn = typeOfLearn;
    }

    public String getTypeDetail() {
        return typeDetail;
    }

    public void setTypeDetail(String typeDetail) {
        this.typeDetail = typeDetail;
    }

    public String getStandardLearn() {
        return standardLearn;
    }

    public void setStandardLearn(String standardLearn) {
        this.standardLearn = standardLearn;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }


    // Getters and Setters
    // ...
}
