package com.sreMake.scheduler.vo;

import com.sreMake.model.scheduler.JobLog;
import com.sreMake.model.user.User;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class JobLogVo {

    private long id;
    private String jobGroup;
    private String jobName;
    private String logs;
    private User createBy;
    private User updateBy;
    private OffsetDateTime createAt;
    private OffsetDateTime updateAt;


    public JobLogVo(JobLog jobLog) {
        this.id = jobLog.id();
        this.jobGroup = jobLog.jobGroup();
        this.jobName = jobLog.jobName();
        this.logs = jobLog.logs();
        this.createBy = jobLog.createBy();
        this.updateBy = jobLog.updateBy();
        this.createAt = jobLog.createAt();
        this.updateAt = jobLog.updateAt();
    }
}
