create table srm_resources
(
    id           bigserial primary key,
    resources    varchar(256)            not null,
    action       varchar(256)            not null,
    type         varchar(26)             not null,
    parent_id       bigint,
    create_at    timestamp default now() not null,
    update_at    timestamp,
    create_by_id bigint
        constraint role_user_id_create_by_id
            references "srm_role",
    update_by_id bigint
        constraint role_user_id_update_by_id
            references "srm_role"
);