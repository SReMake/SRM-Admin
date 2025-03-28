package com.sreMake.scheduler;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.lang.reflect.Method;

public class  DynamicJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        String packageName = dataMap.getString("packageName");
        String methodName = dataMap.getString("methodName");
        try {
            Class<?> clazz = Class.forName(packageName);
            Method method = clazz.getMethod(methodName);
            method.invoke(clazz.getDeclaredConstructor().newInstance());
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }
}
