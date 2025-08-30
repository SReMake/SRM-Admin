create table srm_resources
(
    id           bigserial
        primary key,
    name         varchar(128),
    resources    varchar(512),
    action       varchar(256),
    type         varchar(26)             not null,
    parent_id    bigint
        constraint srm_resources_srm_resources_id_fk
            references srm_resources,
    create_at    timestamp default now() not null,
    update_at    timestamp,
    create_by_id bigint
        constraint role_user_id_create_by_id
            references srm_user,
    update_by_id bigint
        constraint role_user_id_update_by_id
            references srm_user,
    path         varchar(512)
);
create unique index srm_resources_type_path_resources_name_action_uindex
    on srm_resources (type, path, resources, name, action);

