package com.myproject.springboot.mapper;

import com.myproject.springboot.entity.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    // 使用 @Query 注解，根据条件查询计划
    @Query("SELECT p FROM PlanEntity p WHERE " +
            "(:currentTime BETWEEN p.timeStart AND p.timeEnd AND p.dayOfWeek = :dayOfWeek) " +
            "OR (:currentTime BETWEEN p.nightTimeStart AND p.nightTimeEnd AND p.dayOfWeek = :dayOfWeek)")
    PlanEntity findPlansByTimeAndDayOfWeek(@Param("currentTime") String currentTime, @Param("dayOfWeek") Integer dayOfWeek);
    void deleteByPlanName(String planName);
}
