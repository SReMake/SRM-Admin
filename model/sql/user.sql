create table "user"
(
    id           bigserial primary key,
    username     varchar(64)                                      not null,
    password     varchar(256)                                     not null,
    phone        varchar(16),
    avatar       varchar(256),
    status       varchar(25) default 'CREATED'::character varying not null,
    create_at    timestamp   default now()                        not null,
    update_at    timestamp,
    create_by_id bigint
        constraint user_user_id_create_by_id
            references "user",
    update_by_id bigint
        constraint user_user_id_update_by_id
            references "user"
);

