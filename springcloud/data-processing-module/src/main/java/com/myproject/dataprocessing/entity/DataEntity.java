package com.myproject.dataprocessing.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("data_records")
public class DataEntity {

    @Id
    private Long id;
    private String key;
    private String value;
}
