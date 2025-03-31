package com.sreMake.repository.scheduler;

import com.sreMake.model.scheduler.Job;
import com.sreMake.model.scheduler.dto.JobSearchInput;
import org.babyfish.jimmer.Page;
import org.babyfish.jimmer.spring.repo.JavaRepository;
import org.babyfish.jimmer.spring.repo.PageParam;

public interface JobRepository extends JavaRepository<Job, Long> {
    /**
     * 分页查询
     */
    Page<Job> findPage(PageParam pageParam, JobSearchInput params);

    /**
     * 根据jobName与jobGroup查找job
     */
    Job findByJobNameAndJobGroup(String jobName, String jobGroup);

    void deleteByJobGroupAndJobName(String jobGroup, String jobName);

}
