create table srm_role_resources
(
    id           bigserial primary key,
    resources_id bigint                  not null,
    role_id      bigint                  not null,
    create_at    timestamp default now() not null,
    update_at    timestamp,
    create_by_id bigint
        constraint role_user_id_create_by_id
            references "srm_role",
    update_by_id bigint
        constraint role_user_id_update_by_id
            references "srm_role"
);