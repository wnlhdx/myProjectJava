package com.myproject.springboot.service.impl;

import com.myproject.springboot.entity.ExecEntity;
import com.myproject.springboot.mapper.ExecRepository;
import com.myproject.springboot.service.ExecService;
import com.myproject.springboot.tools.OS;
import jakarta.annotation.PostConstruct;
import org.quartz.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class ExecServiceImpl implements ExecService {
    private final ExecRepository execMapper;
    private final OS os=new OS();

    private final Scheduler scheduler;


    public ExecServiceImpl(ExecRepository execMapper,Scheduler Scheduler) {
        this.execMapper = execMapper;
        this.scheduler = Scheduler;
    }


    @PostConstruct
    public void scheduleTasks() throws SchedulerException{
        Flux<ExecEntity> execEntityList = execMapper.findAll();
        execEntityList.doOnNext(this::scheduleJob);
    }

    public void scheduleJob(ExecEntity exec){
        try {
            JobDetail jobDetail = JobBuilder.newJob(getJobClass(exec.getExecDetail())).withIdentity(exec.getExecName()).build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(exec.getExecName() + "Trigger").withSchedule(CronScheduleBuilder.cronSchedule(exec.getExecFrequency())).build();
            scheduler.scheduleJob(jobDetail, trigger);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private Class<? extends Job> getJobClass(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            if (Job.class.isAssignableFrom(clazz)) {
                return clazz.asSubclass(Job.class);
            } else {
                throw new IllegalArgumentException("Class " + className + " is not a subclass of " + Job.class.getName());
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Job class not found: " + className, e);
        }
    }

}
