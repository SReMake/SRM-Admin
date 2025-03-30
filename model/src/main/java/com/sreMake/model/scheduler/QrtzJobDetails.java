package com.sreMake.model.scheduler;

import org.babyfish.jimmer.sql.Column;
import org.babyfish.jimmer.sql.Entity;
import org.babyfish.jimmer.sql.Id;
import org.babyfish.jimmer.sql.Table;
import org.jetbrains.annotations.Nullable;

@Entity
@Table(name = "qrtz_job_details")
public interface QrtzJobDetails {

    @Id
    QrtzJobDetailsPkey id();

    @Nullable
    String description();

    String jobClassName();

    @Column(name = "is_durable")
    boolean durable();

    @Column(name = "is_nonconcurrent")
    boolean nonconcurrent();

    @Column(name = "is_update_data")
    boolean updateData();

    @Column(name = "requests_recovery")
    boolean requestsRecovery();

    @Nullable
    byte[] jobData();
}
