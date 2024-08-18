create table roles
(
    id                 bigint auto_increment primary key,
    created_date       datetime(6)                                                 null,
    last_modified_date datetime(6)                                                 null,
    description        varchar(255)                                                not null,
    name               enum ('ADMIN', 'EMPLOYEE', 'OWNER', 'USER', 'VETERINARIAN') not null,
    constraint uk_name unique (name)
);

create table services
(
    id                 bigint auto_increment primary key,
    created_date       datetime(6)    null,
    description        varchar(500)   not null,
    last_modified_date datetime(6)    null,
    name               varchar(150)   not null,
    price              decimal(38, 2) not null
);

create table users
(
    id                 bigint auto_increment primary key,
    created_date       datetime(6)                              null,
    last_modified_date datetime(6)                              null,
    cpf                varchar(11)                              not null,
    email              varchar(150)                             not null,
    name               varchar(150)                             not null,
    password           varchar(200)                             not null,
    status             enum ('ACTIVE', 'INACTIVE', 'SUSPENDED') null,
    constraint uk_cpf unique (cpf),
    constraint uk_email unique (email)
);
