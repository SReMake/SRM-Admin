package com.sreMake.scheduler.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class QuartzJobLoggingAspect {
    @Before("execution(* org.quartz.Job+.execute(..))")
    public void logBeforeExecution(JoinPoint joinPoint) {
        JobExecutionContext context = (JobExecutionContext) joinPoint.getArgs()[0];
        JobKey jobKey = context.getJobDetail().getKey();
        String jobName = jobKey.getName();
        String jobGroup = jobKey.getGroup();
        log.info("Job {} in group {} is about to execute", jobName, jobGroup);
//        jobExecutionLogService.logJobExecution(jobName, jobGroup, "STARTED", "Job started executing");
//        TODO 记录日志
    }

    @After("execution(* org.quartz.Job+.execute(..))")
    public void logAfterExecution(JoinPoint joinPoint) {
        JobExecutionContext context = (JobExecutionContext) joinPoint.getArgs()[0];
        JobKey jobKey = context.getJobDetail().getKey();
        String jobName = jobKey.getName();
        String jobGroup = jobKey.getGroup();
        log.info("Job {} in group {} executed successfully", jobName, jobGroup);
//        jobExecutionLogService.logJobExecution(jobName, jobGroup, "SUCCESS", "Job executed successfully");
//        TODO 记录日志
    }

    @AfterThrowing(pointcut = "execution(* org.quartz.Job+.execute(..))", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        JobExecutionContext context = (JobExecutionContext) joinPoint.getArgs()[0];
        JobKey jobKey = context.getJobDetail().getKey();
        String jobName = jobKey.getName();
        String jobGroup = jobKey.getGroup();
        log.error("Job {} in group {} threw an exception", jobName, jobGroup, exception);
//        TODO 记录日志
    }
}