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