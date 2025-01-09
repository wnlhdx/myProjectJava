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
@Table(name = "files")
public class FileManageEntity {
    @Id
    @Column("file_name")
    private String fileName;

    @Column("size")
    private String size;

    @Column("path")
    private String path;

    @Column("system")
    private String system;

    @Column("detail")
    private String detail;

    @Column("update_time")
    private String updateTime;

    public FileManageEntity(String fileName, String size, String path, String system, String detail, String updateTime) {
        this.fileName = fileName;
        this.size = size;
        this.path = path;
        this.system = system;
        this.detail = detail;
        this.updateTime = updateTime;
    }
}


