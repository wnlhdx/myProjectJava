package com.myproject.springboot.APITest;


import com.myproject.springboot.entity.PlanEntity;
import com.myproject.springboot.mapper.PlanRepository;
import com.myproject.springboot.service.impl.PlanServiceImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;



@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class PlanServiceTest{
    @Mock
    private PlanRepository planMapper;

    @InjectMocks
    private PlanServiceImpl planService;

    @Autowired
    private MockMvc mockMvc;


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

        List<PlanEntity> planList = new ArrayList<>();
        planList.add(planEntity1);
        planList.add(planEntity2);

        // 模拟返回值
        when(planMapper.findAll()).thenReturn(planList);

        // 执行测试
        List<PlanEntity> result = planService.getAll();

        // 验证结果
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Plan 1", result.get(0).getPlanName());
        Assertions.assertEquals("Plan 2", result.get(1).getPlanName());
        verify(planMapper, times(1)).findAll(); // 验证调用
    }

    @Test
    public void testGetPlanByName() {
        PlanEntity plan = new PlanEntity();
        plan.setPlanName("Test Plan");

        // 模拟返回值
        when(planMapper.findByPlanName("Test Plan")).thenReturn(plan);

        // 执行测试
        PlanEntity result = planService.getPlanByName("Test Plan");

        // 验证结果
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Test Plan", result.getPlanName());
        verify(planMapper, times(1)).findByPlanName("Test Plan"); // 验证调用
    }

    @Test
    public void testAddPlan() {
        PlanEntity plan = new PlanEntity();
        plan.setPlanName("Test Plan");

        // 执行测试
        planService.addPlan(plan);

        // 验证是否调用了保存方法
        verify(planMapper, times(1)).save(plan);
    }

    @Test
    public void testUpdatePlan() {
        PlanEntity plan = new PlanEntity();
        plan.setPlanName("Test Plan");

        // 执行测试
        planService.updatePlan(plan);

        // 验证是否调用了保存方法
        verify(planMapper, times(1)).save(plan);
    }

    @Test
    public void testDeletePlan() {
        // 执行测试
        planService.deletePlan("Test Plan");

        // 验证是否调用了删除方法
        verify(planMapper, times(1)).deleteByPlanName("Test Plan"); // 假设您有这样的方法
    }

    @AfterAll
    public static void releaseMocks() {
        // 此处不需要实现，因为 @Mock 和 @InjectMocks 会自动处理
    }
}
