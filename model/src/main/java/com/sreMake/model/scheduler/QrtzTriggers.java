package com.sreMake.model.scheduler;

import org.babyfish.jimmer.sql.Entity;
import org.babyfish.jimmer.sql.Id;
import org.babyfish.jimmer.sql.Table;
import org.jetbrains.annotations.Nullable;

@Entity
@Table(name = "qrtz_triggers")
public interface QrtzTriggers {
    @Id
    QrtzTriggersPkey id();

    String jobName();

    String jobGroup();

    @Nullable
    String description();

    Long nextFireTime();

    Long prevFireTime();

    Integer priority();

    String triggerState();

    String triggerType();

    long startTime();

    Long endTime();

    @Nullable
    String calendarName();

    Short misfireInstr();

    @Nullable
    byte[] jobData();
}
