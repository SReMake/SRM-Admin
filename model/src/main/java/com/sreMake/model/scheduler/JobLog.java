package com.sreMake.model.scheduler;

import com.sreMake.model.BaseEntity;
import org.babyfish.jimmer.sql.Entity;
import org.babyfish.jimmer.sql.Key;
import org.babyfish.jimmer.sql.ManyToOne;
import org.babyfish.jimmer.sql.Table;

@Entity
@Table(name = "srm_job_log")
public interface JobLog extends BaseEntity {

    @ManyToOne
    @Key
    Job job();

    String jobGroup();

    String jobName();

    String logs();

}
