package com.myproject.springboot.mapper;

import com.myproject.springboot.entity.ExecEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExecRepository extends JpaRepository<ExecEntity, Long> {
    List<ExecEntity> findAll();
    ExecEntity findByExecName(String execName);
    void deleteByExecName(String execName);
}
