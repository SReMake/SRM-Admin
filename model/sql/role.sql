create table srm_role
(
    id           bigserial primary key,
    name         varchar(64)             not null,
    create_at    timestamp default now() not null,
    update_at    timestamp,
    create_by_id bigint
        constraint role_user_id_create_by_id
            references "srm_role",
    update_by_id bigint
        constraint role_user_id_update_by_id
            references "srm_role"
);