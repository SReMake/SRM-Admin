package com.sreMake.repository.scheduler.impl;

import com.sreMake.model.scheduler.JobLog;
import com.sreMake.repository.scheduler.JobLogRepository;
import org.babyfish.jimmer.spring.repo.support.AbstractJavaRepository;
import org.babyfish.jimmer.sql.JSqlClient;
import org.springframework.stereotype.Repository;

@Repository
public class JobLogRepositoryImpl extends AbstractJavaRepository<JobLog, Long> implements JobLogRepository {
    public JobLogRepositoryImpl(JSqlClient sql) {
        super(sql);
    }
}
