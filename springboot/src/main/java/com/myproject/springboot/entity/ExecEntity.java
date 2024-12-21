package com.myproject.springboot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "exec")
public class ExecEntity {

    @Id
    @Column(name = "exec_name")
    private String execName;

    @Column(name = "exec_system")
    private String execSystem;

    @Column(name = "exec_time")
    private String execTime;

    @Column(name = "exec_frequency")
    private String execFrequency;

    @Column(name = "exec_detail")
    private String execDetail;

    @Column(name = "exec_content")
    private String execContent;

    @Column(name = "update_time")
    private String updateTime;


    public String getExecName() {
        return execName;
    }

    public void setExecName(String execName) {
        this.execName = execName;
    }

    public String getExecSystem() {
        return execSystem;
    }

    public void setExecSystem(String execSystem) {
        this.execSystem = execSystem;
    }

    public String getExecTime() {
        return execTime;
    }

    public void setExecTime(String execTime) {
        this.execTime = execTime;
    }

    public String getExecFrequency() {
        return execFrequency;
    }

    public void setExecFrequency(String execFrequency) {
        this.execFrequency = execFrequency;
    }

    public String getExecDetail() {
        return execDetail;
    }

    public void setExecDetail(String execDetail) {
        this.execDetail = execDetail;
    }

    public String getExecContent() {
        return execContent;
    }

    public void setExecContent(String execContent) {
        this.execContent = execContent;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
