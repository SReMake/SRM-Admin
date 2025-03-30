package com.sreMake.repository.scheduler;

import com.sreMake.model.scheduler.QrtzTriggers;
import com.sreMake.model.scheduler.QrtzTriggersPkey;
import org.babyfish.jimmer.spring.repo.JavaRepository;

public interface QrtzTriggersRepository extends JavaRepository<QrtzTriggers, QrtzTriggersPkey> {
}
