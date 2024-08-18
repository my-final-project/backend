create table user_roles
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    constraint FK_role_user foreign key (role_id) references roles (id),
    constraint FK_user_role foreign key (user_id) references users (id)
);
