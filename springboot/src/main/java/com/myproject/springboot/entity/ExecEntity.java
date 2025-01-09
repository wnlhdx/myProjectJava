package com.myproject.springboot.entity;

//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//
//
//@Entity
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
@Table("exec")
public class ExecEntity {

    @Id
    @Column("exec_name")
    private String execName;

    @Column("exec_system")
    private String execSystem;

    @Column("exec_time")
    private String execTime;

    @Column("exec_frequency")
    private String execFrequency;

    @Column("exec_detail")
    private String execDetail;

    @Column("exec_content")
    private String execContent;

    @Column("update_time")
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
