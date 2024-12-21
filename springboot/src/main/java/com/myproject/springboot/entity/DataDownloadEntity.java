package com.myproject.springboot.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "data")
public class DataDownloadEntity {
    @Id
    @Column(name = "data_name")
    private String dataName;

    @Column(name = "data_type")
    private String dataType;

    @Column(name = "website")
    private String website;

    @Column(name = "whether_ranking")
    private String whetherRanking;

    @Column(name = "rangking_top")
    private String rankingTop;

    @Column(name = "download_path")
    private String downloadPath;

    @Column(name = "download_time")
    private String downloadTime;

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getWhetherRanking() {
        return whetherRanking;
    }

    public void setWhetherRanking(String whetherRanking) {
        this.whetherRanking = whetherRanking;
    }

    public String getRankingTop() {
        return rankingTop;
    }

    public void setRankingTop(String rankingTop) {
        this.rankingTop = rankingTop;
    }

    public String getDownloadPath() {
        return downloadPath;
    }

    public void setDownloadPath(String downloadPath) {
        this.downloadPath = downloadPath;
    }

    public String getDownloadTime() {
        return downloadTime;
    }

    public void setDownloadTime(String downloadTime) {
        this.downloadTime = downloadTime;
    }
}

