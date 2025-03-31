package com.sreMake.repository.scheduler.impl;

import com.sreMake.model.Fetchers;
import com.sreMake.model.Tables;
import com.sreMake.model.scheduler.Job;
import com.sreMake.model.scheduler.dto.JobSearchInput;
import com.sreMake.repository.scheduler.JobRepository;
import org.babyfish.jimmer.Page;
import org.babyfish.jimmer.spring.repo.PageParam;
import org.babyfish.jimmer.spring.repo.support.AbstractJavaRepository;
import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.ast.LikeMode;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class JobRepositoryImpl extends AbstractJavaRepository<Job, Long> implements JobRepository {

    public JobRepositoryImpl(JSqlClient sql) {
        super(sql);
    }

    @Override
    public Page<Job> findPage(PageParam pageParam, JobSearchInput params) {
        if (Objects.isNull(params)) {
            return this.findPage(pageParam);
        } else {
            return this.sql.createQuery(Tables.JOB_TABLE).where(
                            Tables.JOB_TABLE.className().likeIf(params.getClassName(), LikeMode.ANYWHERE),
                            Tables.JOB_TABLE.methodName().likeIf(params.getMethodName(), LikeMode.ANYWHERE),
                            Tables.JOB_TABLE.name().likeIf(params.getName(), LikeMode.ANYWHERE),
                            Tables.JOB_TABLE.cron().eqIf(params.getCron())
                    ).orderBy(Tables.JOB_TABLE.id().desc())
                    .select(Tables.JOB_TABLE.fetch(
                            Fetchers.JOB_FETCHER.allScalarFields()
                                    .createBy(Fetchers.USER_FETCHER.username())
                                    .updateBy(Fetchers.USER_FETCHER.username())
                    ))
                    .fetchPage(pageParam.getIndex(), pageParam.getSize());
        }
    }

    @Override
    public Job findByJobNameAndJobGroup(String jobName, String jobGroup) {
        return this.sql.createQuery(Tables.JOB_TABLE).where(
                        Tables.JOB_TABLE.jobName().eq(jobName),
                        Tables.JOB_TABLE.jobGroup().eq(jobGroup)
                ).select(Tables.JOB_TABLE)
                .fetchOptional().orElse(null);
    }

    @Override
    public void deleteByJobGroupAndJobName(String jobGroup, String jobName) {
        this.sql.createDelete(Tables.JOB_TABLE).where(
                Tables.JOB_TABLE.jobGroup().eq(jobGroup),
                Tables.JOB_TABLE.jobName().eq(jobName)
        ).execute();
    }
}
