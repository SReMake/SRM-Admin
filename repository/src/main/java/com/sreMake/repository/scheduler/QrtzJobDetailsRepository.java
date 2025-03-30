package com.sreMake.repository.scheduler;

import com.sreMake.model.scheduler.QrtzJobDetails;
import com.sreMake.model.scheduler.QrtzJobDetailsPkey;
import org.babyfish.jimmer.spring.repo.JavaRepository;

public interface QrtzJobDetailsRepository extends JavaRepository<QrtzJobDetails, QrtzJobDetailsPkey> {
}
