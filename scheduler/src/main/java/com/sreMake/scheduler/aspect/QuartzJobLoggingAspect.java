package com.sreMake.scheduler.aspect;

import com.sreMake.model.scheduler.Job;
import com.sreMake.model.scheduler.JobLogDraft;
import com.sreMake.repository.scheduler.JobLogRepository;
import com.sreMake.repository.scheduler.JobRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Component
@Slf4j
public class QuartzJobLoggingAspect {

    private final JobLogRepository jobLogRepository;
    private final JobRepository JobRepository;

    public QuartzJobLoggingAspect(JobLogRepository jobLogRepository, JobRepository jobRepository) {
        this.jobLogRepository = jobLogRepository;
        JobRepository = jobRepository;
    }

    @Before("execution(* org.quartz.Job+.execute(..))")
    public void logBeforeExecution(JoinPoint joinPoint) {
        JobExecutionContext context = (JobExecutionContext) joinPoint.getArgs()[0];
        JobKey jobKey = context.getJobDetail().getKey();
        String jobName = jobKey.getName();
        String jobGroup = jobKey.getGroup();
        log.info("Job {} in group {} is about to execute", jobName, jobGroup);

        Job job = JobRepository.findByJobNameAndJobGroup(jobName, jobGroup);
        if (job != null) {
            jobLogRepository.insert(JobLogDraft.$.produce(draft -> {
                draft.setJobId(job.id());
                draft.setJobGroup(jobGroup);
                draft.setJobName(jobName);
                draft.setLogs("Job " + jobName + " in group " + jobGroup + " is about to execute");
                if (job.createBy() != null) {
                    draft.setCreateById(Objects.requireNonNull(job.createBy()).id());
                }
                if (job.updateBy() != null) {
                    draft.setUpdateById(Objects.requireNonNull(job.updateBy()).id());
                }
            }));
        }
    }

    @After("execution(* org.quartz.Job+.execute(..))")
    public void logAfterExecution(JoinPoint joinPoint) {
        JobExecutionContext context = (JobExecutionContext) joinPoint.getArgs()[0];
        JobKey jobKey = context.getJobDetail().getKey();
        String jobName = jobKey.getName();
        String jobGroup = jobKey.getGroup();
        log.info("Job {} in group {} executed successfully", jobName, jobGroup);
        Job job = JobRepository.findByJobNameAndJobGroup(jobName, jobGroup);

        if (job != null) {
            jobLogRepository.insert(JobLogDraft.$.produce(draft -> {
                draft.setJobId(job.id());
                draft.setJobGroup(jobGroup);
                draft.setJobName(jobName);
                draft.setLogs("Job " + jobName + " in group " + jobGroup + " is about to execute");
                if (job.createBy() != null) {
                    draft.setCreateById(Objects.requireNonNull(job.createBy()).id());
                }
                if (job.updateBy() != null) {
                    draft.setUpdateById(Objects.requireNonNull(job.updateBy()).id());
                }
            }));
        }
    }

    @AfterThrowing(pointcut = "execution(* org.quartz.Job+.execute(..))", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        JobExecutionContext context = (JobExecutionContext) joinPoint.getArgs()[0];
        JobKey jobKey = context.getJobDetail().getKey();
        String jobName = jobKey.getName();
        String jobGroup = jobKey.getGroup();
        log.error("Job {} in group {} threw an exception", jobName, jobGroup, exception);
        Job job = JobRepository.findByJobNameAndJobGroup(jobName, jobGroup);

        if (job != null) {
            jobLogRepository.insert(JobLogDraft.$.produce(draft -> {
                draft.setJobId(job.id());
                draft.setJobGroup(jobGroup);
                draft.setJobName(jobName);
                draft.setLogs("Job " + jobName + " in group " + jobGroup + " is about to execute");
                if (job.createBy() != null) {
                    draft.setCreateById(Objects.requireNonNull(job.createBy()).id());
                }
                if (job.updateBy() != null) {
                    draft.setUpdateById(Objects.requireNonNull(job.updateBy()).id());
                }
            }));
        }
    }
}