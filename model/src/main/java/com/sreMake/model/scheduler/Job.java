package com.sreMake.model.scheduler;

import com.sreMake.model.BaseEntity;
import org.babyfish.jimmer.sql.Entity;
import org.babyfish.jimmer.sql.Table;
import org.jetbrains.annotations.Nullable;

import java.time.OffsetDateTime;


@Entity
@Table(name = "srm_job")
public interface Job extends BaseEntity {

    String name();

    String jobGroup();

    String jobName();

    String className();

    String methodName();

    String cron();

    OffsetDateTime startTime();

    @Nullable
    OffsetDateTime endTime();
}
