package com.sreMake.repository.scheduler.impl;

import com.sreMake.model.scheduler.QrtzTriggers;
import com.sreMake.model.scheduler.QrtzTriggersPkey;
import com.sreMake.repository.scheduler.QrtzTriggersRepository;
import org.babyfish.jimmer.spring.repo.support.AbstractJavaRepository;
import org.babyfish.jimmer.sql.JSqlClient;
import org.springframework.stereotype.Repository;

@Repository
public class QrtzTriggersRepositoryImpl extends AbstractJavaRepository<QrtzTriggers, QrtzTriggersPkey> implements QrtzTriggersRepository {
    public QrtzTriggersRepositoryImpl(JSqlClient sql) {
        super(sql);
    }
}
