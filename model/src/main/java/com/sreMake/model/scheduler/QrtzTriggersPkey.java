package com.sreMake.model.scheduler;

import org.babyfish.jimmer.sql.Embeddable;

@Embeddable
public interface QrtzTriggersPkey {
    String schedName();

    String triggerName();

    String triggerGroup();
}
