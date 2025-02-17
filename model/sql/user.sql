-- auto-generated definition
create table "user"
(
    id        bigserial
        constraint id
            primary key,
    username  varchar(64)  not null,
    password  varchar(256) not null,
    phone     varchar(16),
    avatar    varchar(256),
    status    varchar(25)  not null default 'CREATED',
    create_at timestamp    not null default now(),
    update_at timestamp,
    create_by bigint,
    update_yy bigint
);

alter table "user"
    owner to postgres;

