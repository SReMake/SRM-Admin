create table srm_role_resources
(
    id           bigserial
        primary key,
    resources_id bigint                  not null
        constraint srm_role_resources_srm_resources_id_fk
            references srm_resources,
    role_id      bigint                  not null
        constraint srm_role_resources_srm_role_id_fk
            references srm_role,
    create_at    timestamp default now() not null,
    update_at    timestamp,
    create_by_id bigint
        constraint role_user_id_create_by_id
            references srm_role,
    update_by_id bigint
        constraint role_user_id_update_by_id
            references srm_role
);