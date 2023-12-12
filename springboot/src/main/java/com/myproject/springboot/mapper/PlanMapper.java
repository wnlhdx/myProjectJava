package com.myproject.springboot.mapper;

import com.myproject.springboot.entity.PlanEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author lkxl
 */
public interface PlanMapper {

    PlanEntity getPlanByName(String planName);


    void insertPlan(PlanEntity plan);


    void updatePlan(PlanEntity plan);


    void deletePlan(String planName);
}
