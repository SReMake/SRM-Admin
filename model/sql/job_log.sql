create table srm_job_log
(
    id           bigserial primary key,
    job_id       bigint                  not null,
    job_group varchar(256) not null,
    job_name  varchar(256) not null,
    logs         text                    not null,
    create_at    timestamp default now() not null,
    update_at    timestamp,
    create_by_id bigint
        constraint role_user_id_create_by_id
            references "srm_role",
    update_by_id bigint
        constraint role_user_id_update_by_id
            references "srm_role"
);