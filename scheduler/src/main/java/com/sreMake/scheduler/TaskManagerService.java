package com.sreMake.scheduler;

import com.sreMake.common.result.ResponseResult;
import org.quartz.*;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskManagerService {
    private final Scheduler scheduler;

    public TaskManagerService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @PostMapping("/schedule")
    public ResponseResult<String> scheduleTask(@RequestParam String packageName, @RequestParam String methodName)
            throws SchedulerException {
        JobDetail job = JobBuilder.newJob(DynamicJob.class)
                .withIdentity(UUID.randomUUID().toString(), "group1")
                .usingJobData("packageName", packageName)
                .usingJobData("methodName", methodName)
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(UUID.randomUUID().toString(), "group1")
                .startNow()
                .build();

        scheduler.scheduleJob(job, trigger);
        return ResponseResult.success("Task scheduled successfully!");
    }

    @DeleteMapping("/unschedule/{jobId}")
    public ResponseResult<String> unscheduleTask(@PathVariable String jobId) throws SchedulerException {
        JobKey jobKey = new JobKey(jobId, "group1");
        if (scheduler.deleteJob(jobKey)) {
            return ResponseResult.success("Task unscheduled successfully!");
        }
        return ResponseResult.success("Task not found!");
    }

}
