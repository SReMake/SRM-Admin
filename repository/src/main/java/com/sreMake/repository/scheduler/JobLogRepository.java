package com.sreMake.repository.scheduler;

import com.sreMake.model.scheduler.JobLog;
import org.babyfish.jimmer.Page;
import org.babyfish.jimmer.spring.repo.JavaRepository;
import org.babyfish.jimmer.spring.repo.PageParam;

public interface JobLogRepository extends JavaRepository<JobLog, Long> {
    /**
     * 分页
     */

    Page<JobLog> findPage(PageParam pageParam, String jobGroup, String jobName);

    void deleteByJobGroupAndJobName(String jobGroup, String jobName);
}
