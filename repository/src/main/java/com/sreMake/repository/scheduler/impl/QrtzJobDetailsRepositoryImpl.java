package com.sreMake.repository.scheduler.impl;

import com.sreMake.model.scheduler.QrtzJobDetails;
import com.sreMake.model.scheduler.QrtzJobDetailsPkey;
import com.sreMake.repository.scheduler.QrtzJobDetailsRepository;
import org.babyfish.jimmer.spring.repo.support.AbstractJavaRepository;
import org.babyfish.jimmer.sql.JSqlClient;
import org.springframework.stereotype.Repository;

@Repository
public class QrtzJobDetailsRepositoryImpl extends AbstractJavaRepository<QrtzJobDetails, QrtzJobDetailsPkey> implements QrtzJobDetailsRepository {
    public QrtzJobDetailsRepositoryImpl(JSqlClient sql) {
        super(sql);
    }
}
