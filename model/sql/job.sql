create table srm_job
(
    id           bigserial primary key,
    name         varchar(256)            not null,
    job_group    varchar(256)            not null,
    job_name     varchar(256)            not null,
    class_name   varchar(256)            not null,
    method_name  varchar(256)            not null,
    cron         varchar(100)            not null,
    start_time   timestamp default now() not null,
    end_time     timestamp,
    create_at    timestamp default now() not null,
    update_at    timestamp,
    create_by_id bigint
        constraint role_user_id_create_by_id
            references "srm_role",
    update_by_id bigint
        constraint role_user_id_update_by_id
            references "srm_role"
);