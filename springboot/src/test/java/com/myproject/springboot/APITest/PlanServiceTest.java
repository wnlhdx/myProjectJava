package com.myproject.springboot.APITest;


import com.myproject.springboot.entity.PlanEntity;
import com.myproject.springboot.mapper.PlanMapper;
import com.myproject.springboot.service.impl.PlanServiceImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class PlanServiceTest{
    @Mock
    private PlanMapper planMapper;

    @InjectMocks
    private PlanServiceImpl planService;


    private static AutoCloseable closeable;

    @BeforeAll
    public static void setUp() {
        closeable=MockitoAnnotations.openMocks(PlanServiceTest.class);
    }

    @Test
    public void testGetAll() {
        // 创建模拟数据
        PlanEntity planEntity1 = new PlanEntity();
        planEntity1.setPlanName("Plan 1");
        // 设置其他属性...

        PlanEntity planEntity2 = new PlanEntity();
        planEntity2.setPlanName("Plan 2");
        // 设置其他属性...

        ArrayList<PlanEntity> planList = new ArrayList<>();
        planList.add(planEntity1);
        planList.add(planEntity2);

        when(planMapper.getAll()).thenReturn(planList);
        List<PlanEntity> result = planService.getAll();

        // 验证结果
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getFirst().getPlanName(), "Plan 1");
        Assertions.assertEquals(result.get(1).getPlanName(), "Plan 2");
        verify(planMapper, times(1)).getAll();
        // 创建 MockitoResultSet 和 MockitoJdbc 对象

    }

    @Test
    public void testGetPlanByName() {
        PlanEntity plan = new PlanEntity();
        plan.setPlanName("Test Plan");
        when(planMapper.getPlanByName("Test Plan")).thenReturn(plan);

        // 执行测试
        PlanEntity result = planService.getPlanByName("Test Plan");

        // 验证结果
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getPlanName(), "Test Plan");
        verify(planMapper, times(1)).getPlanByName("Test Plan");
    }

    @Test
    public void testAddPlan() {
        // Mock 数据
        PlanEntity plan = new PlanEntity();
        plan.setPlanName("Test Plan");

        // 执行测试
        planService.addPlan(plan);

        // 验证是否调用了插入方法
        verify(planMapper, times(1)).insertPlan(plan);
    }

    @Test
    public void testUpdatePlan() {
        // Mock 数据
        PlanEntity plan = new PlanEntity();
        plan.setPlanName("Test Plan");

        // 执行测试
        planService.updatePlan(plan);

        // 验证是否调用了更新方法
        verify(planMapper, times(1)).updatePlan(plan);
    }

    @Test
    public void testDeletePlan() {
        // 执行测试
        planService.deletePlan("Test Plan");

        // 验证是否调用了删除方法
        verify(planMapper, times(1)).deletePlan("Test Plan");
    }


    @AfterAll
    public static void releaseMocks() throws Exception {
        closeable.close();
    }
}