package com.sreMake.scheduler.vo;

import com.sreMake.model.scheduler.Job;
import com.sreMake.model.user.User;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class JobVo {

    private long id;
    private String name;
    private String jobGroup;
    private String jobName;
    private String className;
    private String methodName;
    private String cron;
    private OffsetDateTime startTime;
    private OffsetDateTime endTime;
    private OffsetDateTime createAt;
    private OffsetDateTime updateAt;
    private User createBy;
    private User updateBy;
    public JobVo(Job job) {
        this.id = job.id();
        this.name = job.name();
        this.jobGroup = job.jobGroup();
        this.jobName = job.jobName();
        this.className = job.className();
        this.methodName = job.methodName();
        this.cron = job.cron();
        this.startTime = job.startTime();
        this.endTime = job.endTime();
        this.createAt = job.createAt();
        this.updateAt = job.updateAt();
        this.createBy = job.createBy();
        this.updateBy = job.updateBy();
    }
}
