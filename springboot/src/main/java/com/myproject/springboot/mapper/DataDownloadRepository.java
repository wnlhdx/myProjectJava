package com.myproject.springboot.mapper;

import com.myproject.springboot.entity.DataDownloadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataDownloadRepository extends JpaRepository<DataDownloadEntity, Long> {
    DataDownloadEntity findByDataName(String dataName);
    void deleteByDataName(String dataName);
}
