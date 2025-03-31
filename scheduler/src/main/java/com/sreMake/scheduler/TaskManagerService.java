package com.sreMake.scheduler;

import cn.hutool.core.lang.UUID;
import com.sreMake.common.exception.can.ValidationException;
import com.sreMake.common.result.ResponseResult;
import com.sreMake.model.scheduler.Job;
import com.sreMake.model.scheduler.JobDraft;
import com.sreMake.model.scheduler.JobLog;
import com.sreMake.model.scheduler.dto.JobInput;
import com.sreMake.model.scheduler.dto.JobSearchInput;
import com.sreMake.repository.scheduler.JobLogRepository;
import com.sreMake.repository.scheduler.JobRepository;
import com.sreMake.scheduler.vo.JobLogVo;
import com.sreMake.scheduler.vo.JobVo;
import org.babyfish.jimmer.Page;
import org.babyfish.jimmer.client.EnableImplicitApi;
import org.babyfish.jimmer.client.meta.Api;
import org.babyfish.jimmer.spring.repo.PageParam;
import org.quartz.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@Api
@EnableImplicitApi
public class TaskManagerService {
    private final Scheduler scheduler;
    private final JobRepository jobRepository;
    private final JobLogRepository jobLogRepository;

    public TaskManagerService(Scheduler scheduler, JobRepository jobRepository, JobLogRepository jobLogRepository) {
        this.scheduler = scheduler;
        this.jobRepository = jobRepository;
        this.jobLogRepository = jobLogRepository;
    }

    @PostMapping("/schedule")
    @Transactional
    public ResponseResult<String> scheduleTask(@RequestBody JobInput jobInput)
            throws SchedulerException {
        String name = UUID.fastUUID().toString();
        String group = "DEFAULT";
        JobDetail job = JobBuilder.newJob(DynamicJob.class)
                .withIdentity(name, group)
                .usingJobData("className", jobInput.getClassName())
                .usingJobData("methodName", jobInput.getMethodName())
                .build();

        TriggerBuilder<CronTrigger> trigger = TriggerBuilder.newTrigger().withSchedule(
                CronScheduleBuilder.cronSchedule(jobInput.getCron())
        );
// 设置开始时间
        if (jobInput.getStartTime() == null) {
            trigger = trigger.startNow();
        } else {
            Instant startTime = jobInput.getStartTime().toInstant();
            if (jobInput.getEndTime() != null && startTime.isAfter(jobInput.getEndTime().toInstant())) {
                throw new ValidationException("Start time cannot be after end time");
            }
            trigger = trigger.startAt(Date.from(startTime));
        }

// 设置结束时间
        if (jobInput.getEndTime() != null) {
            trigger = trigger.endAt(Date.from(jobInput.getEndTime().toInstant()));
        }
        jobRepository.insert(JobDraft.$.produce(
                draft -> {
                    draft.setName(jobInput.getName());
                    draft.setJobName(name);
                    draft.setJobGroup(group);
                    draft.setClassName(jobInput.getClassName());
                    draft.setMethodName(jobInput.getMethodName());
                    draft.setCron(jobInput.getCron());
                    if (jobInput.getStartTime() == null) {
                        draft.setStartTime(OffsetDateTime.now());
                    } else {
                        draft.setStartTime(jobInput.getStartTime());
                    }
                    draft.setEndTime(jobInput.getEndTime());
                }
        ));
        scheduler.scheduleJob(job, trigger.build());
        return ResponseResult.success("Task scheduled successfully!");
    }

    @DeleteMapping("/unschedule/{group}/{name}")
    @Transactional
    public ResponseResult<String> unscheduleTask(@PathVariable String group, @PathVariable String name) throws SchedulerException {
        JobKey jobKey = new JobKey(name, group);
        scheduler.deleteJob(jobKey);
        jobRepository.deleteByJobGroupAndJobName(group, name);
        jobLogRepository.deleteByJobGroupAndJobName(group, name);
        return ResponseResult.success("OK");
    }


    @GetMapping("/list")
    public ResponseResult<Page<JobVo>> listJobs(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(required = false) JobSearchInput params) {
        PageParam pageParam = PageParam.byNo(page, size);
        Page<Job> result = jobRepository.findPage(pageParam, params);
        List<JobVo> jobs = result.getRows().stream().map(JobVo::new).toList();
        return ResponseResult.success(new Page<>(jobs, result.getTotalRowCount(), result.getTotalPageCount()));
    }

    @GetMapping("/list/logs/{group}/{name}")
    public ResponseResult<Page<JobLogVo>> listJobLogs(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size, @PathVariable String group, @PathVariable String name) {
        PageParam pageParam = PageParam.byNo(page, size);
        Page<JobLog> result = jobLogRepository.findPage(pageParam, group, name);
        List<JobLogVo> jobLogVos = result.getRows().stream().map(JobLogVo::new).toList();
        return ResponseResult.success(new Page<>(jobLogVos, result.getTotalRowCount(), result.getTotalPageCount()));
    }
}
