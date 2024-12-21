package com.myproject.springboot.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "files")
public class FileManageEntity {
    @Id
    @Column(name = "file_name")
    private String fileName;

    @Column(name = "size")
    private String size;

    @Column(name = "path")
    private String path;

    @Column(name = "system")
    private String system;

    @Column(name = "detail")
    private String detail;

    @Column(name = "update_time")
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


