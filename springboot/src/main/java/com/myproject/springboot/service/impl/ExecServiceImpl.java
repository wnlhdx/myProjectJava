package com.myproject.springboot.service.impl;

import com.myproject.springboot.entity.ExecEntity;
import com.myproject.springboot.mapper.ExecRepository;
import com.myproject.springboot.service.ExecService;
import com.myproject.springboot.tools.OS;
import jakarta.annotation.PostConstruct;
import org.elasticsearch.tasks.Task;
import org.quartz.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExecServiceImpl implements ExecService {
    private final ExecRepository execMapper;
    private final OS os=new OS();

    private Scheduler scheduler;


    public ExecServiceImpl(ExecRepository execMapper,Scheduler Scheduler) {
        this.execMapper = execMapper;
        this.scheduler = Scheduler;
    }


    @PostConstruct
    public void scheduleTasks() throws SchedulerException{
        List<ExecEntity> execEntityList = execMapper.findAll();
        for(ExecEntity exec:execEntityList){
            scheduleJob(exec);
        }
    }

    public void scheduleJob(ExecEntity exec) throws SchedulerException {
        JobDetail jobDetail=JobBuilder.newJob(getJobClass(exec.getExecDetail())).withIdentity(exec.getExecName()).build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(exec.getExecName()+"Trigger").withSchedule(CronScheduleBuilder.cronSchedule(exec.getExecFrequency())).build();
        scheduler.scheduleJob(jobDetail,trigger);
    }

    private  Class<? extends Job> getJobClass(String className){
        try{
            return (Class<? extends Job>) Class.forName(className);
        }catch (ClassNotFoundException e){
            throw new RuntimeException("Job Class not Found:"+className);
        }
    }
}
