create table classrooms
(
    id   bigint primary key auto_increment,
    name varchar(255) not null
);

create table subjects
(
    id    bigint primary key auto_increment,
    name  varchar(255) not null,
    hours int          not null
);

create table students
(
    id           bigint primary key auto_increment,
    name         varchar(255) not null,
    email        varchar(255) not null,
    phone        varchar(255) not null,
    address      varchar(255) not null,
    classroom_id bigint
);