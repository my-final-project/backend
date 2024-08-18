create table appointments
(
    id                 bigint auto_increment primary key,
    appointment_date   datetime(6)  not null,
    created_date       datetime(6)  null,
    last_modified_date datetime(6)  null,
    pet_id             bigint       not null,
    service_id         bigint       not null,
    status             varchar(255) not null,
    user_id            bigint       not null
);

create table consultations
(
    id                 bigint auto_increment primary key,
    consultation_date  datetime(6)  not null,
    created_date       datetime(6)  null,
    last_modified_date datetime(6)  null,
    notes              varchar(500) not null,
    pet_id             bigint       not null,
    veterinarian_id    bigint       not null
);

create table pets
(
    id                 bigint auto_increment primary key,
    breed              varchar(80) null,
    color              varchar(20) null,
    created_date       datetime(6) null,
    date_of_birth      date        null,
    last_modified_date datetime(6) null,
    name               varchar(80) not null,
    species            varchar(80) not null
);
