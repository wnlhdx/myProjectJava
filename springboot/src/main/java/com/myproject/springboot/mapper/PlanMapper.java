package com.myproject.springboot.mapper;

import com.myproject.springboot.entity.PlanEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

/**
 * @author lkxl
 */
@Mapper
public interface PlanMapper {


    List<PlanEntity> getAll();
    PlanEntity getPlanByName(String planName);


    void insertPlan(PlanEntity plan);


    void updatePlan(PlanEntity plan);


    void deletePlan(String planName);
}
