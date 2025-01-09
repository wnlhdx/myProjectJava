package com.myproject.springboot.entity;




//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//
//@Entity

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
@Table("soft")
public class SoftUpdateEntity {
    @Id
    @Column("soft_name")
    private String softName;

    @Column("soft_type")
    private String softType;

    @Column("opreations")
    private String opreations;

    @Column("soft_details")
    private String softDetails;

    @Column("soft_version")
    private String softVersion;

    @Column("download_path")
    private String downloadPath;

    @Column("update_time")
    private String updateTime;

    public String getSoftName() {
        return softName;
    }

    public void setSoftName(String softName) {
        this.softName = softName;
    }

    public String getSoftType() {
        return softType;
    }

    public void setSoftType(String softType) {
        this.softType = softType;
    }

    public String getOpreations() {
        return opreations;
    }

    public void setOpreations(String opreations) {
        this.opreations = opreations;
    }

    public String getSoftDetails() {
        return softDetails;
    }

    public void setSoftDetails(String softDetails) {
        this.softDetails = softDetails;
    }

    public String getSoftVersion() {
        return softVersion;
    }

    public void setSoftVersion(String softVersion) {
        this.softVersion = softVersion;
    }

    public String getDownloadPath() {
        return downloadPath;
    }

    public void setDownloadPath(String downloadPath) {
        this.downloadPath = downloadPath;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}



