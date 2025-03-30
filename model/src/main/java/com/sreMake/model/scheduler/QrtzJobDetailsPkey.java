package com.sreMake.model.scheduler;

import org.babyfish.jimmer.sql.Embeddable;

@Embeddable
public interface QrtzJobDetailsPkey {

    String schedName();


    String jobName();


    String jobGroup();

}
