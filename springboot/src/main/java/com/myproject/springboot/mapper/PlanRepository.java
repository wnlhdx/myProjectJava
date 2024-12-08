package com.myproject.springboot.mapper;

import com.myproject.springboot.entity.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lkxl
 */
@Repository
public interface PlanRepository extends JpaRepository<PlanEntity, Long> {
    // 可以在这里定义自定义查询方法



    List<PlanEntity> findAll();
    PlanEntity findByPlanName(String planName);



    void deleteByPlanName(String planName);
}
