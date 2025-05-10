create table srm_resources
(
    id           bigserial primary key,
    name         varchar(128),
    resources    varchar(512)            not null,
    action       varchar(256),
    type         varchar(26)             not null,
    parent_id    bigint,
    create_at    timestamp default now() not null,
    update_at    timestamp,
    create_by_id bigint
        constraint role_user_id_create_by_id
            references "srm_user",
    update_by_id bigint
        constraint role_user_id_update_by_id
            references "srm_user"
);