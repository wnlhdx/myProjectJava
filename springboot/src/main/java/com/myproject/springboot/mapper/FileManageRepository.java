package com.myproject.springboot.mapper;

import com.myproject.springboot.entity.FileManageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FileManageRepository extends JpaRepository<FileManageEntity, Long> {
    List<FileManageEntity> findAll();
    FileManageEntity findByFileName(String fileName);
    void deleteByFileName(String fileName);
}
