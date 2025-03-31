package com.sreMake.repository.scheduler.impl;

import com.sreMake.model.Fetchers;
import com.sreMake.model.Tables;
import com.sreMake.model.scheduler.JobLog;
import com.sreMake.repository.scheduler.JobLogRepository;
import org.babyfish.jimmer.Page;
import org.babyfish.jimmer.spring.repo.PageParam;
import org.babyfish.jimmer.spring.repo.support.AbstractJavaRepository;
import org.babyfish.jimmer.sql.JSqlClient;
import org.springframework.stereotype.Repository;

@Repository
public class JobLogRepositoryImpl extends AbstractJavaRepository<JobLog, Long> implements JobLogRepository {
    public JobLogRepositoryImpl(JSqlClient sql) {
        super(sql);
    }

    @Override
    public Page<JobLog> findPage(PageParam pageParam, String jobGroup, String jobName) {
        return this.sql.createQuery(Tables.JOB_LOG_TABLE).where(
                        Tables.JOB_LOG_TABLE.jobGroup().eq(jobGroup),
                        Tables.JOB_LOG_TABLE.jobName().eq(jobName)
                )
                .orderBy(Tables.JOB_LOG_TABLE.id().desc())
                .select(Tables.JOB_LOG_TABLE.fetch(
                        Fetchers.JOB_LOG_FETCHER.allScalarFields()
                                .job(Fetchers.JOB_FETCHER.allScalarFields())
                                .createBy(Fetchers.USER_FETCHER.username())
                                .updateBy(Fetchers.USER_FETCHER.username())
                ))
                .fetchPage(pageParam.getIndex(), pageParam.getSize());
    }

    @Override
    public void deleteByJobGroupAndJobName(String jobGroup, String jobName) {
        this.sql.createDelete(Tables.JOB_LOG_TABLE).where(
                Tables.JOB_LOG_TABLE.jobGroup().eq(jobGroup),
                Tables.JOB_LOG_TABLE.jobName().eq(jobName)
        ).execute();
    }
}
