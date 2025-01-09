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

@Table(name = "data")
public class DataDownloadEntity {
    @Id
    @Column("data_name")
    private String dataName;

    @Column("data_type")
    private String dataType;

    @Column( "website")
    private String website;

    @Column("whether_ranking")
    private String whetherRanking;

    @Column("rangking_top")
    private String rankingTop;

    @Column("download_path")
    private String downloadPath;

    @Column("download_time")
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

