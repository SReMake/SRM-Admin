package com.sreMake.scheduler;

import org.jetbrains.annotations.NotNull;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;

public class DynamicJob implements Job, ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        String className = dataMap.getString("className");
        String methodName = dataMap.getString("methodName");
        try {

            // 获取Class对象
            Class<?> clazz = Class.forName(className);

            // 从ApplicationContext中获取Bean
            Object bean = applicationContext.getBean(clazz);

            // 获取Method对象
            Method method = clazz.getMethod(methodName);

            // 调用方法
            method.invoke(bean);

        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }

}
